package edu.ucr.rp.lab05.provider;

import java.util.Random;

//simula provedor de recargas
public class SMSProviderAPIImpl {
   /**
    * 
    * @param service numero telefonico
    * @param amount monto por recargar
    * @return true si es exitosa
    */
    //numero de telefono  y monto
    
    public boolean recharge(String service, Integer amount){
        boolean attempt = new Random().nextBoolean();
        if(attempt){
            System.out.printf("recarga exitosa al servicio %s de %d \n", service , amount);
        }///end if
        else{
            System.out.println("recarga fallida");
        }//end else
        return attempt;// esto es lo importante <--
    }
    
}
