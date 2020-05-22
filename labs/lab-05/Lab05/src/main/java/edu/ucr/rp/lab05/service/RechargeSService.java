package edu.ucr.rp.lab05.service;

import edu.ucr.rp.lab05.provider.SMSProviderAPIImpl;
import edu.ucr.rp.lab05.thread.ThreadPool;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RechargeSService {

    private SMSProviderAPIImpl provider = new SMSProviderAPIImpl();// TODO mejorar

    public enum Status {
        IN_PROGGRESS, COMPLETE, ERROR
    }
    private Map<String, Status> recharges = new HashMap<>();

    /**
     * respon hacer recarga si o si
     *
     * @param service
     * @param amount
     * @return servicio al cual se recarga
     */
    public String recharge(String service, Integer amount) {
        recharges.put(service, Status.IN_PROGGRESS);
        ExecutorService exService = ThreadPool.getPoll();
        exService.submit(() -> runRecharge(service, amount));
        return service;
    }
    
    public Status getStatus (String service){
        return recharges.get(service);
    }

    private void runRecharge(String service, Integer amount) {
        boolean success;
        int retries = 0;
        do {
            success = provider.recharge(service, amount);
            retries++;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RechargeSService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!success && retries < 10);
        recharges.put(service, success? Status.COMPLETE: Status.ERROR);
    }

}
