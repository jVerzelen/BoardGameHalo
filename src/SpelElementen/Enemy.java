package SpelElementen;


/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 12/08/13
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public abstract class Enemy extends PlayerOrEnemy {
    public Weapon weapon;
    public double normalRes;
    public double accurateRes;
    public double explosiveRes;
    public double energyRes;
    public int moneyDrop;
    public int vision; // hoe ver de vijand kan zien en vanaf wanneer hij kan schieten
    public boolean alert = false; // als de vijand je ziet of nadat je op hem schiet wordt hij alert en heeft stealth geen nut meer en heb je ook geen suprise attack meer


    protected Enemy(int location,int type, String name, int health, int maxHealth, int meleeDamage, double meleeHitChance, double evasion, double accuracyBonus, double critBonus, double damageBonus, double critChance, double normalRes, double accurateRes, double explosiveRes, double energyRes, int moneyDrop, int minMovement, int maxMovement, int vision) {
        super(location,type, name, health, maxHealth, meleeDamage, meleeHitChance, evasion, accuracyBonus, critBonus, damageBonus,critChance, minMovement, maxMovement);
        this.normalRes = normalRes;
        this.accurateRes = accurateRes;
        this.explosiveRes = explosiveRes;
        this.energyRes = energyRes;
        this.moneyDrop = moneyDrop;
        this.vision = vision;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }



    @Override
    public String toString() {
        return "name: " + name+"\nhealth: " + health + "/" + maxHealth+"\nvision range: " + vision +"\n\nmelee damage: "+meleeDamage+"\nmelee hit chance: "
                + (int)Math.round(meleeHitChance*100) +"%\n\nmovement: " + minMovement + "-" + maxMovement + "\nevasion: " + (int)Math.round((1-evasion)*100) +"%"
                + "\n\naccuracy bonus: " + (int)Math.round((accuracyBonus -1)*100)+"%\ncrit chance bonus: "
                + (int)Math.round((critBonus-1)*100)+"%\ncrit resistance: " + (int)Math.round((1-critChance)*100)+"%\ndamage bonus: "+(int)Math.round((damageBonus-1)*100)+"%"
                + "\n\nnormal damage taken: " + (int)(normalRes*100) + "%" + "\naccurate damage taken: " + (int)(accurateRes*100) +"%"
                + "\nenergy damage taken: " + (int)(energyRes*100) +"%" +"\nexplosive damage taken: " + (int)(explosiveRes*100) +"%\n\ngold drop: " + moneyDrop;
    }
}
