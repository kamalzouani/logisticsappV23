import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {DeliveryAdminService} from 'src/app/shared/service/admin/delivery/DeliveryAdmin.service';
import {DeliveryDto} from 'src/app/shared/model/delivery/Delivery.model';
import {DeliveryCriteria} from 'src/app/shared/criteria/delivery/DeliveryCriteria.model';
import {AddressDto} from 'src/app/shared/model/address/Address.model';
import {AddressAdminService} from 'src/app/shared/service/admin/address/AddressAdmin.service';
import {OrderDto} from 'src/app/shared/model/order/Order.model';
import {OrderAdminService} from 'src/app/shared/service/admin/order/OrderAdmin.service';
import {DeliveryStatusDto} from 'src/app/shared/model/delivery/DeliveryStatus.model';
import {DeliveryStatusAdminService} from 'src/app/shared/service/admin/delivery/DeliveryStatusAdmin.service';
@Component({
  selector: 'app-delivery-create-admin',
  templateUrl: './delivery-create-admin.component.html'
})
export class DeliveryCreateAdminComponent  implements OnInit {

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

	constructor(private service: DeliveryAdminService , private addressService: AddressAdminService, private orderService: OrderAdminService, private deliveryStatusService: DeliveryStatusAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.addressService.findAll().subscribe((data) => this.addresss = data);
        this.deliveryStatusService.findAll().subscribe((data) => this.deliveryStatuss = data);
        this.orderService.findAll().subscribe((data) => this.orders = data);
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
                this.item = new DeliveryDto();
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
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
    }



    public async openCreateDeliveryStatus(deliveryStatus: string) {
    const isPermistted = await this.roleService.isPermitted('DeliveryStatus', 'add');
    if(isPermistted) {
         this.deliveryStatus = new DeliveryStatusDto();
         this.createDeliveryStatusDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }

    get deliveryStatus(): DeliveryStatusDto {
        return this.deliveryStatusService.item;
    }
    set deliveryStatus(value: DeliveryStatusDto) {
        this.deliveryStatusService.item = value;
    }
    get deliveryStatuss(): Array<DeliveryStatusDto> {
        return this.deliveryStatusService.items;
    }
    set deliveryStatuss(value: Array<DeliveryStatusDto>) {
        this.deliveryStatusService.items = value;
    }
    get createDeliveryStatusDialog(): boolean {
        return this.deliveryStatusService.createDialog;
    }
    set createDeliveryStatusDialog(value: boolean) {
        this.deliveryStatusService.createDialog= value;
    }
    get address(): AddressDto {
        return this.addressService.item;
    }
    set address(value: AddressDto) {
        this.addressService.item = value;
    }
    get addresss(): Array<AddressDto> {
        return this.addressService.items;
    }
    set addresss(value: Array<AddressDto>) {
        this.addressService.items = value;
    }
    get createAddressDialog(): boolean {
        return this.addressService.createDialog;
    }
    set createAddressDialog(value: boolean) {
        this.addressService.createDialog= value;
    }
    get order(): OrderDto {
        return this.orderService.item;
    }
    set order(value: OrderDto) {
        this.orderService.item = value;
    }
    get orders(): Array<OrderDto> {
        return this.orderService.items;
    }
    set orders(value: Array<OrderDto>) {
        this.orderService.items = value;
    }
    get createOrderDialog(): boolean {
        return this.orderService.createDialog;
    }
    set createOrderDialog(value: boolean) {
        this.orderService.createDialog= value;
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


    get items(): Array<DeliveryDto> {
        return this.service.items;
    }

    set items(value: Array<DeliveryDto>) {
        this.service.items = value;
    }

    get item(): DeliveryDto {
        return this.service.item;
    }

    set item(value: DeliveryDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): DeliveryCriteria {
        return this.service.criteria;
    }

    set criteria(value: DeliveryCriteria) {
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
