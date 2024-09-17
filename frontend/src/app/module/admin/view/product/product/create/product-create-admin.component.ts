import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {ProductAdminService} from 'src/app/shared/service/admin/product/ProductAdmin.service';
import {ProductDto} from 'src/app/shared/model/product/Product.model';
import {ProductCriteria} from 'src/app/shared/criteria/product/ProductCriteria.model';
import {ProductCategoryDto} from 'src/app/shared/model/product/ProductCategory.model';
import {ProductCategoryAdminService} from 'src/app/shared/service/admin/product/ProductCategoryAdmin.service';
@Component({
  selector: 'app-product-create-admin',
  templateUrl: './product-create-admin.component.html'
})
export class ProductCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;




	constructor(private service: ProductAdminService , private productCategoryService: ProductCategoryAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.productCategoryService.findAll().subscribe((data) => this.productCategorys = data);
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
                this.item = new ProductDto();
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



    public async openCreateProductCategory(productCategory: string) {
    const isPermistted = await this.roleService.isPermitted('ProductCategory', 'add');
    if(isPermistted) {
         this.productCategory = new ProductCategoryDto();
         this.createProductCategoryDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
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
    get createProductCategoryDialog(): boolean {
        return this.productCategoryService.createDialog;
    }
    set createProductCategoryDialog(value: boolean) {
        this.productCategoryService.createDialog= value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): ProductCriteria {
        return this.service.criteria;
    }

    set criteria(value: ProductCriteria) {
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
