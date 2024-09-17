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

import { AddressCreateAdminComponent } from './address/create/address-create-admin.component';
import { AddressEditAdminComponent } from './address/edit/address-edit-admin.component';
import { AddressViewAdminComponent } from './address/view/address-view-admin.component';
import { AddressListAdminComponent } from './address/list/address-list-admin.component';
import { CityCreateAdminComponent } from './city/create/city-create-admin.component';
import { CityEditAdminComponent } from './city/edit/city-edit-admin.component';
import { CityViewAdminComponent } from './city/view/city-view-admin.component';
import { CityListAdminComponent } from './city/list/city-list-admin.component';
import { CountryCreateAdminComponent } from './country/create/country-create-admin.component';
import { CountryEditAdminComponent } from './country/edit/country-edit-admin.component';
import { CountryViewAdminComponent } from './country/view/country-view-admin.component';
import { CountryListAdminComponent } from './country/list/country-list-admin.component';

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

    AddressCreateAdminComponent,
    AddressListAdminComponent,
    AddressViewAdminComponent,
    AddressEditAdminComponent,
    CityCreateAdminComponent,
    CityListAdminComponent,
    CityViewAdminComponent,
    CityEditAdminComponent,
    CountryCreateAdminComponent,
    CountryListAdminComponent,
    CountryViewAdminComponent,
    CountryEditAdminComponent,
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
  AddressCreateAdminComponent,
  AddressListAdminComponent,
  AddressViewAdminComponent,
  AddressEditAdminComponent,
  CityCreateAdminComponent,
  CityListAdminComponent,
  CityViewAdminComponent,
  CityEditAdminComponent,
  CountryCreateAdminComponent,
  CountryListAdminComponent,
  CountryViewAdminComponent,
  CountryEditAdminComponent,
  ],
})
export class AddressAdminModule { }
