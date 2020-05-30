import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILaboratoryResearch } from 'app/shared/model/laboratory-research.model';

type EntityResponseType = HttpResponse<ILaboratoryResearch>;
type EntityArrayResponseType = HttpResponse<ILaboratoryResearch[]>;

@Injectable({ providedIn: 'root' })
export class LaboratoryResearchService {
  public resourceUrl = SERVER_API_URL + 'api/laboratory-researches';

  constructor(protected http: HttpClient) {}

  create(laboratoryResearch: ILaboratoryResearch): Observable<EntityResponseType> {
    return this.http.post<ILaboratoryResearch>(this.resourceUrl, laboratoryResearch, { observe: 'response' });
  }

  update(laboratoryResearch: ILaboratoryResearch): Observable<EntityResponseType> {
    return this.http.put<ILaboratoryResearch>(this.resourceUrl, laboratoryResearch, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILaboratoryResearch>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILaboratoryResearch[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
