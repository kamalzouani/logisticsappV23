package  ma.zyn.app.ws.dto.delivery;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryStatusDto  extends AuditBaseDto {

    private String code  ;
    private String label  ;
    private String style  ;




    public DeliveryStatusDto(){
        super();
    }




    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }


    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }








}
