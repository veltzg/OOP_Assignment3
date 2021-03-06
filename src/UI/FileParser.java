package UI;

import BusinessLayer.Tiles.*;
import BusinessLayer.GameManager.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class FileParser {

    //field:
    private String args;
    private MessageCallback messageCB;

    //constructor:
    public FileParser(String args, MessageCallback messageCB) {
        this.args = args;
        this.messageCB = messageCB;

    }

    //methods:

    public List<Level> produceLevels(TileFactory tileFactory, Player player) throws IOException {
        File levelsFolder = new File(this.args);
        File[] levelsList = levelsFolder.getCanonicalFile().listFiles();
        List<Level> gameLevels = new ArrayList<Level>();
        for (File levelFile : levelsList) {
            gameLevels.add(fileToLevel(levelFile, tileFactory, player));
        }
        return gameLevels;
    }

    public Level fileToLevel(File levelFile, TileFactory tileFactory, Player player) throws IOException {
        try{
            List<String> levelBoardToString = Files.readAllLines(levelFile.toPath());
            Board board = new Board();
            Level level = new Level(board);
            Tile[][] boardTiles = new Tile[levelBoardToString.size()][levelBoardToString.get(0).length()];
            for (int i = 0; i < boardTiles.length; i++) {
                String row = levelBoardToString.get(i);
                for (int j = 0; j < row.length(); j++) {
                    char c = row.charAt(j);
                    Position position = new Position(j, i);
                    if (c == '#') {
                        boardTiles[i][j] = tileFactory.produceWall(position);
                    }
                    else if (c == '.') {
                        boardTiles[i][j] = tileFactory.produceEmpty(position);
                    }
                    else if (c == '@') {
                        level.setStartPosition(position);
                        boardTiles[i][j] = player;
                    } else {
                        Enemy enemy = tileFactory.produceEnemy(c, position, messageCB);
                        boardTiles[i][j] = enemy;
                        level.addEnemy(enemy);
                    }
                }
            }
            board.setTiles(boardTiles);
            return level;
        }
        catch (IOException e) {
            System.err.println(e.getMessage()+"\n"+e.getStackTrace());
        }
        return null;
    }
}

