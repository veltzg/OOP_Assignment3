package Tiles;

import GameManager.*;
import java.util.*;

public abstract class Unit extends Tile{

    //fields:
    protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defensePoints;
    public MessageCallback mcb;

    //constructors:

    protected Unit (char tile, String name, int healthCapacity, int attack, int defense)
   {
        super (tile);
        this.name = name;
        this.health = new Health(healthCapacity,healthCapacity);
        this.attackPoints = attack;
        this.defensePoints = defense;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public MessageCallback getMcb() {
        return mcb;
    }

    public void setMcb(MessageCallback mcb) {
        this.mcb = mcb;
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
