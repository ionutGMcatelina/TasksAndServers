package Servers;

public class Task implements Comparable{
    private String name;
    private int arriveTime;
    private int period;

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

    public int compareTo(Object task) {
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
