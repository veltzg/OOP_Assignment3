package UI;
import BusinessLayer.GameManager.MessageCallback;
import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        System.out.print("HELP");
        MessageCallback messageCallback= (m)-> System.out.println(m);
        System.out.print("HELP2.0");
        FileParser fileParser = new FileParser(args[0], messageCallback);
        RunGame rg = new RunGame(messageCallback, fileParser);
        rg.runGame();
    }
}
