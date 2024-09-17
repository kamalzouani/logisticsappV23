import {CustomerDto} from '../customer/Customer.model';
import {OrderItemDto} from './OrderItem.model';
import {OrderStatusDto} from './OrderStatus.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class OrderDto extends BaseDto{

   public orderDate: Date;

    public orderStatus: OrderStatusDto ;
    public customer: CustomerDto ;
     public orderItems: Array<OrderItemDto>;


    constructor() {
        super();

        this.orderDate = null;
        this.orderItems = new Array<OrderItemDto>();

        }

}
