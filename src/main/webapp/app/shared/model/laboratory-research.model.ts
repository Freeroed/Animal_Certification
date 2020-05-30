import { IAnimal } from 'app/shared/model/animal.model';
import { LaboratoryTestResult } from 'app/shared/model/enumerations/laboratory-test-result.model';

export interface ILaboratoryResearch {
  id?: number;
  laboratoty?: string;
  indicator?: string;
  resultReceiptDate?: string;
  resurchMethod?: string;
  examinationNumber?: string;
  result?: LaboratoryTestResult;
  animal?: IAnimal;
}

export class LaboratoryResearch implements ILaboratoryResearch {
  constructor(
    public id?: number,
    public laboratoty?: string,
    public indicator?: string,
    public resultReceiptDate?: string,
    public resurchMethod?: string,
    public examinationNumber?: string,
    public result?: LaboratoryTestResult,
    public animal?: IAnimal
  ) {}
}
