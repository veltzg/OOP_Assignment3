package GameManager;

import Tiles.*;
import java.util.*;

public class Position {

    //fields:
    protected int x;
    protected int y;

    //constructor:
    public Position(int x,int y){
        this.x=x;
        this.y=y;
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
