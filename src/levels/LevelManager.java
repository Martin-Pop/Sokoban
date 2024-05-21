package levels;

import java.util.ArrayList;

public class LevelManager {
    private final ArrayList<Level> levels = new ArrayList<>();
    private Level currentLevel;

    public LevelManager() {
        initializeLevels();
    }

    private void initializeLevels(){
        levels.add(new Level(1,20,"/levels/level_one.txt"));
        levels.add(new Level(2,1,"/levels/level_two.txt"));
    }

    public int nextLevel(){
        int next = this.currentLevel.getLevelNumber()+1;
        if (next <= levels.size()){
            return next;
        }else {
            return 1;
            //no more levels available
        }

        /*int current = this.currentLevel.getLevelNumber();
        for (Level l: levels) {
            if (l.getLevelNumber() == current+1){
                this.currentLevel = l;
                break;
            }
        }*/
    }

    public void resetAllLevels(){ //can be simplified
        for (Level level: levels) {
            level.resetBoxes();
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
