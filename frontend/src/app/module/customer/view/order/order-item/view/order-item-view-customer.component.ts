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


import {OrderItemCustomerService} from 'src/app/shared/service/customer/order/OrderItemCustomer.service';
import {OrderItemDto} from 'src/app/shared/model/order/OrderItem.model';
import {OrderItemCriteria} from 'src/app/shared/criteria/order/OrderItemCriteria.model';

import {OrderDto} from 'src/app/shared/model/order/Order.model';
import {OrderCustomerService} from 'src/app/shared/service/customer/order/OrderCustomer.service';
import {ProductDto} from 'src/app/shared/model/product/Product.model';
import {ProductCustomerService} from 'src/app/shared/service/customer/product/ProductCustomer.service';
@Component({
  selector: 'app-order-item-view-customer',
  templateUrl: './order-item-view-customer.component.html'
})
export class OrderItemViewCustomerComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: OrderItemCustomerService, private orderService: OrderCustomerService, private productService: ProductCustomerService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get product(): ProductDto {
        return this.productService.item;
    }
    set product(value: ProductDto) {
        this.productService.item = value;
    }
    get products(): Array<ProductDto> {
        return this.productService.items;
    }
    set products(value: Array<ProductDto>) {
        this.productService.items = value;
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

    get items(): Array<OrderItemDto> {
        return this.service.items;
    }

    set items(value: Array<OrderItemDto>) {
        this.service.items = value;
    }

    get item(): OrderItemDto {
        return this.service.item;
    }

    set item(value: OrderItemDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): OrderItemCriteria {
        return this.service.criteria;
    }

    set criteria(value: OrderItemCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
