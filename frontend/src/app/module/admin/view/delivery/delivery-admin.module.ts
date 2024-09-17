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

import { DeliveryCreateAdminComponent } from './delivery/create/delivery-create-admin.component';
import { DeliveryEditAdminComponent } from './delivery/edit/delivery-edit-admin.component';
import { DeliveryViewAdminComponent } from './delivery/view/delivery-view-admin.component';
import { DeliveryListAdminComponent } from './delivery/list/delivery-list-admin.component';
import { DeliveryStatusCreateAdminComponent } from './delivery-status/create/delivery-status-create-admin.component';
import { DeliveryStatusEditAdminComponent } from './delivery-status/edit/delivery-status-edit-admin.component';
import { DeliveryStatusViewAdminComponent } from './delivery-status/view/delivery-status-view-admin.component';
import { DeliveryStatusListAdminComponent } from './delivery-status/list/delivery-status-list-admin.component';

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

    DeliveryCreateAdminComponent,
    DeliveryListAdminComponent,
    DeliveryViewAdminComponent,
    DeliveryEditAdminComponent,
    DeliveryStatusCreateAdminComponent,
    DeliveryStatusListAdminComponent,
    DeliveryStatusViewAdminComponent,
    DeliveryStatusEditAdminComponent,
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
  DeliveryCreateAdminComponent,
  DeliveryListAdminComponent,
  DeliveryViewAdminComponent,
  DeliveryEditAdminComponent,
  DeliveryStatusCreateAdminComponent,
  DeliveryStatusListAdminComponent,
  DeliveryStatusViewAdminComponent,
  DeliveryStatusEditAdminComponent,
  ],
})
export class DeliveryAdminModule { }
