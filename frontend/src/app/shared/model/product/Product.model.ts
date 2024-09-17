import {ProductCategoryDto} from './ProductCategory.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class ProductDto extends BaseDto{

    public name: string;

    public description: string;

    public price: null | number;

    public stock: null | number;

    public productCategory: ProductCategoryDto ;


    constructor() {
        super();

        this.name = '';
        this.description = '';
        this.price = null;
        this.stock = null;

        }

}
