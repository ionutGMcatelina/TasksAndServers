package Servers;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Server> servers;
    private int goodServer = 0;                                     // The index of the server with minim waiting time

    public Scheduler() {
        servers = new ArrayList<>();
    }

    public void addServer(Server server){
        servers.add(server);
    }

    public void runServers(){
        for (Server server: servers) {
            server.getThread().start();
        }
    }

    private void findGoodServer(){                                  // Search the server with minim waiting time
        int min = 1000000;
        for (Server server : servers){
            if (server.getWaitingPeriod().get() < min){
                min = server.getWaitingPeriod().get();
                goodServer = servers.indexOf(server);
            }
        }
    }

    public void dispatchTask(Task task){
        if (task != null) {
            if (servers.size() != 0) {
                findGoodServer();
                servers.get(goodServer).addTask(task);
            }
        }
    }

    public boolean freeServers(){                                   // Check if all servers are empty
        for (Server server : servers){
            if (server.getTasks().size() != 0){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Server> getServers() {
        return servers;
    }
}
