package BusinessLayer.GameManager;

import java.util.*;
import BusinessLayer.Tiles.*;

public class Level {

    //fields:
    private Board board;
    private List<Enemy> levelEnemyList;
    private Position startPosition;

    //constructor:
    public Level(Board gameBoard) {
        this.board = gameBoard;
        this.levelEnemyList = new ArrayList<>();
    }

    //methods:

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void addEnemy(Enemy e){
        levelEnemyList.add(e);
    }

    public void removeEnemy(Enemy e) { levelEnemyList.remove(e);}

    public List<Enemy> getLevelEnemyList() {
        return levelEnemyList;
    }
}
