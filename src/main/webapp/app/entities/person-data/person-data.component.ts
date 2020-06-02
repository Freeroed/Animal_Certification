import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPersonData } from 'app/shared/model/person-data.model';
import { PersonDataService } from './person-data.service';
import { PersonDataDeleteDialogComponent } from './person-data-delete-dialog.component';

@Component({
  selector: 'jhi-person-data',
  templateUrl: './person-data.component.html',
})
export class PersonDataComponent implements OnInit, OnDestroy {
  personData?: IPersonData[];
  eventSubscriber?: Subscription;

  constructor(protected personDataService: PersonDataService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.personDataService.query().subscribe((res: HttpResponse<IPersonData[]>) => (this.personData = res.body || []));
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

  trackId(index: number, item: IPersonData): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPersonData(): void {
    this.eventSubscriber = this.eventManager.subscribe('personDataListModification', () => this.loadAll());
  }

  delete(personData: IPersonData): void {
    const modalRef = this.modalService.open(PersonDataDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.personData = personData;
  }
}
