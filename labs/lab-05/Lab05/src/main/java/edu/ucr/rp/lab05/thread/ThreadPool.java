package edu.ucr.rp.lab05.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    private static ExecutorService service;

    //patron singelton
    public static ExecutorService getPoll() {
        if (service == null) {
            service = Executors.newCachedThreadPool();

        }
        return service;
    }

}
