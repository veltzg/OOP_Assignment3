package BusinessLayer.Tiles;

import BusinessLayer.GameManager.*;

public abstract class Enemy extends Unit {

    //fields:
    protected Integer experienceValue;

    public EnemyDeathCallback enemyDeathCB;

    public void setEnemyDeathCB(EnemyDeathCallback enemyDeathCB) {
        this.enemyDeathCB = enemyDeathCB;
    }

    //constructor:
    public Enemy(char tile, String name, Integer healthCapacity, Integer attackPoints, Integer defensePoints, Integer experienceValue) {
        super(tile, name, healthCapacity, attackPoints, defensePoints);
        this.experienceValue = experienceValue;
    }

    //methods:

    public Integer getExperienceValue() {
        return experienceValue;
    }

    public void accept(Unit u) { // the player attack the enemy.
        u.visit(this);
    }

    @Override
    public void visit(Enemy e) {}

    public void visit(Player p) { // the enemy attack the player.
        this.battle(p);
        if (!p.isAlive()) {
            p.onDeath();
        }
    }

    public abstract Position enemyTurn(Player p);

    @Override
    public void onDeath() {
        enemyDeathCB.call(this);
    }

    public String describe() {
        return String.format("%s\t\tExperience Value: %s", super.describe(), getExperienceValue());
    }
}
