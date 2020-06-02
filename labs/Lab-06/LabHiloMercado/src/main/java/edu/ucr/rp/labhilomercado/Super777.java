package edu.ucr.rp.labhilomercado;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Super777 {

    private Semaphore semaphore;
    private Lock lock = new ReentrantLock();
    private Random random = new Random();

    public Super777(int peopleInside) {
        semaphore = new Semaphore(peopleInside);
    }//end constructor

    public void filaCajas(double cajasDisponibles, int custo) {
        semaphore = new Semaphore((int) cajasDisponibles);
        System.out.println(custo + "Cancelando los productos adquiridos");
    }//end constructor

    public void gettingInSuperr777() {//entrar
        try {
            semaphore.acquire();
        } catch (InterruptedException ex) {
        }

    }//end gettingInLine

    public void leavingSuper777() {//dejar
        semaphore.release();
    }//end leaving

    public int pasillosPorVisitar(ArrayList listado) {//estanteria de 0 a 3
        int estanteriaNumero = 0;
        for (int i = 0; i < listado.size(); i++) {
            int producto = (int) listado.get(i);
            estanteriaNumero = producto * 4 / 120;
            System.out.println("Para obtener el prducto: " + listado.get(i) + " Debe visitar el pasillo: " + estanteriaNumero);
        }//end for
        return estanteriaNumero;
    }//pasilloPorVisitar
    //Pasillo = # de producto * número de pasillo / Cantidad de productos 

    public void puedoPasarPasillo(Customer client) {
        try {
            lock.lock();
            System.out.printf("pase al pasillo %s\n", client.getNumCustomer());
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException ignored) {

        } finally {
            System.out.printf("sale del pasillo \n", client.getNumCustomer());
            lock.unlock();
        }
    }

}//end class Super777
