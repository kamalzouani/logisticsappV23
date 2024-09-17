
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class CityDto extends BaseDto{

    public name: string;

    public postalCode: string;



    constructor() {
        super();

        this.name = '';
        this.postalCode = '';

        }

}
