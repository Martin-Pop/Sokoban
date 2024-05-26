package game.componenets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTimerTest {

    @Test
    void runOutOfTime() {
        GameTimer gameTimer = new GameTimer();
        gameTimer.startNewTimer(0,true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertFalse(gameTimer.runOutOfTime());
    }
}