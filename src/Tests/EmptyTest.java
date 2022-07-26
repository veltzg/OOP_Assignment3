package Tests;

import BusinessLayer.GameManager.MessageCallback;
import BusinessLayer.GameManager.Position;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Monster;
import BusinessLayer.Tiles.Wall;
import BusinessLayer.Tiles.Warrior;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class EmptyTest {

    Monster m1;
    Warrior w1;
    Empty e1;

    @BeforeEach
    public void setUp() {
        MessageCallback messageCallback = msg -> System.out.println(msg);
        m1 = new Monster('q', "Queen's Guard", 400, 20, 15, 100,  5);
        m1.initialize(new Position(1, 1), messageCallback);
        w1 = new Warrior("Jon Snow", 300, 30, 4, 3);
        w1.initialize(new Position(1, 5), messageCallback);
        e1 = new Empty (new Position(6,6));
    }

    @Test
    public void accept() {
        setUp();
        e1.accept(w1);
        Position empPos = e1.getPosition();
        Position monPos = m1.getPosition();
        Position warPos = w1.getPosition();
        e1.accept(m1);
        Assert.assertTrue("Empty tile should replace Positions with enemy", m1.getPosition().compareTo(empPos) == 0);
        e1.setPosition(empPos);
        e1.accept(w1);
        Assert.assertTrue("Empty tile should replace Positions with player", w1.getPosition().compareTo(empPos) == 0);
    }
}