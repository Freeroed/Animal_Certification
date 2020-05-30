import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LaboratoryResearchService } from 'app/entities/laboratory-research/laboratory-research.service';
import { ILaboratoryResearch, LaboratoryResearch } from 'app/shared/model/laboratory-research.model';
import { LaboratoryTestResult } from 'app/shared/model/enumerations/laboratory-test-result.model';

describe('Service Tests', () => {
  describe('LaboratoryResearch Service', () => {
    let injector: TestBed;
    let service: LaboratoryResearchService;
    let httpMock: HttpTestingController;
    let elemDefault: ILaboratoryResearch;
    let expectedResult: ILaboratoryResearch | ILaboratoryResearch[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LaboratoryResearchService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new LaboratoryResearch(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', LaboratoryTestResult.POSITIVE);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a LaboratoryResearch', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new LaboratoryResearch()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a LaboratoryResearch', () => {
        const returnedFromService = Object.assign(
          {
            laboratoty: 'BBBBBB',
            indicator: 'BBBBBB',
            resultReceiptDate: 'BBBBBB',
            resurchMethod: 'BBBBBB',
            examinationNumber: 'BBBBBB',
            result: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of LaboratoryResearch', () => {
        const returnedFromService = Object.assign(
          {
            laboratoty: 'BBBBBB',
            indicator: 'BBBBBB',
            resultReceiptDate: 'BBBBBB',
            resurchMethod: 'BBBBBB',
            examinationNumber: 'BBBBBB',
            result: 'BBBBBB',
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

      it('should delete a LaboratoryResearch', () => {
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
