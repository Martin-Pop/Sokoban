package frame;

import game.GamePanel;
import game.KeyHandler;
import levels.Level;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements Runnable{


    Thread gameThread = new Thread(this);
    GamePanel gamePanel = new GamePanel(600,500,50,new Level(1,5,"/levels/level_one.txt"));

    public MainPanel(){

        setBounds(0,0,900,750);
        setBackground(Color.DARK_GRAY);
        setLayout(null);

        setVisible(true);
        addKeyListener(new KeyHandler(this));

       add(gamePanel);

       gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/60;
        double nextInterval = System.nanoTime() + drawInterval;

        while (gameThread != null){

            //System.out.println("updating");
            gamePanel.updateGame();
            //repaint();

            try {
                double remainingTime = nextInterval - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextInterval += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

       /* Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,400,100,100);*/
        //gamePanel.draw(g2);

    }
}
