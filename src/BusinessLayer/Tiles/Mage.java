package BusinessLayer.Tiles;

import java.util.*;

public class Mage extends Player{

    public Integer getManaPool() {
        return manaPool;
    }

    public Integer getCurrentPool() {
        return currentPool;
    }

    public Integer getManaCost() {
        return manaCost;
    }

    public Integer getSpellPower() {
        return spellPower;
    }

    public Integer getHitsCount() {
        return hitsCount;
    }

    public Integer getAbilityRange() {
        return abilityRange;
    }

    public void setManaPool(Integer manaPool) {
        this.manaPool = manaPool;
    }

    public void setCurrentPool(Integer currentPool) {
        this.currentPool = currentPool;
    }

    public void setManaCost(Integer manaCost) {
        this.manaCost = manaCost;
    }

    public void setSpellPower(Integer spellPower) {
        this.spellPower = spellPower;
    }

    public void setHitsCount(Integer hitsCount) {
        this.hitsCount = hitsCount;
    }

    public void setAbilityRange(Integer abilityRange) {
        this.abilityRange = abilityRange;
    }

    //fields:
    protected Integer manaPool;
    protected Integer currentPool;
    protected Integer manaCost;
    protected Integer spellPower;
    protected Integer hitsCount;
    protected Integer abilityRange;
    private final Integer MANA_BONUS = 25;
    private final Integer SPELL_BONUS = 10;
    private final Integer CURRENT_POOL_TICK_BONUS = 1;
    private final String ABILITY_NAME = "Blizzard";
    private boolean abilityCasted = false;

    //constructor:
    public Mage(String name, Integer healthCapacity, Integer attackPoints, Integer defensePoints, Integer manaPool, Integer manaCost, Integer spellPower, Integer hitsCount, Integer abilityRange) {
        super(name, healthCapacity, attackPoints, defensePoints);
        this.manaPool = manaPool;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
        this.currentPool = manaPool / 4;
    }

    //methods:

    @Override
    public void levelUp(){
        super.levelUp();
        messageCB.send(getName() + " reached level " + getPlayerLevel() +": +" + (getPlayerLevel() * HEALTH_BONUS) + " Health, +" + (ATTACK_BONUS * getPlayerLevel()) + " Attack, +" + (DEFENSE_BONUS* getPlayerLevel()) + " Defense, +" + (MANA_BONUS * getPlayerLevel()) + " maximum Mana, +" + (SPELL_BONUS * getPlayerLevel() + " spell power"));
        setManaPool(getManaPool() * MANA_BONUS * getPlayerLevel());
        setManaCost(Math.min((int)(getManaPool() * 0.25 + getCurrentPool()), manaPool));
        setSpellPower(getSpellPower() + SPELL_BONUS * getPlayerLevel());
        if(getExperience() >= EXPERIENCE_BONUS * getPlayerLevel())
            levelUp();
    }

    @Override
    public void processStep(){
        if(abilityCasted)
            abilityCasted = false;
        else
            setCurrentPool(Math.min(manaPool, getCurrentPool() + CURRENT_POOL_TICK_BONUS * getPlayerLevel()));
    }

    @Override
    public void castAbility(List<Enemy> enemies) {
        if(getCurrentPool() < getManaCost())
            messageCB.send((getName() + "  tried to cast " + ABILITY_NAME + ", but there was not enough mana: " + currentPool + "/" + manaCost + "."));
        else {
            messageCB.send(getName() + " cast " + ABILITY_NAME + ".");
            setCurrentPool(getCurrentPool() - getManaCost());
            int hits = 0;
            abilityCasted = true;
            List<Enemy> enemiesAround = findEnemiesWithingRange(enemies, abilityRange);
            while (hits < hitsCount && !enemiesAround.isEmpty()){
                Enemy e = enemiesAround.get((int)(Math.random() * enemiesAround.size()));
                attackWithAbility(e, getSpellPower());
                if(!e.isAlive()) {
                    enemiesAround.remove(e);
                    e.onDeath();
                }
                hits ++;
            }
        }
    }

    @Override
    public String describe(){
        String s = super.describe();
        return String.format("%s\t\tMana: %s/%s \t\tSpell Power: %d", s, currentPool, manaPool, spellPower);
    }
}
