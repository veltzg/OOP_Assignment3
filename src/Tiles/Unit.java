package Tiles;

import GameManager.*;
import java.util.*;

public abstract class Unit extends Tile{

    public String getName() {
        return name;
    }

    public Health getHealth() {
        return health;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getDefensePoints() { return defensePoints; }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    public void setEnemyDeathCB(EnemyDeathCallback enemyDeathCB) {
        this.enemyDeathCB = enemyDeathCB;
    }

    //fields:
    protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defensePoints;
    protected MessageCallback messageCB;
    protected EnemyDeathCallback enemyDeathCB;

    //constructors:

    protected Unit (char tile, String name, int healthCapacity, int attack, int defense)
   {
        super (tile);
        this.name = name;
        this.health = new Health(healthCapacity,healthCapacity);
        this.attackPoints = attack;
        this.defensePoints = defense;
    }


    //methods:
    protected void initialize(Position position, MessageCallback messageCallback){
        super.initialize(position);
        this.messageCB = messageCallback;
    }

    protected int attack(){
        int attackRoll = (int)(Math.random()*(getAttackPoints()+1));
        messageCB.send(getName() + " rolled " + attackRoll + " attack points.");
        return attackRoll;
    }

    public int defend(){
        int defenseRoll = (int)(Math.random()*(getDefensePoints()+1));
        messageCB.send(getName() + " rolled " + defenseRoll + " defense points.");
        return defenseRoll;
    }

    // Should be automatically called once the unit finishes its turn
    public abstract void processStep();

    // What happens when the unit dies
    public abstract void onDeath();

    public boolean isAlive(){
        return (getHealth().getHealthAmount() > 0);
    }

    // This unit attempts to interact with another tile.
    public void interact(Tile tile){
		tile.accept(this);
    }

    public void visit(Empty e){
        replacePosition(e);
    }
    public void visit(Wall w){ }
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    // Combat against another unit.
    protected void battle(Unit u){
        describe();
        u.describe();
        messageCB.send(getName() + " engaged in combat with " + u.getName() + ".");
        messageCB.send(describe());
        messageCB.send(u.describe());
        int result = attack() - u.defend();
        if(result > 0){
            u.getHealth().setHealthAmount(u.getHealth().getHealthAmount() - result);
            messageCB.send(getName() + " dealt " + result + " damage to " + u.getName() + ".");
        }
        messageCB.send(getName() + " dealt 0 damage to " + u.getName() + ".");
    }


    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttackPoints(), getDefensePoints());
    }
}
