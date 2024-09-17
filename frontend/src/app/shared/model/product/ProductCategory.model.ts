
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class ProductCategoryDto extends BaseDto{

    public name: string;



    constructor() {
        super();

        this.name = '';

        }

}
