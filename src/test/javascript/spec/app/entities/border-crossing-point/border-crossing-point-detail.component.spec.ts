import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AnimalCretificationTestModule } from '../../../test.module';
import { BorderCrossingPointDetailComponent } from 'app/entities/border-crossing-point/border-crossing-point-detail.component';
import { BorderCrossingPoint } from 'app/shared/model/border-crossing-point.model';

describe('Component Tests', () => {
  describe('BorderCrossingPoint Management Detail Component', () => {
    let comp: BorderCrossingPointDetailComponent;
    let fixture: ComponentFixture<BorderCrossingPointDetailComponent>;
    const route = ({ data: of({ borderCrossingPoint: new BorderCrossingPoint(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnimalCretificationTestModule],
        declarations: [BorderCrossingPointDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BorderCrossingPointDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BorderCrossingPointDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load borderCrossingPoint on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.borderCrossingPoint).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
