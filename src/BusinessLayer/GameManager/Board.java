package BusinessLayer.GameManager;

import java.util.*;
import java.util.stream.Collectors;
import BusinessLayer.Tiles.*;

public class Board {

    private List<Tile> tiles;

    public Board(){
        tiles = new ArrayList<>();
    }

    public void setTiles (Tile [][] board) {
        for (Tile[] line : board) {
            tiles.addAll(Arrays.asList(line));
        }
    }

    public Tile getTile(int x, int y) {
        for(Tile tile : tiles){
            if ((tile.getPosition().getX() == x) && (tile.getPosition().getY() == y)){
                return tile;
            }
        }
        throw new NoSuchElementException();
    }

    public Tile getTile(Position p) {
        for(Tile tile : tiles){
            if (tile.getPosition().compareTo(p) == 0){
                return tile;
            }
        }
        throw new NoSuchElementException("I don't find the Tile");
    }

    public Tile remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new Empty(p));
        return (getTile(p));
    }

    public void add(Tile t) {
        tiles.add(t);
    }

    @Override
    public String toString() {
        tiles = tiles.stream().sorted(Comparator.comparing(Tile::getPosition)).collect(Collectors.toList());
        String boardToString="";
        for(Tile t:tiles){
            if (t.getPosition().x==0){
                boardToString = boardToString + "\n";
            }
            boardToString = boardToString + t.toString();
        }
        return boardToString;
    }
}
