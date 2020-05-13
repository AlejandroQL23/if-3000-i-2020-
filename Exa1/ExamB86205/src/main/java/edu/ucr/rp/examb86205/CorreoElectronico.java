package edu.ucr.rp.examb86205;

import java.util.ArrayList;
import java.util.List;

//esta clase gestiona los correos del cliente
public class CorreoElectronico extends Notificacion {

    private List<String> CorreosElectronico;

    //Creamos este constructor ya que sino lo hacemos la aplicacion da fallo
    public CorreoElectronico() {
        CorreosElectronico = new ArrayList<>();
    }//end constructor

    public List<String> addCorreo(String correoElectro) {//para que agregue cualquier cantidad de ...
        CorreosElectronico.add(correoElectro);
        return CorreosElectronico;
    }// end List

    @Override
    public String mensajePorMetodoDeContacto() {
        String salida = "Al CorreoElectronico(s): " + addCorreo("") + "\n";
        return salida;
    }//sobreEscribimos a como lo necesitemos

}//end correoElectronico
