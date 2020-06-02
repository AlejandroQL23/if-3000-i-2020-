package edu.ucr.rp.labhilomercado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Customer implements Runnable {

    private Super777 super777;
    private final Random random = new Random();
    private int numCustomer;
    private CarniceriaSuper777 carnitas = new CarniceriaSuper777(1);
    boolean carniceria = indicadorCarniceria();
    ArrayList listado = listaDeProductos(cantidadDeArticulos());

    public int getNumCustomer() {
        return numCustomer;
    }
    

    public Customer(Super777 super777, int numCustomer) {
        this.super777 = super777;
        this.numCustomer = numCustomer;
    }

    @Override
    public void run() {

        System.out.printf("Cliente %s parquea en Super777\n", numCustomer);
        sleep();
        System.out.printf("%s esta en la entrada\n", numCustomer);
        super777.gettingInSuperr777();
        System.out.printf("%s ya entro\n", numCustomer);
        sleep();
        System.out.println(numCustomer + " ocupa ir a la seccion de carniceria: " + carniceria);
        sleep();
        butch();
        sleep();
        System.out.println(numCustomer + " me llevare los productos: " + listado);
        super777.pasillosPorVisitar(listado);
          sleep();
        ///  super777.personaEnPasillo(2, numCustomer,super777.pasillosPorVisitar(listado));
        
        System.out.println(numCustomer + "puedo entrar???");
        super777.puedoPasarPasillo(this);
        sleep();
        System.out.println("Y tendre que esperar: " + esperaEnCaja(listado) + " segundos en la caja");
        sleep();
        System.out.println(numCustomer + " haciendo fila para cancelar productos adquiridos");
        super777.filaCajas(2, numCustomer);
        sleep();
        System.out.printf("%s sale de Super777 \n", numCustomer);
        super777.leavingSuper777();
    }//end run

    private void sleep() {
        try {
            Thread.sleep(new Random().nextInt(5000 - 2000 + 1) + 2000);
        }//end try
        catch (InterruptedException ignored) {
        }
    }//end sleep

    public int cantidadDeArticulos() {
        return (random.nextInt(60 - 5 + 1) + 5);
    }//end cantidadDeArticulos

    public ArrayList listaDeProductos(int cantidadDeArticulos) {
        ArrayList<Integer> listado = new ArrayList<Integer>();
        for (int i = 0; i < cantidadDeArticulos; i++) {
            listado.add(random.nextInt(120));
            Collections.sort(listado);
        }
        return listado;
    }//end ArrayList

    public int esperaEnCaja(ArrayList<Integer> listado) {
        int contador = 0;
        for (int i = 0; i < listado.size(); i++) {
            contador = i + 1;
        }
        return contador;
    }

    public boolean indicadorCarniceria() {
        return random.nextBoolean();
    }//si ocupa pasar o no a la carniceria

    public void butch() {
        if (carniceria == true) {

            System.out.println("Cliente  " + numCustomer + "  entró a la carnicería");
            System.out.println(numCustomer + " salió de la carnicería\n");
            carnitas.gettingInLineBucther();
            sleep();
            carnitas.leavingButcherShop();

        }//end if
        else {
            System.out.println(numCustomer + "  NO pasó por la carnicería");
        }// end else()
    }// end bucth()public void butch(){

}//end customer
