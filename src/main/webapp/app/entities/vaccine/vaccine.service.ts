import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVaccine } from 'app/shared/model/vaccine.model';

type EntityResponseType = HttpResponse<IVaccine>;
type EntityArrayResponseType = HttpResponse<IVaccine[]>;

@Injectable({ providedIn: 'root' })
export class VaccineService {
  public resourceUrl = SERVER_API_URL + 'api/vaccines';

  constructor(protected http: HttpClient) {}

  create(vaccine: IVaccine): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vaccine);
    return this.http
      .post<IVaccine>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vaccine: IVaccine): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vaccine);
    return this.http
      .put<IVaccine>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVaccine>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVaccine[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vaccine: IVaccine): IVaccine {
    const copy: IVaccine = Object.assign({}, vaccine, {
      date: vaccine.date && vaccine.date.isValid() ? vaccine.date.toJSON() : undefined,
      validUtil: vaccine.validUtil && vaccine.validUtil.isValid() ? vaccine.validUtil.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date ? moment(res.body.date) : undefined;
      res.body.validUtil = res.body.validUtil ? moment(res.body.validUtil) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((vaccine: IVaccine) => {
        vaccine.date = vaccine.date ? moment(vaccine.date) : undefined;
        vaccine.validUtil = vaccine.validUtil ? moment(vaccine.validUtil) : undefined;
      });
    }
    return res;
  }
}
