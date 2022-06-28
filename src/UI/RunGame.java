package UI;

import BusinessLayer.GameManager.GameFlow;
import BusinessLayer.GameManager.Level;
import BusinessLayer.GameManager.MessageCallback;
import BusinessLayer.GameManager.TileFactory;
import BusinessLayer.Tiles.*;

import java.util.List;
import java.util.Scanner;

public class RunGame {

    //fields:
    private TileFactory tiles;
    private MessageCallback msc;
    private FileParser fp;
    private List<Level> levelsList;
    private GameFlow gameFlow;

    //methods:
    public RunGame(MessageCallback msc, FileParser fp){
        tiles = new TileFactory();
        this.fp = fp;
    }

    public void runGame() {
        Scanner scanner = new Scanner(System.in);
        tiles.printPlayers(msc);
        Player player = choosePlayer(scanner);
        msc.send("You have selected:");
        msc.send(player.getName());
        levelsList = fp.produceLevels(tiles);
        if (!levelsList.isEmpty()) {
            gameFlow = new GameFlow(levelsList[0], player, msc);
            for (Level level :
                    levelsList) {
                gameFlow.setGameLevel(level);
                while (!gameFlow.getGameOver() & !gameFlow.getLevelIsDone()) {
                    gameFlow.printBoard();
                    String userChoice = scanner.next();
                    gameFlow.gameTick(userChoice.charAt(0));
                }
                if(gameFlow.getGameOver()){
                    gameFlow.printBoard();
                    player.describe();
                    msc.send("Game Over.");
                    break;
                }
            }
            msc.send("You Won!");
        }
    }

    private Player choosePlayer(Scanner scanner){
        int chosenPlayer = scanner.nextInt();
        while (chosenPlayer<1 || chosenPlayer>6) {
            msc.send("The selected player should be one of the following: 1, 2, 3, 4, 5, 6, 7.");
            chosenPlayer = scanner.nextInt();
        }
        Player player = tiles.producePlayer(chosenPlayer);
        return player;
    }

}
