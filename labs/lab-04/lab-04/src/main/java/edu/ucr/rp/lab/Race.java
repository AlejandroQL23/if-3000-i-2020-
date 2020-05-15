package edu.ucr.rp.lab;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Race {

    private Racer racer1;
    private Racer racer2;
    private Racer racer3;

    public static void main(String[] args) {
        Race race = new Race();
        race.prepareRacer();
        race.starRace();
    }//end main

    public void prepareRacer() {

        racer1 = new Racer("La pulga");
        racer2 = new Racer("El Fenomeno");
        racer3 = new Racer("El Bicho");

    }//end prepareRacer

    public void starRace() {
        
      //  Thread oldRacer = new Thread(new Racer("O Rei"));
      //  oldRacer.start();

        ExecutorService threadExecutor
                = Executors.newFixedThreadPool(10);
        threadExecutor.execute(racer1);
        threadExecutor.execute(racer2);
        threadExecutor.execute(racer3);
        threadExecutor.execute(new Racer("el 4"));
        threadExecutor.execute(new Racer("el 5"));
        threadExecutor.execute(new Racer("el 6"));
        threadExecutor.shutdown();

    }//end starRace

}
