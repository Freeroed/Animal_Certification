import { Moment } from 'moment';
import { IDocumentType } from 'app/shared/model/document-type.model';
import { IRequest } from 'app/shared/model/request.model';

export interface IDocument {
  id?: number;
  issuedAt?: Moment;
  link?: string;
  documentNumber?: string;
  type?: IDocumentType;
  request?: IRequest;
}

export class Document implements IDocument {
  constructor(
    public id?: number,
    public issuedAt?: Moment,
    public link?: string,
    public documentNumber?: string,
    public type?: IDocumentType,
    public request?: IRequest
  ) {}
}
