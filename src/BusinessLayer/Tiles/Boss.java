package BusinessLayer.Tiles;

import BusinessLayer.GameManager.Position;

import java.util.ArrayList;
import java.util.List;

public class Boss extends Enemy implements HeroicUnit{


    //fields:
    protected Integer visionRange;
    protected Integer abilityFrequency;
    protected Integer combatTicks;
    private final String ABILITY_NAME = "Shoot";

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
            return (this.getPosition().stepLeft());
        if (rand == 2)
            return (this.getPosition().stepRight());
        if (rand == 3)
            return (this.getPosition().stepUp());
        if (rand == 4)
            return (this.getPosition().stepDown());
        return null;
    }

    public Position chase(Player p){
        int dx = this.getPosition().getX() - p.getPosition().getX();
        int dy = this.getPosition().getY() - p.getPosition().getY();
        Position posToReturn;
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0)
                posToReturn = getPosition().stepLeft();
            else
                posToReturn = getPosition().stepRight();
        }
        else {
            if (dy > 0)
                posToReturn = getPosition().stepUp();
            else
                posToReturn = getPosition().stepDown();
        }
        return posToReturn;
    }



    @Override
    public Position enemyTurn(Player p) {
        if (this.getPosition().range(p.getPosition()) < visionRange) {
            if (combatTicks == abilityFrequency) {
                combatTicks = 0;
                List<Unit> player = new ArrayList<>();
                player.add(p);
                castAbility(player);
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
        return getPosition();
    }

    protected void attackWithAbility(Player p, int attackPower){
        int defenseRoll = p.defend();
        int result = attackPower - defenseRoll;
        if (result > 0) {
            p.getHealth().setHealthAmount(p.getHealth().getHealthAmount() - result);
            messageCB.send(getName() + " hit " + p.getName() + " for " + result + " ability damage.");
        }
        else
            messageCB.send(getName() + " dealt 0 damage to " + p.getName() + ".");
    }

    @Override
    public void processStep() {
    }

    @Override
    public void castAbility(List<Unit> player) {
        Player p = (Player) player.get(0);
        messageCB.send(getName() + " shoots " + ABILITY_NAME + " for " + (getAttackPoints()));
        attackWithAbility(p, getAttackPoints());
        if(!p.isAlive())
            p.onDeath();
    }
}
