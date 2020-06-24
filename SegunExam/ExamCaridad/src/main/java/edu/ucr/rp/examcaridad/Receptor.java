package edu.ucr.rp.examcaridad;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Receptor implements Runnable {

    private BlockingQueue<String> gift;
    private String Nombre;
    private String color;
    private Random random = new Random();

    /**
     * Constructor
     * @param gifts
     * @param nombre
     * @param color 
     */
    public Receptor(BlockingQueue<String> gifts, String nombre, String color) {
        this.gift = gifts;
        this.Nombre = nombre;
        this.color = color;
    }//end constructor

    /**
     * Sobre escritura del metodo run, en el cual se maneja el procesmiento de los paquetes
     */
    @Override
    public void run() {
        while (true) {
            try {
                String gifts = gift.take();
                System.out.printf("%s%s recibe paquete con %s regalo(s) , paquetes pendientes de procesar(%d)\n",
                       color, Nombre ,gifts, gift.size());
                Thread.sleep(random.nextInt(5000));
            } //end try //end try
            catch (InterruptedException e) {
                e.printStackTrace();
            }//end catch
        }//end while

    }//end run

}//end receptor


/**  Comportamiento
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