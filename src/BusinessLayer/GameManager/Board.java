package BusinessLayer.GameManager;

import java.util.*;
import java.util.stream.Collectors;

public class Board {

    private List<Tile> tiles;

    public Board(Tile[][] board){
        tiles = new ArrayList<>();
        for(Tile[] line : board){
            tiles.addAll(Arrays.asList(line));
        }
    }

    public Tile getTile(int x, int y) {
        for(Tile t : tiles){
            if ((t.getPosition().getX() == x) && (t.getPosition().getY() == y)){
                return t;
            }
        }
        throw new NoSuchElementException();
    }

    public Tile getTile(Position p) {
        for(Tile t : tiles){
            if (t.getPosition()== p){
                return t;
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

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        return ("To Implement");
    }
}
