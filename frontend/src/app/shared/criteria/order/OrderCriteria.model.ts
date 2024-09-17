import {CustomerCriteria} from '../customer/CustomerCriteria.model';
import {OrderItemCriteria} from './OrderItemCriteria.model';
import {OrderStatusCriteria} from './OrderStatusCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class OrderCriteria extends BaseCriteria {

    public id: number;
    public orderDate: Date;
    public orderDateFrom: Date;
    public orderDateTo: Date;
      public orderItems: Array<OrderItemCriteria>;

}
