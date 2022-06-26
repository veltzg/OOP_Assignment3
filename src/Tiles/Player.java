package Tiles;

import java.util.*;

public abstract class Player extends Unit implements HeroicUnit {

    public static final char PLAYER_TILE = '@';
    protected final Integer EXPERIENCE_BONUS = 50;
    protected final Integer LEVEL_INCREASE = 1;
    protected final Integer HEALTH_BONUS = 10;
    protected final Integer ATTACK_BONUS = 4;
    protected final Integer DEFENSE_BONUS = 1;


    //fields:
    protected Integer experience;
    protected Integer playerLevel;

    //constructor:
    protected Player(String name, Integer healthCapacity, Integer attackPoints, Integer defensePoints) {
        super(PLAYER_TILE, name, healthCapacity, attackPoints, defensePoints);
        this.experience = 0;
        this.playerLevel = 1;
    }

    //methods:

    public Integer getExperience() {
        return experience;
    }

    public Integer getPlayerLevel() {
        return playerLevel;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void setPlayerLevel(Integer playerLevel) {
        this.playerLevel = playerLevel;
    }

    private void setTile(char t){
        tile = t;
    }

    protected void levelUp(){
        experience = experience - playerLevel * EXPERIENCE_BONUS;
        playerLevel = playerLevel + LEVEL_INCREASE;
        health.setHealthPool(health.getHealthPool() + HEALTH_BONUS * playerLevel);
        health.setHealthAmount(health.getHealthPool());
        attackPoints = attackPoints + ATTACK_BONUS * playerLevel;
        defensePoints = defensePoints + DEFENSE_BONUS * playerLevel;
    }

    public abstract void castAbility();

    public abstract void processStep();

    public  void onDeath(){
        setTile('X');
    }

    public void visit(Player p){}

    public void visit(Enemy e){
        battle(e);
        if(!e.isAlive()){
            setExperience(getExperience() + e.getExperienceValue());
            replacePosition(e);
            e.onDeath();
            enemyDeathCB.call(e, this);
            processStep();
        }
    }

    public List<Enemy> findEnemiesWithingRange(List<Enemy> enemies, int range) {
        List<Enemy> result = new ArrayList<>();
        for (Enemy e:
             enemies) {
            if(getPosition().range(e.getPosition()) < range)
                result.add(e);
        }
        return result;
    }

    protected void attackWithAbility(Enemy e, int attackPower){
        int defenseRoll = e.defend();
        int result = attackPower - defenseRoll;
        if (result > 0) {
            e.getHealth().setHealthAmount(e.getHealth().getHealthAmount() - result);
            messageCB.send(getName() + " hit " + e.getName() + " for " + result + "ability damage.");
        }
        else
            messageCB.send(getName() + " dealt 0 damage to " + e.getName() + ".");
    }


    public String describe(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", super.describe(), getPlayerLevel(), getExperience());
    }
}
