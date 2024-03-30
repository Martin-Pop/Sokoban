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

    public BufferedImage getImage() {
        return image;
    }
}
