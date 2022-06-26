package GameManager;

import Tiles.*;
import java.util.*;

public class Level {

    //fields:
    private GameBoard gameBoard;
    private Player player;
    private List<Enemy> enemies;
    private Position startPosition;

    //constructor:
    public Level(GameBoard gameBoard,Player player) {
        this.gameBoard=gameBoard;
        this.player=player;
        this.enemies=new ArrayList<>();
    }

    //methods:

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
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
        enemies.add(e);
    }

}
