import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';

import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




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
  selector: 'app-order-edit-customer',
  templateUrl: './order-edit-customer.component.html'
})
export class OrderEditCustomerComponent implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;
    private _file: any;
    private _files: any;


    private _orderItemsElement = new OrderItemDto();


    private _validOrderStatusCode = true;
    private _validOrderStatusLabel = true;
    private _validOrderStatusStyle = true;



    constructor(private service: OrderCustomerService , private customerService: CustomerCustomerService, private orderItemService: OrderItemCustomerService, private productService: ProductCustomerService, private orderStatusService: OrderStatusCustomerService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.orderItemsElement.product = new ProductDto();
        this.productService.findAll().subscribe((data) => this.products = data);

        this.orderStatusService.findAll().subscribe((data) => this.orderStatuss = data);
        this.customerService.findAll().subscribe((data) => this.customers = data);
    }

    public prepareEdit() {
        this.item.orderDate = this.service.format(this.item.orderDate);
    }



 public edit(): void {
        this.submitted = true;
        this.prepareEdit();
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.editWithShowOption(false);
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Erreurs',
                detail: 'Merci de corrigé les erreurs sur le formulaire'
            });
        }
    }

    public editWithShowOption(showList: boolean) {
        this.service.edit().subscribe(religion=>{
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = religion;
            this.editDialog = false;
            this.submitted = false;
            this.item = new OrderDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public validateOrderItems(){
        this.errorMessages = new Array();
    }

    public setValidation(value: boolean){
    }

   public addOrderItems() {
        if( this.item.orderItems == null )
            this.item.orderItems = new Array<OrderItemDto>();
       this.validateOrderItems();
       if (this.errorMessages.length === 0) {
            if(this.orderItemsElement.id == null){
                this.item.orderItems.push(this.orderItemsElement);
            }else{
                const index = this.item.orderItems.findIndex(e => e.id == this.orderItemsElement.id);
                this.item.orderItems[index] = this.orderItemsElement;
            }
          this.orderItemsElement = new OrderItemDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs', detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
        }
   }

    public deleteOrderItems(p: OrderItemDto) {
        this.item.orderItems.forEach((element, index) => {
            if (element === p) { this.item.orderItems.splice(index, 1); }
        });
    }

    public editOrderItems(p: OrderItemDto) {
        this.orderItemsElement = {... p};
        this.activeTab = 0;
    }


    public validateForm(): void{
        this.errorMessages = new Array<string>();
    }




   public async openCreateOrderStatus(orderStatus: string) {
        const isPermistted = await this.roleService.isPermitted('OrderStatus', 'edit');
        if (isPermistted) {
             this.orderStatus = new OrderStatusDto();
             this.createOrderStatusDialog = true;
        }else {
             this.messageService.add({
                severity: 'error', summary: 'erreur', detail: 'problème de permission'
            });
        }
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
    get createProductDialog(): boolean {
        return this.productService.createDialog;
    }
    set createProductDialog(value: boolean) {
        this.productService.createDialog= value;
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
    get createCustomerDialog(): boolean {
        return this.customerService.createDialog;
    }
    set createCustomerDialog(value: boolean) {
        this.customerService.createDialog= value;
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
    get createOrderStatusDialog(): boolean {
        return this.orderStatusService.createDialog;
    }
    set createOrderStatusDialog(value: boolean) {
        this.orderStatusService.createDialog= value;
    }

    get orderItemsElement(): OrderItemDto {
        if( this._orderItemsElement == null )
            this._orderItemsElement = new OrderItemDto();
         return this._orderItemsElement;
    }

    set orderItemsElement(value: OrderItemDto) {
        this._orderItemsElement = value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): OrderCriteria {
        return this.service.criteria;
    }

    set criteria(value: OrderCriteria) {
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
