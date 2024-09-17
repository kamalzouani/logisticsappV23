import {OrderDto} from './Order.model';
import {ProductDto} from '../product/Product.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class OrderItemDto extends BaseDto{

    public quantity: null | number;

    public product: ProductDto ;
    public order: OrderDto ;


    constructor() {
        super();

        this.quantity = null;
        this.product = new ProductDto() ;
        this.order = new OrderDto() ;

        }

}
