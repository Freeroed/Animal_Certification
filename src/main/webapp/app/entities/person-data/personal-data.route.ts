import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPersonalData, PersonalData } from 'app/shared/model/personal-data.model';
import { PersonalDataService } from './personal-data.service';
import { PersonalDataComponent } from './personal-data.component';
import { PersonalDataDetailComponent } from './personal-data-detail.component';
import { PersonalDataUpdateComponent } from './personal-data-update.component';

@Injectable({ providedIn: 'root' })
export class PersonalDataResolve implements Resolve<IPersonalData> {
  constructor(private service: PersonalDataService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPersonalData> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((personData: HttpResponse<PersonalData>) => {
          if (personData.body) {
            return of(personData.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PersonalData());
  }
}

export const personalDataRoute: Routes = [
  {
    path: '',
    component: PersonalDataComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.personData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PersonalDataDetailComponent,
    resolve: {
      personData: PersonalDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.personData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PersonalDataUpdateComponent,
    resolve: {
      personData: PersonalDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.personData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PersonalDataUpdateComponent,
    resolve: {
      personData: PersonalDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.personData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
