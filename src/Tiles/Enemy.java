package Tiles;

import GameManager.*;
import java.util.*;

public abstract class Enemy extends Unit {

    //fields:
    protected Integer experienceValue;

    public EnemyDeathCallback enemyDeathCB;

    //constructor:
    public Enemy(char tile, String name, Integer healthCapacity, Integer attackPoints, Integer defensePoints, Integer experienceValue) {
        super(tile, name, healthCapacity, attackPoints, defensePoints);
        this.experienceValue = experienceValue;
    }

    //methods:
    public void setEnemyDeathCB(EnemyDeathCallback enemyDeathCB) {
        this.enemyDeathCB = enemyDeathCB;
    }

    public Integer getExperienceValue() {
        return experienceValue;
    }

    public void onDeath(Player player) {
        messageCB.send(getName() + " is dead. " + player.getName() + " gained " + getExperienceValue() + " Experience");
        enemyDeathCB.call(this);
    }

    @Override
    public void visit(Enemy e) {
        this.replacePosition(e);
    }

    public void accept(Unit u) { // the player attack the enemy.
        u.visit(this);
    }

    public void visit(Player p) { // the enemy attack the player.
        this.battle(p);
        if (!p.isAlive()) {
            p.onDeath(this);
        }
    }

    public Position gameEnemyTick(Player p) {
        return this.enemyTurn(p);
    }

    public abstract Position enemyTurn(Player p);

    @Override
    public void onDeath() {
        messageCB.send(getName() + " is dead.");
    }// X gained X points?



    public String describe() {
        return String.format("%s\t\tExperience Value: %s", super.describe(), getExperienceValue());
    }
}
