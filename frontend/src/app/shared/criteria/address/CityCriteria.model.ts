
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class CityCriteria extends BaseCriteria {

    public id: number;
    public name: string;
    public nameLike: string;
    public postalCode: string;
    public postalCodeLike: string;

}
