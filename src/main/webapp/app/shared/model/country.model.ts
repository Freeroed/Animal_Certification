import { IRegion } from 'app/shared/model/region.model';

export interface ICountry {
  id?: number;
  name?: string;
  nameEng?: string;
  isoCode?: string;
  region?: IRegion;
}

export class Country implements ICountry {
  constructor(public id?: number, public name?: string, public nameEng?: string, public isoCode?: string, public region?: IRegion) {}
}
