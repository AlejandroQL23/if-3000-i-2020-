package edu.ucr.rp.labhilomercado;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static int numCusto;
    static Random random = new Random();

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        Super777 super777 = new Super777(3);
        while (true) {
            numCusto++;
            service.submit(new Customer(super777, numCusto));
            try {
                Thread.sleep(random.nextInt(2000));
            }//end try
            catch (InterruptedException e) {
                e.printStackTrace();
            }//end catch       
        }//end whileTrue
    }//end main args ...

}//end class Mian
