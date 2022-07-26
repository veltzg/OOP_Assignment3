package BusinessLayer.Tiles;

import BusinessLayer.GameManager.*;
import BusinessLayer.Tiles.*;

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


    //fields:
    protected String name;
    public Health health;
    protected int attackPoints;
    protected int defensePoints;
    protected MessageCallback messageCB;

    //constructors:

    public Unit (char tile, String name, int healthCapacity, int attack, int defense)
   {
        super(tile);
        this.name = name;
        this.health = new Health(healthCapacity,healthCapacity);
        this.attackPoints = attack;
        this.defensePoints = defense;
    }


    //methods:
    public void initialize(Position position, MessageCallback messageCallback){
        super.initialize(position);
        this.messageCB = messageCallback;
    }

    public int attack(){
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
    public void battle(Unit u){
        messageCB.send(getName() + " engaged in combat with " + u.getName() + ".");
        messageCB.send(describe());
        messageCB.send(u.describe());
        int result = attack() - u.defend();
        if(result > 0){
            u.getHealth().setHealthAmount(u.getHealth().getHealthAmount() - result);
            messageCB.send(getName() + " dealt " + result + " damage to " + u.getName() + ".");
        }
        else
            messageCB.send(getName() + " dealt 0 damage to " + u.getName() + ".");
    }


    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttackPoints(), getDefensePoints());
    }
}
