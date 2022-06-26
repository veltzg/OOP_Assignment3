package Tiles;

import GameManager.*;
import java.util.*;

public class Monster extends Enemy{

    //fields:
    protected Integer visionRange;

    //constructor:
    public Monster(char tile, String name, Integer healthPool, Integer attackPoints, Integer defensePoints, Integer experienceValue, Integer visionRange) {
        super(tile, name, healthPool, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
    }

    //methods:

    @Override
    public Position enemyTurn(Player p) {
        if (this.getPosition().range(p.getPosition()) < visionRange) {
            return chase(p);
        }
        else {
            return randomMove();
        }
    }

    public Position randomMove(){
        int rand = (int)(Math.random() * 5);
        if (rand == 0)
            return (this.getPosition());
        if (rand == 1)
            return (this.getPosition().moveLeft());
        if (rand == 2)
            return (this.getPosition().moveRight());
        if (rand == 3)
            return (this.getPosition().moveUp());
        if (rand == 4)
            return (this.getPosition().moveDown());
        return null;
    }

    public Position chase(Player p){
        int dx = this.getPosition().getX() - p.getPosition().getX();
        int dy = this.getPosition().getY() - p.getPosition().getY();
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0)
                return getPosition().moveLeft();
            else
                return getPosition().moveRight();
        }
        else {
            if (dy > 0)
                return getPosition().moveUp();
            else
                return getPosition().moveDown();
        }
    }

    @Override
    public void processStep() {
        throw new NoSuchElementException();
    }

    public String describe() {
        return String.format("%s\t\tVision Range: %s", super.describe(),visionRange);
    }


}
