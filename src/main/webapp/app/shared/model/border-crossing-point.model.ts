import { ICountry } from 'app/shared/model/country.model';

export interface IBorderCrossingPoint {
  id?: number;
  location?: string;
  adjacentPoint?: string;
  classification?: string;
  schedule?: string;
  scheduleOfOfficals?: string;
  firstCountry?: ICountry;
  secondCountry?: ICountry;
}

export class BorderCrossingPoint implements IBorderCrossingPoint {
  constructor(
    public id?: number,
    public location?: string,
    public adjacentPoint?: string,
    public classification?: string,
    public schedule?: string,
    public scheduleOfOfficals?: string,
    public firstCountry?: ICountry,
    public secondCountry?: ICountry
  ) {}
}
