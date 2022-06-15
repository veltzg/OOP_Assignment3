package Tiles;

import GameManager.*;
import java.util.*;

public abstract class Unit extends Tile{

    //fields:
    protected String name;
    protected Integer healthPool;
    protected Integer healthAmount;
    protected Integer attackPoints;
    protected Integer defensePoints;

    //constructors:
    protected Unit
    (char tile, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints){
        super (tile);
        this.name = name;
        this.healthPool = healthPool;
        this.healthAmount = healthAmount;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
    }


    //methods:
    protected void initialize(Position position, MessageCallback messageCallback){
        ...
    }

    protected int attack(){
		...
    }

    public int defend(){
        ...
    }

    // Should be automatically called once the unit finishes its turn
    public abstract void processStep();

    // What happens when the unit dies
    public abstract void onDeath();

    // This unit attempts to interact with another tile.
    public void interact(Tile tile){
		...
    }

    public void visit(Empty e){
		...
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    // Combat against another unit.
    protected void battle(Unit u){
        ...
    }


    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }
}
