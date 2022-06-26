package Tiles;

import GameManager.Position;

import java.util.List;

public class Boss extends Enemy implements HeroicUnit{



    //fields:
    protected Integer visionRange;
    protected Integer abilityFrequency;
    protected Integer combatTicks;

    //constructor:

    public Boss(char tile, String name, Integer healthPool, Integer attackPoints, Integer defensePoints, Integer experienceValue, Integer visionRange, Integer abilityFrequency) {
        super(tile, name, healthPool, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
        this.abilityFrequency = abilityFrequency;
        this.combatTicks = 0;
    }

    //methods:

    public Integer getVisionRange() {
        return visionRange;
    }

    public void setVisionRange(Integer visionRange) {
        this.visionRange = visionRange;
    }

    public Integer getAbilityFrequency() {
        return abilityFrequency;
    }

    public void setAbilityFrequency(Integer abilityFrequency) {
        this.abilityFrequency = abilityFrequency;
    }

    public Integer getCombatTicks() {
        return combatTicks;
    }

    public void setCombatTicks(Integer combatTicks) {
        this.combatTicks = combatTicks;
    }

    public Position randomMove(){
        int rand = (int)(Math.random() * 5);
        if (rand == 0)
            return (this.getPosition());
        if (rand == 1)
            return (this.getPosition().moveLeft());
        if (rand == 2)
            return (this.getPosition().moveRight());
        if (rand == 3)
            return (this.getPosition().moveUp());
        if (rand == 4)
            return (this.getPosition().moveDown());
        return null;
    }

    public Position chase(Player p){
        int dx = this.getPosition().getX() - p.getPosition().getX();
        int dy = this.getPosition().getY() - p.getPosition().getY();
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0)
                return getPosition().moveLeft();
            else
                return getPosition().moveRight();
        }
        else {
            if (dy > 0)
                return getPosition().moveUp();
            else
                return getPosition().moveDown();
        }
    }



    @Override
    public Position enemyTurn(Player p) {
        if (this.getPosition().range(p.getPosition()) < visionRange) {
            if (combatTicks == abilityFrequency) {
               System.out.print("castAbility");
            }
            else {
                combatTicks++;
                return chase(p);
            }
        }
        else {
            combatTicks = 0;
            return randomMove();
        }
        return null;
    }


    @Override
    public void processStep() {
    }

    @Override
    public void castAbility(List<Enemy> enemies) {

    }
}
