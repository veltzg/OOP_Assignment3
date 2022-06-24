package Tiles;

import GameManager.*;
import java.util.*;

public abstract class Enemy extends Unit{

    //fields:
    protected Integer experienceValue;

    //constructor:
    protected Enemy(char tile, String name, Integer healthCapacity, Integer attackPoints, Integer defensePoints, Integer experienceValue) {
        super(tile, name, healthCapacity, attackPoints, defensePoints);
        this.experienceValue = experienceValue;
    }

    //methods:
    public void onDeath(Player player) {
        mcb.send(getName() + " is dead. "+player.getName()+" gained "+GetExperience()+" Experience");
        deathCallBack.call();
    }

    @Override
    public void visit(Enemy e) {
        swap(this, e);
    }

    public void accept(Unit u) { // the player attack the enemy.
        u.visit(this);
    }

    public void visit(Player player) { // the enemy attack the player.
        messageCallback.send(getName() + " engaged in combat with " + player.getName());
        messageCallback.send(describe());
        messageCallback.send(player.describe());
        int attack = rollAttack();
        int defense = player.rollDefense();
        this.attacking(attack, player  , defense);
        if (player.isDead()){
            player.onDeath(this);
        }
    }

    public Integer GetExperience(){
        return Experience;
    }
    public Position gameEnemyTick(Player p){
        return this.eTurn(p);
    }

    public abstract Position eTurn(Player p);

    public String describe() {
        return String.format("%s\t\tExperience Value: %s", super.describe(), GetExperience());
    }

}

}
