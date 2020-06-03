package edu.ucr.rp.lab6.sockets.cliente;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            Client.enviarMensaje("127.0.0.1", 5454,"Puedo comer pizza??");
        });
        executorService.submit(() -> {
            Client.enviarMensaje("127.0.0.1", 5454, "Puedo salir al chino??");
        });
    }
}
