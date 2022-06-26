package GameManager;

import Tiles.*;
import java.util.*;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;

    public GameBoard(Tile[][] board){
        tiles = new ArrayList<>();
        for(Tile[] line : board){
            tiles.addAll(Arrays.asList(line));
        }
    }

    public Tile get(int x, int y) {
        for(Tile t : tiles){
            if ((t.getPosition().getX() == x) && (t.getPosition().getY() == y)){
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

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        return ("To Implement");
    }
}