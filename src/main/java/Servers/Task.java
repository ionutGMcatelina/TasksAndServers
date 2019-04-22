package Servers;

public class Task implements Comparable{
    private String name;                                        // Numele task-ului
    private int arriveTime;                                     // Timpul de sosire
    private int period;                                         // Timpul de procesare

    public Task(String name, int arriveTime, int period){
        this.name = name;
        this.arriveTime = arriveTime;
        this.period = period;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    int getPeriod() {
        return period;
    }

    public int compareTo(Object task) {                         // Metoda folosita de Collections.sort()
        return arriveTime - ((Task)task).arriveTime;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " arrive time: " + arriveTime + " period: " + period;
    }
}
