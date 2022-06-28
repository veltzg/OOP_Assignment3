package Tiles;

import GameManager.*;
import java.util.*;

public class Rogue extends Player {

    //fields:
    protected Integer cost;

    public void setCurrentEnergy(Integer currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    protected Integer currentEnergy;
    private final Integer MAX_ENERGY = 100;

    //constructor:
    public Rogue(String name, Integer healthCpacity, Integer attackPoints, Integer defensePoints, Integer cost) {
        super(name, healthCpacity, attackPoints, defensePoints);
        this.cost = cost;
        this.currentEnergy = MAX_ENERGY;
    }

    @Override
    public void castAbility(List<Enemy> enemies) {
    }

    @Override
    public void processStep() {
        setCurrentEnergy(Math.min(currentEnergy + 10, MAX_ENERGY));
    }

    //methods:

}
