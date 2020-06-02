import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPersonData } from 'app/shared/model/person-data.model';

@Component({
  selector: 'jhi-person-data-detail',
  templateUrl: './person-data-detail.component.html',
})
export class PersonDataDetailComponent implements OnInit {
  personData: IPersonData | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personData }) => (this.personData = personData));
  }

  previousState(): void {
    window.history.back();
  }
}
