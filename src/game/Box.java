package game;

import java.awt.image.BufferedImage;

public class Box {

    private int posX;
    private int posY;

    private int boxNumber;
    private BufferedImage image;

    public Box(int posX, int posY, int boxNumber, BufferedImage image) {
        this.posX = posX;
        this.posY = posY;
        this.boxNumber = boxNumber;
        this.image = image;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void move(Direction d, int speed){
        switch (d){
            case UP -> posY -= speed;
            case DOWN ->  posY += speed;
            case LEFT -> posX -= speed;
            case RIGHT -> posX += speed;
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
