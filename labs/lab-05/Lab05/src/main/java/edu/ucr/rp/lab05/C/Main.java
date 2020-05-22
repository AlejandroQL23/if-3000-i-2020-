package edu.ucr.rp.lab05.C;

import edu.ucr.rp.lab05.service.RechargeSService;
import edu.ucr.rp.lab05.thread.ThreadPool;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        RechargeSService service = new RechargeSService();
        service.recharge("64588374", 1000);
        //System.out.println("Recarga exitosa !!!");
        
        ThreadPool.getPoll().submit(new Runnable(){
            @Override
            public void run(){
                RechargeSService.Status status;
                do{
                    status = service.getStatus("64588374");
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException ignored){
                        
                    }
                }while(status == RechargeSService.Status.IN_PROGGRESS);
                if(status == RechargeSService.Status.COMPLETE){
                    System.out.println("exitoso");
                }
                else{
                    System.out.println("fallida");
                }
                    
            }
        });
    }

}
