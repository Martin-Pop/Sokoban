package game.componenets;

import frame.FrameManager;
import frame.MainPanel;
import game.GameState;

public class GameStateManager {
    private GameState currentState;

    private MainPanel mainPanel;
    private FrameManager frameManager;

    public GameStateManager(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void setFrameManager(FrameManager frameManager) {
        this.frameManager = frameManager;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
        this.frameManager.update(currentState);
    }
}
