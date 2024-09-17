import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import {RoleService} from "../zynerator/security/shared/service/Role.service";
import {AppComponent} from "../app.component";
import {AuthService} from "../zynerator/security/shared/service/Auth.service";
import {Router} from "@angular/router";
import {AppLayoutComponent} from "./app.layout.component";

@Component({
  selector: 'app-menu',
  templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {
  model: any[];
  modelanonymous: any[];
    modelAdmin: any[];
  modelCustomer: any[];
constructor(public layoutService: LayoutService, public app: AppComponent, public appMain: AppLayoutComponent, private roleService: RoleService, private authService: AuthService, private router: Router) { }
  ngOnInit() {
    this.modelAdmin =
      [

				{
                    label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'Product Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste product',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/product/product/list']
								  },
								  {
									label: 'Liste product category',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/product/product-category/list']
								  },
						]
					  },
					  {
						label: 'Order Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste order',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/order/order/list']
								  },
								  {
									label: 'Liste order status',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/order/order-status/list']
								  },
								  {
									label: 'Liste order item',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/order/order-item/list']
								  },
						]
					  },
					  {
						label: 'Address Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste address',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/address/address/list']
								  },
								  {
									label: 'Liste city',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/address/city/list']
								  },
								  {
									label: 'Liste country',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/address/country/list']
								  },
						]
					  },
					  {
						label: 'Customer Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste customer',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/customer/customer/list']
								  },
						]
					  },
					  {
						label: 'Delivery Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste delivery',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/delivery/delivery/list']
								  },
								  {
									label: 'Liste delivery status',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/admin/delivery/delivery-status/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'pi pi-wallet',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];
    this.modelCustomer =
      [

				{
                    label: 'Pages',
                    icon: 'pi pi-fw pi-briefcase',
                    items: [
					  {
						label: 'Order Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste order',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/customer/order/order/list']
								  },
								  {
									label: 'Liste order item',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/customer/order/order-item/list']
								  },
						]
					  },
					  {
						label: 'Customer Management',
						icon: 'pi pi-wallet',
						items: [
								  {
									label: 'Liste customer',
									icon: 'pi pi-fw pi-plus-circle',
									routerLink: ['/app/customer/customer/customer/list']
								  },
						]
					  },

				   {
					  label: 'Security Management',
					  icon: 'pi pi-wallet',
					  items: [
						  {
							  label: 'List User',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/user/list']
						  },
						  {
							  label: 'List Model',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/model-permission/list']
						  },
						  {
							  label: 'List Action Permission',
							  icon: 'pi pi-fw pi-plus-circle',
							  routerLink: ['/app/admin/security/action-permission/list']
						  },
					  ]
				  }
			]
	    }
    ];

        if (this.authService.authenticated) {
            if (this.authService.authenticatedUser.roleUsers) {
              this.authService.authenticatedUser.roleUsers.forEach(role => {
                  const roleName: string = this.getRole(role.role.authority);
                  this.roleService._role.next(roleName.toUpperCase());
                  eval('this.model = this.model' + this.getRole(role.role.authority));
              })
            }
        }
  }

    getRole(text){
        const [role, ...rest] = text.split('_');
        return this.upperCaseFirstLetter(rest.join(''));
    }

    upperCaseFirstLetter(word: string) {
      if (!word) { return word; }
      return word[0].toUpperCase() + word.substr(1).toLowerCase();
    }

    onMenuClick(event) {
        this.appMain.onMenuClick(event);
    }
}
