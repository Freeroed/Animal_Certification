import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { VaccineService } from 'app/entities/vaccine/vaccine.service';
import { IVaccine, Vaccine } from 'app/shared/model/vaccine.model';
import { VaccineType } from 'app/shared/model/enumerations/vaccine-type.model';

describe('Service Tests', () => {
  describe('Vaccine Service', () => {
    let injector: TestBed;
    let service: VaccineService;
    let httpMock: HttpTestingController;
    let elemDefault: IVaccine;
    let expectedResult: IVaccine | IVaccine[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(VaccineService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Vaccine(0, 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', currentDate, VaccineType.TREATMENT);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_TIME_FORMAT),
            validUtil: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Vaccine', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            date: currentDate.format(DATE_TIME_FORMAT),
            validUtil: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
            validUtil: currentDate,
          },
          returnedFromService
        );

        service.create(new Vaccine()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Vaccine', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            date: currentDate.format(DATE_TIME_FORMAT),
            batchNumber: 'BBBBBB',
            nameAndManufacturer: 'BBBBBB',
            validUtil: currentDate.format(DATE_TIME_FORMAT),
            type: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
            validUtil: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Vaccine', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            date: currentDate.format(DATE_TIME_FORMAT),
            batchNumber: 'BBBBBB',
            nameAndManufacturer: 'BBBBBB',
            validUtil: currentDate.format(DATE_TIME_FORMAT),
            type: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
            validUtil: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Vaccine', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
