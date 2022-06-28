package BusinessLayer.GameManager;

import java.util.*;
import java.util.stream.Collectors;

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

    //returns a tile from a specifiedd locaion on board
    public Tile getTile(Position p) {
        for(Tile tile : tiles){
            if (tile.getPosition() == p){
                return tile;
            }
        }
        throw new NoSuchElementException();
    }

    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new Empty(p));
    }

    public void add(Tile t) {
        tiles.add(t);
    }

    private void arrangeBoard(){
        tiles.sort(Tile::compareTo);
    }

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        return ("To Implement");
    }
}
