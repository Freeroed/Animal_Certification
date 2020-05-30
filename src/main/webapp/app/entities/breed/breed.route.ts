import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBreed, Breed } from 'app/shared/model/breed.model';
import { BreedService } from './breed.service';
import { BreedComponent } from './breed.component';
import { BreedDetailComponent } from './breed-detail.component';
import { BreedUpdateComponent } from './breed-update.component';

@Injectable({ providedIn: 'root' })
export class BreedResolve implements Resolve<IBreed> {
  constructor(private service: BreedService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBreed> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((breed: HttpResponse<Breed>) => {
          if (breed.body) {
            return of(breed.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Breed());
  }
}

export const breedRoute: Routes = [
  {
    path: '',
    component: BreedComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCretificationApp.breed.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BreedDetailComponent,
    resolve: {
      breed: BreedResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCretificationApp.breed.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BreedUpdateComponent,
    resolve: {
      breed: BreedResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCretificationApp.breed.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BreedUpdateComponent,
    resolve: {
      breed: BreedResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCretificationApp.breed.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
