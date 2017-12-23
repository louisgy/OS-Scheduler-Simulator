/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osschedulersimulator;

import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Source;

/**
 *
 * @author georgy
 */
public class Manager {

    public void launchThread() {

        // check if there are threads left to ru
        if (Data.indexOfRunningThread < Data.currentListSize) {
            BoundThread boundThread = Data.threadfactory.getProcessList().get(Data.indexOfRunningThread);
            timeManagement t1 = new timeManagement();

            Calling c = new Calling();

            try {
                t1.start();
                t1.join(0);
                boundThread.start();
                boundThread.join(0);
                c.start();
                c.join(0);

            } catch (InterruptedException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("task completed" + LocalTime.now());
        }
    }
}

class timeManagement extends Thread {

    @Override
    public void run() {
        // Get The running thread
        BoundThread boundThread = Data.threadfactory.getProcessList().get(Data.indexOfRunningThread);
        // Initialize completion and waiting Time
        Data.timeline.initializeThreadBurst(boundThread.getId());
        // Initialize start, resume, suspend and end time
        Data.timeline.initializeThreadTime(boundThread.getId());
        // Register the time the  thread started
        Data.timeline.setTimeLine(boundThread.getId(), 1);


    }

}

class Calling extends Thread {

    public void run() {
        // The running thread
        BoundThread boundThread = Data.threadfactory.getProcessList().get(Data.indexOfRunningThread);
        // update time when thread ends and update completion time
        Data.timeline.setTimeLine(boundThread.getId(), 2);
 
        // next thread
        Data.indexOfRunningThread++;
        Data.btnTrigger.doClick();

    }
}
