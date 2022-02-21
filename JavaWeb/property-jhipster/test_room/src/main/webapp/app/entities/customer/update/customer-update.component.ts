import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ICustomer, Customer } from '../customer.model';
import { CustomerService } from '../service/customer.service';
import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';
import { IProperty } from 'app/entities/property/property.model';
import { PropertyService } from 'app/entities/property/service/property.service';
import { IName } from 'app/entities/name/name.model';
import { NameService } from 'app/entities/name/service/name.service';
import { Gender } from 'app/entities/enumerations/gender.model';

@Component({
  selector: 'jhi-customer-update',
  templateUrl: './customer-update.component.html',
})
export class CustomerUpdateComponent implements OnInit {
  isSaving = false;
  genderValues = Object.keys(Gender);

  usersSharedCollection: IUser[] = [];
  propertiesSharedCollection: IProperty[] = [];
  namesSharedCollection: IName[] = [];

  editForm = this.fb.group({
    id: [],
    email: [null, [Validators.required]],
    phone: [null, [Validators.required]],
    birth: [null, [Validators.required]],
    gender: [],
    user: [null, Validators.required],
    properties: [null, Validators.required],
    name: [null, Validators.required],
  });

  constructor(
    protected customerService: CustomerService,
    protected userService: UserService,
    protected propertyService: PropertyService,
    protected nameService: NameService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ customer }) => {
      this.updateForm(customer);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const customer = this.createFromForm();
    if (customer.id !== undefined) {
      this.subscribeToSaveResponse(this.customerService.update(customer));
    } else {
      this.subscribeToSaveResponse(this.customerService.create(customer));
    }
  }

  trackUserById(index: number, item: IUser): number {
    return item.id!;
  }

  trackPropertyById(index: number, item: IProperty): number {
    return item.id!;
  }

  trackNameById(index: number, item: IName): number {
    return item.id!;
  }

  getSelectedProperty(option: IProperty, selectedVals?: IProperty[]): IProperty {
    if (selectedVals) {
      for (const selectedVal of selectedVals) {
        if (option.id === selectedVal.id) {
          return selectedVal;
        }
      }
    }
    return option;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomer>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(customer: ICustomer): void {
    this.editForm.patchValue({
      id: customer.id,
      email: customer.email,
      phone: customer.phone,
      birth: customer.birth,
      gender: customer.gender,
      user: customer.user,
      properties: customer.properties,
      name: customer.name,
    });

    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing(this.usersSharedCollection, customer.user);
    this.propertiesSharedCollection = this.propertyService.addPropertyToCollectionIfMissing(
      this.propertiesSharedCollection,
      ...(customer.properties ?? [])
    );
    this.namesSharedCollection = this.nameService.addNameToCollectionIfMissing(this.namesSharedCollection, customer.name);
  }

  protected loadRelationshipsOptions(): void {
    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(map((users: IUser[]) => this.userService.addUserToCollectionIfMissing(users, this.editForm.get('user')!.value)))
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));

    this.propertyService
      .query()
      .pipe(map((res: HttpResponse<IProperty[]>) => res.body ?? []))
      .pipe(
        map((properties: IProperty[]) =>
          this.propertyService.addPropertyToCollectionIfMissing(properties, ...(this.editForm.get('properties')!.value ?? []))
        )
      )
      .subscribe((properties: IProperty[]) => (this.propertiesSharedCollection = properties));

    this.nameService
      .query()
      .pipe(map((res: HttpResponse<IName[]>) => res.body ?? []))
      .pipe(map((names: IName[]) => this.nameService.addNameToCollectionIfMissing(names, this.editForm.get('name')!.value)))
      .subscribe((names: IName[]) => (this.namesSharedCollection = names));
  }

  protected createFromForm(): ICustomer {
    return {
      ...new Customer(),
      id: this.editForm.get(['id'])!.value,
      email: this.editForm.get(['email'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      birth: this.editForm.get(['birth'])!.value,
      gender: this.editForm.get(['gender'])!.value,
      user: this.editForm.get(['user'])!.value,
      properties: this.editForm.get(['properties'])!.value,
      name: this.editForm.get(['name'])!.value,
    };
  }
}