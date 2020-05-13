package edu.ucr.rp.examb86205;

import java.util.ArrayList;
import java.util.List;

//clase encargada de gestionar los telefonos del cliente
public class Telefono extends Notificacion {

    private List<String> telefonos; // Abstraemos
    
    //Creamos este constructor ya que sino lo hacemos la aplicacion da fallo
    public Telefono(){
     telefonos = new ArrayList<>();
    }//end constructor
    
    public List<String> addTele(String Telefono) {//para que agregue cualquier cantidad de ...
        telefonos.add(Telefono);
        return telefonos;
    }// end List

    @Override
    public String mensajePorMetodoDeContacto() {
      String salida="Al Teléfono(s): "+ addTele("")+"\n";
      return salida;
    }//sobreEscribimos a como lo necesitemos

}//end telefono
