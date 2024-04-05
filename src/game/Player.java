package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    private int posX;
    private int posY;

    private int roomWidth;
    private int roomHeight;

    public Player(int roomWidth, int roomHeight) {
        this.roomWidth = roomWidth;
        this.roomHeight = roomHeight;

        this.posX = roomWidth/2;
        this.posY = roomHeight/2;
    }

    public void move(Direction d,int speed){
        switch (d){
            case UP -> updateY(-speed);
            case DOWN ->  updateY(speed);
            case LEFT -> updateX(-speed);
            case RIGHT -> updateX(speed);
        }
    }

    public void updateX(int value){
        this.posX += value;
    }

    public void updateY(int value){
        this.posY += value;
    }



    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void drawPlayer(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(posX,posY,50,50);
    }


}
