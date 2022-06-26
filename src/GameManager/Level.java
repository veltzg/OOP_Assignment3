package GameManager;

import Tiles.*;
import java.util.*;

public class Level {

    //fields:
    private Board board;
    private Player player;
    private List<Enemy> levelEnemyList;
    private Position startPosition;

    //constructor:
    public Level(Board gameBoard,Player player) {
        this.board =gameBoard;
        this.player=player;
        this.levelEnemyList = new ArrayList<>();
    }

    //methods:

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
