import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AnimalCertificationTestModule } from '../../../test.module';
import { LaboratoryResearchUpdateComponent } from 'app/entities/laboratory-research/laboratory-research-update.component';
import { LaboratoryResearchService } from 'app/entities/laboratory-research/laboratory-research.service';
import { LaboratoryResearch } from 'app/shared/model/laboratory-research.model';

describe('Component Tests', () => {
  describe('LaboratoryResearch Management Update Component', () => {
    let comp: LaboratoryResearchUpdateComponent;
    let fixture: ComponentFixture<LaboratoryResearchUpdateComponent>;
    let service: LaboratoryResearchService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCertificationTestModule],
        declarations: [LaboratoryResearchUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(LaboratoryResearchUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LaboratoryResearchUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LaboratoryResearchService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LaboratoryResearch(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new LaboratoryResearch();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
