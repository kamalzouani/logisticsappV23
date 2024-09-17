import {ProductCategoryCriteria} from './ProductCategoryCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class ProductCriteria extends BaseCriteria {

    public id: number;
    public name: string;
    public nameLike: string;
    public description: string;
    public descriptionLike: string;
     public price: number;
     public priceMin: number;
     public priceMax: number;
     public stock: number;
     public stockMin: number;
     public stockMax: number;

}
