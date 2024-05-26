package levels;

import static org.junit.jupiter.api.Assertions.*;

class LevelManagerTest {

    @org.junit.jupiter.api.Test
    void nextLevel() {
        LevelManager levelManager = new LevelManager();
        levelManager.setCurrentLevel(10);
        assertEquals(0,levelManager.nextLevel());
    }
}