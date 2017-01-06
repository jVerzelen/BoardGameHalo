package characters;

import SpelElementen.Player;
import SpelElementen.Weapon;
import Weapons.Pistol;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 13/08/13
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
public class MasterChief extends Player{
    public MasterChief(String name) {
        super(name,1, 500, 500, 75, 0.8, 1,1, 1, 1, 1.2, new Pistol(),2,8,0,0);
        try {
            Image afbeelding = ImageIO.read(getClass().getResource("charactersAfbeeldingen/masterChief.jpg"));
            super.setAfbeelding(afbeelding);
        } catch (IOException e) {

        }
    }

    /*public MasterChief(String name) {
        super(name, 1, 500, 500, 75, 0.8, 1, 1, 1, 1, 1.2, new Pistol());
        //String name,int type, int health, int maxHealth, int meleeDamage, double meleeHitChance, double evasion,double critChance, double accuracyBonus, double critBonus, double damageBonus, Weapon weapon1) {

    }*/
}
