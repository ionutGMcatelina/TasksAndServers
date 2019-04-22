package Simulation;

import Servers.Scheduler;
import Servers.Server;
import Servers.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class SimulationManager implements Runnable{
    private int timeLimit;                                                          // Elementele necesare simularii
    private int maxProcessingTime;
    private int minProcessingTime;
    private int numberOfServers;
    private int numberOfTasks;
    private Thread t = new Thread(this);                                     // Thread-ul simularii

    private Scheduler scheduler;                                                    // Face legatura dintre servere si task-uri
    private SimulationFrame simulationFrame;                                        // Interfata grafica
    private ArrayList<Task> tasks;                                                  // Lista de task-uri

    private SimulationManager(){
        tasks = new ArrayList<>();
        scheduler = new Scheduler();

        simulationFrame = new SimulationFrame();
        simulationFrame.setSize(1450, 800);
        simulationFrame.setBackground(new Color(0,0,139));
        simulationFrame.setVisible(true);
        simulationFrame.addStartButtonListener(new AddStartActionListener());
        simulationFrame.addResetButtonListener(new AddResetActionListener());
    }

    private void generateNRandomTasks(){                                            // Genereaza lista de task-uri random
        int[] v = new int[timeLimit];                                               // Vector de pozitii
        for (int i = 0; i < numberOfTasks; i++){
            int arriveTime = (int)Math.abs(Math.random() * (timeLimit - 1) + 1);    // arriveTime trebuie sa fie diferit la fiecare task
            while (v[arriveTime] == 1){                                             // Cat timp mai am un numar egar, generez altul
                arriveTime = (int)Math.abs(Math.random() * (timeLimit - 1) + 1);
            }
            v[arriveTime] = 1;                                                      // Marchez elementul ca folosit
            int period = (int)Math.abs(Math.random() * (maxProcessingTime - minProcessingTime) + minProcessingTime); // Calculez random perioada intre minProcessingTime si maxProcessingTime
            tasks.add(new Task("Task" + (i + 1), arriveTime, period));        // Creez un nou task cu aceste numere
        }
        Collections.sort(tasks);                                                    // Sortez lista dupa arriveTime
    }

    public void run() {                                                             // Metoda run folosita de thread
        int currentTime = 0;
        int index = 0;
        int suma, sumaTotal = 0, sumaTasks, tasksTime = 0;                          // Elemente folosite pentru calcularea statisticilor
        int max = 0, maxTime = 0, maxTasks = 0;
        while (currentTime < timeLimit || scheduler.freeServers()) {                // Cat timp nu am depasit timeLimit sau inca mai sunt task-uri in servere
            suma = 0;
            sumaTasks = 0;
            currentTime++;
            simulationFrame.getTime().setText("Time: " + currentTime);
            if (index < tasks.size() && tasks.get(index).getArriveTime() == currentTime) {      // Daca primul task din lista are arriveTime egal cu timpul curent
                scheduler.dispatchTask(tasks.get(index));                                       // Il adaug in serverul potrivit
                tasks.remove(tasks.get(index));                                                 // Sterg elementul din lista
                index++;
            }
            simulationFrame.setStatusText("\nServere la timpul " + currentTime + "\n");         // Adaug in status din simulationFrame starea serverelor
            for (Server server : scheduler.getServers()) {
                simulationFrame.setStatusText(server + "\n");
            }

            for (int i = 0; i < numberOfServers; i++){                                          // Actualizez textField-urile din frame
                simulationFrame.setServersText(i, scheduler.getServers().get(i).getStringTasks() + "",
                        "Server " + (i + 1) + " - timp de asteptare: " + scheduler.getServers().get(i).getWaitingPeriod());
                suma += scheduler.getServers().get(i).getWaitingPeriod().get();
                sumaTasks += scheduler.getServers().get(i).getTasks().size();
                if (scheduler.getServers().get(i).getTasks().size() != 0)
                    sumaTotal += scheduler.getServers().get(i).getWaitingPeriod().get() / scheduler.getServers().get(i).getTasks().size();
            }
            if (max < suma){                                                        // Calculez perioada maxima de asteptare si momentul in care a fost acel timp
                max = suma;
                maxTime = currentTime;
            }
            if (maxTasks < sumaTasks){                                              // Calculez numarul maxim de taskuri si momentul in care au fost acestea
                maxTasks = sumaTasks;
                tasksTime = currentTime;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Server.setOver(true);                                                       // Opresc serverele
        int i = 0;
        simulationFrame.setStatusText("\nServere la timpul " + (currentTime + 1) + "\n");             // Actualizez statusul, serverele din frame si afisez statusul
        for (Server server : scheduler.getServers()) {
            simulationFrame.setServersText(i, scheduler.getServers().get(i).getStringTasks() + "",
                    "Server " + i + "; timp de asteptare: " + scheduler.getServers().get(i).getWaitingPeriod());
            simulationFrame.setStatusText(server + "\n");
            i++;
        }
        simulationFrame.setStatisticsText("\nOra de varf: " + maxTime + " cu perioada de asteptare " + max + "\n");
        simulationFrame.setStatisticsText("\nNumarul maxim de task-uri : " + maxTasks + " la timpul " + tasksTime + "\n");
        simulationFrame.setStatisticsText("\nTimpul mediu de asteptare: " + (sumaTotal / timeLimit) / numberOfServers);
    }

    class AddStartActionListener implements ActionListener {                        // Ascultator pentru butonul START
        public void actionPerformed(ActionEvent e) {
            try {                                                                   // Iau elementele necesare simularii din frame
                timeLimit = Integer.parseInt(simulationFrame.getTimeLimit().getText());
                maxProcessingTime = Integer.parseInt(simulationFrame.getMaxProcessingTime().getText());
                minProcessingTime = Integer.parseInt(simulationFrame.getMinProcessingTime().getText());
                numberOfServers = Integer.parseInt(simulationFrame.getNrServere().getText());
                numberOfTasks = Integer.parseInt(simulationFrame.getNumberOfTasks().getText());
            } catch (Exception exception){                                          // Daca nu au fost introduse numere, afisez un mesaj de eroare
                simulationFrame.showJOptionPane("Date invalide, verificati daca in toate campurile sunt doar numere!");
                return;
            }

            if (timeLimit <= numberOfTasks){                                        // Daca datele nu au fost introduse corect afisez un mesaj de eroare
                simulationFrame.showJOptionPane("Timpul simularii trebuie sa fie mai mare decat numarul de servere!");
                return;
            }
            if (maxProcessingTime < minProcessingTime){
                simulationFrame.showJOptionPane("Timpul maxim de procesare nu poate fi mai mic decat cel minim!");
                return;
            }

            simulationFrame.changeContentPane();                                    // Schimb panel-ul

            for (int i = 0; i < numberOfServers; i++)                               // Adaug in scheduler numarul de servere necesar
                scheduler.addServer(new Server(new AtomicInteger(0)));

            generateNRandomTasks();                                                 // Generez numberOfTasks task-uri

            scheduler.runServers();                                                 // Pornesc thread-urile
            t.start();
        }
    }

    class AddResetActionListener implements ActionListener {                        // Ascultator pentru butonul de RESET
        public void actionPerformed(ActionEvent e) {
            simulationFrame.getNrServere().setText("");                             // Golesc toate campurile din panoul principal
            simulationFrame.getTimeLimit().setText("");
            simulationFrame.getMaxProcessingTime().setText("");
            simulationFrame.getMinProcessingTime().setText("");
            simulationFrame.getNumberOfTasks().setText("");
        }
    }

    public static void main(String[] args) {
        new SimulationManager();
    }
}
