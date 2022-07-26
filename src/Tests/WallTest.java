package Tests;

import BusinessLayer.GameManager.MessageCallback;
import BusinessLayer.GameManager.Position;
import BusinessLayer.Tiles.Monster;
import BusinessLayer.Tiles.Trap;
import BusinessLayer.Tiles.Wall;
import BusinessLayer.Tiles.Warrior;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class WallTest {

    Monster m1;
    Warrior w1;
    Wall wall;

    @BeforeEach
    public void setUp() {
        MessageCallback messageCallback = msg -> System.out.println(msg);
        m1 = new Monster('q', "Queen's Guard", 400, 20, 15, 100,  5);
        m1.initialize(new Position(1, 1), messageCallback);
        w1 = new Warrior("Jon Snow", 300, 30, 4, 3);
        w1.initialize(new Position(1, 5), messageCallback);
        wall = new Wall (new Position(6,6));
    }

    @Test
    public void accept() {
        setUp();
        wall.accept(w1);
        Position wallPos = wall.getPosition();
        Position monPos = m1.getPosition();
        Position warPos = w1.getPosition();
        wall.accept(m1);
        Assert.assertTrue("Wall should obligate enemy's movement", m1.getPosition().compareTo(monPos) == 0);
        wall.accept(w1);
        Assert.assertTrue("Wall should obligate player's movement", w1.getPosition().compareTo(warPos) == 0);
    }
}