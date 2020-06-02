import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPersonData } from 'app/shared/model/person-data.model';

type EntityResponseType = HttpResponse<IPersonData>;
type EntityArrayResponseType = HttpResponse<IPersonData[]>;

@Injectable({ providedIn: 'root' })
export class PersonDataService {
  public resourceUrl = SERVER_API_URL + 'api/person-data';

  constructor(protected http: HttpClient) {}

  create(personData: IPersonData): Observable<EntityResponseType> {
    return this.http.post<IPersonData>(this.resourceUrl, personData, { observe: 'response' });
  }

  update(personData: IPersonData): Observable<EntityResponseType> {
    return this.http.put<IPersonData>(this.resourceUrl, personData, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPersonData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPersonData[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
