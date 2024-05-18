package game.componenets;

import game.Direction;

import java.awt.image.BufferedImage;

public class Box {

    private int posX;
    private int posY;

    private final int starterPositionX;
    private final int starterPositionY;

    private boolean correctPosition = false;

    private int boxNumber;
    private BufferedImage image;
    private BufferedImage winnerImage;

    public Box(int posX, int posY, int boxNumber, BufferedImage image, BufferedImage winnerImage) {
        this.posX = posX;
        this.posY = posY;
        this.boxNumber = boxNumber;
        this.image = image;
        this.winnerImage = winnerImage;

        this.starterPositionX = posX;
        this.starterPositionY = posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isCorrectPosition() {
        return correctPosition;
    }

    public void setCorrectPosition(boolean correctPosition) {
        this.correctPosition = correctPosition;
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
        if (correctPosition){
            return winnerImage;
        }else {
            return image;
        }
    }

    public void resetPosition(){
        this.posX = starterPositionX;
        this.posY = starterPositionY;
        setCorrectPosition(false);
    }

}
