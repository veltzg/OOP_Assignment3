package Tiles;

import GameManager.*;
import java.util.*;

public class Warrior extends Player{

    //fields:
    protected Integer abilityCooldown;
    protected Integer remainingCooldown;

    //constructors:
    protected Warrior(char tile, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints) {
        super(tile, name, healthPool, healthAmount, attackPoints, defensePoints);
    }

    //methods:


}
