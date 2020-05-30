import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAnimalType, AnimalType } from 'app/shared/model/animal-type.model';
import { AnimalTypeService } from './animal-type.service';

@Component({
  selector: 'jhi-animal-type-update',
  templateUrl: './animal-type-update.component.html',
})
export class AnimalTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    typeName: [null, [Validators.required]],
    typeNameEng: [null, [Validators.required]],
    scientificNameENG: [null, [Validators.required]],
  });

  constructor(protected animalTypeService: AnimalTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ animalType }) => {
      this.updateForm(animalType);
    });
  }

  updateForm(animalType: IAnimalType): void {
    this.editForm.patchValue({
      id: animalType.id,
      typeName: animalType.typeName,
      typeNameEng: animalType.typeNameEng,
      scientificNameENG: animalType.scientificNameENG,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const animalType = this.createFromForm();
    if (animalType.id !== undefined) {
      this.subscribeToSaveResponse(this.animalTypeService.update(animalType));
    } else {
      this.subscribeToSaveResponse(this.animalTypeService.create(animalType));
    }
  }

  private createFromForm(): IAnimalType {
    return {
      ...new AnimalType(),
      id: this.editForm.get(['id'])!.value,
      typeName: this.editForm.get(['typeName'])!.value,
      typeNameEng: this.editForm.get(['typeNameEng'])!.value,
      scientificNameENG: this.editForm.get(['scientificNameENG'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnimalType>>): void {
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
