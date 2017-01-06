package Weapons;

import SpelElementen.Weapon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 12/08/13
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
public class AssaultRifle extends Weapon {
    public AssaultRifle() {
        super("assault rifle","normal", 5, 0.75, 4, 36, 108, 108 , 0,true);

        try {
            Image afbeelding = ImageIO.read(getClass().getResource("weaponAfbeeldingen/assault rifle.png"));
            super.afbeelding=afbeelding;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
