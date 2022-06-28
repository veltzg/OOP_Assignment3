package BusinessLayer.Tiles;

import java.util.List;

public class Hunter extends Player{


    protected Hunter(String name, Integer healthCapacity, Integer attackPoints, Integer defensePoints) {
        super(name, healthCapacity, attackPoints, defensePoints);
    }

    @Override
    public void castAbility(List<Enemy> enemies) {

    }

    @Override
    public void processStep() {

    }
}
