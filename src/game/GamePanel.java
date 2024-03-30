package game;

import levels.Level;
import levels.TileType;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    KeyHandler keyHandler = new KeyHandler(this);
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

    String direction;
    int grid = 50;
    int speed = 5;
    //GRID WALK ON TOP
    public void updateGame() {

        int x = player.getPosX();
        int y = player.getPosY();

        System.out.println("x:" + x + " y:" + y + " d: "+ direction);

        if (x % grid != 0 || y % grid != 0) {
            switch (direction) {
                case "up" -> player.updateY(-speed);
                case "down" -> player.updateY(speed);
                case "left" -> player.updateX(-speed);
                case "right" -> player.updateX(speed);
            }
        } else {
            if (keyHandler.up && y > tileSize) {
                direction = "up";
                if (canMove(direction, x, y)) {
                    player.updateY(-speed);
                }
            } else if (keyHandler.down && y < (this.height - tileSize)) {
                direction = "down";
                if (canMove(direction, x, y)) {
                    player.updateY(speed);
                }
            } else if (keyHandler.left && x > tileSize) {
                direction = "left";
                if (canMove(direction, x, y)) {
                    player.updateX(-speed);
                }
            } else if (keyHandler.right && x < (this.width - tileSize)) {
                direction = "right";
                if (canMove(direction, x, y)) {
                    player.updateX(speed);
                }
            }
        }
        repaint();
    }

    public boolean canMove(String direction, int x, int y){
        boolean can = true;
        switch (direction) {
            case "up" -> {
                if (level.getTileTypeOnPosition(x,y-50) == TileType.WALL){
                    can = false;
                }
            }
            case "down" -> {
                if (level.getTileTypeOnPosition(x,y+50) == TileType.WALL){
                    can = false;
                }
            }
            case "left" -> {
                if (level.getTileTypeOnPosition(x-50,y) == TileType.WALL){
                    can = false;
                }
            }
            case "right" -> {
                if (level.getTileTypeOnPosition(x+50,y) == TileType.WALL){
                    can = false;
                }
            }
        }
        return can;
    }




    /*public void draw(Graphics2D g2){
        g2.fillRect(posX,posY,100,100);
    }*/

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        level.drawLevel(g2);

        player.drawPlayer(g2);
    }
}
