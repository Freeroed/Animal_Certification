import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPersonalData, PersonalData } from 'app/shared/model/personal-data.model';
import { PersonalDataService } from './personal-data.service';

@Component({
  selector: 'jhi-personal-data-update',
  templateUrl: './personal-data-update.component.html',
})
export class PersonalDataUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    surname: [null, [Validators.required]],
    nameEng: [],
    surnameEng: [],
    patronymic: [],
    phone: [],
    inn: [],
  });

  constructor(protected personalDataService: PersonalDataService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personData }) => {
      this.updateForm(personData);
    });
  }

  updateForm(personData: IPersonalData): void {
    this.editForm.patchValue({
      id: personData.id,
      name: personData.name,
      surname: personData.surname,
      nameEng: personData.nameEng,
      surnameEng: personData.surnameEng,
      patronymic: personData.patronymic,
      phone: personData.phone,
      inn: personData.inn,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const personData = this.createFromForm();
    if (personData.id !== undefined) {
      this.subscribeToSaveResponse(this.personalDataService.update(personData));
    } else {
      this.subscribeToSaveResponse(this.personalDataService.create(personData));
    }
  }

  private createFromForm(): IPersonalData {
    return {
      ...new PersonalData(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      surname: this.editForm.get(['surname'])!.value,
      nameEng: this.editForm.get(['nameEng'])!.value,
      surnameEng: this.editForm.get(['surnameEng'])!.value,
      patronymic: this.editForm.get(['patronymic'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      inn: this.editForm.get(['inn'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonalData>>): void {
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
}
