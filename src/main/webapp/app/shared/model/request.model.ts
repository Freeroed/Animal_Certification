import { Moment } from 'moment';
import { IDocument } from 'app/shared/model/document.model';
import { IUser } from 'app/core/user/user.model';
import { ICountry } from 'app/shared/model/country.model';
import { IBorderCrossingPoint } from 'app/shared/model/border-crossing-point.model';
import { IAnimal } from 'app/shared/model/animal.model';
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
  documents?: IDocument[];
  creater?: IUser;
  veterinarian?: IUser;
  rshInspector?: IUser;
  destinationCountry?: ICountry;
  borderCrossingPoint?: IBorderCrossingPoint;
  animals?: IAnimal[];
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
    public documents?: IDocument[],
    public creater?: IUser,
    public veterinarian?: IUser,
    public rshInspector?: IUser,
    public destinationCountry?: ICountry,
    public borderCrossingPoint?: IBorderCrossingPoint,
    public animals?: IAnimal[]
  ) {}
}
