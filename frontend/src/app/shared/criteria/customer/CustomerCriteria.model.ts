import {AddressCriteria} from '../address/AddressCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class CustomerCriteria extends BaseCriteria {

    public firstName: string;
    public firstNameLike: string;
    public lastName: string;
    public lastNameLike: string;
    public phoneNumber: string;
    public phoneNumberLike: string;
    public credentialsNonExpired: null | boolean;
    public accountNonExpired: null | boolean;
    public username: string;
    public usernameLike: string;
    public passwordChanged: null | boolean;
    public accountNonLocked: null | boolean;
    public password: string;
    public passwordLike: string;
    public email: string;
    public emailLike: string;
    public enabled: null | boolean;
  public address: AddressCriteria ;
  public addresss: Array<AddressCriteria> ;

}
