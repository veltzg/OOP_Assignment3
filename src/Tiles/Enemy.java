package Tiles;

import GameManager.*;
import java.util.*;

public abstract class Enemy extends Unit{

    //fields:
    protected Integer experienceValue;

    //constructor:
    protected Enemy(char tile, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints) {
        super(tile, name, healthPool, healthAmount, attackPoints, defensePoints);
    }

    //methods:

}
