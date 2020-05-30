export interface IAnimalType {
  id?: number;
  typeName?: string;
  typeNameEng?: string;
  scientificNameENG?: string;
}

export class AnimalType implements IAnimalType {
  constructor(public id?: number, public typeName?: string, public typeNameEng?: string, public scientificNameENG?: string) {}
}
