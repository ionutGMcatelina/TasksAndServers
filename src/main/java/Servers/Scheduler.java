package Servers;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Server> servers;                              // Lista de servere
    private int goodServer = 0;                                     // Indexul serverului cu waitingTime minim
    private int min;

    public Scheduler() {
        servers = new ArrayList<>();
    }

    public void addServer(Server server){                           // Adauga un nou server in lista
        servers.add(server);
    }

    public void runServers(){                                       // Porneste serverele
        for (Server server: servers) {
            server.getThread().start();
        }
    }

    private void findGoodServer(){                                  // Cauta serverul cu waitingTime minim
        min = 1000000;
        for (Server server : servers){
            if (server.getWaitingPeriod().get() < min){
                min = server.getWaitingPeriod().get();
                goodServer = servers.indexOf(server);
            }
        }
    }

    public void dispatchTask(Task task){                            // Adauga un task la serverul de pe pozitia goodServer
        if (task != null) {
            if (servers.size() != 0) {
                findGoodServer();
                servers.get(goodServer).addTask(task);
            }
        }
    }

    public boolean freeServers(){                                   // Verifica daca toate serverele sunt goale
        for (Server server : servers){
            if (server.getTasks().size() != 0){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Server> getServers() {
        return servers;
    }       // Returneaza lista de servere
}
