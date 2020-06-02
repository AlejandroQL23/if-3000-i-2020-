package edu.ucr.rp.labhilomercado;

import java.util.concurrent.Semaphore;

public class CarniceriaSuper777 {

   private Semaphore semaphoreB;
    
    public CarniceriaSuper777(int availableSlots) {
        semaphoreB = new Semaphore(availableSlots);
    }

    public void gettingInLineBucther() {
        try {
            semaphoreB.acquire();
        } catch (InterruptedException ignored) {
        }
    }//end getting

    public void leavingButcherShop() {
        semaphoreB.release();
    }//end leavin


}//end CarniceriaSuper777
