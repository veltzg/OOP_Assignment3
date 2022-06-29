package Tests;

import BusinessLayer.GameManager.MessageCallback;
import BusinessLayer.GameManager.Position;
import BusinessLayer.Tiles.Warrior;
import org.junit.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.Test;

public class WarriorTest {

    Warrior w1;
    Warrior w2;

    @BeforeEach
    void setUp() {
        MessageCallback messageCallback= msg->System.out.println(msg);
        Warrior w1 =  new Warrior("Jon Snow", 300, 30, 4, 3);
        w1.initialize(new Position(3,4), messageCallback);
        Warrior w2 = new Warrior("The Hound", 400, 20, 6, 5);
        w2.initialize(new Position(6,10), messageCallback);
    }

    @After
    void tearDown() {
    }

    @Test
    void castAbility() {
    }

    @Test
    void processStep() {
        w2.processStep();
        Assert.assertEquals("process step should change remaining cooldown to 0",0,w2.getRemainingCooldown(),0);
        w2.setRemainingCooldown(3);
        w2.processStep();
        Assert.assertEquals("process step should change remaining cooldown to 2",2,w2.getRemainingCooldown(),0);
    }

    @Test
    void levelUp() {
        w1.experience = 75;
        w1.levelUp();
        Assert.assertEquals("leveling up should change experience to 25",25,w1.getExperience(),0);
        Assert.assertEquals("leveling up should change level to 2",2,w1.getPlayerLevel(),0);
        Assert.assertEquals("leveling up should change healthPool to 330",330,w1.getHealth().getHealthPool(),0);
        Assert.assertEquals("leveling up should change attack to 42",42,w1.getAttackPoints(),0);
        Assert.assertEquals("leveling up should change defense to 42",8,w1.getDefensePoints(),0);
        Assert.assertEquals("leveling up should change cooldown to 0",0,w1.getRemainingCooldown(),0);
    }

    @Test
    void describe() {
    }
}