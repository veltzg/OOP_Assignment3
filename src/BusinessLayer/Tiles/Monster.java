package BusinessLayer.Tiles;

import java.util.*;
import BusinessLayer.GameManager.Position;

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
        int rand = (int)(Math.floor(Math.random()*5));
        if (rand == 1)
            return (this.getPosition().stepLeft());
        else if (rand == 2)
            return (this.getPosition().stepRight());
        else if (rand == 3)
            return (this.getPosition().stepUp());
        else if (rand == 4)
            return (this.getPosition().stepDown());
        else
            return (this.getPosition());
    }

    public Position chase(Player p) {
        int dx = this.getPosition().getX() - p.getPosition().getX();
        int dy = this.getPosition().getY() - p.getPosition().getY();
        Position posToReturn;
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0)
                posToReturn = getPosition().stepLeft();
            else
                posToReturn = getPosition().stepRight();
        }
        else {
            if (dy > 0)
                posToReturn = getPosition().stepUp();
            else
                posToReturn = getPosition().stepDown();
        }
        return posToReturn;
    }

    @Override
    public void processStep() {
        throw new NoSuchElementException();
    }

    public String describe() {
        return String.format("%s\t\tVision Range: %s", super.describe(),visionRange);
    }


}
