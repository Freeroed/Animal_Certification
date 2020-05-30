import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILaboratoryResearch, LaboratoryResearch } from 'app/shared/model/laboratory-research.model';
import { LaboratoryResearchService } from './laboratory-research.service';
import { IAnimal } from 'app/shared/model/animal.model';
import { AnimalService } from 'app/entities/animal/animal.service';

@Component({
  selector: 'jhi-laboratory-research-update',
  templateUrl: './laboratory-research-update.component.html',
})
export class LaboratoryResearchUpdateComponent implements OnInit {
  isSaving = false;
  animals: IAnimal[] = [];

  editForm = this.fb.group({
    id: [],
    laboratoty: [null, [Validators.required]],
    indicator: [null, [Validators.required]],
    resultReceiptDate: [null, [Validators.required]],
    resurchMethod: [null, [Validators.required]],
    examinationNumber: [null, [Validators.required]],
    result: [null, [Validators.required]],
    animal: [],
  });

  constructor(
    protected laboratoryResearchService: LaboratoryResearchService,
    protected animalService: AnimalService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ laboratoryResearch }) => {
      this.updateForm(laboratoryResearch);

      this.animalService.query().subscribe((res: HttpResponse<IAnimal[]>) => (this.animals = res.body || []));
    });
  }

  updateForm(laboratoryResearch: ILaboratoryResearch): void {
    this.editForm.patchValue({
      id: laboratoryResearch.id,
      laboratoty: laboratoryResearch.laboratoty,
      indicator: laboratoryResearch.indicator,
      resultReceiptDate: laboratoryResearch.resultReceiptDate,
      resurchMethod: laboratoryResearch.resurchMethod,
      examinationNumber: laboratoryResearch.examinationNumber,
      result: laboratoryResearch.result,
      animal: laboratoryResearch.animal,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const laboratoryResearch = this.createFromForm();
    if (laboratoryResearch.id !== undefined) {
      this.subscribeToSaveResponse(this.laboratoryResearchService.update(laboratoryResearch));
    } else {
      this.subscribeToSaveResponse(this.laboratoryResearchService.create(laboratoryResearch));
    }
  }

  private createFromForm(): ILaboratoryResearch {
    return {
      ...new LaboratoryResearch(),
      id: this.editForm.get(['id'])!.value,
      laboratoty: this.editForm.get(['laboratoty'])!.value,
      indicator: this.editForm.get(['indicator'])!.value,
      resultReceiptDate: this.editForm.get(['resultReceiptDate'])!.value,
      resurchMethod: this.editForm.get(['resurchMethod'])!.value,
      examinationNumber: this.editForm.get(['examinationNumber'])!.value,
      result: this.editForm.get(['result'])!.value,
      animal: this.editForm.get(['animal'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILaboratoryResearch>>): void {
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
