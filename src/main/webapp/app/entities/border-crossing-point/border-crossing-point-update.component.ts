import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBorderCrossingPoint, BorderCrossingPoint } from 'app/shared/model/border-crossing-point.model';
import { BorderCrossingPointService } from './border-crossing-point.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';

@Component({
  selector: 'jhi-border-crossing-point-update',
  templateUrl: './border-crossing-point-update.component.html',
})
export class BorderCrossingPointUpdateComponent implements OnInit {
  isSaving = false;
  countries: ICountry[] = [];

  editForm = this.fb.group({
    id: [],
    location: [null, [Validators.required]],
    adjacentPoint: [null, [Validators.required]],
    classification: [null, [Validators.required]],
    schedule: [null, [Validators.required]],
    scheduleOfOfficals: [null, [Validators.required]],
    coordinates: [null, [Validators.required]],
    firstCountry: [],
    secondCountry: [],
  });

  constructor(
    protected borderCrossingPointService: BorderCrossingPointService,
    protected countryService: CountryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ borderCrossingPoint }) => {
      this.updateForm(borderCrossingPoint);

      this.countryService.query().subscribe((res: HttpResponse<ICountry[]>) => (this.countries = res.body || []));
    });
  }

  updateForm(borderCrossingPoint: IBorderCrossingPoint): void {
    this.editForm.patchValue({
      id: borderCrossingPoint.id,
      location: borderCrossingPoint.location,
      adjacentPoint: borderCrossingPoint.adjacentPoint,
      classification: borderCrossingPoint.classification,
      schedule: borderCrossingPoint.schedule,
      scheduleOfOfficals: borderCrossingPoint.scheduleOfOfficals,
      coordinates: borderCrossingPoint.coordinates,
      firstCountry: borderCrossingPoint.firstCountry,
      secondCountry: borderCrossingPoint.secondCountry,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const borderCrossingPoint = this.createFromForm();
    if (borderCrossingPoint.id !== undefined) {
      this.subscribeToSaveResponse(this.borderCrossingPointService.update(borderCrossingPoint));
    } else {
      this.subscribeToSaveResponse(this.borderCrossingPointService.create(borderCrossingPoint));
    }
  }

  private createFromForm(): IBorderCrossingPoint {
    return {
      ...new BorderCrossingPoint(),
      id: this.editForm.get(['id'])!.value,
      location: this.editForm.get(['location'])!.value,
      adjacentPoint: this.editForm.get(['adjacentPoint'])!.value,
      classification: this.editForm.get(['classification'])!.value,
      schedule: this.editForm.get(['schedule'])!.value,
      scheduleOfOfficals: this.editForm.get(['scheduleOfOfficals'])!.value,
      coordinates: this.editForm.get(['coordinates'])!.value,
      firstCountry: this.editForm.get(['firstCountry'])!.value,
      secondCountry: this.editForm.get(['secondCountry'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBorderCrossingPoint>>): void {
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

  trackById(index: number, item: ICountry): any {
    return item.id;
  }
}
