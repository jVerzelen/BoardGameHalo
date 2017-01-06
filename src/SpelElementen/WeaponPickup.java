package SpelElementen;

import Weapons.AssaultRifle;
import Weapons.Dmr;
import Weapons.Pistol;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 12/08/13
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */
public class WeaponPickup {
    public int location;
    public Weapon weapon;
    public Image afbeelding;

    public WeaponPickup(int location, Weapon weapon) {
        this.location = location;
        this.weapon = weapon;
        setImage();
    }

    public WeaponPickup(int location) {
        this.location = location;
        Random random = new Random();
        int r = random.nextInt(100);
        // aan de hand van de random een random wapen geven
        //TODO: ZIE DAT ER ZEKER EEN WAPEN GESELECTEERD WORDT!!!!!!!!!!!!!
        if(r<=50){
            weapon = new AssaultRifle();
        }
        if(r>50){
            weapon = new Dmr();
        }
        setImage();
    }

    public void setImage(){
        try {
            if(weapon.unscWeapon){
                afbeelding = ImageIO.read(getClass().getResource("images/unscWD.jpg"));
            }else {
                afbeelding = ImageIO.read(getClass().getResource("images/covenantWD.jpg"));
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
