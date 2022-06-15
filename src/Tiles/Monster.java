package Tiles;

import GameManager.*;
import java.util.*;

public class Monster extends Enemy{

    //fields:
    protected Integer visionRange;

    //constructor:
    protected Monster(char tile, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints) {
        super(tile, name, healthPool, healthAmount, attackPoints, defensePoints);
    }

    //methods:

}
