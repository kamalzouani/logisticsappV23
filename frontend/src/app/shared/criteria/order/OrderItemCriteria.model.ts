import {OrderCriteria} from './OrderCriteria.model';
import {ProductCriteria} from '../product/ProductCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class OrderItemCriteria extends BaseCriteria {

    public id: number;
     public quantity: number;
     public quantityMin: number;
     public quantityMax: number;
  public product: ProductCriteria ;
  public products: Array<ProductCriteria> ;
  public order: OrderCriteria ;
  public orders: Array<OrderCriteria> ;

}
