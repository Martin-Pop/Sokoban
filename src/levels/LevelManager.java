package levels;

import java.util.ArrayList;

public class LevelManager {
    private ArrayList<Level> levels = new ArrayList<>();
    private Level currentLevel;

    public LevelManager() {
        initializeLevels();
    }

    private void initializeLevels(){
        levels.add(new Level(1,1,"/levels/level_one.txt"));
        levels.add(new Level(2,1,"/levels/level_two.txt"));
    }

    public void nextLevel(){
        int current = this.currentLevel.getLevelNumber();
        for (Level l: levels) {
            if (l.getLevelNumber() == current+1){
                this.currentLevel = l;
                break;
            }
        }
    }

    public void setCurrentLevel(int levelNumber){
        for (Level l: levels) {
            if (l.getLevelNumber() == levelNumber){
                this.currentLevel = l;
                break;
            }
        }
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
