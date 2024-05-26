package levels.tiles;

import levels.TileType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void getTileType() {
        Tile tile = new Tile(TileType.VOID);
        assertNull(tile.getImage());
    }
}