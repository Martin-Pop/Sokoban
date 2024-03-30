package levels.tiles;

import levels.TileType;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    private TileType tileType;

    public Tile(BufferedImage image, TileType tileType) {
        this.image = image;
        this.tileType = tileType;
    }

    public BufferedImage getImage() {
        return image;
    }

    public TileType getTileType() {
        return tileType;
    }

    @Override
    public String toString() {
        return "im a tile";
    }
}
