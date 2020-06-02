import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPersonData, PersonData } from 'app/shared/model/person-data.model';
import { PersonDataService } from './person-data.service';

@Component({
  selector: 'jhi-person-data-update',
  templateUrl: './person-data-update.component.html',
})
export class PersonDataUpdateComponent implements OnInit {
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

  constructor(protected personDataService: PersonDataService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personData }) => {
      this.updateForm(personData);
    });
  }

  updateForm(personData: IPersonData): void {
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
      this.subscribeToSaveResponse(this.personDataService.update(personData));
    } else {
      this.subscribeToSaveResponse(this.personDataService.create(personData));
    }
  }

  private createFromForm(): IPersonData {
    return {
      ...new PersonData(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonData>>): void {
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
