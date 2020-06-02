import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDocument, Document } from 'app/shared/model/document.model';
import { DocumentService } from './document.service';
import { IDocumentType } from 'app/shared/model/document-type.model';
import { DocumentTypeService } from 'app/entities/document-type/document-type.service';
import { IRequest } from 'app/shared/model/request.model';
import { RequestService } from 'app/entities/request/request.service';

type SelectableEntity = IDocumentType | IRequest;

@Component({
  selector: 'jhi-document-update',
  templateUrl: './document-update.component.html',
})
export class DocumentUpdateComponent implements OnInit {
  isSaving = false;
  documenttypes: IDocumentType[] = [];
  requests: IRequest[] = [];

  editForm = this.fb.group({
    id: [],
    issuedAt: [null, [Validators.required]],
    link: [null, [Validators.required]],
    documentNumber: [null, [Validators.required]],
    type: [],
    request: [],
  });

  constructor(
    protected documentService: DocumentService,
    protected documentTypeService: DocumentTypeService,
    protected requestService: RequestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ document }) => {
      if (!document.id) {
        const today = moment().startOf('day');
        document.issuedAt = today;
      }

      this.updateForm(document);

      this.documentTypeService.query().subscribe((res: HttpResponse<IDocumentType[]>) => (this.documenttypes = res.body || []));

      this.requestService.query().subscribe((res: HttpResponse<IRequest[]>) => (this.requests = res.body || []));
    });
  }

  updateForm(document: IDocument): void {
    this.editForm.patchValue({
      id: document.id,
      issuedAt: document.issuedAt ? document.issuedAt.format(DATE_TIME_FORMAT) : null,
      link: document.link,
      documentNumber: document.documentNumber,
      type: document.type,
      request: document.request,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const document = this.createFromForm();
    if (document.id !== undefined) {
      this.subscribeToSaveResponse(this.documentService.update(document));
    } else {
      this.subscribeToSaveResponse(this.documentService.create(document));
    }
  }

  private createFromForm(): IDocument {
    return {
      ...new Document(),
      id: this.editForm.get(['id'])!.value,
      issuedAt: this.editForm.get(['issuedAt'])!.value ? moment(this.editForm.get(['issuedAt'])!.value, DATE_TIME_FORMAT) : undefined,
      link: this.editForm.get(['link'])!.value,
      documentNumber: this.editForm.get(['documentNumber'])!.value,
      type: this.editForm.get(['type'])!.value,
      request: this.editForm.get(['request'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDocument>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
