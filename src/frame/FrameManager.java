package frame;

import game.GameState;
import game.componenets.*;
import game.componenets.Timer;

import javax.swing.*;

public class FrameManager {
    private MainMenu mainMenu;
    private GameModeSelectionMenu gameModeSelectionMenu;
    private GamePanel gamePanel;
    private Timer timer;
    private ControlPanel controlPanel;

    private GameState lastGameState;

    public FrameManager(MainMenu mainMenu, GameModeSelectionMenu gameModeSelectionMenu, GamePanel gamePanel, Timer timer, ControlPanel controlPanel) {
        this.mainMenu = mainMenu;
        this.gameModeSelectionMenu = gameModeSelectionMenu;
        this.gamePanel = gamePanel;
        this.timer = timer;
        this.controlPanel = controlPanel;
    }

    public void update(GameState newGameState){
        if (newGameState != lastGameState){
            System.out.println(newGameState);
            switch (newGameState){
                case PLAYING -> {
                    System.out.println("playin");
                    this.gameModeSelectionMenu.setVisible(false);
                    this.timer.setVisible(true);
                    this.controlPanel.setVisible(true);
                    this.gamePanel.setVisible(true);
                    this.gamePanel.requestFocus();
                }
                case WINNER -> {
                    //win
                }
                case MAIN_MENU -> {
                    System.out.println("mainmenu");
                    this.gamePanel.setVisible(false);
                    this.timer.setVisible(false);
                    this.controlPanel.setVisible(false);
                    this.mainMenu.setVisible(true);
                    this.mainMenu.requestFocus();
                }
                case LEVEL_CHOICE -> {
                    //level options
                }
                case GAME_MODE_CHOICE -> {
                    System.out.println("gamemode");
                    this.mainMenu.setVisible(false);
                    this.gamePanel.setVisible(false);
                    this.controlPanel.setVisible(false);
                    this.timer.setVisible(false);
                    this.gameModeSelectionMenu.setVisible(true);
                    this.gameModeSelectionMenu.requestFocus();
                }
                default -> {
                    return;
                }
            }
            lastGameState = newGameState;
        }
    }

}
