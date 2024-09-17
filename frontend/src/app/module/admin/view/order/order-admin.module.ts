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

import { OrderCreateAdminComponent } from './order/create/order-create-admin.component';
import { OrderEditAdminComponent } from './order/edit/order-edit-admin.component';
import { OrderViewAdminComponent } from './order/view/order-view-admin.component';
import { OrderListAdminComponent } from './order/list/order-list-admin.component';
import { OrderStatusCreateAdminComponent } from './order-status/create/order-status-create-admin.component';
import { OrderStatusEditAdminComponent } from './order-status/edit/order-status-edit-admin.component';
import { OrderStatusViewAdminComponent } from './order-status/view/order-status-view-admin.component';
import { OrderStatusListAdminComponent } from './order-status/list/order-status-list-admin.component';
import { OrderItemCreateAdminComponent } from './order-item/create/order-item-create-admin.component';
import { OrderItemEditAdminComponent } from './order-item/edit/order-item-edit-admin.component';
import { OrderItemViewAdminComponent } from './order-item/view/order-item-view-admin.component';
import { OrderItemListAdminComponent } from './order-item/list/order-item-list-admin.component';

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

    OrderCreateAdminComponent,
    OrderListAdminComponent,
    OrderViewAdminComponent,
    OrderEditAdminComponent,
    OrderStatusCreateAdminComponent,
    OrderStatusListAdminComponent,
    OrderStatusViewAdminComponent,
    OrderStatusEditAdminComponent,
    OrderItemCreateAdminComponent,
    OrderItemListAdminComponent,
    OrderItemViewAdminComponent,
    OrderItemEditAdminComponent,
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
  OrderCreateAdminComponent,
  OrderListAdminComponent,
  OrderViewAdminComponent,
  OrderEditAdminComponent,
  OrderStatusCreateAdminComponent,
  OrderStatusListAdminComponent,
  OrderStatusViewAdminComponent,
  OrderStatusEditAdminComponent,
  OrderItemCreateAdminComponent,
  OrderItemListAdminComponent,
  OrderItemViewAdminComponent,
  OrderItemEditAdminComponent,
  ],
})
export class OrderAdminModule { }
