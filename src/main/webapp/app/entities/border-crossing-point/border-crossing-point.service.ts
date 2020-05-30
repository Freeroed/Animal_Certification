import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBorderCrossingPoint } from 'app/shared/model/border-crossing-point.model';

type EntityResponseType = HttpResponse<IBorderCrossingPoint>;
type EntityArrayResponseType = HttpResponse<IBorderCrossingPoint[]>;

@Injectable({ providedIn: 'root' })
export class BorderCrossingPointService {
  public resourceUrl = SERVER_API_URL + 'api/border-crossing-points';

  constructor(protected http: HttpClient) {}

  create(borderCrossingPoint: IBorderCrossingPoint): Observable<EntityResponseType> {
    return this.http.post<IBorderCrossingPoint>(this.resourceUrl, borderCrossingPoint, { observe: 'response' });
  }

  update(borderCrossingPoint: IBorderCrossingPoint): Observable<EntityResponseType> {
    return this.http.put<IBorderCrossingPoint>(this.resourceUrl, borderCrossingPoint, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBorderCrossingPoint>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBorderCrossingPoint[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
