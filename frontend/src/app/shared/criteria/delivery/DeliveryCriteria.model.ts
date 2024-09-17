import {AddressCriteria} from '../address/AddressCriteria.model';
import {OrderCriteria} from '../order/OrderCriteria.model';
import {DeliveryStatusCriteria} from './DeliveryStatusCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class DeliveryCriteria extends BaseCriteria {

    public id: number;
    public deliveryDate: Date;
    public deliveryDateFrom: Date;
    public deliveryDateTo: Date;
  public address: AddressCriteria ;
  public addresss: Array<AddressCriteria> ;

}
