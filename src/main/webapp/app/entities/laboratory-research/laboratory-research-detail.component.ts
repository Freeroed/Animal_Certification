import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILaboratoryResearch } from 'app/shared/model/laboratory-research.model';

@Component({
  selector: 'jhi-laboratory-research-detail',
  templateUrl: './laboratory-research-detail.component.html',
})
export class LaboratoryResearchDetailComponent implements OnInit {
  laboratoryResearch: ILaboratoryResearch | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ laboratoryResearch }) => (this.laboratoryResearch = laboratoryResearch));
  }

  previousState(): void {
    window.history.back();
  }
}
