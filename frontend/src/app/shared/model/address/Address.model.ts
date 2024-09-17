
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class AddressDto extends BaseDto{

    public street: string;

    public number: string;

    public city: string;

    public postalCode: string;

    public country: string;



    constructor() {
        super();

        this.street = '';
        this.number = '';
        this.city = '';
        this.postalCode = '';
        this.country = '';

        }

}
