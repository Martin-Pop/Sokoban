package game.componenets;

import game.Direction;
import game.GameState;
import game.movement.KeyHandler;
import game.movement.Movement;
import game.movement.MovementStack;
import game.player.Player;
import levels.Level;
import levels.LevelManager;
import levels.TileType;
import levels.tiles.Tile;
import game.GameMode;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    KeyHandler keyHandler = new KeyHandler();
    MovementStack stack = new MovementStack();
    private Player player;

    private int width;
    private int height;

    private LevelManager levelManager = new LevelManager();
    private Level level;
    private GameMode gameMode;
    private GameStateManager gameStateManager;
    private InformationPanel informationPanel;

    private GameTimer gameTimer;

    public GamePanel(int width, int height, GameTimer gameTimer, GameStateManager gameStateManager, InformationPanel informationPanel) {

        this.width = width;
        this.height = height;
        this.gameStateManager = gameStateManager;

        this.gameTimer = gameTimer;
        this.informationPanel = informationPanel;

       initialize();
    }

    private void initialize(){
        setBounds(50, 50, width, height);
        setBackground(Color.GRAY);
        setFocusable(true);

        addKeyListener(keyHandler);
    }

    public void setUpLevel(int levelNumber){
        System.out.println("RESTARTING LEVEL");
        levelManager.setCurrentLevel(levelNumber);

        this.level = levelManager.getCurrentLevel();
        this.player = new Player(level.getPlayerSpawnX(), level.getPlayerSpawnY());
        resetLevel();
        //TODO maybe set the timer inside update
        gameTimer.startNewTimer(level.getTimeAmount(), gameMode == GameMode.FREE);
        System.out.println("just set timer");
        informationPanel.setLevelNumber(level.getLevelNumber());
        gameStateManager.setCurrentState(GameState.PLAYING);
        informationPanel.update();
    }


    public void resetLevel(){
        this.level.resetBoxes();
        this.player.resetPlayer();
        this.stack.clear();
        if (!gameTimer.runOutOfTime() || gameStateManager.getCurrentState() == GameState.WINNER){
            gameStateManager.setCurrentState(GameState.PLAYING);
        }else {
            System.out.println("CAN NO RESET LEVEL");
        }
        informationPanel.update();
    }


    private Direction direction = Direction.NONE;
    private Direction lastDirection = Direction.NONE;

    private int boxMoved = 0; //if box was moved more tiles in one direction
    private int speed = 3; // movement speed
    private Box box; //current box

    public void updateGame() {

        int playerX = player.getPosX();
        int playerY = player.getPosY();

        //System.out.println("x:" + playerX + " y:" + playerY + " d: "+ direction);

        if (gameTimer.runOutOfTime()){
            System.out.println("RUN OUT OF TIME");
            gameStateManager.setCurrentState(GameState.RUN_OUT_OF_TIME);
            informationPanel.update();
            return;
        }

        if ((direction == Direction.NONE && level.checkWin()) || gameStateManager.getCurrentState() == GameState.WINNER){
            System.out.println("WINNER");
            if (gameMode == GameMode.NORMAL){
                int next = levelManager.nextLevel();
                if (next !=0){ // if there still are levels
                    setUpLevel(levelManager.nextLevel());
                }else {
                    gameStateManager.setCurrentState(GameState.WINNER);
                    gameTimer.reset();
                }
            }else {
                gameStateManager.setCurrentState(GameState.WINNER);
            }
            informationPanel.update();
            return;
        }

        if (keyHandler.revertMovement) {
            if (!stack.isEmpty()) {

                Movement m = stack.pop();
                System.out.println(m);
                player.setPosX(m.getPlayerX());
                player.setPosY(m.getPlayerY());

                m.getBox().setPosX(m.getBoxX());
                m.getBox().setPosY(m.getBoxY());

                m.getBox().setCorrectPosition(level.getTileOnPosition(m.getBoxX(), m.getBoxY()).getTileType() == TileType.BOX_DESTINATION);

                keyHandler.revertMovement = false;
                return;
            } else {
                System.out.println("nothing in stack");
                keyHandler.revertMovement = false;
            }
        }

        if (playerX % 50 != 0 || playerY % 50 != 0) { // if player is still moving

            int remaining = calculateRemaining(lastDirection, playerX, playerY);
            //System.out.println("remaining : "+ remaining);

            player.move(lastDirection, Math.min(speed, remaining));

            if (box != null) { // if box is being pushed
                box.move(lastDirection, Math.min(speed, remaining));
            }
        } else {
            direction = keyHandler.direction;

            if (direction != Direction.NONE) { // if player is moving

                Tile nextTile = level.getNextTile(direction, playerX, playerY, false);

                if (nextTile.getTileType() != TileType.WALL) { // if next tile is not a wall
                    //System.out.println("getting box" + " " + playerX + " " + playerY);
                    box = level.getBox(direction, playerX, playerY, false);

                    if (box != null) { // if the next tile has a box
                        Tile tileBehindBox = level.getNextTile(direction, playerX, playerY, true);

                        if (tileBehindBox.getTileType() != TileType.WALL && level.getBox(direction, playerX, playerY, true) == null) { // if there is no wall or box behind the box

                            if (boxMoved == 0) {
                                stack.add(new Movement(box, box.getPosX(), box.getPosY(), direction));
                            }

                            box.move(direction, speed);
                            player.move(direction, speed);

                            boxMoved++;
                            box.setCorrectPosition(tileBehindBox.getTileType() == TileType.BOX_DESTINATION);
                        }
                    } else {
                        player.move(direction, speed);
                    }
                    lastDirection = direction;
                }
            } else {
                boxMoved = 0;
            }

        }
        //System.out.println("above repaint");
        repaint();
    }

    private int calculateRemaining(Direction d, int playerX, int playerY) {
        switch (d) {
            case UP -> {
                return playerY % 50;
            }
            case DOWN -> {
                return 50 - (playerY % 50);
            }
            case LEFT -> {
                return playerX % 50;
            }
            case RIGHT -> {
                return 50 - (playerX % 50);
            }
            default -> {
                return 0;
            }
        }
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
        if (gameMode == GameMode.FREE){
            gameStateManager.setCurrentState(GameState.LEVEL_CHOICE);
        }else {
            setUpLevel(1);
        }

        //
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        level.drawLevel(g2);
        player.drawPlayer(g2, direction, lastDirection, box != null);
    }
}
