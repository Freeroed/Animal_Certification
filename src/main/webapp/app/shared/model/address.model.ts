export interface IAddress {
  id?: number;
  region?: string;
  city?: string;
  street?: string;
  house?: string;
  apartment?: number;
}

export class Address implements IAddress {
  constructor(
    public id?: number,
    public region?: string,
    public city?: string,
    public street?: string,
    public house?: string,
    public apartment?: number
  ) {}
}
