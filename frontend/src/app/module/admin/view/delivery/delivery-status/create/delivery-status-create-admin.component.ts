import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {DeliveryStatusAdminService} from 'src/app/shared/service/admin/delivery/DeliveryStatusAdmin.service';
import {DeliveryStatusDto} from 'src/app/shared/model/delivery/DeliveryStatus.model';
import {DeliveryStatusCriteria} from 'src/app/shared/criteria/delivery/DeliveryStatusCriteria.model';
@Component({
  selector: 'app-delivery-status-create-admin',
  templateUrl: './delivery-status-create-admin.component.html'
})
export class DeliveryStatusCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validDeliveryStatusCode = true;
   private _validDeliveryStatusLabel = true;
   private _validDeliveryStatusStyle = true;

	constructor(private service: DeliveryStatusAdminService , @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
    }



    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new DeliveryStatusDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }


    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }





    public  setValidation(value: boolean){
        this.validDeliveryStatusCode = value;
        this.validDeliveryStatusLabel = value;
        this.validDeliveryStatusStyle = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateDeliveryStatusCode();
        this.validateDeliveryStatusLabel();
        this.validateDeliveryStatusStyle();
    }

    public validateDeliveryStatusCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validDeliveryStatusCode = false;
        } else {
            this.validDeliveryStatusCode = true;
        }
    }
    public validateDeliveryStatusLabel(){
        if (this.stringUtilService.isEmpty(this.item.label)) {
        this.errorMessages.push('Label non valide');
        this.validDeliveryStatusLabel = false;
        } else {
            this.validDeliveryStatusLabel = true;
        }
    }
    public validateDeliveryStatusStyle(){
        if (this.stringUtilService.isEmpty(this.item.style)) {
        this.errorMessages.push('Style non valide');
        this.validDeliveryStatusStyle = false;
        } else {
            this.validDeliveryStatusStyle = true;
        }
    }






    get validDeliveryStatusCode(): boolean {
        return this._validDeliveryStatusCode;
    }

    set validDeliveryStatusCode(value: boolean) {
         this._validDeliveryStatusCode = value;
    }
    get validDeliveryStatusLabel(): boolean {
        return this._validDeliveryStatusLabel;
    }

    set validDeliveryStatusLabel(value: boolean) {
         this._validDeliveryStatusLabel = value;
    }
    get validDeliveryStatusStyle(): boolean {
        return this._validDeliveryStatusStyle;
    }

    set validDeliveryStatusStyle(value: boolean) {
         this._validDeliveryStatusStyle = value;
    }



    get items(): Array<DeliveryStatusDto> {
        return this.service.items;
    }

    set items(value: Array<DeliveryStatusDto>) {
        this.service.items = value;
    }

    get item(): DeliveryStatusDto {
        return this.service.item;
    }

    set item(value: DeliveryStatusDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): DeliveryStatusCriteria {
        return this.service.criteria;
    }

    set criteria(value: DeliveryStatusCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }

}
