import {AddressDto} from '../address/Address.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class CustomerDto extends BaseDto{

    public firstName: string;

    public lastName: string;

    public phoneNumber: string;

   public credentialsNonExpired: null | boolean;

   public accountNonExpired: null | boolean;

    public username: string;

   public passwordChanged: null | boolean;

   public accountNonLocked: null | boolean;

    public password: string;

    public email: string;

   public enabled: null | boolean;

    public address: AddressDto ;


    constructor() {
        super();

        this.firstName = '';
        this.lastName = '';
        this.phoneNumber = '';
        this.credentialsNonExpired = null;
        this.accountNonExpired = null;
        this.username = '';
        this.passwordChanged = null;
        this.accountNonLocked = null;
        this.password = '';
        this.email = '';
        this.enabled = null;
        this.address = new AddressDto() ;

        }

}
