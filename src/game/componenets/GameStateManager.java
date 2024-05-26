package game.componenets;

import frame.FrameManager;
import game.GameState;

public class GameStateManager {
    private GameState currentState;

    private FrameManager frameManager;

    public GameStateManager() {
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
