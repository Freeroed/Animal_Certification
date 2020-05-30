import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { ICountry } from 'app/shared/model/country.model';
import { IDocument } from 'app/shared/model/document.model';
import { TransportType } from 'app/shared/model/enumerations/transport-type.model';
import { TransactionType } from 'app/shared/model/enumerations/transaction-type.model';
import { RequestStatus } from 'app/shared/model/enumerations/request-status.model';

export interface IRequest {
  id?: number;
  transportType?: TransportType;
  vehicleNumber?: string;
  transactionType?: TransactionType;
  storageWay?: string;
  postalCode?: string;
  departureAt?: Moment;
  createdAt?: Moment;
  lastModifiedAt?: Moment;
  status?: RequestStatus;
  creater?: IUser;
  veterinarian?: IUser;
  rshInspector?: IUser;
  destinationCountry?: ICountry;
  borderCrossingPoint?: ICountry;
  documents?: IDocument[];
}

export class Request implements IRequest {
  constructor(
    public id?: number,
    public transportType?: TransportType,
    public vehicleNumber?: string,
    public transactionType?: TransactionType,
    public storageWay?: string,
    public postalCode?: string,
    public departureAt?: Moment,
    public createdAt?: Moment,
    public lastModifiedAt?: Moment,
    public status?: RequestStatus,
    public creater?: IUser,
    public veterinarian?: IUser,
    public rshInspector?: IUser,
    public destinationCountry?: ICountry,
    public borderCrossingPoint?: ICountry,
    public documents?: IDocument[]
  ) {}
}
