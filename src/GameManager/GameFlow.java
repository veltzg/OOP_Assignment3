package GameManager;

import Tiles.*;
import java.util.*;

public class GameFlow {

    //fields:
    private Level level;
    private Player player;
    private List<Enemy> flowEnemyList;
    private MessageCallback messageCB;
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
            enemy.setEnemyDeathCB((enemy1) -> this.enemyIsDead(enemy));
        }
    }

    public void gameTick(char c) {
        playerMove(c);
        player.processStep();
        if (flowEnemyList.size() == 0) {
            levelIsDone = true;
            System.out.print("to move level or end game");
        }
        for (Enemy e : flowEnemyList) {
            Position pos = e.enemyTurn(player);
            e.interact(level.getBoard().getTile(pos));
            if (!player.isAlive()){
                gameOver = true;
                System.out.print("to end game somehow");
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


    public void enemyIsDead(Enemy e) {
        flowEnemyList.remove(e);
        level.removeEnemy(e);
        player.setExperience(player.getExperience() + e.getExperienceValue());
        Tile empty = new Empty (e.getPosition());
        level.getBoard().add(empty);
        level.getBoard().remove(e);
        player.replacePosition(empty);
        messageCB.send(e.getName() + " is dead. "+ player.getName() +" gained "+ e.getExperienceValue() +" Experience");
    }


}
