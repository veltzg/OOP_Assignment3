package Tests;

import BusinessLayer.GameManager.MessageCallback;
import BusinessLayer.GameManager.Position;
import BusinessLayer.Tiles.Mage;
import BusinessLayer.Tiles.Player;
import BusinessLayer.Tiles.Rogue;
import BusinessLayer.Tiles.Warrior;
import org.junit.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.Test;

public class playerTest{
    Player warrior;
    Player mage;
    Player rogue;
    @Before
    public void setUp() throws Exception {
        warrior =  new Warrior("Jon Snow", 300, 30, 4, 3);
        mage = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5,6);
        rogue = new Rogue("Arya Stark", 150, 40, 2, 20);

        MessageCallback messageCallback= msg->System.out.println(msg);

    }

    @Test
    public void LevelUpWarrior() {
        warrior.gainExperience(50);
        Assert.assertEquals(2, warrior.getLevel());
        Assert.assertEquals(115,warrior.getHealth().getHealthPool());
        Assert.assertEquals(106, warrior.getAttack());

    }

    @Test
    public void LevelUpMage() {
        mage.gainExperience(50);
        Assert.assertEquals(2, mage.getLevel());
        Assert.assertEquals(110, mage.getHealth().getHealthPool());
        Assert.assertEquals(104, mage.getAttack());
    }

    @Test
    public void LevelUpRogue() {
        rogue.gainExperience(50);
        Assert.assertEquals(2, rogue.getLevel());
        Assert.assertEquals(110, rogue.getHealth().getHealthPool());
        Assert.assertEquals(107, rogue.getAttack());
    }
}