package Tests;

import BusinessLayer.GameManager.EnemyDeathCallback;
import BusinessLayer.GameManager.MessageCallback;
import BusinessLayer.Tiles.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import BusinessLayer.GameManager.Position;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RogueTest {

    Rogue r1;
    Rogue r2;
    List<Unit> enemies;

    @BeforeEach
    void setUp() {
        MessageCallback messageCallback= msg->System.out.println(msg);
        EnemyDeathCallback enemyDeathCallback = e -> e = e;
        r1 = new Rogue("Arya Stark", 150, 40, 2, 20);
        r1.initialize(new Position(3,4), messageCallback);
        r2 = new Rogue("Bronn", 250, 35, 3, 50);
        r2.initialize(new Position(6,10), messageCallback);
        enemies = new ArrayList<Unit>();
        Monster e1 = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        e1.initialize(new Position(7,10), messageCallback);
        e1.setEnemyDeathCB(enemyDeathCallback);
        enemies.add(e1);
        Trap e2 = new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10);
        e2.initialize(new Position(6,11), messageCallback);
        e2.setEnemyDeathCB(enemyDeathCallback);
        enemies.add(e2);
        Monster e3 = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        e3.initialize(new Position(8,10), messageCallback);
        e3.setEnemyDeathCB(enemyDeathCallback);
        enemies.add(e3);
        Trap e4 = new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10);
        e4.initialize(new Position(6,15), messageCallback);
        e4.setEnemyDeathCB(enemyDeathCallback);
        enemies.add(e4);
    }

    @Test
    public void castAbility() {
        setUp();
        int health1 = enemies.get(0).getHealth().getHealthAmount();
        int health2 = enemies.get(1).getHealth().getHealthAmount();
        r2.castAbility(enemies);
        Assert.assertEquals("casting ability should change current energy to 50", 50, r2.getCurrentEnergy(), 0);
        Assert.assertTrue("cast ability should decrease or not change enemy's health", enemies.get(0).getHealth().getHealthAmount() < health1 || enemies.get(1).getHealth().getHealthAmount() < health2);
    }

    @Test
    public void levelUp() {
        setUp();
        r1.experience = 75;
        r1.levelUp();
        Assert.assertEquals("leveling up should change experience to 25",25,r1.getExperience(),0);
        Assert.assertEquals("leveling up should change level to 2",2,r1.getPlayerLevel(),0);
        Assert.assertEquals("leveling up should change healthPool to 170",170,r1.getHealth().getHealthPool(),0);
        Assert.assertEquals("leveling up should change attack to 54",54,r1.getAttackPoints(),0);
        Assert.assertEquals("leveling up should change defense to 4",4,r1.getDefensePoints(),0);
        Assert.assertEquals("leveling up should change energy to 100",100,r1.getCurrentEnergy(),0);
    }

    @Test
    public void processStep() {
        setUp();
        r2.setCurrentEnergy(70);
        r2.processStep();
        Assert.assertEquals("process step should change current enegy to 80", 80, r2.getCurrentEnergy(),0);
        r2.setCurrentEnergy(195);
        r2.processStep();
        Assert.assertEquals("process step should change remaining current mana to 100",100,r2.getCurrentEnergy(),0);
    }

    @Test
    public void findEnemiesWithingRange() {
        setUp();
        List<Unit> enemies1 = r2.findEnemiesWithingRange(enemies, 2);
        Assert.assertTrue("e1,e2 is in range 2 from r2", enemies.contains(enemies1.get(0)) && enemies.contains(enemies1.get(1)));
        enemies1 = r1.findEnemiesWithingRange(enemies, 2);
        Assert.assertEquals("no enemy is in range 2 from r1", 0, enemies1.size());
    }
}