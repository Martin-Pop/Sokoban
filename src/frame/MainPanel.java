package frame;

import game.GameMode;
import game.GameState;
import game.componenets.*;
import game.componenets.Timer;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements Runnable{


    Thread gameThread = new Thread(this);
    GameStateManager gameStateManager;
    GameMode gameMode;

    GameModeSelectionMenu gameModeSelectionMenu;
    MainMenu mainMenu;
    Timer gameTimer;
    GamePanel gamePanel;
    ControlPanel controlPanel;
    ReturnToMenuPanel returnToMenuPanel;
    InformationPanel informationPanel;

    FrameManager frameManager;

    public MainPanel(){
        initialize();
    }

    public void initialize(){
        setBounds(0,0,900,750);
        setBackground(new Color(0, 60, 67));
        setLayout(null);

        gameModeSelectionMenu = new GameModeSelectionMenu();
        gameStateManager = new GameStateManager();
        mainMenu = new MainMenu(gameStateManager);
        controlPanel = new ControlPanel(gameStateManager);
        returnToMenuPanel = new ReturnToMenuPanel(gameStateManager);
        informationPanel = new InformationPanel(gameStateManager);
        gameTimer = new Timer();
        gamePanel = new GamePanel(600,500, gameTimer, gameStateManager, informationPanel);

        frameManager = new FrameManager(mainMenu,gameModeSelectionMenu, gamePanel, gameTimer, controlPanel, informationPanel, returnToMenuPanel);

        add(gameModeSelectionMenu);
        add(mainMenu);
        add(controlPanel);
        add(gameTimer);
        add(informationPanel);
        add(returnToMenuPanel);
        add(gamePanel);
        //gameModeSelectionMenu.setVisible(true);

        //add(new MainMenu());

        setVisible(true);

        startGame();
    }

    public void startGame(){
        gameStateManager.setCurrentState(GameState.MAIN_MENU);

        gameThread.start();

        //waitForGameModeSelection();

        //gameModeSelectionMenu.setVisible(false);
        //remove(mainMenuPanel);

        //gamePanel = new GamePanel(600,500,50, gameTimer);



        //gamePanel.requestFocus(); // very important
    }

    private void waitForGameModeSelection(){
        gameModeSelectionMenu.resetOption();
        while (gameModeSelectionMenu.getGameMode() == null){
            gameMode = gameModeSelectionMenu.getGameMode();
            System.out.println(gameMode);
            System.out.println("waitinmg");
        }
    }

    @Override
    public void run() {
        double runInterval = 1000000000/60;
        double nextInterval = System.nanoTime() + runInterval;

        System.out.println("state:" + gameStateManager.getCurrentState());

        while (gameThread != null){
            GameState state = gameStateManager.getCurrentState();
            //System.out.println(gameModeSelectionMenu.getGameMode());
            frameManager.update(state);
            switch (state){
                case GAME_MODE_CHOICE -> {
                    gameMode = gameModeSelectionMenu.getGameMode();
                    if (gameMode != null){
                        gamePanel.setGameMode(gameMode);
                        //gameStateManager.setCurrentState(GameState.PLAYING);
                    }
                }
                case RESET_LEVEL -> {
                    gamePanel.resetLevel();
                }
                case PLAYING -> {
                    gamePanel.updateGame();
                }
            }

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
