package BusinessLayer.Tiles;

import java.util.*;

public abstract class Player extends Unit implements HeroicUnit {

    public static final char PLAYER_TILE = '@';
    protected final Integer EXPERIENCE_BONUS = 50;
    protected final Integer LEVEL_INCREASE = 1;
    protected final Integer HEALTH_BONUS = 10;
    protected final Integer ATTACK_BONUS = 4;
    protected final Integer DEFENSE_BONUS = 1;

    public Integer getExperience() {
        return experience;
    }

    public Integer getPlayerLevel() {
        return playerLevel;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
        if(experience >= EXPERIENCE_BONUS * playerLevel)
            levelUp();
    }

    public void setPlayerLevel(Integer playerLevel) {
        this.playerLevel = playerLevel;
    }

    private void setTile(char t){
        tile = t;
    }

    //fields:
    public Integer experience;
    protected Integer playerLevel;

    //constructor:
    protected Player(String name, Integer healthCapacity, Integer attackPoints, Integer defensePoints) {
        super(PLAYER_TILE, name, healthCapacity, attackPoints, defensePoints);
        this.experience = 0;
        this.playerLevel = 1;
    }

    //methods:

    protected void levelUp(){
        experience = experience - playerLevel * EXPERIENCE_BONUS;
        playerLevel = playerLevel + LEVEL_INCREASE;
        health.setHealthPool(health.getHealthPool() + HEALTH_BONUS * playerLevel);
        health.setHealthAmount(health.getHealthPool());
        attackPoints = attackPoints + ATTACK_BONUS * playerLevel;
        defensePoints = defensePoints + DEFENSE_BONUS * playerLevel;
    }

    public abstract void castAbility(List<Unit> enemies);
    public abstract void processStep();
    public  void onDeath(){
        setTile('X');
    }
    public void visit(Player p){}
    public void visit(Enemy e){
        battle(e);
        if(!e.isAlive()){
            e.onDeath();
        }
    }

    public List<Unit> findEnemiesWithingRange(List<Unit> enemies, int range) {
        List<Unit> result = new ArrayList<>();
        for (Unit e:
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
            messageCB.send(getName() + " hit " + e.getName() + " for " + result + " ability damage.");
        }
        else
            messageCB.send(getName() + " dealt 0 damage to " + e.getName() + ".");
    }


    public String describe(){
        String s = super.describe();
        return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", s, getPlayerLevel(), getExperience(), EXPERIENCE_BONUS * playerLevel );
    }

    @Override
    public void accept(Unit other){
        other.visit(this);
    }
}
