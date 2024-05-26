package game.movement;

import game.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {

    @Test
    void playerPosition(){
        Movement movement = new Movement(null,450,500, Direction.UP);
        assertEquals(550,movement.getPlayerY());

        Movement movement2 = new Movement(null,100,250, Direction.RIGHT);
        assertEquals(50,movement2.getPlayerX());
    }

}