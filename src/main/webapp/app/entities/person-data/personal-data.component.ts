import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPersonalData } from 'app/shared/model/personal-data.model';
import { PersonalDataService } from './personal-data.service';
import { PersonalDataDeleteDialogComponent } from './personal-data-delete-dialog.component';

@Component({
  selector: 'jhi-person-data',
  templateUrl: './personal-data.component.html',
})
export class PersonalDataComponent implements OnInit, OnDestroy {
  personalData?: IPersonalData[];
  eventSubscriber?: Subscription;

  constructor(
    protected personalDataService: PersonalDataService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.personalDataService.query().subscribe((res: HttpResponse<IPersonalData[]>) => (this.personalData = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPersonData();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPersonalData): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPersonData(): void {
    this.eventSubscriber = this.eventManager.subscribe('personDataListModification', () => this.loadAll());
  }

  delete(personData: IPersonalData): void {
    const modalRef = this.modalService.open(PersonalDataDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.personData = personData;
  }
}
