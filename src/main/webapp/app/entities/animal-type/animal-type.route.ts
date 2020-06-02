import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAnimalType, AnimalType } from 'app/shared/model/animal-type.model';
import { AnimalTypeService } from './animal-type.service';
import { AnimalTypeComponent } from './animal-type.component';
import { AnimalTypeDetailComponent } from './animal-type-detail.component';
import { AnimalTypeUpdateComponent } from './animal-type-update.component';

@Injectable({ providedIn: 'root' })
export class AnimalTypeResolve implements Resolve<IAnimalType> {
  constructor(private service: AnimalTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAnimalType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((animalType: HttpResponse<AnimalType>) => {
          if (animalType.body) {
            return of(animalType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AnimalType());
  }
}

export const animalTypeRoute: Routes = [
  {
    path: '',
    component: AnimalTypeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.animalType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AnimalTypeDetailComponent,
    resolve: {
      animalType: AnimalTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.animalType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AnimalTypeUpdateComponent,
    resolve: {
      animalType: AnimalTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.animalType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AnimalTypeUpdateComponent,
    resolve: {
      animalType: AnimalTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCertificationApp.animalType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
