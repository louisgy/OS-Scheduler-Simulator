// Author : Georgy Louis
// May 22,2017
package osschedulersimulator;

public class Task {

    int action;
    Bound cpu;
    Bound io;

    public Task(Bound io, Bound cpu, int action) {
        this.io = io;
        this.cpu = cpu;
        this.action = action;
    }

    public void processorWork() {
        switch (action) {
            case 1:
                ioBoundWork();
                break;
            case 2:
                cpuBoundWork();
                break;
            case 3:
                cpuIoBoundWork();
                break;
            default:
                break;
        }
    }

    public void ioBoundWork() {
        io.work();
    }

    public void cpuIoBoundWork() {
        io.work();
        cpu.work();
    }

    public void cpuBoundWork() {
        cpu.work();
    }
}
