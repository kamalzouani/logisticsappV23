import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {OrderStatusAdminService} from 'src/app/shared/service/admin/order/OrderStatusAdmin.service';
import {OrderStatusDto} from 'src/app/shared/model/order/OrderStatus.model';
import {OrderStatusCriteria} from 'src/app/shared/criteria/order/OrderStatusCriteria.model';
@Component({
  selector: 'app-order-status-create-admin',
  templateUrl: './order-status-create-admin.component.html'
})
export class OrderStatusCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validOrderStatusCode = true;
   private _validOrderStatusLabel = true;
   private _validOrderStatusStyle = true;

	constructor(private service: OrderStatusAdminService , @Inject(PLATFORM_ID) private platformId? ) {
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
                this.item = new OrderStatusDto();
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
        this.validOrderStatusCode = value;
        this.validOrderStatusLabel = value;
        this.validOrderStatusStyle = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateOrderStatusCode();
        this.validateOrderStatusLabel();
        this.validateOrderStatusStyle();
    }

    public validateOrderStatusCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validOrderStatusCode = false;
        } else {
            this.validOrderStatusCode = true;
        }
    }
    public validateOrderStatusLabel(){
        if (this.stringUtilService.isEmpty(this.item.label)) {
        this.errorMessages.push('Label non valide');
        this.validOrderStatusLabel = false;
        } else {
            this.validOrderStatusLabel = true;
        }
    }
    public validateOrderStatusStyle(){
        if (this.stringUtilService.isEmpty(this.item.style)) {
        this.errorMessages.push('Style non valide');
        this.validOrderStatusStyle = false;
        } else {
            this.validOrderStatusStyle = true;
        }
    }






    get validOrderStatusCode(): boolean {
        return this._validOrderStatusCode;
    }

    set validOrderStatusCode(value: boolean) {
         this._validOrderStatusCode = value;
    }
    get validOrderStatusLabel(): boolean {
        return this._validOrderStatusLabel;
    }

    set validOrderStatusLabel(value: boolean) {
         this._validOrderStatusLabel = value;
    }
    get validOrderStatusStyle(): boolean {
        return this._validOrderStatusStyle;
    }

    set validOrderStatusStyle(value: boolean) {
         this._validOrderStatusStyle = value;
    }



    get items(): Array<OrderStatusDto> {
        return this.service.items;
    }

    set items(value: Array<OrderStatusDto>) {
        this.service.items = value;
    }

    get item(): OrderStatusDto {
        return this.service.item;
    }

    set item(value: OrderStatusDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): OrderStatusCriteria {
        return this.service.criteria;
    }

    set criteria(value: OrderStatusCriteria) {
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
