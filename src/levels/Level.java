package levels;

import game.Box;
import levels.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Level {

    private int levelNumber;
    private int timeAmount;

    private HashMap<String, Tile> tileMap = new HashMap<>();

    private Tile[][] tiles = new Tile[12][10];
    private ArrayList<Box> boxes = new ArrayList<>();

    public Level(int levelNumber, int timeAmount, String path) {
        this.levelNumber = levelNumber;
        this.timeAmount = timeAmount;
        createTiles();
        loadTiles(path);
    }

    public void createTiles(){

        try {
            tileMap.put("floor",new Tile(ImageIO.read(getClass().getResourceAsStream("/levels/tiles/floor.png")),TileType.FLOOR));
            tileMap.put("wall",new Tile(ImageIO.read(getClass().getResourceAsStream("/levels/tiles/wall.png")),TileType.WALL));
            tileMap.put("boxDestination",new Tile(ImageIO.read(getClass().getResourceAsStream("/levels/tiles/boxDestination.png")),TileType.BOX_DESTINATION));
        }catch (Exception e){
            System.out.println("TILE IMAGE PATH DOES NOT EXIST");
        }
    }

    public void loadTiles(String path){
        try {
            InputStream is = getClass().getResourceAsStream("/levels/level_one.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int boxCount = 1;
            int column = 0;
            String line = "";

            while ((line = br.readLine()) != null){
                String[] s = line.split("\\s");
                for (int i = 0; i< s.length;i++){
                    switch (s[i]){
                        case "0" -> tiles[i][column] = tileMap.get("floor");
                        case "1" -> tiles[i][column] = tileMap.get("wall");
                        case "2" -> tiles[i][column] = tileMap.get("boxDestination");
                        case "3" -> {
                            tiles[i][column] = tileMap.get("floor");
                            boxes.add(new Box(i*50,column*50,boxCount,
                                    ImageIO.read(getClass().getResourceAsStream("/levels/tiles/grayBox.png")),
                                    ImageIO.read(getClass().getResourceAsStream("/levels/tiles/yellowBox.png"))
                            ));
                            boxCount++;
                        }
                    }
                }
                //[row][i] swapped
                column++;
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Box checkBoxOnPosition(int x, int y){
        Box b = null;
        for (Box box : boxes){
            if (box.getPosX() == x && box.getPosY() == y){
                b = box;
            }
        }
        return b;
    }

    public Tile getTileOnPosition(int x, int y){
        return tiles[x/50][y/50];
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void drawLevel(Graphics2D g2){
        //floor
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                g2.drawImage(tiles[i][j].getImage(),i*50,j*50,50,50,null);
            }
        }
        //boxes
        for (Box box: boxes) {
            g2.drawImage(box.getImage(), box.getPosX(), box.getPosY(), 50, 50, null);
        }
    }

}
