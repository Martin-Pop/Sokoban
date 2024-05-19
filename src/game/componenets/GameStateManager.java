package game.componenets;

import frame.MainPanel;
import game.GameState;

public class GameStateManager {
    private GameState currentState;

    private MainPanel mainPanel;

    public GameStateManager(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
        System.out.println("in game StateManager");
        mainPanel.stateChanged(this.currentState);
    }
}
