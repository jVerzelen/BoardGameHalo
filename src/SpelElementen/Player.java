package SpelElementen;

import SpelElementen.PlayerOrEnemy;
import SpelElementen.Weapon;
import Weapons.Pistol;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 12/08/13
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
public class Player extends PlayerOrEnemy {
    public Weapon weapon1;
    public Weapon weapon2;
    public int gold;
    public double stealth;// laat je toe binnen enemy vision te komen zonder hij reageert
    public double stealthChance;

    public Player(String name,int type, int health, int maxHealth, int meleeDamage, double meleeHitChance,
                  double evasion,double critChance, double accuracyBonus, double critBonus, double damageBonus, Weapon weapon1, int minMovement, int maxMovement, double stealth, double stealthChance) {
        super(0,type, name, health, maxHealth, meleeDamage, meleeHitChance, evasion,accuracyBonus, critBonus, damageBonus, critChance, minMovement, maxMovement);
        //int location,int type, String name, int health, int maxHealth, int meleeDamage, double meleeHitChance, double evasion, double accuracyBonus, double critBonus, double damageBonus,double critChance
        this.weapon1 = weapon1;
        this.gold = 0;
        this.stealth = stealth;
        this.stealthChance = stealthChance;
    }


    public String showBothWeapons(){
        try{
            return "\nHuidige wapens:\nWapen 1:\n" + weapon1.toString() +  "\nWapen2:\n" + weapon2.toString();
        }catch (NullPointerException error){
            return "\nHuidige wapens:\nWapen 1:\n" + weapon1.toString();
        }
    }

    @Override
    public String toString() {
        return "name: " + name+"\nhealth: " + health + "/" + maxHealth+"\n\nmelee damage: "+meleeDamage+"\nmelee hit chance: "
                + (int)Math.round(meleeHitChance*100) +"%\n\nmovement: " + minMovement + "-" + maxMovement + "\nevasion: " + (int)Math.round((1-evasion)*100) +"%\nstealth: "
                + (int)Math.round(stealth*100)+"%\nstealth chance: " + (int)Math.round(stealthChance*100)+"%\n\naccuracy bonus: " + (int)Math.round((accuracyBonus -1)*100)+"%\ncrit chance bonus: "
                + (int)Math.round((critBonus-1)*100)+"%\ncrit resistance: " + (int)Math.round((1-critChance)*100)+"%\ndamage bonus: "+(int)Math.round((damageBonus-1)*100)+"%\n\ngold: " + gold;
    }
}
