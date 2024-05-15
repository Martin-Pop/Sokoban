package game.componenets;

import game.Direction;
import game.GameState;
import game.movement.KeyHandler;
import game.movement.Movement;
import game.movement.MovementStack;
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

    private int tileSize;

    private LevelManager levelManager = new LevelManager();
    private Level level;
    private GameMode gameMode;
    private GameState gameState;

    private Timer timer;

    public GamePanel(int width, int height, int tileSize, GameMode mode, Timer timer) {

        this.width = width;
        this.height = height;
        this.tileSize = tileSize;
        this.gameMode = mode;

        this.timer = timer;

       initialize();
    }

    private void initialize(){
        setBounds(50, 50, width, height);
        setBackground(Color.GRAY);
        setFocusable(true);

        addKeyListener(keyHandler);

        if (gameMode == GameMode.NORMAL){
            levelManager.setCurrentLevel(1);
            //gameState = GameState.PLAYING;
        }else {
            //TODO let player choose his level
        }

        restartLevel();
    }

    private void restartLevel(){
        System.out.println("RESTARTING");
        this.level = levelManager.getCurrentLevel();
        this.player = new Player(level.getPlayerSpawnX(), level.getPlayerSpawnY());

        timer.setNewTime(level.getTimeAmount());
    }

    Direction direction = Direction.NONE;
    Direction lastDirection;

    int boxMoved = 0; //if box was moved more tiles in one direction
    int grid = 50;
    int speed = 3; // speed is dynamic ()

    Box box; //current box

    public void updateGame() {
        //TODO update game based of game state
        int playerX = player.getPosX();
        int playerY = player.getPosY();

        //System.out.println("x:" + playerX + " y:" + playerY + " d: "+ direction);

        if (timer.runOutOfTime()){
            System.out.println("RUN OUT OF TIME");
            return;
        }

        if (direction == Direction.NONE && level.checkWin()){
            System.out.println("WINNER");
            levelManager.nextLevel();
            restartLevel();
            return;
        }

        if (keyHandler.reset) {
            if (!stack.isEmpty()) {
                System.out.println("RESET");
                Movement m = stack.pop();
                System.out.println(m);
                player.setPosX(m.getPlayerX());
                player.setPosY(m.getPlayerY());

                m.getBox().setPosX(m.getBoxX());
                m.getBox().setPosY(m.getBoxY());

                m.getBox().setCorrectPosition(level.getTileOnPosition(m.getBoxX(), m.getBoxY()).getTileType() == TileType.BOX_DESTINATION);

                keyHandler.reset = false;
                return;
            } else {
                System.out.println("NOO");
                keyHandler.reset = false;
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

                Tile nextTile = getNextTile(direction, playerX, playerY, false);

                if (nextTile.getTileType() != TileType.WALL) { // if next tile is not a wall
                    //System.out.println("getting box" + " " + playerX + " " + playerY);
                    box = getBox(direction, playerX, playerY, false);

                    if (box != null) { // if the next tile has a box
                        Tile tileBehindBox = getNextTile(direction, playerX, playerY, true);

                        if (tileBehindBox.getTileType() != TileType.WALL && getBox(direction, playerX, playerY, true) == null) { // if there is no wall or box behind the box

                            if (boxMoved == 0) {
                                stack.add(new Movement(box, box.getPosX(), box.getPosY(), direction));
                                //System.out.println(stack);
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

    public Box getBox(Direction d, int x, int y, Boolean checkSecondNextTile) {
        int multiplayer = 1;
        if (checkSecondNextTile) {
            multiplayer = 2;
        }
        switch (d) {
            case UP -> {
                return level.checkBoxOnPosition(x, y - (50 * multiplayer));
            }
            case DOWN -> {
                return level.checkBoxOnPosition(x, y + (50 * multiplayer));
            }
            case LEFT -> {
                return level.checkBoxOnPosition(x - (50 * multiplayer), y);
            }
            case RIGHT -> {
                return level.checkBoxOnPosition(x + (50 * multiplayer), y);
            }
            default -> {
                return null;
            }
        }
    }

    private Tile getNextTile(Direction d, int x, int y, Boolean checkSecondNextTile) {
        int multiplayer = 1;
        if (checkSecondNextTile) {
            multiplayer = 2;
        }
        switch (d) {
            case UP -> {
                return level.getTileOnPosition(x, y - (50 * multiplayer));
            }
            case DOWN -> {
                return level.getTileOnPosition(x, y + (50 * multiplayer));
            }
            case LEFT -> {
                return level.getTileOnPosition(x - (50 * multiplayer), y);
            }
            case RIGHT -> {
                return level.getTileOnPosition(x + (50 * multiplayer), y);
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        level.drawLevel(g2);

        player.drawPlayer(g2);
    }
}
