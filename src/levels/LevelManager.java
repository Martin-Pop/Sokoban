package levels;

import java.util.ArrayList;

public class LevelManager {
    private final ArrayList<Level> levels = new ArrayList<>();
    private Level currentLevel;

    public LevelManager() {
        initializeLevels();
    }

    private void initializeLevels(){
        levels.add(new Level("/levels/levelFiles/level_one.txt"));
        levels.add(new Level("/levels/levelFiles/level_two.txt"));
        levels.add(new Level("/levels/levelFiles/level_three.txt"));
        levels.add(new Level("/levels/levelFiles/level_four.txt"));
        levels.add(new Level("/levels/levelFiles/level_five.txt"));
        levels.add(new Level("/levels/levelFiles/level_six.txt"));
        levels.add(new Level("/levels/levelFiles/level_seven.txt"));
        levels.add(new Level("/levels/levelFiles/level_eight.txt"));
        levels.add(new Level("/levels/levelFiles/level_nine.txt"));
    }

    public int nextLevel(){
        int next = this.currentLevel.getLevelNumber()+1;
        if (next <= levels.size()){
            return next;
        }else {
            return 1;
            //no more levels available
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
