export interface IDocumentType {
  id?: number;
  name?: string;
  nameEng?: string;
}

export class DocumentType implements IDocumentType {
  constructor(public id?: number, public name?: string, public nameEng?: string) {}
}
