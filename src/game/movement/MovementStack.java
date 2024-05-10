package game.movement;

import java.util.Arrays;
import java.util.Stack;

public class MovementStack extends Stack<Movement> {
    //for method Override later (maybe detect repeated movement ?)

    @Override
    public String toString() {
        return "MovementStack{" +
                "elementData=" + Arrays.toString(elementData) +
                '}';
    }
}
