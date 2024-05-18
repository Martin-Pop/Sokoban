package game.componenets;

import javax.swing.*;
import java.awt.*;

public class Timer extends JPanel implements Runnable{

    private JLabel label;
    private JLabel timeLabel;
    private Thread timerThread;

    private int timerTime;
    private boolean runOutOfTime = false;
    private boolean infiniteTime = false;
    private boolean stopTimer = false;

    public Timer() {
        initialize();
    }

    public void initialize(){
        setBounds(700, 50, 150, 100);
        setFocusable(true);
        setLayout(null);

        this.timerThread = new Thread(this);

        label = new JLabel("REMAINING TIME:");
        label.setBounds(0,0,150,50);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(new Color(227, 254, 247));
        label.setBackground(new Color(19, 93, 102));
        label.setOpaque(true);
        //label.setVisible(true);
        //label.setFont(new Font("Monospaced", Font.PLAIN, 15));

        timeLabel = new JLabel("0");
        timeLabel.setBounds(0,50,150,50);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setForeground(new Color(227, 254, 247));
        timeLabel.setBackground(new Color(19, 93, 102));
        timeLabel.setOpaque(true);
        //timeLabel.setVisible(true);

        add(label);
        add(timeLabel);
        setVisible(false);
    }

    public boolean runOutOfTime(){
        return runOutOfTime;
    }

    public void enableInfiniteTime(){
        this.infiniteTime = true;
    }

    public void setNewTime(int time) {
        this.timerTime = time;
        if (!timerThread.isAlive()){
            start();
        }
    }

    public void start(){
        if (!infiniteTime){
            this.timerThread.start();
        }else {
            timeLabel.setText("infinite");
        }
    }

    @Override
    public void run() {
        while (timerTime > 0 && !infiniteTime && !stopTimer){
            try {
                timerTime--;
                timeLabel.setText(String.valueOf(timerTime));
                System.out.println("running");
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        runOutOfTime = true;
    }
}
