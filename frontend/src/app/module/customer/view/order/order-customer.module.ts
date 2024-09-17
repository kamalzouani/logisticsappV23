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

import { OrderCreateCustomerComponent } from './order/create/order-create-customer.component';
import { OrderEditCustomerComponent } from './order/edit/order-edit-customer.component';
import { OrderViewCustomerComponent } from './order/view/order-view-customer.component';
import { OrderListCustomerComponent } from './order/list/order-list-customer.component';
import { OrderItemCreateCustomerComponent } from './order-item/create/order-item-create-customer.component';
import { OrderItemEditCustomerComponent } from './order-item/edit/order-item-edit-customer.component';
import { OrderItemViewCustomerComponent } from './order-item/view/order-item-view-customer.component';
import { OrderItemListCustomerComponent } from './order-item/list/order-item-list-customer.component';

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

    OrderCreateCustomerComponent,
    OrderListCustomerComponent,
    OrderViewCustomerComponent,
    OrderEditCustomerComponent,
    OrderItemCreateCustomerComponent,
    OrderItemListCustomerComponent,
    OrderItemViewCustomerComponent,
    OrderItemEditCustomerComponent,
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
  OrderCreateCustomerComponent,
  OrderListCustomerComponent,
  OrderViewCustomerComponent,
  OrderEditCustomerComponent,
  OrderItemCreateCustomerComponent,
  OrderItemListCustomerComponent,
  OrderItemViewCustomerComponent,
  OrderItemEditCustomerComponent,
  ],
})
export class OrderCustomerModule { }
