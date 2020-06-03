export interface IPersonalData {
  id?: number;
  name?: string;
  surname?: string;
  nameEng?: string;
  surnameEng?: string;
  patronymic?: string;
  phone?: string;
  inn?: string;
}

export class PersonalData implements IPersonalData {
  constructor(
    public id?: number,
    public name?: string,
    public surname?: string,
    public nameEng?: string,
    public surnameEng?: string,
    public patronymic?: string,
    public phone?: string,
    public inn?: string
  ) {}
}
