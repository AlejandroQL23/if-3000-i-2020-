

package edu.ucr.rp.lab;

import java.util.List;
import java.util.Random;

class AddNumbers implements Runnable {
    private List<Integer> numbers;
    public AddNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
    @Override
    public void run() {
            for (int count=1000;count>0;count--) 
                numbers.add(new Random().nextInt(10));
    }
}//end addNUmbers