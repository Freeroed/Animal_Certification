export interface IPersonData {
  id?: number;
  name?: string;
  surname?: string;
  nameEng?: string;
  surnameEng?: string;
  patronymic?: string;
  phone?: string;
  address?: string;
  inn?: string;
}

export class PersonData implements IPersonData {
  constructor(
    public id?: number,
    public name?: string,
    public surname?: string,
    public nameEng?: string,
    public surnameEng?: string,
    public patronymic?: string,
    public phone?: string,
    public address?: string,
    public inn?: string
  ) {}
}
