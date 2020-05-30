import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAnimal, Animal } from 'app/shared/model/animal.model';
import { AnimalService } from './animal.service';
import { IBreed } from 'app/shared/model/breed.model';
import { BreedService } from 'app/entities/breed/breed.service';

@Component({
  selector: 'jhi-animal-update',
  templateUrl: './animal-update.component.html',
})
export class AnimalUpdateComponent implements OnInit {
  isSaving = false;
  breeds: IBreed[] = [];

  editForm = this.fb.group({
    id: [],
    nickname: [null, [Validators.required]],
    birthdate: [null, [Validators.required]],
    gender: [null, [Validators.required]],
    chip: [],
    birthPlace: [],
    tnvedCode: [],
    color: [null, [Validators.required]],
    colorEng: [],
    status: [null, [Validators.required]],
    breed: [],
  });

  constructor(
    protected animalService: AnimalService,
    protected breedService: BreedService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ animal }) => {
      if (!animal.id) {
        const today = moment().startOf('day');
        animal.birthdate = today;
      }

      this.updateForm(animal);

      this.breedService.query().subscribe((res: HttpResponse<IBreed[]>) => (this.breeds = res.body || []));
    });
  }

  updateForm(animal: IAnimal): void {
    this.editForm.patchValue({
      id: animal.id,
      nickname: animal.nickname,
      birthdate: animal.birthdate ? animal.birthdate.format(DATE_TIME_FORMAT) : null,
      gender: animal.gender,
      chip: animal.chip,
      birthPlace: animal.birthPlace,
      tnvedCode: animal.tnvedCode,
      color: animal.color,
      colorEng: animal.colorEng,
      status: animal.status,
      breed: animal.breed,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const animal = this.createFromForm();
    if (animal.id !== undefined) {
      this.subscribeToSaveResponse(this.animalService.update(animal));
    } else {
      this.subscribeToSaveResponse(this.animalService.create(animal));
    }
  }

  private createFromForm(): IAnimal {
    return {
      ...new Animal(),
      id: this.editForm.get(['id'])!.value,
      nickname: this.editForm.get(['nickname'])!.value,
      birthdate: this.editForm.get(['birthdate'])!.value ? moment(this.editForm.get(['birthdate'])!.value, DATE_TIME_FORMAT) : undefined,
      gender: this.editForm.get(['gender'])!.value,
      chip: this.editForm.get(['chip'])!.value,
      birthPlace: this.editForm.get(['birthPlace'])!.value,
      tnvedCode: this.editForm.get(['tnvedCode'])!.value,
      color: this.editForm.get(['color'])!.value,
      colorEng: this.editForm.get(['colorEng'])!.value,
      status: this.editForm.get(['status'])!.value,
      breed: this.editForm.get(['breed'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnimal>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IBreed): any {
    return item.id;
  }
}
