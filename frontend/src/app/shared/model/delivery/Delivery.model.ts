import {AddressDto} from '../address/Address.model';
import {OrderDto} from '../order/Order.model';
import {DeliveryStatusDto} from './DeliveryStatus.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class DeliveryDto extends BaseDto{

   public deliveryDate: Date;

    public address: AddressDto ;
    public deliveryStatus: DeliveryStatusDto ;
    public order: OrderDto ;


    constructor() {
        super();

        this.deliveryDate = null;
        this.address = new AddressDto() ;

        }

}
