package BusinessLayer.GameManager;

import BusinessLayer.Tiles.*;
import java.util.*;

public class GameFlow {

    //fields:
    private Level level;
    private Player player;
    private List<Enemy> flowEnemyList;
    private MessageCallback messageCB;

    public boolean isLevelIsDone() {
        return levelIsDone;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private boolean levelIsDone;
    private boolean gameOver;

    //constructors:

    public GameFlow (Level level, Player player, MessageCallback messageCB)
    {
        this.level = level;
        this.player = player;
        this.messageCB = messageCB;
        this.player.initialize(level.getStartPosition(), messageCB);
        this.flowEnemyList = level.getLevelEnemyList();
        this.levelIsDone = false;
        this.gameOver = false;
    }

    public void setGameLevel (Level newLevel) {
        this.level = newLevel;
        this.player.initialize(newLevel.getStartPosition(), messageCB);
        this.flowEnemyList = newLevel.getLevelEnemyList();
        this.levelIsDone = false;
        for (Enemy enemy: flowEnemyList) {
            EnemyDeathCallback enemyDeathCallback = e -> this.enemyIsDead(enemy);
            enemy.setEnemyDeathCB(enemyDeathCallback);
        }
    }

    public void gameTick(char c) {
        playerMove(c);
        player.processStep();
        if (flowEnemyList.size() == 0) {
            levelIsDone = true;
        }
        for (Enemy e : flowEnemyList) {
            Position pos = e.enemyTurn(player);
            e.interact(level.getBoard().getTile(pos));
            if (!player.isAlive()){
                gameOver = true;
            }
        }
    }

    public void playerMove(char c) {
        Position playerPosition = player.getPosition();
        switch (c) {
            case 'w':
                player.interact(level.getBoard().getTile(playerPosition.stepUp()));
                break;
            case 's':
                player.interact(level.getBoard().getTile(playerPosition.stepDown()));
                break;
            case 'd':
                player.interact(level.getBoard().getTile(playerPosition.stepRight()));
                break;
            case 'a':
                player.interact(level.getBoard().getTile(playerPosition.stepLeft()));
                break;
            case 'e':
                player.castAbility(flowEnemyList);
                break;
            case 'q':
                break;
            default:
                messageCB.send("You inserted an invalid input");
        }
    }

    public void printBoard() {
        messageCB.send(level.getBoard().toString());
        messageCB.send(player.describe());
    }

    public void enemyIsDead(Enemy e) {
        messageCB.send(e.getName() + " is dead. "+ player.getName() +" gained "+ e.getExperienceValue() +" Experience");
        flowEnemyList.remove(e);
        level.removeEnemy(e);
        player.setExperience(player.getExperience() + e.getExperienceValue());
        Tile newEmpty = level.getBoard().remove(e);
        player.replacePosition(newEmpty);
    }


}
