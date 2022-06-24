package Tiles;

import GameManager.*;
import java.util.*;

public class Monster extends Enemy{

    //fields:
    protected Integer visionRange;

    //constructor:
    protected Monster(char tile, String name, Integer healthCapacity, Integer attackPoints, Integer defensePoints, Integer experienceValue, Integer visionRange) {
        super(tile, name, healthCapacity, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
    }

    //methods:

}
