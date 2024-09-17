import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AuthGuard} from 'src/app/zynerator/security/guards/auth.guard';

import { ActivationAdminComponent } from './activation-admin/activation-admin.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
import { ForgetPasswordAdminComponent } from './forget-password-admin/forget-password-admin.component';
import { ChangePasswordAdminComponent } from './change-password-admin/change-password-admin.component';

@NgModule({
    imports: [
        RouterModule.forChild(
            [
                {
                    path: '',
                    children: [
                        {
                            path: 'login',
                            children: [
                                {
                                    path: '',
                                    component: LoginAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'register',
                            children: [
                                {
                                    path: '',
                                    component: RegisterAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'forget-password',
                            children: [
                                {
                                    path: '',
                                    component: ForgetPasswordAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },{
                            path: 'change-password',
                            children: [
                                {
                                    path: '',
                                    component: ChangePasswordAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'activation',
                            children: [
                                {
                                    path: '',
                                    component: ActivationAdminComponent ,
                                    canActivate: [AuthGuard]
                                }
                              ]
                        },
                        {
                            path: 'product',
                            loadChildren: () => import('./view/product/product-admin-routing.module').then(x => x.ProductAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'address',
                            loadChildren: () => import('./view/address/address-admin-routing.module').then(x => x.AddressAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'delivery',
                            loadChildren: () => import('./view/delivery/delivery-admin-routing.module').then(x => x.DeliveryAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'customer',
                            loadChildren: () => import('./view/customer/customer-admin-routing.module').then(x => x.CustomerAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'order',
                            loadChildren: () => import('./view/order/order-admin-routing.module').then(x => x.OrderAdminRoutingModule),
                            canActivate: [AuthGuard],
                        },
                        {
                            path: 'security',
                            loadChildren: () => import('../security/security-routing.module').then(x => x.SecurityRoutingModule),
                            canActivate: [AuthGuard],
                        }
                    ]
                },
            ]
        ),
    ],
    exports: [RouterModule],
})
export class AdminRoutingModule { }
