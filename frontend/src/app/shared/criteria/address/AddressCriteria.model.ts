
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class AddressCriteria extends BaseCriteria {

    public id: number;
    public street: string;
    public streetLike: string;
    public number: string;
    public numberLike: string;
    public city: string;
    public cityLike: string;
    public postalCode: string;
    public postalCodeLike: string;
    public country: string;
    public countryLike: string;

}
