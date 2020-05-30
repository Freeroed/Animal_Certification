import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { AnimalService } from 'app/entities/animal/animal.service';
import { IAnimal, Animal } from 'app/shared/model/animal.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { AnimalStatus } from 'app/shared/model/enumerations/animal-status.model';

describe('Service Tests', () => {
  describe('Animal Service', () => {
    let injector: TestBed;
    let service: AnimalService;
    let httpMock: HttpTestingController;
    let elemDefault: IAnimal;
    let expectedResult: IAnimal | IAnimal[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AnimalService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Animal(
        0,
        'AAAAAAA',
        currentDate,
        Gender.MALE,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        AnimalStatus.READY_TO_REQUEST
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            birthdate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Animal', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            birthdate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthdate: currentDate,
          },
          returnedFromService
        );

        service.create(new Animal()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Animal', () => {
        const returnedFromService = Object.assign(
          {
            nickname: 'BBBBBB',
            birthdate: currentDate.format(DATE_TIME_FORMAT),
            gender: 'BBBBBB',
            chip: 'BBBBBB',
            birthPlace: 'BBBBBB',
            tnvedCode: 'BBBBBB',
            color: 'BBBBBB',
            colorEng: 'BBBBBB',
            status: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthdate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Animal', () => {
        const returnedFromService = Object.assign(
          {
            nickname: 'BBBBBB',
            birthdate: currentDate.format(DATE_TIME_FORMAT),
            gender: 'BBBBBB',
            chip: 'BBBBBB',
            birthPlace: 'BBBBBB',
            tnvedCode: 'BBBBBB',
            color: 'BBBBBB',
            colorEng: 'BBBBBB',
            status: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthdate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Animal', () => {
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
