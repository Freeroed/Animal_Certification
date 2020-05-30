import { IAnimalType } from 'app/shared/model/animal-type.model';

export interface IBreed {
  id?: number;
  breedName?: string;
  breedNameEng?: string;
  type?: IAnimalType;
}

export class Breed implements IBreed {
  constructor(public id?: number, public breedName?: string, public breedNameEng?: string, public type?: IAnimalType) {}
}
