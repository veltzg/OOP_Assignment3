package Tests;

import BusinessLayer.Tiles.Enemy;
import BusinessLayer.Tiles.Monster;
import BusinessLayer.Tiles.Trap;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import BusinessLayer.GameManager.MessageCallback;
import BusinessLayer.GameManager.Position;
import BusinessLayer.Tiles.Mage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {

    Mage m1;
    Mage m2;
    List<Enemy> enemies;

    @BeforeEach
    void setUp() {
        MessageCallback messageCallback= msg->System.out.println(msg);
        m1 =  new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5,6);
        m1.initialize(new Position(3,4), messageCallback);
        m2 =  new Mage("Thores of Myr", 250,25,4,150,20,20,3,4);
        m2.initialize(new Position(6,10), messageCallback);
        enemies = new ArrayList<Enemy>();
        Monster e1 = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        e1.initialize(new Position(8,10), messageCallback);
        enemies.add(e1);
        Trap e2 = new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10);
        e2.initialize(new Position(6,8), messageCallback);
        enemies.add(e2);
        Monster e3 = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        e3.initialize(new Position(8,10), messageCallback);
        enemies.add(e3);
        Trap e4 = new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10);
        e4.initialize(new Position(6,15), messageCallback);
        enemies.add(e4);
    }

    @Test
    public void levelUp() {
        setUp();
        m1.experience = 75;
        m1.levelUp();
        Assert.assertEquals("leveling up should change experience to 25",25,m1.getExperience(),0);
        Assert.assertEquals("leveling up should change level to 2",2,m1.getPlayerLevel(),0);
        Assert.assertEquals("leveling up should change healthPool to 120",120,m1.getHealth().getHealthPool(),0);
        Assert.assertEquals("leveling up should change attack to 13",13,m1.getAttackPoints(),0);
        Assert.assertEquals("leveling up should change defense to 3",3,m1.getDefensePoints(),0);
        Assert.assertEquals("leveling up should change mana pool to 350",350 ,m1.getManaPool(),0);
        Assert.assertEquals("leveling up should change mana cost to 162",162 ,m1.getCurrentPool(),0);
        Assert.assertEquals("leveling up should change spell power to 35",35 ,m1.getSpellPower(),0);

    }

    @Test
    public void processStep() {
        setUp();
        m2.processStep();
        Assert.assertEquals("process step should change current mana to 38", 38, m2.getCurrentPool(),0);
        m2.setCurrentPool(149);
        m2.setPlayerLevel(2);
        m2.processStep();
        Assert.assertEquals("process step should change remaining current mana to 150",150,m2.getCurrentPool(),0);
    }

    @Test
    public void castAbility() {
        setUp();
        int health1 = 0;
        int health2 = 0;
        int health3 = 0;
        if(enemies.size() > 0){
            health1 = enemies.get(0).getHealth().getHealthAmount();
            health2 = enemies.get(1).getHealth().getHealthAmount();
            health3 = enemies.get(2).getHealth().getHealthAmount();
        }
        m2.castAbility(enemies);
        Assert.assertEquals("cast ability should change current mana to 17",17,m2.getCurrentPool(),0);
        Assert.assertTrue("cast ability should decrease or not change enemy's health", enemies.get(0).getHealth().getHealthAmount() < health1 || enemies.get(1).getHealth().getHealthAmount() < health2 ||enemies.get(2).getHealth().getHealthAmount() < health3);
    }

}