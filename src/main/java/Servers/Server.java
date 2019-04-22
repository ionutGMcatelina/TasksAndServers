package Servers;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;                                 // Coada de task-uri
    private AtomicInteger waitingPeriod;                               // Perioada totala de asteptare
    private static boolean over = false;                               // Boolean folosit pentru a opri serverul
    private Thread thread = new Thread(this);                   // Thread pentru server

    public Server(AtomicInteger waitingPeriod){
        tasks = new ArrayBlockingQueue<>(20);
        this.waitingPeriod = waitingPeriod;
    }

    void addTask(Task newTask){                                         // Adauga un task in coada
        tasks.add(newTask);
        waitingPeriod.set(waitingPeriod.get() + newTask.getPeriod());
    }

    public void run() {
        try {
            while (!over) {                                             // Serverul merge cat timp over este fals
                if (tasks.size() != 0) {                                // Daca mai sunt task-uri in coada
                    int x = 0;                                          // Merg cu x pana la perioada primului element din coada
                    while (x < tasks.element().getPeriod()) {
                        sleep(1000);                              // Pun thread-ul pe sleep pentru o secunda
                        waitingPeriod.set(waitingPeriod.get() - 1);     // Decrementez timpul de asteptare al serverului
                        x++;
                    }
                    tasks.take();                                       // Sterg primul element din coada
                } else {
                    sleep(1000);                                  // Altfel pun serverul pe sleep pentru o secunda
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public String getStringTasks() {                                    // Returneaza numele tuturor task-urilor
        String s = "";
        for (Task task : tasks){
            s += task.getName() + " ";
        }
        return s;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public static void setOver(boolean over) {                          // Modifica over, pentru a opri serverele
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
