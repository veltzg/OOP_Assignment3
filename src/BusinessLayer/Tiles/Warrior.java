package BusinessLayer.Tiles;

import java.util.*;

public class Warrior extends Player{

    //fields:
    private final String ABILITY_NAME = "Avenger's Shield";
    private Integer abilityCooldown;
    private Integer remainingCooldown;
    private final Integer healing = 10;
    private final Integer castingRange = 3;

    //constructors:
    public Warrior(String name, Integer healthCapacity, Integer attackPoints, Integer defensePoints, Integer coolDown) {
        super(name, healthCapacity, attackPoints, defensePoints);
        this.abilityCooldown = coolDown;
        this.remainingCooldown = 0;
    }

    //methods:

    @Override
    public void castAbility(List<Enemy> allEnemies){
        if(remainingCooldown > 0)
            messageCB.send(getName() + " tried to cast " + ABILITY_NAME + ", but there is a cooldown: " +  remainingCooldown + ".");
        else {
            messageCB.send(getName() + " used " + ABILITY_NAME + " healing for " + (healing * getDefensePoints()));
            remainingCooldown = abilityCooldown;
            health.setHealthAmount(Math.min(health.getHealthAmount() + 10 * getDefensePoints(), health.getHealthPool()));
            List<Enemy> enemiesAround = findEnemiesWithingRange(allEnemies, castingRange);
            if(!enemiesAround.isEmpty()) {
                Enemy enemyToCast = enemiesAround.get((int) Math.random() * enemiesAround.size());
                attackWithAbility(enemyToCast, (int) (0.1 * health.getHealthPool()));
            }
        }
    }

    @Override
    public void processStep(){
        if (remainingCooldown > 0){
            remainingCooldown -= 1;
        }
    }

    @Override
    public void levelUp(){
        super.levelUp();
        messageCB.send(getName() + " reached level " + getPlayerLevel() +": +" + (getPlayerLevel() * (HEALTH_BONUS + 5)) + " Health, +" + ((2+ATTACK_BONUS) * getPlayerLevel()) + " Attack, +" + ((1 + DEFENSE_BONUS)* getPlayerLevel()) + " Defense");
        remainingCooldown = 0;
        getHealth().setHealthPool(getHealth().getHealthPool() + 5 * getPlayerLevel());
        getHealth().setHealthAmount(getHealth().getHealthPool());
        setAttackPoints(getAttackPoints() + 2 * getPlayerLevel());
        setDefensePoints(getDefensePoints() + 1 * getPlayerLevel());
    }

    @Override
    public String describe() {
        String s = super.describe();
        return String.format("%s\t\tCooldown: %s/%s ", s, remainingCooldown, abilityCooldown);
    }
}
