package Weapons;

import SpelElementen.Weapon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 12/08/13
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class Pistol extends Weapon {
    public Pistol() {
        super("pistol","normal", 10, 0.75, 5, 12, 10000, 10000 , 0.1,true);

        try {
            Image afbeelding = ImageIO.read(getClass().getResource("weaponAfbeeldingen/handgun.png"));
            super.afbeelding=afbeelding;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
