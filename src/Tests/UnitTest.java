package Tests;

import BusinessLayer.GameManager.MessageCallback;
import BusinessLayer.GameManager.Position;
import BusinessLayer.Tiles.Monster;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Tiles.Warrior;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class UnitTest {

    Monster m1;
    Warrior w1;

    @BeforeEach
    public void setUp() {
        MessageCallback messageCallback = msg -> System.out.println(msg);
        m1 = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        m1.initialize(new Position(1, 1), messageCallback);
        w1 = new Warrior("Jon Snow", 300, 30, 4, 3);
        w1.initialize(new Position(1, 2), messageCallback);
    }

    @Test
    public void battleTest() {
        setUp();
        String attackerDes = m1.describe();
        int attackedH = w1.getHealth().getHealthAmount();
        m1.battle(w1);
        Assert.assertTrue("Attacker's (monster) vitals should not change in a battle", (attackerDes.equals(m1.describe())));
        Assert.assertTrue("Attacked's (warrior) Health should decrease or not change at all after a battle",(w1.getHealth().getHealthAmount() <= attackedH));
        attackerDes = w1.describe();
        attackedH = m1.getHealth().getHealthAmount();
        w1.battle(m1);
        Assert.assertTrue("Attacker's (warrior) vitals should not change in a battle", (attackerDes.equals(w1.describe())));
        Assert.assertTrue("Attacked's (monster) Health should decrease or not change at all after a battle",(m1.getHealth().getHealthAmount() <= attackedH));
        int monExp = m1.getExperienceValue();
        int warExp = w1.getExperience();
        Position monPos = m1.getPosition();
        while (m1.getHealth().getHealthAmount() > 0 ){
            w1.battle(m1);
        }
        Assert.assertEquals("When enemy is dead, 'isAlive' field turns False", false, m1.isAlive());
    }
}
