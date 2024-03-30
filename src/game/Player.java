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


    BufferedImage image, floor;

    {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/levels/tiles/wall.png"));
            floor = ImageIO.read(getClass().getResourceAsStream("/levels/tiles/floor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Player(int roomWidth, int roomHeight) {
        this.roomWidth = roomWidth;
        this.roomHeight = roomHeight;

        this.posX = roomWidth/2;
        this.posY = roomHeight/2;
    }

    public void updateX(int value){
        this.posX += value;
    }

    public void updateY(int value){
        this.posY += value;
    }

    public void updatePlayer(){

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
