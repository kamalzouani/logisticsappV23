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


import {ProductAdminService} from 'src/app/shared/service/admin/product/ProductAdmin.service';
import {ProductDto} from 'src/app/shared/model/product/Product.model';
import {ProductCriteria} from 'src/app/shared/criteria/product/ProductCriteria.model';

import {ProductCategoryDto} from 'src/app/shared/model/product/ProductCategory.model';
import {ProductCategoryAdminService} from 'src/app/shared/service/admin/product/ProductCategoryAdmin.service';
@Component({
  selector: 'app-product-view-admin',
  templateUrl: './product-view-admin.component.html'
})
export class ProductViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: ProductAdminService, private productCategoryService: ProductCategoryAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get productCategory(): ProductCategoryDto {
        return this.productCategoryService.item;
    }
    set productCategory(value: ProductCategoryDto) {
        this.productCategoryService.item = value;
    }
    get productCategorys(): Array<ProductCategoryDto> {
        return this.productCategoryService.items;
    }
    set productCategorys(value: Array<ProductCategoryDto>) {
        this.productCategoryService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<ProductDto> {
        return this.service.items;
    }

    set items(value: Array<ProductDto>) {
        this.service.items = value;
    }

    get item(): ProductDto {
        return this.service.item;
    }

    set item(value: ProductDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): ProductCriteria {
        return this.service.criteria;
    }

    set criteria(value: ProductCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
