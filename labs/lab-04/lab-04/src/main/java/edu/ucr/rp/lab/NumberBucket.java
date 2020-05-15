package edu.ucr.rp.lab;

import java.util.ArrayList;
import java.util.List;

public class NumberBucket {

    private List<Integer> numbers;

    public NumberBucket() {
        numbers = new ArrayList<>();
    }

    public void add(Integer value) {// otro hilo
        synchronized(this){// otro pregunta si puede acceder?? monitor lock true
        numbers.add(value);// hilo en  progreso
        }//sale primero y pasa a false
        System.out.println("added " + value);
    }
    
    public void remove(int index){
        synchronized(this){ //semaforo-monitor-bandera
        numbers.remove(index);
        }
        
    }

    public int size() {
        return numbers.size();
    }
}
