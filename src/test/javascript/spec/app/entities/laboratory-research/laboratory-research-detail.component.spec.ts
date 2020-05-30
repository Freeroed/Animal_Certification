import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AnimalCretificationTestModule } from '../../../test.module';
import { LaboratoryResearchDetailComponent } from 'app/entities/laboratory-research/laboratory-research-detail.component';
import { LaboratoryResearch } from 'app/shared/model/laboratory-research.model';

describe('Component Tests', () => {
  describe('LaboratoryResearch Management Detail Component', () => {
    let comp: LaboratoryResearchDetailComponent;
    let fixture: ComponentFixture<LaboratoryResearchDetailComponent>;
    const route = ({ data: of({ laboratoryResearch: new LaboratoryResearch(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCretificationTestModule],
        declarations: [LaboratoryResearchDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(LaboratoryResearchDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LaboratoryResearchDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load laboratoryResearch on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.laboratoryResearch).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
