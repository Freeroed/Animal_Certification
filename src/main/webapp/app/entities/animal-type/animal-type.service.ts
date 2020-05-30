import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAnimalType } from 'app/shared/model/animal-type.model';

type EntityResponseType = HttpResponse<IAnimalType>;
type EntityArrayResponseType = HttpResponse<IAnimalType[]>;

@Injectable({ providedIn: 'root' })
export class AnimalTypeService {
  public resourceUrl = SERVER_API_URL + 'api/animal-types';

  constructor(protected http: HttpClient) {}

  create(animalType: IAnimalType): Observable<EntityResponseType> {
    return this.http.post<IAnimalType>(this.resourceUrl, animalType, { observe: 'response' });
  }

  update(animalType: IAnimalType): Observable<EntityResponseType> {
    return this.http.put<IAnimalType>(this.resourceUrl, animalType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAnimalType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAnimalType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
