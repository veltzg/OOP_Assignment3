package Tests;

import BusinessLayer.GameManager.Position;
import BusinessLayer.Tiles.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TileTest {

    Tile t1;
    Tile t2;

    @BeforeEach
    public void setUp() {
        t1 = new Wall(new Position(1,1));
        t2 = new Empty(new Position(6,6));
    }

    @Test
    public void replacePosition(){
        setUp();
        Position t1InitPos = t1.getPosition();
        Position t2InitPos = t2.getPosition();
        t1.replacePosition(t2);
        Assert.assertTrue("Positions should be replaced", t1.getPosition().compareTo(t2InitPos) == 0);
        Assert.assertTrue("Positions should be replaced", t2.getPosition().compareTo(t1InitPos) == 0 );
    }
}
