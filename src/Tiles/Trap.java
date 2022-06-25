package Tiles;

import GameManager.*;
import java.util.*;

public class Trap extends Enemy{

    //fields:
    protected Integer visibilityTime;
    protected Integer invisibilityTime;
    protected Integer tickCount;
    protected Boolean visible;
    protected final char visTrapChar;

    //constructor:
    public Trap(char tile, String name, Integer healthPool, Integer attackPoints, Integer defensePoints, Integer experienceValue, Integer visTime, Integer invisTime) {
        super(tile, name, healthPool, attackPoints, defensePoints, experienceValue);
        this.visibilityTime = visTime;
        this.invisibilityTime = invisTime;
        this.tickCount = 0;
        this.visible = true;
        this.visTrapChar = tile;
    }

    public void setTile(){
        if (visible)
            this.tile = visTrapChar;
        else
            this.tile = '.';
    }

    @Override
    public Position enemyTurn(Player p) {
        visible = (visibilityTime > tickCount);
        this.setTile();
        if (tickCount == (visibilityTime + invisibilityTime)){
            tickCount = 0;
        }
        else {
            tickCount ++;
        }
        if (this.getPosition().range(p.getPosition()) < 2) {
            visit(p);
        }
        return getPosition();
    }


    @Override
    public void processStep() {

    }

    //methods:
}
