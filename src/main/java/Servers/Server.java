package Servers;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;   
    private AtomicInteger waitingPeriod;                               // Total waiting time
    private static boolean over = false;                               // Used to stop the servers
    private Thread thread = new Thread(this);                 

    public Server(AtomicInteger waitingPeriod){
        tasks = new ArrayBlockingQueue<>(20);
        this.waitingPeriod = waitingPeriod;
    }

    void addTask(Task newTask){                                         // It adds a task in the queue
        tasks.add(newTask);
        waitingPeriod.set(waitingPeriod.get() + newTask.getPeriod());
    }

    public void run() {
        try {
            while (!over) { 
                if (tasks.size() != 0) {       
                    int x = 0;                 
                    while (x < tasks.element().getPeriod()) {
                        sleep(1000);              
                        waitingPeriod.set(waitingPeriod.get() - 1);    
                        x++;
                    }
                    tasks.take();                             
                } else {
                    sleep(1000);                          
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public String getStringTasks() {              
        String s = "";
        for (Task task : tasks){
            s += task.getName() + " ";
        }
        return s;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public static void setOver(boolean over) {
        Server.over = over;
    }

    Thread getThread() {
        return thread;
    }

    @Override
    public String toString() {
        return tasks + " waiting period: " + waitingPeriod;
    }
}
