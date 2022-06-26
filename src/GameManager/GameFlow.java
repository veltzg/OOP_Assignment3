package GameManager;

import Tiles.*;
import java.util.*;
import java.util.stream.Collectors;

public class GameFlow {

    //fields:
    private Level level;
    private Player player;
    private List<Enemy> flowEnemyList;
    private MessageCallback messageCB;

    //constructors:

    public GameFlow (Level level, Player player)
    {
        this.level = level;
        this.player = player;
        this.flowEnemyList = level.getLevelEnemyList();
    }

    public void playerMove(char c) {
        Position playerPosition = player.getPosition();
        switch (c) {
            case 'w':
                player.interact(level.getBoard().getTile(playerPosition.moveUp()));
                break;
            case 's':
                player.interact(level.getBoard().getTile(playerPosition.moveDown()));
                break;
            case 'd':
                player.interact(level.getBoard().getTile(playerPosition.moveRight()));
                break;
            case 'a':
                player.interact(level.getBoard().getTile(playerPosition.moveLeft()));
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

    public void gameTick(char c) {
        playerMove(c);
        player.processStep();
        this.flowEnemyList = level.getLevelEnemyList();
        if (flowEnemyList.size() == 0) {
            System.out.print("to move level or end game");
        }
        for (Enemy e : flowEnemyList) {
            e.setPosition(e.gameEnemyTick(player));
            if (!player.isAlive())
                System.out.print("to end game somehow");
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
    }
}
