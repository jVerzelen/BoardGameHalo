package SpelElementen;

import java.awt.*;
import java.security.PublicKey;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 12/08/13
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public abstract class Weapon {
    public String name;
    public String soort;
    public int damage;
    public double accuracy;
    public int range;
    public int magazine;
    public int currAmmo;
    public int maxAmmo;
    public double critChance;
    public boolean unscWeapon;
    public Image afbeelding;
     /*
    Random random = new Random();
    int r;
    int sprong;
    int hoeveelste; */

    Weapon compare1 = null;
    Weapon compare2 = null;

    public Weapon(String name,String soort, int damage, double accuracy, int range, int magazine, int currAmmo, int maxAmmo, double critChance,boolean unscWeapon) {
        this.name = name;
        this.soort = soort;
        this.damage = damage;
        this.accuracy = accuracy;
        this.range = range;
        this.magazine = magazine;
        this.currAmmo = currAmmo;
        this.maxAmmo = maxAmmo;
        this.critChance = critChance;
        this.unscWeapon = unscWeapon;
    }

    public String compareWeapon(Weapon weapon2){
        return "weapon: " + name + " (" + weapon2.name + ")" +"\nsoort damage: " + soort + " (" + weapon2.soort +")" +"\n\ndamage: "+damage+ " (" + (weapon2.damage) +")" +
                "\naccuracy: " + (int)(accuracy*100)+ "% (" +(((int)(weapon2.accuracy*100)) +"%)"
                +"\nrange: " + range+ " (" + (weapon2.range) + ")" +"\nmagazine size: " + magazine+ " (" + (weapon2.magazine) + ")"+
                "\nammo: " + currAmmo + "/" + maxAmmo + " (" + + weapon2.currAmmo + "/" + weapon2.maxAmmo +  ")" + "\n\ncritical hit chance: " + (int)(critChance*100)+"%" + " (" + ((int)(weapon2.critChance*100)))+"%)";

    }

    public void RandomWapen(){
        //TODO:geef random wapen meer kans op slecht dan goed
        //eerst opdelen in normaal en goed
        //dan opdelen per wapen
    }
    /*
    //DIT IS VOOR VELE SOORTEN WAPENS TE HEBBEN
    public Weapon kiesWapen(List<Weapon> wapenKeuzes){
        sprong = 100/wapenKeuzes.size();
        r = random.nextInt(100)+1;
        hoeveelste=1;
        while (r>sprong){
            r-=sprong;
            hoeveelste++;
        }
        return wapenKeuzes.get(hoeveelste);
    } */

    @Override
    public String toString() {
        String result = String.format("_________________________________________\n%s (%s)\ndamage: %3d magazine: %2d\nrange: %4d accuracy: %3.2f\nammo: %5d max ammo: %3d\ncrit chance: %1.2f\n_________________________________________", name,soort,damage,magazine,range,accuracy,currAmmo,maxAmmo,critChance);
        //return result;
        return "weapon: " + name +"\nsoort damage: " + soort +"\n\ndamage: "+damage+
                "\naccuracy: " + (int)(accuracy*100)+"%\nrange: " + range+"\nmagazine size: " + magazine+
                "\nammo: " + currAmmo + "/" + maxAmmo + "\n\ncritical hit chance: " + (int)(critChance*100)+"%";
    }
    /*
    public String name;
    public String soort;
    public int damage;
    public double accuracy;
    public int range;
    public int magazine;
    public int currAmmo;
    public int maxAmmo;
    public double critChance;
    public boolean unscWeapon;
     */
}
