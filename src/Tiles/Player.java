package Tiles;

import GameManager.*;
import java.util.*;

public abstract class Player extends Unit {

    //fields:
    protected Integer experience;
    protected Integer playerLevel;

    //constructor:
    protected Player(char tile, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints) {
        super(tile, name, healthPool, healthAmount, attackPoints, defensePoints);
    }

    //methods:

}
