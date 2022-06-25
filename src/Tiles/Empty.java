package Tiles;

import GameManager.*;
import java.util.*;

public class Empty extends Tile{
    private static final char emptyCharTile = '.';

    public Empty(){super(emptyCharTile) ;}
    public Empty(Position p) { super(emptyCharTile); this.position = p; }


    public void accept(Unit unit){
        unit.visit(this);
    }
}
