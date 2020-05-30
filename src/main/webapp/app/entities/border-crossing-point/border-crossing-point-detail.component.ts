import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBorderCrossingPoint } from 'app/shared/model/border-crossing-point.model';

@Component({
  selector: 'jhi-border-crossing-point-detail',
  templateUrl: './border-crossing-point-detail.component.html',
})
export class BorderCrossingPointDetailComponent implements OnInit {
  borderCrossingPoint: IBorderCrossingPoint | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ borderCrossingPoint }) => (this.borderCrossingPoint = borderCrossingPoint));
  }

  previousState(): void {
    window.history.back();
  }
}
