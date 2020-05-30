import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IVaccine, Vaccine } from 'app/shared/model/vaccine.model';
import { VaccineService } from './vaccine.service';
import { IAnimal } from 'app/shared/model/animal.model';
import { AnimalService } from 'app/entities/animal/animal.service';

@Component({
  selector: 'jhi-vaccine-update',
  templateUrl: './vaccine-update.component.html',
})
export class VaccineUpdateComponent implements OnInit {
  isSaving = false;
  animals: IAnimal[] = [];

  editForm = this.fb.group({
    id: [],
    title: [null, [Validators.required]],
    date: [null, [Validators.required]],
    batchNumber: [null, [Validators.required]],
    nameAndManufacturer: [null, [Validators.required]],
    validUtil: [null, [Validators.required]],
    type: [null, [Validators.required]],
    animal: [],
  });

  constructor(
    protected vaccineService: VaccineService,
    protected animalService: AnimalService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vaccine }) => {
      if (!vaccine.id) {
        const today = moment().startOf('day');
        vaccine.date = today;
        vaccine.validUtil = today;
      }

      this.updateForm(vaccine);

      this.animalService.query().subscribe((res: HttpResponse<IAnimal[]>) => (this.animals = res.body || []));
    });
  }

  updateForm(vaccine: IVaccine): void {
    this.editForm.patchValue({
      id: vaccine.id,
      title: vaccine.title,
      date: vaccine.date ? vaccine.date.format(DATE_TIME_FORMAT) : null,
      batchNumber: vaccine.batchNumber,
      nameAndManufacturer: vaccine.nameAndManufacturer,
      validUtil: vaccine.validUtil ? vaccine.validUtil.format(DATE_TIME_FORMAT) : null,
      type: vaccine.type,
      animal: vaccine.animal,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vaccine = this.createFromForm();
    if (vaccine.id !== undefined) {
      this.subscribeToSaveResponse(this.vaccineService.update(vaccine));
    } else {
      this.subscribeToSaveResponse(this.vaccineService.create(vaccine));
    }
  }

  private createFromForm(): IVaccine {
    return {
      ...new Vaccine(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      date: this.editForm.get(['date'])!.value ? moment(this.editForm.get(['date'])!.value, DATE_TIME_FORMAT) : undefined,
      batchNumber: this.editForm.get(['batchNumber'])!.value,
      nameAndManufacturer: this.editForm.get(['nameAndManufacturer'])!.value,
      validUtil: this.editForm.get(['validUtil'])!.value ? moment(this.editForm.get(['validUtil'])!.value, DATE_TIME_FORMAT) : undefined,
      type: this.editForm.get(['type'])!.value,
      animal: this.editForm.get(['animal'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVaccine>>): void {
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

  trackById(index: number, item: IAnimal): any {
    return item.id;
  }
}
