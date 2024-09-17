import {Component, OnInit} from '@angular/core';


import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';


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
  selector: 'app-delivery-view-admin',
  templateUrl: './delivery-view-admin.component.html'
})
export class DeliveryViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: DeliveryAdminService, private addressService: AddressAdminService, private orderService: OrderAdminService, private deliveryStatusService: DeliveryStatusAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
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

    public hideViewDialog() {
        this.viewDialog = false;
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

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): DeliveryCriteria {
        return this.service.criteria;
    }

    set criteria(value: DeliveryCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
