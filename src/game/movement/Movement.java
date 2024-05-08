package game.movement;

import game.Box;
import game.Direction;

public class Movement {
    private int playerMovedBy; // how many tiles player moved
    private int boxMovedBy; // how many tiles box moved (if did)
    private Box box; // box that player moved (if did)

    private Direction direction;

    public Movement(int playerMovedBy, Direction direction) {
        this.playerMovedBy = playerMovedBy;
        this.direction = direction;

        this.box = null;
    }

    public Movement(int playerMovedBy, int boxMovedBy, Box box, Direction direction) {
        this.playerMovedBy = playerMovedBy;
        this.boxMovedBy = boxMovedBy;
        this.box = box;
        this.direction = direction;
    }

    public int getPlayerMovedBy() {
        return playerMovedBy;
    }

    public int getBoxMovedBy() {
        return boxMovedBy;
    }

    public Box getBox() {
        return box;
    }

    public Direction getDirection() {
        return direction;
    }
}
