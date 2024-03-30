package game;

import levels.Level;

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

        if (x % grid != 0 || y % grid != 0) {
            switch (direction) {
                case "up" -> player.updateY(-speed);
                case "down" -> player.updateY(speed);
                case "left" -> player.updateX(-speed);
                case "right" -> player.updateX(speed);
            }
        } else {
            if (keyHandler.up && y >= tileSize) {
                player.updateY(-speed);
                direction = "up";
            } else if (keyHandler.down && y < (this.height - tileSize)) {
                player.updateY(speed);
                direction = "down";
            } else if (keyHandler.left && x >= tileSize) {
                player.updateX(-speed);
                direction = "left";
            } else if (keyHandler.right && x < (this.width - tileSize)) {
                player.updateX(speed);
                direction = "right";
            }
        }
        repaint();
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
