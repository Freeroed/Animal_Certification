import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BorderCrossingPointService } from 'app/entities/border-crossing-point/border-crossing-point.service';
import { IBorderCrossingPoint, BorderCrossingPoint } from 'app/shared/model/border-crossing-point.model';

describe('Service Tests', () => {
  describe('BorderCrossingPoint Service', () => {
    let injector: TestBed;
    let service: BorderCrossingPointService;
    let httpMock: HttpTestingController;
    let elemDefault: IBorderCrossingPoint;
    let expectedResult: IBorderCrossingPoint | IBorderCrossingPoint[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BorderCrossingPointService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new BorderCrossingPoint(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BorderCrossingPoint', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new BorderCrossingPoint()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BorderCrossingPoint', () => {
        const returnedFromService = Object.assign(
          {
            location: 'BBBBBB',
            adjacentPoint: 'BBBBBB',
            classification: 'BBBBBB',
            schedule: 'BBBBBB',
            scheduleOfOfficals: 'BBBBBB',
            coordinates: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BorderCrossingPoint', () => {
        const returnedFromService = Object.assign(
          {
            location: 'BBBBBB',
            adjacentPoint: 'BBBBBB',
            classification: 'BBBBBB',
            schedule: 'BBBBBB',
            scheduleOfOfficals: 'BBBBBB',
            coordinates: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BorderCrossingPoint', () => {
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
