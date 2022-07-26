package Tests;

import BusinessLayer.GameManager.MessageCallback;
import BusinessLayer.GameManager.Position;
import BusinessLayer.Tiles.Enemy;
import BusinessLayer.Tiles.Monster;
import BusinessLayer.Tiles.Trap;
import BusinessLayer.Tiles.Warrior;
import org.junit.*;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class WarriorTest {

    Warrior w1;
    Warrior w2;
    List<Enemy> enemies;

    @BeforeEach
    public void setUp() {
        MessageCallback messageCallback= msg->System.out.println(msg);
        w1 =  new Warrior("Jon Snow", 300, 30, 4, 3);
        w1.initialize(new Position(3,4), messageCallback);
        w2 = new Warrior("The Hound", 400, 20, 6, 5);
        w2.initialize(new Position(6,10), messageCallback);
        enemies = new ArrayList<Enemy>();
        Monster e1 = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        e1.initialize(new Position(4,4), messageCallback);
        enemies.add(e1);
        Trap e2 = new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10);
        e2.initialize(new Position(3,8), messageCallback);
        enemies.add(e2);
    }

    @Test
    public void castAbility() {
        setUp();
        int health = 0;
        if(enemies.size() > 0){
            health = enemies.get(0).getHealth().getHealthAmount();
        }
        w1.castAbility(enemies);
        Assert.assertEquals("cast ability should change remaining cooldown to 3",3,w1.getRemainingCooldown(),0);
        Assert.assertTrue("cast ability should decrease or not change enemy's health", enemies.get(0).getHealth().getHealthAmount() <= health);
        health = enemies.get(0).getHealth().getHealthAmount();
        w1.castAbility(enemies);
        Assert.assertEquals("cast ability should not work, therefore enemy's health shouldn't change", health, enemies.get(0).getHealth().getHealthAmount());
    }

    @Test
    public void processStep() {
        setUp();
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