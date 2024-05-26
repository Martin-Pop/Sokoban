package game.componenets;

import game.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void move() {
        Box box = new Box(100,50,true,null,null);
        box.move(Direction.DOWN,10);
        assertEquals(60,box.getPosY());

        box.move(Direction.LEFT,50);
        assertEquals(50,box.getPosX());

        box.move(Direction.UP,40);
        assertEquals(20,box.getPosY());
    }
}