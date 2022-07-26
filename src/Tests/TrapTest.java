package Tests;

import BusinessLayer.GameManager.MessageCallback;
import BusinessLayer.GameManager.Position;
import BusinessLayer.Tiles.Monster;
import BusinessLayer.Tiles.Trap;
import BusinessLayer.Tiles.Warrior;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TrapTest {

    Trap t1;
    Warrior w1;

    @BeforeEach
    public void setUp() {
        MessageCallback messageCallback = msg -> System.out.println(msg);
        t1 = new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10);
        t1.initialize(new Position(1, 1), messageCallback);
        w1 = new Warrior("Jon Snow", 300, 30, 4, 3);
        w1.initialize(new Position(1, 5), messageCallback);
    }

    @Test
    public void enemyTurn() {
        setUp();
        t1.enemyTurn(w1);
        Assert.assertTrue("The trap should be visible", t1.isVisible());
        t1.enemyTurn(w1);
        Assert.assertTrue("The trap should not be visible", !t1.isVisible());
        for (int i = 0; i <= 10; i++) {
            t1.enemyTurn(w1);
        }
        Assert.assertTrue("The trap should be visible", t1.isVisible());
    }
}