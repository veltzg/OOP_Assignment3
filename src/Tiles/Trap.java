package Tiles;

import GameManager.*;
import java.util.*;

public class Trap extends Enemy{

    //fields:
    protected Integer visibilityTime;
    protected Integer invisibilityTime;
    protected Integer tickCount;
    protected Boolean visible;

    //constructor:
    protected Trap(char tile, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints) {
        super(tile, name, healthPool, healthAmount, attackPoints, defensePoints);
    }

    //methods:
}
