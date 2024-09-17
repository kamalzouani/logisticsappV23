import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {EditorModule} from "primeng/editor";

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';
import {TranslateModule} from '@ngx-translate/core';
import {FileUploadModule} from 'primeng/fileupload';
import {FullCalendarModule} from '@fullcalendar/angular';
import {CardModule} from "primeng/card";
import {TagModule} from "primeng/tag";

import { ProductCreateAdminComponent } from './product/create/product-create-admin.component';
import { ProductEditAdminComponent } from './product/edit/product-edit-admin.component';
import { ProductViewAdminComponent } from './product/view/product-view-admin.component';
import { ProductListAdminComponent } from './product/list/product-list-admin.component';
import { ProductCategoryCreateAdminComponent } from './product-category/create/product-category-create-admin.component';
import { ProductCategoryEditAdminComponent } from './product-category/edit/product-category-edit-admin.component';
import { ProductCategoryViewAdminComponent } from './product-category/view/product-category-view-admin.component';
import { ProductCategoryListAdminComponent } from './product-category/list/product-category-list-admin.component';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';
import {PaginatorModule} from 'primeng/paginator';



@NgModule({
  declarations: [

    ProductCreateAdminComponent,
    ProductListAdminComponent,
    ProductViewAdminComponent,
    ProductEditAdminComponent,
    ProductCategoryCreateAdminComponent,
    ProductCategoryListAdminComponent,
    ProductCategoryViewAdminComponent,
    ProductCategoryEditAdminComponent,
  ],
  imports: [
    CommonModule,
    ToastModule,
    ToolbarModule,
    TableModule,
    ConfirmDialogModule,
    DialogModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SplitButtonModule,
    BrowserAnimationsModule,
    DropdownModule,
    TabViewModule,
    InputSwitchModule,
    InputTextareaModule,
    CalendarModule,
    PanelModule,
    MessageModule,
    MessagesModule,
    InputNumberModule,
    BadgeModule,
    MultiSelectModule,
    PaginatorModule,
    TranslateModule,
    FileUploadModule,
    FullCalendarModule,
    CardModule,
    EditorModule,
    TagModule,


  ],
  exports: [
  ProductCreateAdminComponent,
  ProductListAdminComponent,
  ProductViewAdminComponent,
  ProductEditAdminComponent,
  ProductCategoryCreateAdminComponent,
  ProductCategoryListAdminComponent,
  ProductCategoryViewAdminComponent,
  ProductCategoryEditAdminComponent,
  ],
})
export class ProductAdminModule { }
