package Tiles;

import GameManager.*;
import java.util.*;

public class Mage extends Player{

    //fields:
    protected Integer manaPool;
    protected Integer currentPool;
    protected Integer manaCost;
    protected Integer spellPower;
    protected Integer hitsCount;
    protected Integer abilityRange;

    //constructor:
    protected Mage(char tile, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints) {
        super(tile, name, healthPool, healthAmount, attackPoints, defensePoints);
    }

    //methods:

}
