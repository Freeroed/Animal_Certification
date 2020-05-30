import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBorderCrossingPoint, BorderCrossingPoint } from 'app/shared/model/border-crossing-point.model';
import { BorderCrossingPointService } from './border-crossing-point.service';
import { BorderCrossingPointComponent } from './border-crossing-point.component';
import { BorderCrossingPointDetailComponent } from './border-crossing-point-detail.component';
import { BorderCrossingPointUpdateComponent } from './border-crossing-point-update.component';

@Injectable({ providedIn: 'root' })
export class BorderCrossingPointResolve implements Resolve<IBorderCrossingPoint> {
  constructor(private service: BorderCrossingPointService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBorderCrossingPoint> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((borderCrossingPoint: HttpResponse<BorderCrossingPoint>) => {
          if (borderCrossingPoint.body) {
            return of(borderCrossingPoint.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BorderCrossingPoint());
  }
}

export const borderCrossingPointRoute: Routes = [
  {
    path: '',
    component: BorderCrossingPointComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCretificationApp.borderCrossingPoint.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BorderCrossingPointDetailComponent,
    resolve: {
      borderCrossingPoint: BorderCrossingPointResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCretificationApp.borderCrossingPoint.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BorderCrossingPointUpdateComponent,
    resolve: {
      borderCrossingPoint: BorderCrossingPointResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCretificationApp.borderCrossingPoint.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BorderCrossingPointUpdateComponent,
    resolve: {
      borderCrossingPoint: BorderCrossingPointResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'animalCretificationApp.borderCrossingPoint.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
