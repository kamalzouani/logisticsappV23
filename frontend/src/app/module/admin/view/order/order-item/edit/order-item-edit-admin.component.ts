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




import {OrderItemAdminService} from 'src/app/shared/service/admin/order/OrderItemAdmin.service';
import {OrderItemDto} from 'src/app/shared/model/order/OrderItem.model';
import {OrderItemCriteria} from 'src/app/shared/criteria/order/OrderItemCriteria.model';


import {OrderDto} from 'src/app/shared/model/order/Order.model';
import {OrderAdminService} from 'src/app/shared/service/admin/order/OrderAdmin.service';
import {ProductDto} from 'src/app/shared/model/product/Product.model';
import {ProductAdminService} from 'src/app/shared/service/admin/product/ProductAdmin.service';

@Component({
  selector: 'app-order-item-edit-admin',
  templateUrl: './order-item-edit-admin.component.html'
})
export class OrderItemEditAdminComponent implements OnInit {

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







    constructor(private service: OrderItemAdminService , private orderService: OrderAdminService, private productService: ProductAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.productService.findAll().subscribe((data) => this.products = data);
        this.orderService.findAll().subscribe((data) => this.orders = data);
    }

    public prepareEdit() {
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
            this.item = new OrderItemDto();
        } , error =>{
            console.log(error);
        });
    }

    public hideEditDialog() {
        this.editDialog = false;
        this.setValidation(true);
    }





    public setValidation(value: boolean){
    }

    public validateForm(): void{
        this.errorMessages = new Array<string>();
    }




   public async openCreateOrder(order: string) {
        const isPermistted = await this.roleService.isPermitted('Order', 'edit');
        if (isPermistted) {
             this.order = new OrderDto();
             this.createOrderDialog = true;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
    }

    get criteria(): OrderItemCriteria {
        return this.service.criteria;
    }

    set criteria(value: OrderItemCriteria) {
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
