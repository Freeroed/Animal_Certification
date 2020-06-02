import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPersonData, PersonData } from 'app/shared/model/person-data.model';
import { PersonDataService } from './person-data.service';
import { PersonDataComponent } from './person-data.component';
import { PersonDataDetailComponent } from './person-data-detail.component';
import { PersonDataUpdateComponent } from './person-data-update.component';

@Injectable({ providedIn: 'root' })
export class PersonDataResolve implements Resolve<IPersonData> {
  constructor(private service: PersonDataService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPersonData> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((personData: HttpResponse<PersonData>) => {
          if (personData.body) {
            return of(personData.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PersonData());
  }
}

export const personDataRoute: Routes = [
  {
    path: '',
    component: PersonDataComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.personData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PersonDataDetailComponent,
    resolve: {
      personData: PersonDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.personData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PersonDataUpdateComponent,
    resolve: {
      personData: PersonDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.personData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PersonDataUpdateComponent,
    resolve: {
      personData: PersonDataResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.personData.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
