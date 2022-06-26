package Tiles;

import GameManager.*;
import java.util.*;

public abstract class Enemy extends Unit {

    //fields:
    protected Integer experienceValue;


    //constructor:
    public Enemy(char tile, String name, Integer healthCapacity, Integer attackPoints, Integer defensePoints, Integer experienceValue) {
        super(tile, name, healthCapacity, attackPoints, defensePoints);
        this.experienceValue = experienceValue;
    }

    //methods:

    public Integer getExperienceValue() {
        return experienceValue;
    }

    public void accept(Unit u) { // the player attack the enemy.
        u.visit(this);
    }

    @Override
    public void visit(Enemy e) {
        this.replacePosition(e);
    }

    public void visit(Player p) { // the enemy attack the player.
        this.battle(p);
        if (!p.isAlive()) {
            p.onDeath();
        }
    }

    public Position gameEnemyTick(Player p) {
        return this.enemyTurn(p);
    }

    public abstract Position enemyTurn(Player p);

    @Override
    public void onDeath() {} // Make sure to remove from GameBoard

    public String describe() {
        return String.format("%s\t\tExperience Value: %s", super.describe(), getExperienceValue());
    }
}
