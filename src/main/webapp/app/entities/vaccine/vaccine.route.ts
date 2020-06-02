import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IVaccine, Vaccine } from 'app/shared/model/vaccine.model';
import { VaccineService } from './vaccine.service';
import { VaccineComponent } from './vaccine.component';
import { VaccineDetailComponent } from './vaccine-detail.component';
import { VaccineUpdateComponent } from './vaccine-update.component';

@Injectable({ providedIn: 'root' })
export class VaccineResolve implements Resolve<IVaccine> {
  constructor(private service: VaccineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVaccine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((vaccine: HttpResponse<Vaccine>) => {
          if (vaccine.body) {
            return of(vaccine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Vaccine());
  }
}

export const vaccineRoute: Routes = [
  {
    path: '',
    component: VaccineComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.vaccine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VaccineDetailComponent,
    resolve: {
      vaccine: VaccineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.vaccine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VaccineUpdateComponent,
    resolve: {
      vaccine: VaccineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.vaccine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VaccineUpdateComponent,
    resolve: {
      vaccine: VaccineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.vaccine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
