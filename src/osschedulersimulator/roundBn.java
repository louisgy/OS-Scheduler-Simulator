/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osschedulersimulator;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author georgy
 */
public class roundBn {

    public BoundThread currentBoundThread;
    public BoundThread previousBoundThread;
    public BoundThread nextBoundThread;

    public void roll() {
        // Retrieve current Thread
        this.currentBoundThread = grabAThread(Data.indexOfRunningThread);
        // Retrieve Previous thread
        this.previousBoundThread = grabAThread(getPreviousThread(Data.currentListSize, Data.indexOfRunningThread));
        // Retrieve next  thread
        this.nextBoundThread = grabAThread(findNextThread(Data.currentListSize, Data.indexOfRunningThread));

        // Case where all threads start
        if (Data.indexOfRunningThread < Data.currentListSize && (!this.currentBoundThread.StartedStatus)) {

            // set currentThread startus as started
            this.currentBoundThread.setHasStartedStatus(true);

            // check if the previous has started and suspend it if true
            if (this.previousBoundThread.StartedStatus) {
              
                // Update Completion Time
                Data.timeline.updateSupendedThread(this.previousBoundThread.getId());
                // start the thread
                this.previousBoundThread.suspend();
            }

            // Initialize completion and waiting Time
            Data.timeline.initializeThreadBurst(this.currentBoundThread.getId());
            // Initialize start, resume, suspend and end time
            Data.timeline.initializeThreadTime(this.currentBoundThread.getId());
            // Register the time the  thread started
            Data.timeline.setTimeLineFirstStart(this.currentBoundThread.getId());

//            Data.timeline.displayData();
            // Update waiting time
            Data.timeline.updateResumeThreadStart(this.currentBoundThread.getId());
            //start current thread
            this.currentBoundThread.start();

            // render next thread current running thread
            Data.indexOfRunningThread = findNextThread(Data.currentListSize, Data.indexOfRunningThread);
         

        } else {    // Case where threads resume

            if (this.previousBoundThread.completionStatus) {
           
                // Remove completed thread
                Data.threadfactory.removeFromList(getPreviousThread(Data.currentListSize, Data.indexOfRunningThread));
                
                // since one thread is removed, the thread list size reduces by 1
                Data.currentListSize--;
                

                // 0 the timer won't have a chance to start again because no thread will will restart the timer
                // 1 it has a chance to start again because as a thread completes, it restart the timer
                // the thread restarts the timer because it does not need to left the cpu idle while it is doing nothing 
                if (Data.currentListSize == 1 || Data.currentListSize == 0) {
                    Data.timer.stop();
                   
                }
            }
            // update completion for previous thread since it is about to be suspended, 
            // and was the last one to resume or start
            Data.timeline.updateSupendedThread(this.previousBoundThread.getId());
            // suspend previous thread
            this.previousBoundThread.suspend();
            // update waiting time for next thread since it is about to resume
            Data.timeline.updateResumedThread(this.currentBoundThread.getId());
            // resume current thread
            this.currentBoundThread.resume();
            // render next thread current - it will resume and previous suspended
            Data.indexOfRunningThread = findNextThread(Data.currentListSize, Data.indexOfRunningThread);

        }
    }

    public BoundThread grabAThread(int index) {
        return Data.threadfactory.getProcessList().get(index);
    }

    private int getPreviousThread(int numbOfThread, int currentThreadIndex) {
        if (currentThreadIndex == 0) {
            return (numbOfThread - 1);
        } else if (currentThreadIndex == (numbOfThread)) {
            return 0;
        } else {
            return (currentThreadIndex - 1);
        }
    }

    public void showListElement() {
        int y = Data.threadfactory.getProcessList().size();
        ArrayList<BoundThread> processList = Data.threadfactory.getProcessList();
        for (int i = 0; i < y; i++) {
            System.out.println("Index :" + i + " ID:" + processList.get(i).getId());

        }
    }

    public int findNextThread(int numbOfThread, int currentThreadIndex) {
        if (currentThreadIndex < numbOfThread - 1) {
            return currentThreadIndex + 1;
        } else {
            return 0;
        }
    }

}
