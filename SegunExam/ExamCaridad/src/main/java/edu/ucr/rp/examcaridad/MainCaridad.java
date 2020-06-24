package edu.ucr.rp.examcaridad;

import static edu.ucr.rp.examcaridad.Output.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class MainCaridad {

    public static void main(String[] args) {

        BlockingQueue<String> letters = new LinkedBlockingQueue<>();
        ExecutorService service = Executors.newCachedThreadPool();

        // service.submit(new Recolector(letters, "Kylo", ANSI_PURPLE));//<2 seg
        //  service.submit(new Recolector(letters, "Kyla", ANSI_PURPLE));//<2 seg
        service.submit(new Recolector(letters, "Kyle", ANSI_PURPLE));//<2 seg
        service.submit(new Receptor(letters, "Beto", ANSI_CYAN));//<5 seg
        service.submit(new Receptor(letters, "Beta", ANSI_CYAN));//<5 seg
        //  service.submit(new Receptor(letters, "Beti", ANSI_CYAN));//<5 seg
        service.shutdown();

    }//end Main

}//end MainCaridad
