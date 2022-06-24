package Tiles;

import GameManager.*;
import java.util.*;

public abstract class Enemy extends Unit {

    //fields:
    protected Integer experienceValue;

    public EnemyDeathCallback enemyDeathCB;

    //constructor:
    protected Enemy(char tile, String name, Integer healthCapacity, Integer attackPoints, Integer defensePoints, Integer experienceValue) {
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
        enemyDeathCB.call();
    }

    @Override
    public void visit(Enemy e) {
        replacePosition(this, e);
    }

    public void accept(Unit u) { // the player attack the enemy.
        u.visit(this);
    }

    public void visit(Player player) { // the enemy attack the player.
        messageCB.send(getName() + " engaged in combat with " + player.getName());
        messageCB.send(describe());
        messageCB.send(player.describe());
        int attack = rollAttack();
        int defense = player.rollDefense();
        this.attacking(attack, player, defense);
        if (player.isDead()) {
            player.onDeath(this);
        }
    }

    public Position gameEnemyTick(Player p) {
        return this.enemyTurn(p);
    }

    public abstract Position enemyTurn(Player p);

    @Override
    public void onDeath() {
        messageCB.send(getName() + " is dead.");
        enemyDeathCB.call();
    }// X gained X points?

    public String describe() {
        return String.format("%s\t\tExperience Value: %s", super.describe(), getExperienceValue());
    }
}
