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
public class OdstSoldier extends Player{
    public OdstSoldier(String name) {
        super(name,1, 400, 400, 50, 0.9, 0.85,1, 1.2, 1.2, 1, new Pistol(),3,9,0.5,1);
        //String name,int type, int health, int maxHealth, int meleeDamage, double meleeHitChance, double evasion,double critChance, double accuracyBonus, double critBonus, double damageBonus, Weapon weapon1) {

        try {
            Image afbeelding = ImageIO.read(getClass().getResource("charactersAfbeeldingen/odst.jpg"));
            super.setAfbeelding(afbeelding);
        } catch (IOException e) {

        }
    }
}
