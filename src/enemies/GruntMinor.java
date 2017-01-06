package enemies;

import SpelElementen.Enemy;
import Weapons.AssaultRifle;
import Weapons.Pistol;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 13/08/13
 * Time: 12:18
 * To change this template use File | Settings | File Templates.
 */
public class GruntMinor extends Enemy {

    public GruntMinor(int location) {
        super(location,2, "grunt minor", 100, 100, 0, 0, 0.9, 1, 1, 1, 2, 0.9, 1, 1, 0.9, 100,1,5,7);
        Random random = new Random(100);
        int weaponNumber = random.nextInt();
        //random wapen geven
        Pistol weapon = new Pistol();
        super.setWeapon(weapon);

        try {
            Image afbeelding = ImageIO.read(getClass().getResource("enemieAfbeeldingen/grunt minor.png"));
            super.setAfbeelding(afbeelding);
        } catch (IOException e) {

        }

    }
}
