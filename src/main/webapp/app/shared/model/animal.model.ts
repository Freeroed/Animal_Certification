import { Moment } from 'moment';
import { IBreed } from 'app/shared/model/breed.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { AnimalStatus } from 'app/shared/model/enumerations/animal-status.model';

export interface IAnimal {
  id?: number;
  nickname?: string;
  birthdate?: Moment;
  gender?: Gender;
  chip?: string;
  birthPlace?: string;
  tnvedCode?: string;
  color?: string;
  colorEng?: string;
  status?: AnimalStatus;
  breed?: IBreed;
}

export class Animal implements IAnimal {
  constructor(
    public id?: number,
    public nickname?: string,
    public birthdate?: Moment,
    public gender?: Gender,
    public chip?: string,
    public birthPlace?: string,
    public tnvedCode?: string,
    public color?: string,
    public colorEng?: string,
    public status?: AnimalStatus,
    public breed?: IBreed
  ) {}
}
