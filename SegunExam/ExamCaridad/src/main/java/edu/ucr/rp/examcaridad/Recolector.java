package edu.ucr.rp.examcaridad;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Recolector implements Runnable {

    private BlockingQueue<String> gitf;
    private String Nombre;
    private int count;
    private String color;
    private Random random = new Random();

    /**
     * Constructor de la clase
     * @param gifts
     * @param nombre
     * @param color 
     */
    public Recolector(BlockingQueue<String> gifts, String nombre, String color) {
        this.gitf = gifts;
        this.Nombre = nombre;
        this.color = color;
    }//end constructor 

    /**
     * Sobre escritura del metodo run, el en cual se maneja la recoleccion de regalos.
     */
    @Override
    public void run() {
        while (true) {
            count = 1+random.nextInt(5);
            String gift = count + "";
            System.out.printf(" %s%s recolecta paquete con (%d) regalo(s) \n", color, Nombre, count);
            gitf.add(gift);
            try {
                Thread.sleep(random.nextInt(2000));
            } //end try
            catch (InterruptedException e) {
                e.printStackTrace();
            }//end catch

        }//end while
    }//end run
}//end Recolector


/** Comportamiento
 * Kyle recolecta paquete con 1 regalo(s)
Beto recibe paquete con 1 regalos. Paquetes pendientes de procesar(0)
Kyle recolecta paquete con 3 regalo(s)
Beto recibe paquete con 3 regalos. Paquetes pendientes de procesar(0)
Kyle recolecta paquete con 3 regalo(s)
Beto recibe paquete con 3 regalos. Paquetes pendientes de procesar(0)
Kyle recolecta paquete con 1 regalo(s)
Kyle recolecta paquete con 2 regalo(s)
Kyle recolecta paquete con 2 regalo(s)
Beto recibe paquete con 1 regalos. Paquetes pendientes de procesar(2)
Kyle recolecta paquete con 5 regalo(s)
Kyle recolecta paquete con 2 regalo(s)
Kyle recolecta paquete con 5 regalo(s)
Beto recibe paquete con 2 regalos. Paquetes pendientes de procesar(4)
Kyle recolecta paquete con 1 regalo(s)
Kyle recolecta paquete con 3 regalo(s)
Beto recibe paquete con 2 regalos. Paquetes pendientes de procesar(5)
Kyle recolecta paquete con 2 regalo(s)
Kyle recolecta paquete con 3 regalo(s)
Kyle recolecta paquete con 4 regalo(s)
Kyle recolecta paquete con 1 regalo(s)
Kyle recolecta paquete con 2 regalo(s)
Kyle recolecta paquete con 2 regalo(s)
Beto recibe paquete con 5 regalos. Paquetes pendientes de procesar(10)
 */
