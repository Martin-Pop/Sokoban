package frame;

import game.GameMode;
import game.GameState;
import game.componenets.*;
import game.componenets.Timer;

import javax.swing.*;
import java.awt.*;
import java.lang.management.ThreadInfo;

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
    LevelSelectionMenu levelSelectionMenu;

    FrameManager frameManager;

    public MainPanel(){
        initialize();
    }

    public void initialize(){
        setBounds(0,0,900,750);
        setBackground(new Color(0, 60, 67));
        setLayout(null);

        gameModeSelectionMenu = new GameModeSelectionMenu();
        levelSelectionMenu = new LevelSelectionMenu(10);
        gameStateManager = new GameStateManager(this);
        mainMenu = new MainMenu(gameStateManager);
        controlPanel = new ControlPanel(gameStateManager);
        returnToMenuPanel = new ReturnToMenuPanel(gameStateManager);
        informationPanel = new InformationPanel(gameStateManager);
        gameTimer = new Timer();
        gamePanel = new GamePanel(600,500, gameTimer, gameStateManager, informationPanel);

        frameManager = new FrameManager(this,mainMenu,gameModeSelectionMenu, levelSelectionMenu, gamePanel, gameTimer, controlPanel, informationPanel, returnToMenuPanel);
        gameStateManager.setFrameManager(frameManager);

        add(gameModeSelectionMenu);
        add(mainMenu);
        add(controlPanel);
        add(gameTimer);
        add(informationPanel);
        add(returnToMenuPanel);
        add(levelSelectionMenu);

        add(gamePanel);
        //gameModeSelectionMenu.setVisible(true);

        //add(new MainMenu());

        setVisible(true);

        startGame();
    }

    public void startGame(){
        gameStateManager.setCurrentState(GameState.MAIN_MENU);
        gameThread = new Thread(this);
        gameThread.start();

    }

    /*public void startThread(){
        System.out.println(gameThread.getState());
        if (!gameThread.isAlive()){
            gameThread = new Thread(this);
            gameThread.start();
        }
    }*/

    @Override
    public void run() {
        double runInterval = 1000000000/60;
        double nextInterval = System.nanoTime() + runInterval;

        System.out.println("running thread id: "+gameThread.getId());
        System.out.println("state:" + gameStateManager.getCurrentState());
        GameState state = gameStateManager.getCurrentState();
        while (gameThread != null){
            //System.out.println(gameModeSelectionMenu.getGameMode());
            // state == GameState.PLAYING || state == GameState.RESET_LEVEL || state == GameState.GAME_MODE_CHOICE|| state == GameState.LEVEL_CHOICE
            //frameManager.update(state);

            switch (state){
                case GAME_MODE_CHOICE -> {
                    //System.out.println("HERE");
                    gameMode = gameModeSelectionMenu.getGameMode();
                    if (gameMode != null){
                        gamePanel.setGameMode(gameMode);
                    }
                }
                case RESET_LEVEL -> {
                    gamePanel.resetLevel();
                }
                case PLAYING -> {
                    gamePanel.updateGame();
                }
                case LEVEL_CHOICE -> {
                    if (levelSelectionMenu.getSelectedLevel() > 0) {
                        gamePanel.setUpLevel(levelSelectionMenu.getSelectedLevel());
                        gameStateManager.setCurrentState(GameState.PLAYING);
                    }
                }case MAIN_MENU -> {
                    gameModeSelectionMenu.resetOption();
                    levelSelectionMenu.setSelectedLevel(0);
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
            state = gameStateManager.getCurrentState();
        }
        System.out.println("THREAD IS ENDING");
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
