package GameManager;

import Tiles.*;
import java.util.*;

public class Position {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //fields:
    public int x;
    public int y;


    //constructor:
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Position p) {
        if (p.getX() == getX() && p.getY() == getY())
            return 0;
        else {
            if (p.getY() > getY())
                return -1;
            else {
                if (getY() > p.getY()) {
                    return 1;
                } else if (p.getX() > getX())
                    return -1;
                else
                    return 1;
            }
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position moveLeft() {
        x = getX() - 1;
        return this;
    }

    public Position moveRight() {
        x = getX() + 1;
        return this;
    }

    public Position moveUp() {
        y = getY() + 1;
        return this;
    }

    public Position moveDown() {
        y = getY() - 1;
        return this;
    }


    public  int range(Position U2){
        int range = (int)(Math.sqrt(Math.pow((this.getX() - U2.getX()), 2) + Math.pow((this.getY() - U2.getY()), 2)));
        return range;
    }
}
