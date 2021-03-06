import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPersonalData } from 'app/shared/model/personal-data.model';

@Component({
  selector: 'jhi-personal-data-detail',
  templateUrl: './personal-data-detail.component.html',
})
export class PersonalDataDetailComponent implements OnInit {
  personalData: IPersonalData | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personData }) => (this.personalData = personData));
  }

  previousState(): void {
    window.history.back();
  }
}
