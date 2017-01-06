package enemies;

import SpelElementen.Enemy;
import SpelElementen.Weapon;
import Weapons.AssaultRifle;
import Weapons.Pistol;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 12/08/13
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
public class BruteMinor extends Enemy {

    public BruteMinor(int location) {
        super(location,3, "brute minor", 125, 125, 50, 0.75, 1, 1, 1, 1, 1, 1, 1, 1, 0.85, 150,2,6,7);

        Random random = new Random(100);
        int weaponNumber = random.nextInt();
        //random wapen geven
        Pistol weapon = new Pistol();
        super.setWeapon(weapon);
        try {
            Image afbeelding = ImageIO.read(getClass().getResource("enemieAfbeeldingen/bruteMinor.png"));
            super.setAfbeelding(afbeelding);
        } catch (IOException e) {

        }

    }
}


