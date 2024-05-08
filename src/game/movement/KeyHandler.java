package game.movement;

import game.Direction;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public Direction direction = Direction.NONE;

    JPanel panel;
    public KeyHandler(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_W -> direction = Direction.UP;
            case KeyEvent.VK_S -> direction = Direction.DOWN;
            case KeyEvent.VK_A -> direction = Direction.LEFT;
            case KeyEvent.VK_D -> direction = Direction.RIGHT;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        direction = Direction.NONE;
        /*int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_D -> right = false;
        }*/
    }
}
