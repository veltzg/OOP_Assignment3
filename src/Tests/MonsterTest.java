package Tests;

import BusinessLayer.GameManager.MessageCallback;
import BusinessLayer.GameManager.Position;
import BusinessLayer.Tiles.Monster;
import BusinessLayer.Tiles.Warrior;
import org.junit.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.Test;

public class MonsterTest {

    Monster m1;
    Warrior w1;

    @BeforeEach
    public void setUp() {
        MessageCallback messageCallback = msg -> System.out.println(msg);
        m1 = new Monster('q', "Queen's Guard", 400, 20, 15, 100,  5);
        m1.initialize(new Position(1, 1), messageCallback);
        w1 = new Warrior("Jon Snow", 300, 30, 4, 3);
        w1.initialize(new Position(1, 5), messageCallback);
    }

    @Test
    public void chase() {
        setUp();
        m1.setPosition(m1.chase(w1));
        Assert.assertEquals("The distance between the enemy and the player should be 3",3,m1.getPosition().range(w1.getPosition()));
        m1.setPosition(m1.chase(w1));
        Assert.assertEquals("The distance between the enemy and the player should be 2",2,m1.getPosition().range(w1.getPosition()));
    }

    @Test
    public void randomMove() {
        setUp();
        Position initialPos = m1.getPosition();
        Position pos1 = m1.randomMove();
        Assert.assertTrue("The Monster should make a movement", pos1 != initialPos);
        Position pos2 = m1.randomMove();
        Position pos3 = m1.randomMove();
        Position pos4 =  m1.randomMove();
        Assert.assertTrue("Moves should be random - 4 random moves should not be identical", (pos1 != pos2 || pos1 != pos3 || pos1 != pos4 || pos2 != pos3 || pos2 != pos4 || pos3 != pos4));
    }
}