package edu.ucr.rp.examb86205;

import java.util.ArrayList;
import java.util.List;
//clase que gestiona los datosVarios asociados al cliente
public class DireccionDeCorreo extends Notificacion {

    private List<String> datosVarios;

    //Creamos este constructor ya que sino lo hacemos la aplicacion da fallo
    public DireccionDeCorreo() {
        datosVarios = new ArrayList<>();
    }//end constructor

    public List<String> addDatos(String datVarios) {//para que agregue cualquier cantidad de ...
        datosVarios.add(datVarios);
        return datosVarios;
    }// end List

    @Override
    public String mensajePorMetodoDeContacto() {
        String salida = "Al Correo(s): " + addDatos("") + "\n";
        return salida;
    }//sobreEscribimos a como lo necesitemos

}//end dirreccion de correo
