package Weapons;

import SpelElementen.Weapon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 15/08/13
 * Time: 0:49
 * To change this template use File | Settings | File Templates.
 */
public class Dmr extends Weapon {
    public Dmr() {
        super("DMR", "accurate", 10, 0.8, 7, 12, 48, 48, 0.2, false);

        try {
            Image afbeelding = ImageIO.read(getClass().getResource("weaponAfbeeldingen/DMR.png"));
            super.afbeelding=afbeelding;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
