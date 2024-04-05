package game;

import levels.Level;
import levels.TileType;
import levels.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    KeyHandler keyHandler = new KeyHandler(this);
    private Player player;

    private int width;
    private int height;

    private int tileSize;

    private Level level;
    private ArrayList<Box> boxes;


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
        this.boxes = level.getBoxes();
        //level.loop();

        System.out.println(width);
        System.out.println(height);


    }

    Direction direction = Direction.NONE;
    Direction lastDirection;
    int grid = 50;
    int speed = 5; // must divide 50 evenly (grid)
    //GRID WALK ON TOP
    Box box;
    public void updateGame() {

        int playerX = player.getPosX();
        int playerY = player.getPosY();

        System.out.println("x:" + playerX + " y:" + playerY + " d: "+ direction);

        if (playerX % 50 != 0 || playerY % 50 != 0){ // if player is still moving
            player.move(lastDirection, speed);
            if (box != null){ // if box is being pushed
                box.move(lastDirection,speed);
            }
        }else {
            System.out.println("NEW DIRECTION");
            direction = keyHandler.direction;
            if (direction != Direction.NONE){ // if player is moving
                Tile nextTile = getNextTile(direction, playerX, playerY, false);
                if (nextTile.getTileType() != TileType.WALL){ // if next tile is not a wall
                    System.out.println("getting box" + " " + playerX + " " + playerY);
                    box = getCurrentBox(direction,playerX,playerY);
                    System.out.println(box);
                    if (box != null){ // if the next tile has a box
                        Tile tileBehindBox = getNextTile(direction, playerX , playerY , true);
                        if (tileBehindBox.getTileType() !=TileType.WALL){ // if there is no wall behind the box
                            box.move(direction,speed);
                            player.move(direction,speed);
                        }
                    }else {
                        player.move(direction,speed);
                    }

                    lastDirection = direction;
                }
            }

        }

        repaint();
    }

    public Box getCurrentBox(Direction d, int x, int y){
        Box b;
        switch (d){
            case UP -> b = level.getBoxOnPosition(x,y-50);
            case DOWN ->  b = level.getBoxOnPosition(x,y+50);
            case LEFT -> b = level.getBoxOnPosition(x-50,y);
            case RIGHT -> b = level.getBoxOnPosition(x+50,y);
            default -> b = null;
        }
        return b;
    }

    public Tile getNextTile(Direction d, int x, int y, Boolean checkSecondNextTile){
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
