package BusinessLayer.Tiles;

import java.util.*;

public class Rogue extends Player {

    //fields:
    private Integer cost;

    public void setCurrentEnergy(Integer currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public Integer getCurrentEnergy() {
        return currentEnergy;
    }

    private Integer currentEnergy;
    private final Integer MAX_ENERGY = 100;
    private final Integer ATTACK_EXTRA = 3;
    private final String ABILITY_NAME = "Fan of Knives";
    private final Integer ABILITY_RANGE = 2;
    private boolean abilityCasted = false;


    //constructor:
    public Rogue(String name, Integer healthCpacity, Integer attackPoints, Integer defensePoints, Integer cost) {
        super(name, healthCpacity, attackPoints, defensePoints);
        this.cost = cost;
        this.currentEnergy = MAX_ENERGY;
    }

    //methods:
    @Override
    public void castAbility(List<Unit> enemies) {
        if(currentEnergy < cost)
            messageCB.send((getName() + "  tried to cast " + ABILITY_NAME + ", but there was not enough energy: " + currentEnergy + "/" + MAX_ENERGY + "."));
        else{
            messageCB.send(getName() + " cast " + ABILITY_NAME + ".");
            setCurrentEnergy(currentEnergy - cost);
            abilityCasted = true;
            List<Unit> enemiesAround = findEnemiesWithingRange(enemies, ABILITY_RANGE);
            for (Unit e:
                 enemiesAround) {
                attackWithAbility((Enemy)e, getAttackPoints());
                if(!e.isAlive())
                    e.onDeath();
            }
        }
    }

    @Override
    public void levelUp(){
        super.levelUp();
        messageCB.send(getName() + " reached level " + getPlayerLevel() +": +" + (getPlayerLevel() * HEALTH_BONUS) + " Health, +" + ((ATTACK_BONUS + ATTACK_EXTRA) * getPlayerLevel()) + " Attack, +" + (DEFENSE_BONUS* getPlayerLevel()) + " Defense");
        setCurrentEnergy(MAX_ENERGY);
        setAttackPoints(getAttackPoints() + ATTACK_EXTRA * getPlayerLevel());
        if(getExperience() >= EXPERIENCE_BONUS * getPlayerLevel())
            levelUp();
    }

    @Override
    public void processStep() {
        if(abilityCasted)
            abilityCasted = false;
        else
            setCurrentEnergy(Math.min(currentEnergy + 10, MAX_ENERGY));
    }

    @Override
    public String describe(){
        String s = super.describe();
        return String.format("%s\t\tEnergy: %s/%s ", s, getCurrentEnergy(), MAX_ENERGY);
    }
}
