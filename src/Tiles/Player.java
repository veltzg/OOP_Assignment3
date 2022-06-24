package Tiles;

import GameManager.*;
import java.util.*;

public abstract class Player extends Unit {

    protected final Integer EXPERIENCE_BONUS = 50;
    protected final Integer LEVEL_INCREASE = 1;
    protected final Integer HEALTH_BONUS = 10;
    protected final Integer ATTACK_BONUS = 4;
    protected final Integer DEFENSE_BONUS = 1;

    public Integer getExperience() {
        return experience;
    }

    public Integer getPlayerLevel() {
        return playerLevel;
    }

    //fields:
    protected Integer experience;
    protected Integer playerLevel;

    //constructor:
    protected Player(char tile, String name, Integer healthCapacity, Integer attackPoints, Integer defensePoints) {
        super(tile, name, healthCapacity, attackPoints, defensePoints);
        this.experience = 0;
        this.playerLevel = 1;
    }

    //methods:

    //getters:

    protected void levelUp(){
        experience = experience - playerLevel * EXPERIENCE_BONUS;
        playerLevel = playerLevel + LEVEL_INCREASE;
        health.setHealthPool(health.getHealthPool() + HEALTH_BONUS * playerLevel);
        health.setHealthAmount(health.getHealthPool());
        attackPoints = attackPoints + ATTACK_BONUS * playerLevel;
        defensePoints = defensePoints + DEFENSE_BONUS * playerLevel;
    }

    public abstract void castAbility();
    public abstract void processStep();
    public abstract void onDeath();
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    public String describe(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", super.describe(), getPlayerLevel(), getExperience());
    }
}
