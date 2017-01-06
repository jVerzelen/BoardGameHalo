package SpelElementen;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 12/08/13
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 */
public abstract class PlayerOrEnemy {
    public int location;
    public String name;
    public int health;
    public int maxHealth;
    public int meleeDamage;
    public double meleeHitChance;
    public double evasion;
    public double critChance;
    public double accuracyBonus;
    public double critBonus;
    public double damageBonus;
    public Image afbeelding;
    public int type;
    //type = 1 = UNSC
    //type = 2 = Covenant
    //type = 3 = Brutes
    public int minMovement;
    public int maxMovement;

    protected PlayerOrEnemy(int location,int type, String name, int health, int maxHealth, int meleeDamage, double meleeHitChance, double evasion, double accuracyBonus, double critBonus, double damageBonus,double critChance, int minMovement, int maxMovement) {
        this.location = location;
        this.type = type;
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.meleeDamage = meleeDamage;
        this.meleeHitChance = meleeHitChance;
        this.evasion = evasion;
        this.critChance = critChance;
        this.accuracyBonus = accuracyBonus;
        this.critBonus = critBonus;
        this.damageBonus = damageBonus;
        this.minMovement = minMovement;
        this.maxMovement = maxMovement;
    }

    public void setAfbeelding(Image afbeelding) {
        this.afbeelding = afbeelding;
    }
}
