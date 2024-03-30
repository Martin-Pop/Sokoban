package levels;

import levels.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

public class Level {

    private int levelNumber;
    private int timeAmount;

    private HashMap<String, Tile> tileMap = new HashMap<>();

    private Tile[][] tiles = new Tile[12][10];

    public Level(int levelNumber, int timeAmount, String path) {
        this.levelNumber = levelNumber;
        this.timeAmount = timeAmount;
        createTiles();
        loadTiles(path);
    }

    public void createTiles(){
        /*BufferedImage wall, floor;
        {
            try {
                wall = ImageIO.read(getClass().getResourceAsStream("/levels/tiles/wall.png"));
                floor = ImageIO.read(getClass().getResourceAsStream("/levels/tiles/floor.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        tileMap.put("wall",new Tile(wall,TileType.WALL));
        tileMap.put("floor",new Tile(floor,TileType.FLOOR));*/
        try {
            tileMap.put("floor",new Tile(ImageIO.read(getClass().getResourceAsStream("/levels/tiles/floor.png")),TileType.FLOOR));
            tileMap.put("wall",new Tile(ImageIO.read(getClass().getResourceAsStream("/levels/tiles/wall.png")),TileType.WALL));
        }catch (Exception e){
            System.out.println("TILE IMAGE PATH DOES NOT EXIST");
        }
    }

    public void loadTiles(String path){
        try {
            InputStream is = getClass().getResourceAsStream("/levels/level_one.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int row = 0;
            String line = "";

            while ((line = br.readLine()) != null){
                String[] s = line.split("\\s");
                for (int i = 0; i< s.length;i++){
                    switch (s[i]){
                        case "0" -> tiles[i][row] = tileMap.get("floor");
                        case "1" -> tiles[i][row] = tileMap.get("wall");
                    }
                }
                //[row][i] swapped
                row++;
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void drawLevel(Graphics2D g2){
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                g2.drawImage(tiles[i][j].getImage(),i*50,j*50,50,50,null);
            }
        }
    }

}
