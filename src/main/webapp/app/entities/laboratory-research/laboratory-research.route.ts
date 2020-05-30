import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILaboratoryResearch, LaboratoryResearch } from 'app/shared/model/laboratory-research.model';
import { LaboratoryResearchService } from './laboratory-research.service';
import { LaboratoryResearchComponent } from './laboratory-research.component';
import { LaboratoryResearchDetailComponent } from './laboratory-research-detail.component';
import { LaboratoryResearchUpdateComponent } from './laboratory-research-update.component';

@Injectable({ providedIn: 'root' })
export class LaboratoryResearchResolve implements Resolve<ILaboratoryResearch> {
  constructor(private service: LaboratoryResearchService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILaboratoryResearch> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((laboratoryResearch: HttpResponse<LaboratoryResearch>) => {
          if (laboratoryResearch.body) {
            return of(laboratoryResearch.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LaboratoryResearch());
  }
}

export const laboratoryResearchRoute: Routes = [
  {
    path: '',
    component: LaboratoryResearchComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCretificationApp.laboratoryResearch.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LaboratoryResearchDetailComponent,
    resolve: {
      laboratoryResearch: LaboratoryResearchResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCretificationApp.laboratoryResearch.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LaboratoryResearchUpdateComponent,
    resolve: {
      laboratoryResearch: LaboratoryResearchResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCretificationApp.laboratoryResearch.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LaboratoryResearchUpdateComponent,
    resolve: {
      laboratoryResearch: LaboratoryResearchResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCretificationApp.laboratoryResearch.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
