package Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationFrame  extends JFrame {
    private JPanel content = new JPanel();

    // panoul cu servere
    private JTextField[] servers;                                               // Cate un JTextField pentru fiecare server
    private JTextArea status = new JTextArea("STATUS\n", 30, 30);
    private JTextArea statistics = new JTextArea("STATISTICI\n", 30, 30);

    private JButton start = new JButton("START");
    private JButton reset = new JButton("RESET");

    private JLabel title = new JLabel("Simulare distribuire task-uri");
    private JLabel time = new JLabel("Time: ");                            // Timpul simularii
    private JLabel[] serversLabel;                                              // Cate un JLabel pentru fiecare server
    private JPanel statusLable = new JPanel();

    // panoul pentru introducerea datelor
    private JTextField nrServere = new JTextField(15);         // Pentru datele introduse de utilizator
    private JTextField timeLimit = new JTextField(15);
    private JTextField maxProcessingTime = new JTextField(15);
    private JTextField minProcessingTime = new JTextField( 15);
    private JTextField numberOfTasks = new JTextField(15);

    SimulationFrame(){                                                           // Se construieste panoul pentru introducerea datelor
        JLabel nrServereLabel = new JLabel("Numar servere: ");
        JLabel timeLimitLabel = new JLabel("Timpul simularii: ");
        JLabel maxProcessingTimeLabel = new JLabel("Timpul maxim de procesare: ");
        JLabel minProcessingTimeLabel = new JLabel("Timpul minim de procecsare: ");
        JLabel numberOfTasksLabel = new JLabel("Numar task-uri: ");

        JLabel title2 = new JLabel("Simulare distribuire task-uri");
        JLabel input = new JLabel("Introduceti datele");

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

        nrServere.setBackground(new Color(173,216,230));
        nrServere.setFont(new Font( "Calibri", Font.PLAIN, 20));
        timeLimit.setBackground(new Color(173,216,230));
        timeLimit.setFont(new Font( "Calibri", Font.PLAIN, 20));
        maxProcessingTime.setBackground(new Color(173,216,230));
        maxProcessingTime.setFont(new Font( "Calibri", Font.PLAIN, 20));
        minProcessingTime.setBackground(new Color(173,216,230));
        minProcessingTime.setFont(new Font( "Calibri", Font.PLAIN, 20));
        numberOfTasks.setBackground(new Color(173,216,230));
        numberOfTasks.setFont(new Font( "Calibri", Font.PLAIN, 20));

        nrServereLabel.setForeground(Color.LIGHT_GRAY);
        nrServereLabel.setFont(new Font( "Calibri", Font.PLAIN, 25));
        timeLimitLabel.setForeground(Color.LIGHT_GRAY);
        timeLimitLabel.setFont(new Font( "Calibri", Font.PLAIN, 25));
        maxProcessingTimeLabel.setForeground(Color.LIGHT_GRAY);
        maxProcessingTimeLabel.setFont(new Font( "Calibri", Font.PLAIN, 25));
        minProcessingTimeLabel.setForeground(Color.LIGHT_GRAY);
        minProcessingTimeLabel.setFont(new Font( "Calibri", Font.PLAIN, 25));
        numberOfTasksLabel.setForeground(Color.LIGHT_GRAY);
        numberOfTasksLabel.setFont(new Font( "Calibri", Font.PLAIN, 25));

        inputContent.add(nrServereLabel);
        inputContent.add(nrServere);
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

    void setServersText(int i, String s, String nr){                                // Actualizeaza campurile pentru servere
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

    void setStatusText(String statusText){                                          // Adauga text la status
        status.setText(status.getText() + statusText);
    }

    void changeContentPane(){                                                       // Creeaza panoul pentru simulare
        int nrS = Integer.parseInt(nrServere.getText());
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
        statusLable.setOpaque(false);
        statusLable.setForeground(Color.LIGHT_GRAY);

        for (int i = 0; i < nrS; i++){
            serversFrame.add(serversLabel[i]);
            serversFrame.add(Box.createRigidArea(new Dimension(0, 20)));
            serversFrame.add(servers[i]);
            serversFrame.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        serversFrame.setLayout(new BoxLayout(serversFrame, BoxLayout.Y_AXIS));

        statusLable.add(serversFrame);
        statusLable.add(Box.createRigidArea(new Dimension(20, 0)));
        statusLable.add(scrollPane);
        statusLable.add(Box.createRigidArea(new Dimension(20, 0)));
        statusLable.add(statistics);

        content.add(Box.createRigidArea(new Dimension(0, 20)));
        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 20)));
        content.add(time);
        content.add(Box.createRigidArea(new Dimension(0, 20)));
        content.add(statusLable);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        this.setContentPane(content);                                           // Seteaza acest panou ca principal
        this.revalidate();
    }

    JTextField getNrServere() {
        return nrServere;
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

    void setStatisticsText(String statisticsText){                              // Adauga text la statistici
        statistics.setText(statistics.getText() + statisticsText);
    }

    void showJOptionPane(String message){                                       // Deschide o fereastra de eroare cu mesajul trimis ca parametru
        JOptionPane.showMessageDialog(this,message);
    }

}
