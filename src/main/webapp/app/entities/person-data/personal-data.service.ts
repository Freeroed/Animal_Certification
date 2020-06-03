import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPersonalData } from 'app/shared/model/personal-data.model';

type EntityResponseType = HttpResponse<IPersonalData>;
type EntityArrayResponseType = HttpResponse<IPersonalData[]>;

@Injectable({ providedIn: 'root' })
export class PersonalDataService {
  public resourceUrl = SERVER_API_URL + 'api/person-data';

  constructor(protected http: HttpClient) {}

  create(personData: IPersonalData): Observable<EntityResponseType> {
    return this.http.post<IPersonalData>(this.resourceUrl, personData, { observe: 'response' });
  }

  update(personData: IPersonalData): Observable<EntityResponseType> {
    return this.http.put<IPersonalData>(this.resourceUrl, personData, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPersonalData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPersonalData[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
