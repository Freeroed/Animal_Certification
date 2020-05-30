import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVaccine } from 'app/shared/model/vaccine.model';

@Component({
  selector: 'jhi-vaccine-detail',
  templateUrl: './vaccine-detail.component.html',
})
export class VaccineDetailComponent implements OnInit {
  vaccine: IVaccine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vaccine }) => (this.vaccine = vaccine));
  }

  previousState(): void {
    window.history.back();
  }
}
