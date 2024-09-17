import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';

import { LoginCustomerComponent } from './login-customer/login-customer.component';
import { RegisterCustomerComponent } from './register-customer/register-customer.component';
import { ChangePasswordCustomerComponent } from './change-password-customer/change-password-customer.component';
import { ActivationCustomerComponent } from './activation-customer/activation-customer.component';
import { ForgetPasswordCustomerComponent } from './forget-password-customer/forget-password-customer.component';


import { CustomerCustomerModule } from './view/customer/customer-customer.module';
import { CustomerCustomerRoutingModule } from './view/customer/customer-customer-routing.module';
import { OrderCustomerModule } from './view/order/order-customer.module';
import { OrderCustomerRoutingModule } from './view/order/order-customer-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';


@NgModule({
  declarations: [
   LoginCustomerComponent,
   RegisterCustomerComponent,
   ChangePasswordCustomerComponent,
   ActivationCustomerComponent,
   ForgetPasswordCustomerComponent
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
  CustomerCustomerModule,
  CustomerCustomerRoutingModule,
  OrderCustomerModule,
  OrderCustomerRoutingModule,
   SecurityModule,
   SecurityRoutingModule
  ],
  exports: [
    LoginCustomerComponent,
    RegisterCustomerComponent,
    ChangePasswordCustomerComponent,
    ActivationCustomerComponent,
    ForgetPasswordCustomerComponent,

    CustomerCustomerModule,
    OrderCustomerModule,
    SecurityModule
  ],

})
export class CustomerModule { }
