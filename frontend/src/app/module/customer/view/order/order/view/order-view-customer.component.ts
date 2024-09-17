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


import {OrderCustomerService} from 'src/app/shared/service/customer/order/OrderCustomer.service';
import {OrderDto} from 'src/app/shared/model/order/Order.model';
import {OrderCriteria} from 'src/app/shared/criteria/order/OrderCriteria.model';

import {CustomerDto} from 'src/app/shared/model/customer/Customer.model';
import {CustomerCustomerService} from 'src/app/shared/service/customer/customer/CustomerCustomer.service';
import {OrderItemDto} from 'src/app/shared/model/order/OrderItem.model';
import {OrderItemCustomerService} from 'src/app/shared/service/customer/order/OrderItemCustomer.service';
import {ProductDto} from 'src/app/shared/model/product/Product.model';
import {ProductCustomerService} from 'src/app/shared/service/customer/product/ProductCustomer.service';
import {OrderStatusDto} from 'src/app/shared/model/order/OrderStatus.model';
import {OrderStatusCustomerService} from 'src/app/shared/service/customer/order/OrderStatusCustomer.service';
@Component({
  selector: 'app-order-view-customer',
  templateUrl: './order-view-customer.component.html'
})
export class OrderViewCustomerComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;


    orderItems = new OrderItemDto();
    orderItemss: Array<OrderItemDto> = [];

    constructor(private service: OrderCustomerService, private customerService: CustomerCustomerService, private orderItemService: OrderItemCustomerService, private productService: ProductCustomerService, private orderStatusService: OrderStatusCustomerService){
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
    get customer(): CustomerDto {
        return this.customerService.item;
    }
    set customer(value: CustomerDto) {
        this.customerService.item = value;
    }
    get customers(): Array<CustomerDto> {
        return this.customerService.items;
    }
    set customers(value: Array<CustomerDto>) {
        this.customerService.items = value;
    }
    get orderStatus(): OrderStatusDto {
        return this.orderStatusService.item;
    }
    set orderStatus(value: OrderStatusDto) {
        this.orderStatusService.item = value;
    }
    get orderStatuss(): Array<OrderStatusDto> {
        return this.orderStatusService.items;
    }
    set orderStatuss(value: Array<OrderStatusDto>) {
        this.orderStatusService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<OrderDto> {
        return this.service.items;
    }

    set items(value: Array<OrderDto>) {
        this.service.items = value;
    }

    get item(): OrderDto {
        return this.service.item;
    }

    set item(value: OrderDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): OrderCriteria {
        return this.service.criteria;
    }

    set criteria(value: OrderCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
