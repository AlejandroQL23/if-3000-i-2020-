package edu.ucr.rp.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class concurrentObject {

    public static void main(String[] args) throws InterruptedException {
        List numbers = new Vector<Integer>();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new AddNumbers(numbers));
        service.execute(new AddNumbers(numbers));
        //  while (true) {
        Thread.sleep(5000);
        System.out.println(numbers.size());
        //  }
        service.shutdown();
    }
}
