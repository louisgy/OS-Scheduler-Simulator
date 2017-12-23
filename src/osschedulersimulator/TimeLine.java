/*
 * Author : Georgy Louis
 */
package osschedulersimulator;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author glouis
 */
public class TimeLine {

    // index 0 completion 
    // index 1  wait
    private Map< Long, Long[]> cpuBurst;

    // index 0 first time startTime
    // index 1 last time resumeTime
    // index 2 last time suspendTime
    // index 3 time process endTime
    private Map<Long, LocalTime[]> timeTrack;

    LocalTime startPoint; // time the time line started
    // Constructor

    public TimeLine() {
        cpuBurst = new HashMap< Long, Long[]>();
        timeTrack = new HashMap< Long, LocalTime[]>();
        this.startPoint = null;
    }

    // Adding a thread for the first time -- Initialization
    public void initializeThreadBurst(Long id) {
        Long burst[] = {0L, 0L};
        this.cpuBurst.put(id, burst);
    }

    // Add time when thread is first created -- Initialization
    public void initializeThreadTime(Long id) {
        LocalTime time[] = {LocalTime.now(), LocalTime.MIN, LocalTime.MIN, LocalTime.MIN};
        this.timeTrack.put(id, time);
    }
  // FCFS , SJF
    public void setTimeLine(Long id, int action) {
        if (action == 1) {                               // FCFS first start
            LocalTime l = LocalTime.now();
            // update start time
            this.timeTrack.get(id)[0] = l;
            if (startPoint == null) {
                this.startPoint = l;
              
            }

        } else if (action == 2) {                        // When the thread ends (FCFS)
            // update time this thread ends
            this.timeTrack.get(id)[3] = LocalTime.now();
            // get time when threads start, resume,suspend and end
            LocalTime timetrack[] = this.timeTrack.get(id);
            // update completion time -- difference between the time the thread start and ends
            this.cpuBurst.get(id)[0] = Duration.between(timetrack[0], timetrack[3]).toMillis();
            // update waiting time 
            this.cpuBurst.get(id)[1] = Duration.between(this.startPoint, timetrack[0]).toMillis();

        }
    }
    
    // Round robin
    // set time line when thread first start
    public void setTimeLineFirstStart(Long id){
        LocalTime l = LocalTime.now();
            // update start time
            this.timeTrack.get(id)[0] = l;
            this.timeTrack.get(id)[1] = l;
            if (startPoint == null) 
                this.startPoint = l;
              
    }
    // Round Robin
    // Update time line as a thread is suspended
    // When a thread is suspended, the COMPLETION TIME is updated
    // Work for Round Robin
    public void updateSupendedThread(Long id) {
        LocalTime l = LocalTime.now();
        // register suspended time 
        this.timeTrack.get(id)[2] = l;  // update suspend time
        // get time when threads start, resume,suspend and end
        LocalTime timetrack[] = this.timeTrack.get(id);
        // update completion time --
        this.cpuBurst.get(id)[0] = this.cpuBurst.get(id)[0] + Duration.between(timetrack[1], timetrack[2]).toMillis();

    }
     // Round Robin
    // Update time line as thread resumes
    // When a thread is resumed, the WAITING TIME is updated 
    // Work for Round Robin
    public void updateResumedThread(Long id) {
        // waiting time = last time suspended (-) last time resume 
        this.timeTrack.get(id)[1]= LocalTime.now();
            this.cpuBurst.get(id)[1] = this.cpuBurst.get(id)[1] + Duration.between(this.timeTrack.get(id)[2], this.timeTrack.get(id)[1]).toMillis();
    }
    
    
    //Round Robin
    // Resume thread the first time
    public void updateResumeThreadStart(Long id){
         this.cpuBurst.get(id)[1] = this.cpuBurst.get(id)[1] + Duration.between(this.startPoint, this.timeTrack.get(id)[1]).toMillis();
 
    }

    public void displayData() {
        displayTimeTrack();
        displayBurst();
    }

    // display time thread start suspend resume and ends
    public void displayTimeTrack() {
        //iterating over records
        for (Long key : timeTrack.keySet()) {
            LocalTime[] value = timeTrack.get(key);
            System.out.println("Time Track: " + "Key = " + key + "  Value = " + Arrays.toString(value));
        }
    }

    // display completion and waiting Time
    public void displayBurst() {
        //iterating over records
        for (Long key : cpuBurst.keySet()) {
            Long[] value = cpuBurst.get(key);
            System.out.println(" Burst Time:" + "Key = " + key + "  Value = " + Arrays.toString(value));
        }
    }
    
        public Object[][] fillTable() {

        Object[][] data = new Object[cpuBurst.size()][6];
        // Map Size
        int index = 0;

        for (Long key : cpuBurst.keySet()) {
            Long[] time = cpuBurst.get(key);
            LocalTime[] res_time = timeTrack.get(key);
            data[index][0] = key; // process key identifier
            data[index][1] = time[0].toString(); // Completion Time
            data[index][2] = time[1].toString();  // Waiting Time
            data[index][3] = res_time[0].toString(); // start time
            data[index][4] = res_time[1].toString();  // Resume time
            data[index][5] = res_time[2].toString();  // suspend time

            index++;

        }
        return data;
    }

}
