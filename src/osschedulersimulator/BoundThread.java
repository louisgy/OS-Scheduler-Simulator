/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osschedulersimulator;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author georgy
 */
public class BoundThread extends Thread {

    Task task;
    int threadNumber;
    boolean completionStatus = false;
    boolean StartedStatus = false;

    public void setHasStartedStatus(boolean hasStartedStatus) {
        this.StartedStatus = hasStartedStatus;
    }

    public BoundThread(Task task,int threadNumber) {
        this.task = task;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
             
        task.processorWork();
        // TO indicate that this thread has completed its action
        completionStatus = true;
    }

    public boolean isCompleted() {
        return completionStatus;
    }

    public boolean isStarted() {
        return StartedStatus;
    }

    public int getThreadNumber() {
        return threadNumber;
    }
    
    
    
    
}
