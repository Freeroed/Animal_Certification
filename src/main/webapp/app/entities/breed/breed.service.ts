import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBreed } from 'app/shared/model/breed.model';

type EntityResponseType = HttpResponse<IBreed>;
type EntityArrayResponseType = HttpResponse<IBreed[]>;

@Injectable({ providedIn: 'root' })
export class BreedService {
  public resourceUrl = SERVER_API_URL + 'api/breeds';

  constructor(protected http: HttpClient) {}

  create(breed: IBreed): Observable<EntityResponseType> {
    return this.http.post<IBreed>(this.resourceUrl, breed, { observe: 'response' });
  }

  update(breed: IBreed): Observable<EntityResponseType> {
    return this.http.put<IBreed>(this.resourceUrl, breed, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBreed>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBreed[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
