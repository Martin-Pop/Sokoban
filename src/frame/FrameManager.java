package frame;

import game.GameState;
import game.componenets.*;
import game.componenets.Timer;

public class FrameManager {

    private MainPanel mainPanel;

    private MainMenu mainMenu;
    private GameModeSelectionMenu gameModeSelectionMenu;
    private LevelSelectionMenu levelSelectionMenu;
    private GamePanel gamePanel;
    private Timer timer;
    private ControlPanel controlPanel;
    private ReturnToMenuPanel returnToMenuPanel;
    private InformationPanel informationPanel;

    private GameState lastGameState;

    public FrameManager(MainPanel mainPanel, MainMenu mainMenu, GameModeSelectionMenu gameModeSelectionMenu, LevelSelectionMenu levelSelectionMenu, GamePanel gamePanel, Timer timer, ControlPanel controlPanel, InformationPanel informationPanel, ReturnToMenuPanel returnToMenuPanel) {
        this.mainPanel = mainPanel;
        this.mainMenu = mainMenu;
        this.gameModeSelectionMenu = gameModeSelectionMenu;
        this.levelSelectionMenu = levelSelectionMenu;
        this.gamePanel = gamePanel;
        this.timer = timer;
        this.controlPanel = controlPanel;
        this.returnToMenuPanel = returnToMenuPanel;
        this.informationPanel = informationPanel;
    }

    public void update(GameState newGameState){
        System.out.println("last state :" + lastGameState);
        System.out.println("new state :" + newGameState);
        if (newGameState != lastGameState){
            switch (newGameState){
                case PLAYING, RESET_LEVEL, RUN_OUT_OF_TIME -> {
                    this.gameModeSelectionMenu.setVisible(false);
                    this.levelSelectionMenu.setVisible(false);
                    this.timer.setVisible(true);
                    this.controlPanel.setVisible(true);
                    this.informationPanel.setVisible(true);
                    this.returnToMenuPanel.setVisible(true);
                    this.gamePanel.setVisible(true);
                    this.gamePanel.requestFocus();

                    this.mainPanel.startThread();
                }
                case WINNER -> {
                    //win
                }
                case MAIN_MENU -> {
                    this.gamePanel.setVisible(false);
                    this.timer.setVisible(false);
                    this.controlPanel.setVisible(false);
                    this.informationPanel.setVisible(false);
                    this.returnToMenuPanel.setVisible(false);
                    this.levelSelectionMenu.setVisible(false);
                    this.mainMenu.setVisible(true);
                    this.mainMenu.requestFocus();
                    this.timer.stopTimer();
                }
                case LEVEL_CHOICE -> {
                    this.gameModeSelectionMenu.setVisible(false);
                    this.mainMenu.setVisible(false);
                    this.gamePanel.setVisible(false);
                    this.controlPanel.setVisible(false);
                    this.informationPanel.setVisible(false);
                    this.returnToMenuPanel.setVisible(true);
                    this.timer.setVisible(false);
                    this.levelSelectionMenu.setVisible(true);
                    this.levelSelectionMenu.requestFocus();
                }
                case GAME_MODE_CHOICE -> {
                    this.mainMenu.setVisible(false);
                    this.levelSelectionMenu.setVisible(false);
                    this.gamePanel.setVisible(false);
                    this.controlPanel.setVisible(false);
                    this.informationPanel.setVisible(false);
                    this.returnToMenuPanel.setVisible(false);
                    this.timer.setVisible(false);
                    this.gameModeSelectionMenu.resetOption();
                    this.gameModeSelectionMenu.setVisible(true);
                    this.gameModeSelectionMenu.requestFocus();

                    this.mainPanel.startThread();

                }
                default -> {
                    return;
                }
            }
            lastGameState = newGameState;
        }
    }
}
