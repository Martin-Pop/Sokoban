package game.componenets;

import game.GameState;

public class GameStateManager {
    private GameState currentState;

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }
}
