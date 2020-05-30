import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBreed, Breed } from 'app/shared/model/breed.model';
import { BreedService } from './breed.service';
import { IAnimalType } from 'app/shared/model/animal-type.model';
import { AnimalTypeService } from 'app/entities/animal-type/animal-type.service';

@Component({
  selector: 'jhi-breed-update',
  templateUrl: './breed-update.component.html',
})
export class BreedUpdateComponent implements OnInit {
  isSaving = false;
  animaltypes: IAnimalType[] = [];

  editForm = this.fb.group({
    id: [],
    breedName: [null, [Validators.required]],
    breedNameEng: [null, [Validators.required]],
    type: [],
  });

  constructor(
    protected breedService: BreedService,
    protected animalTypeService: AnimalTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ breed }) => {
      this.updateForm(breed);

      this.animalTypeService.query().subscribe((res: HttpResponse<IAnimalType[]>) => (this.animaltypes = res.body || []));
    });
  }

  updateForm(breed: IBreed): void {
    this.editForm.patchValue({
      id: breed.id,
      breedName: breed.breedName,
      breedNameEng: breed.breedNameEng,
      type: breed.type,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const breed = this.createFromForm();
    if (breed.id !== undefined) {
      this.subscribeToSaveResponse(this.breedService.update(breed));
    } else {
      this.subscribeToSaveResponse(this.breedService.create(breed));
    }
  }

  private createFromForm(): IBreed {
    return {
      ...new Breed(),
      id: this.editForm.get(['id'])!.value,
      breedName: this.editForm.get(['breedName'])!.value,
      breedNameEng: this.editForm.get(['breedNameEng'])!.value,
      type: this.editForm.get(['type'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBreed>>): void {
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

  trackById(index: number, item: IAnimalType): any {
    return item.id;
  }
}
