import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnimalType } from 'app/shared/model/animal-type.model';

@Component({
  selector: 'jhi-animal-type-detail',
  templateUrl: './animal-type-detail.component.html',
})
export class AnimalTypeDetailComponent implements OnInit {
  animalType: IAnimalType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ animalType }) => (this.animalType = animalType));
  }

  previousState(): void {
    window.history.back();
  }
}
