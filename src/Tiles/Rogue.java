package Tiles;

import GameManager.*;
import java.util.*;

public class Rogue extends Player {

    //fields:
    protected Integer cost;
    protected Integer currentEnergy;

    //constructor:
    protected Rogue(char tile, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints) {
        super(tile, name, healthPool, healthAmount, attackPoints, defensePoints);
    }

    //methods:

}
