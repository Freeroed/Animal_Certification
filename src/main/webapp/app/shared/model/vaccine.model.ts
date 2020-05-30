import { Moment } from 'moment';
import { IAnimal } from 'app/shared/model/animal.model';
import { VaccineType } from 'app/shared/model/enumerations/vaccine-type.model';

export interface IVaccine {
  id?: number;
  title?: string;
  date?: Moment;
  batchNumber?: string;
  nameAndManufacturer?: string;
  validUtil?: Moment;
  type?: VaccineType;
  animal?: IAnimal;
}

export class Vaccine implements IVaccine {
  constructor(
    public id?: number,
    public title?: string,
    public date?: Moment,
    public batchNumber?: string,
    public nameAndManufacturer?: string,
    public validUtil?: Moment,
    public type?: VaccineType,
    public animal?: IAnimal
  ) {}
}
