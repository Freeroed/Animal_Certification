import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRequest } from 'app/shared/model/request.model';

type EntityResponseType = HttpResponse<IRequest>;
type EntityArrayResponseType = HttpResponse<IRequest[]>;

@Injectable({ providedIn: 'root' })
export class RequestService {
  public resourceUrl = SERVER_API_URL + 'api/requests';

  constructor(protected http: HttpClient) {}

  create(request: IRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(request);
    return this.http
      .post<IRequest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(request: IRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(request);
    return this.http
      .put<IRequest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRequest>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRequest[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(request: IRequest): IRequest {
    const copy: IRequest = Object.assign({}, request, {
      departureAt: request.departureAt && request.departureAt.isValid() ? request.departureAt.toJSON() : undefined,
      createdAt: request.createdAt && request.createdAt.isValid() ? request.createdAt.toJSON() : undefined,
      lastModifiedAt: request.lastModifiedAt && request.lastModifiedAt.isValid() ? request.lastModifiedAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.departureAt = res.body.departureAt ? moment(res.body.departureAt) : undefined;
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
      res.body.lastModifiedAt = res.body.lastModifiedAt ? moment(res.body.lastModifiedAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((request: IRequest) => {
        request.departureAt = request.departureAt ? moment(request.departureAt) : undefined;
        request.createdAt = request.createdAt ? moment(request.createdAt) : undefined;
        request.lastModifiedAt = request.lastModifiedAt ? moment(request.lastModifiedAt) : undefined;
      });
    }
    return res;
  }
}
