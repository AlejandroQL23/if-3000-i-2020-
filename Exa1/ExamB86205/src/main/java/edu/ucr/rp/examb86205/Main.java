package edu.ucr.rp.examb86205;

//Aljandro Quesada Leiva B86205
public class Main {

    public static void main(String[] args) {

        //Instancias de clase
        Cliente nuevoCliente = new Cliente();
        Notificacion mensaje = new Notificacion();
        Telefono tel = new Telefono();
        CorreoElectronico cE = new CorreoElectronico();
        DireccionDeCorreo dC = new DireccionDeCorreo();

        nuevoCliente.setNombre("Veggeta777");//definimos nombre del cliente

        String Tel1 = "88889999"; // creando y agregando los numeros de telefono
        String Tel2 = "11223344";
        String Tel3 = "55664433";
        tel.addTele(Tel1);
        tel.addTele(Tel2);
        tel.addTele(Tel3);

        //Ahora creamos y agregaremos los datos varios
        String datVar1 = "Costa Rica, San Isidro de El Guarco, Cartago, 30802, De la escuela, 100 al norte";
        String datVar2 = "Costa Rica, Guadalupe, Cartago, 38522, Del Super 99, 200 al este";
        String datVar3 = "Costa Rica, La Joya, Cartago, 347352, De la escuela, 100 al norte";
        dC.addDatos(datVar1);
        dC.addDatos(datVar2);
        dC.addDatos(datVar3);

        //por ultimo creamos y creamos multiples correos
        String correo1 = "jsmith@gmail.com";
        String correo2 = "smith@hotmail.com";
        String correo3 = "Smith@ucr.ac.cr.com";
        cE.addCorreo(correo1);
        cE.addCorreo(correo2);
        cE.addCorreo(correo3);
        
        //Salida final

        System.out.println("Enviando mensaje a: " + nuevoCliente.getNombre());
        System.out.println("*****************************************************************");

        System.out.println(mensaje.Notificacion());
        System.out.println(tel.mensajePorMetodoDeContacto());

        System.out.println("*****************************************************************");
        System.out.println("*****************************************************************");

        System.out.println(mensaje.Notificacion());
        System.out.println(dC.mensajePorMetodoDeContacto());

        System.out.println("*****************************************************************");
        System.out.println("*****************************************************************");

        System.out.println(mensaje.Notificacion());
        System.out.println(cE.mensajePorMetodoDeContacto());

    }//end main args

}// end Main
