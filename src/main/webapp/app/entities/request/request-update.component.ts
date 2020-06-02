import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRequest, Request } from 'app/shared/model/request.model';
import { RequestService } from './request.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';
import { IBorderCrossingPoint } from 'app/shared/model/border-crossing-point.model';
import { BorderCrossingPointService } from 'app/entities/border-crossing-point/border-crossing-point.service';
import { IAnimal } from 'app/shared/model/animal.model';
import { AnimalService } from 'app/entities/animal/animal.service';

type SelectableEntity = IUser | ICountry | IBorderCrossingPoint | IAnimal;

@Component({
  selector: 'jhi-request-update',
  templateUrl: './request-update.component.html',
})
export class RequestUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  countries: ICountry[] = [];
  bordercrossingpoints: IBorderCrossingPoint[] = [];
  animals: IAnimal[] = [];

  editForm = this.fb.group({
    id: [],
    transportType: [],
    vehicleNumber: [],
    transactionType: [],
    storageWay: [],
    postalCode: [],
    departureAt: [],
    createdAt: [null, [Validators.required]],
    lastModifiedAt: [],
    status: [null, [Validators.required]],
    creater: [],
    veterinarian: [],
    rshInspector: [],
    destinationCountry: [],
    borderCrossingPoint: [],
    animals: [],
  });

  constructor(
    protected requestService: RequestService,
    protected userService: UserService,
    protected countryService: CountryService,
    protected borderCrossingPointService: BorderCrossingPointService,
    protected animalService: AnimalService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ request }) => {
      if (!request.id) {
        const today = moment().startOf('day');
        request.departureAt = today;
        request.createdAt = today;
        request.lastModifiedAt = today;
      }

      this.updateForm(request);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.countryService.query().subscribe((res: HttpResponse<ICountry[]>) => (this.countries = res.body || []));

      this.borderCrossingPointService
        .query()
        .subscribe((res: HttpResponse<IBorderCrossingPoint[]>) => (this.bordercrossingpoints = res.body || []));

      this.animalService.query().subscribe((res: HttpResponse<IAnimal[]>) => (this.animals = res.body || []));
    });
  }

  updateForm(request: IRequest): void {
    this.editForm.patchValue({
      id: request.id,
      transportType: request.transportType,
      vehicleNumber: request.vehicleNumber,
      transactionType: request.transactionType,
      storageWay: request.storageWay,
      postalCode: request.postalCode,
      departureAt: request.departureAt ? request.departureAt.format(DATE_TIME_FORMAT) : null,
      createdAt: request.createdAt ? request.createdAt.format(DATE_TIME_FORMAT) : null,
      lastModifiedAt: request.lastModifiedAt ? request.lastModifiedAt.format(DATE_TIME_FORMAT) : null,
      status: request.status,
      creater: request.creater,
      veterinarian: request.veterinarian,
      rshInspector: request.rshInspector,
      destinationCountry: request.destinationCountry,
      borderCrossingPoint: request.borderCrossingPoint,
      animals: request.animals,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const request = this.createFromForm();
    if (request.id !== undefined) {
      this.subscribeToSaveResponse(this.requestService.update(request));
    } else {
      this.subscribeToSaveResponse(this.requestService.create(request));
    }
  }

  private createFromForm(): IRequest {
    return {
      ...new Request(),
      id: this.editForm.get(['id'])!.value,
      transportType: this.editForm.get(['transportType'])!.value,
      vehicleNumber: this.editForm.get(['vehicleNumber'])!.value,
      transactionType: this.editForm.get(['transactionType'])!.value,
      storageWay: this.editForm.get(['storageWay'])!.value,
      postalCode: this.editForm.get(['postalCode'])!.value,
      departureAt: this.editForm.get(['departureAt'])!.value
        ? moment(this.editForm.get(['departureAt'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      lastModifiedAt: this.editForm.get(['lastModifiedAt'])!.value
        ? moment(this.editForm.get(['lastModifiedAt'])!.value, DATE_TIME_FORMAT)
        : undefined,
      status: this.editForm.get(['status'])!.value,
      creater: this.editForm.get(['creater'])!.value,
      veterinarian: this.editForm.get(['veterinarian'])!.value,
      rshInspector: this.editForm.get(['rshInspector'])!.value,
      destinationCountry: this.editForm.get(['destinationCountry'])!.value,
      borderCrossingPoint: this.editForm.get(['borderCrossingPoint'])!.value,
      animals: this.editForm.get(['animals'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequest>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IAnimal[], option: IAnimal): IAnimal {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
