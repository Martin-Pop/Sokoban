package game.movement;

import game.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public Direction direction = Direction.NONE;
    public boolean holdUp, holdDown, holdLeft, holdRight;
    public boolean revertMovement;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_W -> {
                direction = Direction.UP;
                holdUp = true;
            }
            case KeyEvent.VK_S -> {
                direction = Direction.DOWN;
                holdDown = true;
            }
            case KeyEvent.VK_A -> {
                direction = Direction.LEFT;
                holdLeft = true;
            }
            case KeyEvent.VK_D -> {
                direction = Direction.RIGHT;
                holdRight = true;
            }
            case KeyEvent.VK_R -> revertMovement = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_W -> holdUp = false;
            case KeyEvent.VK_S -> holdDown = false;
            case KeyEvent.VK_A -> holdLeft = false;
            case KeyEvent.VK_D -> holdRight = false;
        }

        if (!holdUp && !holdDown && !holdRight && !holdLeft){
            direction = Direction.NONE;
        }
    }
}
