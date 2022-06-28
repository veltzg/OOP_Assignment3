package UI;

import BusinessLayer.GameManager.MessageCallback;

public class MainClass {
    public static void main(String[] args) {
        MessageCallback messageCallback= msg->System.out.println(msg);
        FileParser fileParser=new FileParser(args[0]);
        RunGame rg = new RunGame(messageCallback, fileParser);
        rg.runGame();

    }
}
