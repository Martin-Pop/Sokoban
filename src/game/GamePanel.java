package game;

import game.movement.KeyHandler;
import game.movement.MovementStack;
import levels.Level;
import levels.TileType;
import levels.tiles.Tile;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    KeyHandler keyHandler = new KeyHandler(this);
    MovementStack stack = new MovementStack();
    private Player player;

    private int width;
    private int height;

    private int tileSize;

    private Level level;

    public GamePanel(int width, int height, int tileSize, Level level) {
        setBounds(50, 50, width, height);
        setBackground(Color.GRAY);
        addKeyListener(keyHandler);
        setFocusable(true);

        this.player = new Player(width, height);

        this.width = width;
        this.height = height;
        this.tileSize = tileSize;

        this.level = level;
        //level.loop();

        System.out.println(width);
        System.out.println(height);


    }

    Direction direction = Direction.NONE;
    Direction lastDirection;
    int grid = 50;
    int speed = 3; // speed is dynamic ()

    Box box; //current box
    public void updateGame() {

        int playerX = player.getPosX();
        int playerY = player.getPosY();

        System.out.println("x:" + playerX + " y:" + playerY + " d: "+ direction);
        //TODO if keyHandler gets signal to revert movement, reset direction and get the last move from stack
        if (playerX % 50 != 0 || playerY % 50 != 0){ // if player is still moving

            int remaining = calculateRemaining(lastDirection, playerX, playerY);
            System.out.println("remaining : "+ remaining);

            player.move(lastDirection, Math.min(speed, remaining));

            if (box != null){ // if box is being pushed
                box.move(lastDirection, Math.min(speed, remaining));
            }

        }else {
            direction = keyHandler.direction;
            if (direction != Direction.NONE){ // if player is moving
                Tile nextTile = getNextTile(direction, playerX, playerY, false);

                if (nextTile.getTileType() != TileType.WALL){ // if next tile is not a wall
                    System.out.println("getting box" + " " + playerX + " " + playerY);
                    box = getBox(direction,playerX,playerY, false);

                    if (box != null){ // if the next tile has a box
                        Tile tileBehindBox = getNextTile(direction, playerX , playerY , true);

                        if (tileBehindBox.getTileType() !=TileType.WALL && getBox(direction,playerX,playerY, true) == null){ // if there is no wall or box behind the box
                            //TODO here add last movement to the stack
                            box.move(direction,speed);
                            player.move(direction,speed);

                            box.setCorrectPosition(tileBehindBox.getTileType() == TileType.BOX_DESTINATION);
                        }

                    }else {
                        //TODO also here
                        player.move(direction,speed);
                    }

                    lastDirection = direction;
                }
            }

        }

        repaint();
    }

    private int calculateRemaining(Direction d, int playerX, int playerY){
        int rem;
        switch (d){
            case UP -> rem = playerY % 50;
            case DOWN ->  rem = 50 - (playerY % 50);
            case LEFT -> rem = playerX % 50;
            case RIGHT -> rem = 50 - (playerX % 50);
            default -> rem = 0;
        }
        return rem;
    }

    public Box getBox(Direction d, int x, int y, Boolean checkSecondNextTile){
        int multiplayer = 1;
        if (checkSecondNextTile){
            multiplayer = 2;
        }
        Box b;
        switch (d){
            case UP -> b = level.checkBoxOnPosition(x,y-(50*multiplayer));
            case DOWN ->  b = level.checkBoxOnPosition(x,y+(50*multiplayer));
            case LEFT -> b = level.checkBoxOnPosition(x-(50*multiplayer),y);
            case RIGHT -> b = level.checkBoxOnPosition(x+(50*multiplayer),y);
            default -> b = null;
        }
        return b;
    }

    private Tile getNextTile(Direction d, int x, int y, Boolean checkSecondNextTile){
        int multiplayer = 1;
        if (checkSecondNextTile){
            multiplayer = 2;
        }
        Tile tile;
        switch (d){
            case UP -> tile = level.getTileOnPosition(x,y-(50*multiplayer));
            case DOWN ->  tile = level.getTileOnPosition(x,y+(50*multiplayer));
            case LEFT -> tile = level.getTileOnPosition(x-(50*multiplayer),y);
            case RIGHT -> tile = level.getTileOnPosition(x+(50*multiplayer),y);
            default -> tile = null;
        }
        return tile;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        level.drawLevel(g2);

        player.drawPlayer(g2);
    }
}
