package Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class SimulationFrame  extends JFrame {
    private JPanel content = new JPanel();

    // The panel with servers
    private JTextField[] servers;
    private JTextArea status = new JTextArea("STATUS\n", 30, 30);
    private JTextArea statistics = new JTextArea("STATISTICS\n", 30, 30);

    private JButton start = new JButton("START");
    private JButton reset = new JButton("RESET");

    private JLabel title = new JLabel("Tasks distribution");
    private JLabel time = new JLabel("Time: ");
    private JLabel[] serversLabel;
    private JPanel statusLabel = new JPanel();

    // The panel for input
    private JTextField nrServers = new JTextField(15);
    private JTextField timeLimit = new JTextField(15);
    private JTextField maxProcessingTime = new JTextField(15);
    private JTextField minProcessingTime = new JTextField( 15);
    private JTextField numberOfTasks = new JTextField(15);

    SimulationFrame(){
        JLabel nrServersLabel = new JLabel("Number of servers: ");
        JLabel timeLimitLabel = new JLabel("Simulation time: ");
        JLabel maxProcessingTimeLabel = new JLabel("Maxim processing time: ");
        JLabel minProcessingTimeLabel = new JLabel("Minim processing time: ");
        JLabel numberOfTasksLabel = new JLabel("Number of tasks: ");

        JLabel title2 = new JLabel("Tasks distribution");
        JLabel input = new JLabel("Complete the fields");

        JPanel buttonsFrame = new JPanel();
        buttonsFrame.add(start);
        start.setBackground(new Color(173,216,230));
        buttonsFrame.add(reset);
        reset.setBackground(new Color(173,216,230));

        start.setFont(new Font( "Calibri", Font.PLAIN, 25));
        reset.setFont(new Font( "Calibri", Font.PLAIN, 25));

        JPanel inputContent = new JPanel();
        JPanel userContent = new JPanel();
        buttonsFrame.setOpaque(false);

        title2.setFont(new Font( "Calibri", Font.PLAIN, 30));
        title2.setOpaque(false);
        title2.setForeground(Color.LIGHT_GRAY);
        input.setFont(new Font( "Calibri", Font.PLAIN, 25));
        input.setForeground(Color.LIGHT_GRAY);

        nrServers.setBackground(new Color(173,216,230));
        nrServers.setFont(new Font( "Calibri", Font.PLAIN, 20));
        timeLimit.setBackground(new Color(173,216,230));
        timeLimit.setFont(new Font( "Calibri", Font.PLAIN, 20));
        maxProcessingTime.setBackground(new Color(173,216,230));
        maxProcessingTime.setFont(new Font( "Calibri", Font.PLAIN, 20));
        minProcessingTime.setBackground(new Color(173,216,230));
        minProcessingTime.setFont(new Font( "Calibri", Font.PLAIN, 20));
        numberOfTasks.setBackground(new Color(173,216,230));
        numberOfTasks.setFont(new Font( "Calibri", Font.PLAIN, 20));

        nrServersLabel.setForeground(Color.LIGHT_GRAY);
        nrServersLabel.setFont(new Font( "Calibri", Font.PLAIN, 25));
        timeLimitLabel.setForeground(Color.LIGHT_GRAY);
        timeLimitLabel.setFont(new Font( "Calibri", Font.PLAIN, 25));
        maxProcessingTimeLabel.setForeground(Color.LIGHT_GRAY);
        maxProcessingTimeLabel.setFont(new Font( "Calibri", Font.PLAIN, 25));
        minProcessingTimeLabel.setForeground(Color.LIGHT_GRAY);
        minProcessingTimeLabel.setFont(new Font( "Calibri", Font.PLAIN, 25));
        numberOfTasksLabel.setForeground(Color.LIGHT_GRAY);
        numberOfTasksLabel.setFont(new Font( "Calibri", Font.PLAIN, 25));

        inputContent.add(nrServersLabel);
        inputContent.add(nrServers);
        inputContent.add(maxProcessingTimeLabel);
        inputContent.add(maxProcessingTime);
        inputContent.add(minProcessingTimeLabel);
        inputContent.add(minProcessingTime);
        inputContent.add(numberOfTasksLabel);
        inputContent.add(numberOfTasks);
        inputContent.add(timeLimitLabel);
        inputContent.add(timeLimit);
        inputContent.setLayout(new GridLayout(5, 5));
        inputContent.setForeground(Color.LIGHT_GRAY);
        inputContent.setOpaque(false);

        userContent.add(Box.createRigidArea(new Dimension(0, 40)));
        userContent.add(title2);
        userContent.add(Box.createRigidArea(new Dimension(0, 20)));
        userContent.add(input);
        userContent.add(Box.createRigidArea(new Dimension(0, 40)));
        userContent.add(inputContent);
        userContent.add(Box.createRigidArea(new Dimension(0, 40)));
        userContent.add(buttonsFrame);
        userContent.setLayout(new BoxLayout(userContent, BoxLayout.Y_AXIS));
        userContent.setOpaque(false);

        this.setContentPane(userContent);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void setServersText(int i, String s, String nr){
        servers[i].setText(s);
        serversLabel[i].setText(nr);
    }

    void addStartButtonListener(ActionListener e){
        start.addActionListener(e);
    }

    void addResetButtonListener(ActionListener e){
        reset.addActionListener(e);
    }

    JLabel getTime() {
        return time;
    }

    void setStatusText(String statusText){ 
        status.setText(status.getText() + statusText);
    }

    void changeContentPane(){
        int nrS = Integer.parseInt(nrServers.getText());
        servers = new JTextField[nrS];
        serversLabel = new JLabel[nrS];

        for (int i = 0; i < nrS; i++){
            servers[i] = new JTextField(30);
            servers[i] = new JTextField(30);
            servers[i].setFont(new Font( "Calibri", Font.PLAIN, 20));
            servers[i].setBackground(new Color(173,216,230));
            servers[i].setEditable(false);

            serversLabel[i] = new JLabel("Server " + i);
            serversLabel[i].setFont(new Font( "Calibri", Font.PLAIN, 20));
            serversLabel[i].setForeground(Color.LIGHT_GRAY);
        }

        JScrollPane scrollPane = new JScrollPane(status);

        title.setFont(new Font( "Calibri", Font.PLAIN, 30));
        title.setOpaque(false);
        title.setForeground(Color.LIGHT_GRAY);
        time.setFont(new Font( "Calibri", Font.PLAIN, 25));
        time.setForeground(Color.LIGHT_GRAY);

        JPanel serversFrame = new JPanel();
        serversFrame.setOpaque(false);
        serversFrame.setForeground(Color.LIGHT_GRAY);

        status.setBackground(new Color(173,216,230));
        status.setEditable(false);
        status.setFont(new Font( "Calibri", Font.PLAIN, 15));
        statistics.setBackground(new Color(173,216,230));
        statistics.setEditable(false);
        statistics.setFont(new Font( "Calibri", Font.PLAIN, 15));
        statusLabel.setOpaque(false);
        statusLabel.setForeground(Color.LIGHT_GRAY);

        for (int i = 0; i < nrS; i++){
            serversFrame.add(serversLabel[i]);
            serversFrame.add(Box.createRigidArea(new Dimension(0, 20)));
            serversFrame.add(servers[i]);
            serversFrame.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        serversFrame.setLayout(new BoxLayout(serversFrame, BoxLayout.Y_AXIS));

        statusLabel.add(serversFrame);
        statusLabel.add(Box.createRigidArea(new Dimension(20, 0)));
        statusLabel.add(scrollPane);
        statusLabel.add(Box.createRigidArea(new Dimension(20, 0)));
        statusLabel.add(statistics);

        content.add(Box.createRigidArea(new Dimension(0, 20)));
        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 20)));
        content.add(time);
        content.add(Box.createRigidArea(new Dimension(0, 20)));
        content.add(statusLabel);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        this.setContentPane(content);
        this.revalidate();
    }

    JTextField getNrServers() {
        return nrServers;
    }

    JTextField getTimeLimit() {
        return timeLimit;
    }

    JTextField getMaxProcessingTime() {
        return maxProcessingTime;
    }

    JTextField getMinProcessingTime() {
        return minProcessingTime;
    }

    JTextField getNumberOfTasks() {
        return numberOfTasks;
    }

    void setStatisticsText(String statisticsText){                
        statistics.setText(statistics.getText() + statisticsText);
    }

    void showJOptionPane(String message){               
        JOptionPane.showMessageDialog(this,message);
    }

}
