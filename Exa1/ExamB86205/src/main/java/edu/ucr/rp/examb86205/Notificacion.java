package edu.ucr.rp.examb86205;
//esta clase sera la que herede a las otras clases, ademas si en un futuro se 
//necesitara cambiar el mensaje solo se tendria que hacer aqui

public class Notificacion {

    public String Notificacion() {
        String salida = "Enviando el mensaje 'Su producto está listo para retirar'";
        return salida;
    }//end notificacion

    //este metodo se sobreescribira en otros metodos a conveniencia
    public String mensajePorMetodoDeContacto() {
        String salida = "";
        return salida;
    }//end mensaje

}//end notificacion
