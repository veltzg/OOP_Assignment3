package Tiles;

import GameManager.*;
import java.util.*;
import javax.swing.text.Position;
import java.awt.*;

public abstract class Tile implements Comparable<Tile> {

    //fields:
    protected char tile;
    protected Position position;

    //constructors:
    protected Tile (char tile) {
        this.tile = tile;
    }

    //methods:

    protected void initialize(Position position){
        this.position = position;
    }

    public char getTile() {
        return tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void accept(Unit unit);

    @Override
    public int compareTo(Tile tile) {
        return getPosition().compareTo(tile.getPosition());
    }

    @Override
    public String toString() {
        return String.valueOf(tile);
    }
}