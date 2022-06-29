package UI;
import BusinessLayer.GameManager.MessageCallback;
import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        MessageCallback messageCallback= (m)-> System.out.println(m);
        FileParser fileParser = new FileParser(args[0], messageCallback);
        RunGame rg = new RunGame(messageCallback, fileParser);
        rg.runGame();
    }
}
