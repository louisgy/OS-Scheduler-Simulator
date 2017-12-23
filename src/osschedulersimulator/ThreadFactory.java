/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osschedulersimulator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author georgy
 */
public class ThreadFactory {

    Anagrams a;
    Fibonacci f;
    BoundThread b[];
    Task t;
    ArrayList <BoundThread> processList;
 

    public ThreadFactory() {
        processList = new ArrayList<BoundThread>();
    }

    public ArrayList createProcessList (int numb_process){
        for (int i = 0; i < numb_process; i++) {
            this.processList.add(new BoundThread(new Task(createAnagram(i), createFibonacci(i), 3), i));
   
        }
        return this.processList;
    }
    
      public Anagrams createAnagram(int i) {
        return (new Anagrams(Data.prefix[i], Data.word[i]));
    }

    public Fibonacci createFibonacci(int i) {
        return (new Fibonacci(Data.value[i]));
    }
    
     public ArrayList<BoundThread> getProcessList() {
        return processList;
    }
     
     public void removeFromList (int index){
         this.processList.remove(index);
     }

}
