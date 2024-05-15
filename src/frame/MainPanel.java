package frame;

import game.GameMode;
import game.GameState;
import game.componenets.*;
import game.componenets.Timer;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements Runnable{


    Thread gameThread = new Thread(this);
    GamePanel gamePanel;
    GameStateManager gameStateManager = new GameStateManager();

    GameModeSelectionMenu gameModeSelectionMenu = new GameModeSelectionMenu();
    Timer gameTimer;
    GameMode gameMode;

    public MainPanel(){
        initialize();
    }

    public void initialize(){
        setBounds(0,0,900,750);
        setBackground(Color.DARK_GRAY);
        setLayout(null);

        add(gameModeSelectionMenu);
        gameModeSelectionMenu.setVisible(true);

        gameTimer = new Timer();
        add(gameTimer);
        add(new ControlPanel(gameStateManager));

        //add(new MainMenu());

        setVisible(true);
    }

    public void startGame(){
        gameStateManager.setCurrentState(GameState.MAIN_MENU);
        //TODO frame manager?

         while (gameModeSelectionMenu.getGameMode() == null){
            gameMode = gameModeSelectionMenu.getGameMode();
             System.out.println(gameMode);
        }
        gameModeSelectionMenu.setVisible(false);
        //remove(mainMenuPanel);

        gamePanel = new GamePanel(600,500,50, GameMode.NORMAL, gameTimer);
        add(gamePanel);


        gamePanel.requestFocus(); // very important
        gameThread.start();
    }

    @Override
    public void run() {
        double runInterval = 1000000000/60;
        double nextInterval = System.nanoTime() + runInterval;

        while (gameThread != null){

            gamePanel.updateGame();

            try {
                double remainingTime = nextInterval - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextInterval += runInterval;

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
