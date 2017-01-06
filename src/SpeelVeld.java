import SpelElementen.Enemy;
import SpelElementen.Player;
import SpelElementen.Weapon;
import SpelElementen.WeaponPickup;
import Weapons.Pistol;
import characters.Elite;
import characters.MasterChief;
import characters.OdstSoldier;
import enemies.BruteMinor;
import enemies.GruntMinor;

import javax.swing.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 12/08/13
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 */
public class SpeelVeld {

    Player player;
    /*
    * Player player2;
    * Player currentPlayer;//gebruik deze om te switchen tussen 2 players en dan copieren (kan wss makkelijker)
    *
    *
    *
    * */

    DamageReport playerToEnemy;
    DamageReport enemyToPlayer;
    MeleeDamageReport meleeDamageReport;

    //TODO: was vroeger gwn LIST!!!!!!!!!!!
    ArrayList<Enemy> enemies;
    Weapon lastWeaponPickup = new Pistol();

    ArrayList<WeaponPickup> weaponPickups;
    int[] veld;
    Random random = new Random();
    int dobbel1=0;
    int dobbel2;
    int aantalRespawns=0;
    int beurtnr = 0;

    public Weapon compareWeapon1=null;
    public Weapon compareWeapon2=null;

    public boolean setCompareWeapon(Weapon weapon){
        if(compareWeapon1==null){
            compareWeapon1 = weapon;
        }else{
            compareWeapon2 = weapon;
            return true;
        }
        return false;
    }

    public String compareWeapons(){
        if(compareWeapon1!=null&&compareWeapon2!=null){
            String message = compareWeapon1.compareWeapon(compareWeapon2);
            compareWeapon1=null;
            compareWeapon2=null;
            return message;
        }
        return "";
    }

    public SpeelVeld(String nm,int character,int veldgrootte){
        if(character==1){
            player = new MasterChief(nm);
        }
        if(character==2){
            player = new OdstSoldier(nm);
        }
        if(character==3){
            player = new Elite(nm);
        }

        enemies = new ArrayList<Enemy>();
        weaponPickups = new ArrayList<WeaponPickup>();
        veld = new int[veldgrootte];

        int i;

        for(i=20;i<veld.length;i+=random.nextInt(7)+8){
            //met functie random vijanden toevoegen
            enemies.add(voegRandomVijandToe(i));
            //enemies.add(new BruteMinor(i));
        }
        for(i=5;i<veld.length;i+=random.nextInt(10)+10) {
            for (Enemy enemy : enemies) {
                if(enemy.location==i){
                    i++;
                }
            }
            if(i<veld.length){
                weaponPickups.add(new WeaponPickup(i));
            }
        }
        updateVeld();
    }
    /*
    public SpeelVeld() {
        player = new Player(0,"joni");

        enemieAfbeeldingen = new ArrayList<Enemy>();
        weaponPickups = new ArrayList<WeaponPickup>();
        veld = new int[200];

        int i;

        for(i=20;i<veld.length;i+=random.nextInt(7)+8){
            //met functie random vijanden toevoegen
            enemieAfbeeldingen.add(voegRandomVijandToe(i));
            //enemieAfbeeldingen.add(new BruteMinor(i));
        }
        for(i=5;i<veld.length;i+=random.nextInt(10)+10) {
            for (Enemy enemy : enemieAfbeeldingen) {
                if(enemy.location==i){
                    i++;
                }
            }
            if(i<veld.length){
                weaponPickups.add(new WeaponPickup(i));
            }
        }
        updateVeld();
    }  */

    public Enemy voegRandomVijandToe(int loc){
        //TODO:
        Enemy enemy;
        int r = random.nextInt(100);
        if(r<50){
            enemy = new GruntMinor(loc);
        }else{
            enemy = new BruteMinor(loc);
        }
        return enemy;
    }


    public void updateVeld(){
        int i;
        int j;
        int range;

        for(i=0;i<veld.length;i++){
            veld[i] = 0;
        }

        i = player.location;
        veld[i] = 1;

        for (Enemy enemy : enemies) {
            i = enemy.location;
            range = enemy.weapon.range;
            j = i - range;
            for(j=j;j<i;j++){
                if(veld[j]==1){
                    veld[j]=6;
                }else{
                    veld[j] = 4;
                }

            }
            veld[i] = 2;
        }

        for (WeaponPickup weaponPickup : weaponPickups) {
            i = weaponPickup.location;
            if(veld[i]==4){
                veld[i]=5;
            }else{
                veld[i] = 3;
            }
        }
    }

    public String showVeldRange(){

        updateVeld();
        StringBuilder stringBuilder = new StringBuilder();
        for (int k : veld) {
            if(k==0){
                stringBuilder.append(".");
            }
            if(k==1){
                stringBuilder.append("P");
            }
            if(k==2){
                stringBuilder.append("E");
            }
            if(k==3){
                stringBuilder.append("W");
            }
            if(k==4){
                stringBuilder.append("-");
            }
            if(k==5){
                stringBuilder.append("w");
            }
            if(k==6){
                stringBuilder.append("p");
            }

        }
        return stringBuilder.toString();
    }

    public boolean endGame(){
        return player.location>=veld.length; //todo: aantal beurten tellen + gold + respawns
    }

    /*
    public void sortVijanden(){
        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(enemies,new Comparator<Enemy>() {
            @Override
            public int compare(Enemy o1, Enemy o2) {
                return o1.location - o2.location;
            }
        });
        int i;
        for(i=0;i<enemies.size();i++){
            if(player.location>enemies.get(i).location){
                enemies.remove(i);
            }
        }
    } */


    public String  rol(){
        // met booleans bv hebt ge bonus gerold
        //hier op false zetten
        beurtnr++;
        dobbel1 = random.nextInt(player.maxMovement-player.minMovement)+player.minMovement+1;
        //dobbel2 = random.nextInt(10)+1;//voor verschillende rollen bonus. zoja boolean op true zetten + volgende functie nakijken
        /*if(dobbel2 is bonus)
            eerst bonus geven
            else{
         */
        return "U heeft " + dobbel1 + " gerold. Hoeveel wilt u zich verplaatsen? U kan ook teruggaan maar niet voorbij vijanden lopen.";
    }

    public int getMaxVeld(){
        return player.location + dobbel1;
    }
    public int getMinVeld(){
        return player.location - dobbel1;
    }



    public boolean zetStappen(int aantal){ //TODO: NIET VOORBIJ VIJANDNE LOPEN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(Math.abs(aantal)<=dobbel1){
            if(player.location+aantal<0){
                player.location = 0;
            }else{
                if(player.location+aantal>enemies.get(0).location){
                    return false;
                }else{
                    player.location+=aantal;
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public boolean checkBonussen(){
        for (WeaponPickup weaponPickup : weaponPickups) {
            if(weaponPickup.location==player.location){
                return true;
            }
        }
        return false;
    }

    public String showBonus(){ //als wapen al gepakt is gwn ammo max
        int i;
        for(i=0;i<weaponPickups.size();i++){
            if(weaponPickups.get(i).location==player.location){
                lastWeaponPickup = weaponPickups.get(i).weapon;
            }
        }
        //check wapen al equipped en of hij al 2 wapens heeft
        try{
            if(player.weapon1.name==lastWeaponPickup.name||player.weapon2.name==lastWeaponPickup.name){
                if(player.weapon1.name==lastWeaponPickup.name){
                    player.weapon1.currAmmo = player.weapon1.maxAmmo;
                    removeWeaponPickup();
                }
                if(player.weapon2.name==lastWeaponPickup.name){
                    player.weapon2.currAmmo = player.weapon2.maxAmmo;
                    removeWeaponPickup();
                }
                return "\nUw "+lastWeaponPickup.name+" ammo is bijgevuld!";
            }else{
                return "U heeft het volgende wapen gevonden:\n" + lastWeaponPickup.toString() +"\n"+ player.showBothWeapons() + "\nWilt u het wapen dat u heeft gevonden verwissellen met wapen 1 of 2 of wil u het laten liggen(0)?\nVerwissel met: ";
            }
        }catch (NullPointerException error){
            player.weapon2 = lastWeaponPickup;
            removeWeaponPickup();
            return "U heeft een '" + lastWeaponPickup.name + "' opgeraapt.";
        }
    }

    public boolean bonusAlEquipped(){
        int i;
        for(i=0;i<weaponPickups.size();i++){
            if(weaponPickups.get(i).location==player.location){
                lastWeaponPickup = weaponPickups.get(i).weapon;
            }
        }
        try{
            if(player.weapon1.name==lastWeaponPickup.name||player.weapon2.name==lastWeaponPickup.name){
                return true;
            }else{
                return false;
            }
        }catch (NullPointerException error){
            return true;
        }
    }

    public String raapOpOfEquip(){
        try{
            if(player.weapon1.name==lastWeaponPickup.name||player.weapon2.name==lastWeaponPickup.name){
                if(player.weapon1.name==lastWeaponPickup.name){
                    player.weapon1.currAmmo = player.weapon1.maxAmmo;
                    removeWeaponPickup();
                }
                if(player.weapon2.name==lastWeaponPickup.name){
                    player.weapon2.currAmmo = player.weapon2.maxAmmo;
                    removeWeaponPickup();
                }
                return "\nUw "+lastWeaponPickup.name+" ammo is bijgevuld!";
            }
        }catch (NullPointerException error){
            if(player.weapon1.name==lastWeaponPickup.name){
                player.weapon1.currAmmo = player.weapon1.maxAmmo;
                return "\nUw "+player.weapon1.name +" ammo is bijgevuld!";
            }else{
                player.weapon2 = lastWeaponPickup;
                removeWeaponPickup();
                return "\nU heeft een '" + lastWeaponPickup.name + "' opgeraapt.";
            }

        }
        return "";
    }

    public String vervangGevondenWapenVraag(){
        return "U heeft het volgende wapen gevonden:\n" + lastWeaponPickup.toString() +"\n"+ player.showBothWeapons() + "\nWilt u het wapen dat u heeft gevonden verwissellen met wapen 1 of 2 of wil u het laten liggen(0)?\nVerwissel met: ";
    }

    public void removeWeaponPickup(){
        int i;
        for(i=0;i<weaponPickups.size();i++){
            if(weaponPickups.get(i).location==player.location){
                weaponPickups.remove(i);
            }
        }
    }

    public void equipBonus(int slot){
        if(slot==1||slot==2){
            if(slot==1){
                player.weapon1 = lastWeaponPickup;
                removeWeaponPickup();
            }
            if(slot==2){
                player.weapon2 = lastWeaponPickup;
                removeWeaponPickup();
            }
        }
    }

    public boolean meleeOfNiet(){
        if(enemies.get(0).location==player.location){
            return true;
        }else{
            return false;
        }
    }

    public String  meleeFight(){
        playerToEnemy=null;
        enemyToPlayer=null;
        //als vijand maar 10% health heeft ineens dood zonder kans op terugslaan
        StringBuilder report = new StringBuilder();
        Enemy enemy = enemies.get(0);

        int remainingEnemyHealth = enemies.get(0).health;
        int remainingPlayerHealth = player.health;

        boolean enemyHealthToLow = false;
        boolean playerHealthToLow = false;

        boolean enemyDood = false;
        boolean playerDood = false;

        double playerHitKans = player.meleeHitChance * enemy.evasion;
        double enemyHitKans = enemies.get(0).meleeHitChance;

        double rol;

        if(remainingEnemyHealth<=enemies.get(0).maxHealth*0.2){//minder dan 20% health
            enemyHealthToLow=true;
        }
        if(remainingPlayerHealth<=player.maxHealth*0.1){
            playerHealthToLow=true;
        }
        if(enemyHealthToLow){
            report.append(player.name + " slaagt " + enemy.name + " in een slag neer!\n");
            meleeDamageReport = new MeleeDamageReport(report.toString());
            enemyDeath();
            return meleeDamageReport.toString() +"\n" + enemy.name + " is dood en geeft "+enemy.moneyDrop+" gold!";
        }else if(playerHealthToLow){
            report.append(enemy.name + " slaagt " + player.name + " in een slag neer!\n");
            meleeDamageReport = new MeleeDamageReport(report.toString());
            playerDeath();
            return meleeDamageReport.toString() + "\n" + player.name + " is dood en spawned 20 plaatsen terug";
        }

        do{           //niet gebeuren als al dood
            rol = random.nextDouble();
            if(rol<=playerHitKans){
                enemies.get(0).health-=player.meleeDamage;
                report.append(player.name + " slaagt " + enemy.name + " voor " + player.meleeDamage + " damage\n");
            }else{
                report.append(player.name + " mist!\n");
            }

            rol = random.nextDouble();
            if(rol<=enemyHitKans){
                player.health-=enemy.meleeDamage;
                report.append(enemy.name + " slaagt " + player.name + " voor " + enemy.meleeDamage + " damage\n");
            }else{
                report.append(enemy.name + " mist!\n");
            }

            report.append("\n");

            if(enemies.get(0).health<=0){
                enemyDood=true;
            }
            if(player.health<=0){
                playerDood=true;
            }

        }while (playerDood==false&&enemyDood==false);
        if(playerDood&&enemyDood){
            playerDeath();
            enemyDeath();
            report.append("\n"+ player.name + " is dood" +"\n"+enemy.name+" is dood"+"\n");
        }else{
            if(enemyDood){
                report.append("\n"+ player.name + " health: " + player.health +"\n"+enemy.name+" is dood"+"\n");
                enemyDeath();
            }
            if(playerDood){
                report.append("\n"+ player.name + " is dood: " +"\n"+enemy.name+" health: " + enemies.get(0).health+"\n");
                playerDeath();
            }
        }

        meleeDamageReport = new MeleeDamageReport(report.toString());
        if(enemyDood&&!playerDood){
            return meleeDamageReport.toString() + "\n" + enemy.name + " is dood en geeft " + enemy.moneyDrop + " gold!";
        }
        if(playerDood&&!enemyDood){
            return meleeDamageReport.toString() + "\n" + player.name + " is dood en respawned 20 plaatsen terug";
        }
        return meleeDamageReport.toString() + "\n" + enemy.name + " is dood en geeft " + enemy.moneyDrop + " gold!" + "\n" + player.name + " is dood en respawned 20 plaatsen terug";

    }
    public void enemyDeath(){  //TODO: kan dat dit niet werkt!!
        player.gold+=enemies.get(0).moneyDrop;
        enemies.get(0).weapon.currAmmo/=2;
        boolean ligtAlEenWapen=false;
        do{
            for (WeaponPickup weaponPickup : weaponPickups) {
                if(enemies.get(0).location==weaponPickup.location){
                    enemies.get(0).location++;
                }
            }
        }while (ligtAlEenWapen);

        weaponPickups.add(new WeaponPickup(enemies.get(0).location,enemies.get(0).weapon));
        enemies.remove(0);
    }

    public void playerDeath(){
        aantalRespawns++;
        //geld afnemen
        if(player.location<20){
            player.location=0;
        }else{
            player.location-=20;
        }
        player.health=player.maxHealth;
    }
    public String kanVijandVuren(){
        meleeDamageReport=null;   //TODO: verandert
        playerToEnemy=null;
        if((enemies.get(0).location-player.location)<=enemies.get(0).weapon.range){
            int enemyDamage=berekenEnemyDamage(enemies.get(0).weapon,false);
            boolean playerDood=false;
            player.health-= enemyDamage;
            if(player.health<=0){
                playerDood=true;
                playerDeath();
                enemyToPlayer.remainingPlayerHealth=0;
            }else{
                enemyToPlayer.remainingPlayerHealth=player.health;
            }
            enemyToPlayer.remainingEnemyHealth=enemies.get(0).health;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(enemies.get(0).name+" valt " + player.name +" aan\n");
            //stringBuilder.append(enemyToPlayer.toString());
            if(playerDood){
                stringBuilder.append(player.name+" is dood en spawned 20 plaatsen terug");
            }else{
                stringBuilder.append(player.name +" health: "+player.health+" (-" +enemyDamage+")" );
            }
            return stringBuilder.toString();

        }
        return "";
    }

    public String vuurOpVijand(int wapen){ //TODO: nog ammo wegnemen
        //TODO: zien dat vijand dichter komt
        meleeDamageReport = null;
        if(wapen==1 || wapen==2){
            int playerDamage=0;
            int enemyDamage=0;
            boolean enemyDood=false;
            boolean playerDood=false;
            Enemy enemy = enemies.get(0);
            if(wapen==1){
                playerDamage=berekenPlayerDamage(player.weapon1);
            }
            try{
            if(wapen==2){
                playerDamage=berekenPlayerDamage(player.weapon2);
            }
            }catch(NullPointerException error){
                playerDamage=berekenPlayerDamage(player.weapon1);
            }

            if(enemies.get(0).health-playerDamage<=0){
                enemyDamage = berekenEnemyDamage(enemies.get(0).weapon,true);
            }else{
                enemyDamage = berekenEnemyDamage(enemies.get(0).weapon,false);
            }



            player.health-= enemyDamage;
            enemies.get(0).health-= playerDamage;

            if(enemies.get(0).health<=0){
                enemyDood=true;//nog gold aan player geven
                enemyDeath();
                enemyToPlayer.remainingEnemyHealth=0;
                playerToEnemy.remainingEnemyHealth=0;
            }else{
                enemyToPlayer.remainingEnemyHealth=enemies.get(0).health;
                playerToEnemy.remainingEnemyHealth=enemies.get(0).health;
            }

            if(player.health<=0){
                playerDood=true;
                playerDeath();
                enemyToPlayer.remainingPlayerHealth=0;
                playerToEnemy.remainingPlayerHealth=0;
            }else{
                enemyToPlayer.remainingPlayerHealth=player.health;
                playerToEnemy.remainingPlayerHealth=player.health;
            }
            StringBuilder stringBuilder = new StringBuilder();
            //stringBuilder.append(playerToEnemy.toString() + "\n\n" + enemyToPlayer.toString());
            if(playerDood){
                stringBuilder.append(player.name+" is dood en spawned 20 plaatsen terug");
            }else{
                stringBuilder.append(player.name +" health: "+player.health +" (-"+playerDamage+")");
            }
            if(enemyDood){
                stringBuilder.append("\n"+enemy.name+" is dood en geeft "+enemy.moneyDrop+" gold");
            }else{
                stringBuilder.append("\n"+enemies.get(0).name+" health: "+enemies.get(0).health+" (-"+enemyDamage+")");
            }
            return stringBuilder.toString();

        }
        return "";
    }

    public int berekenPlayerDamage(Weapon weapon/*,int aantalKogels*/){//MMS nog aanpassen
        int totalDamage=0;
        int neededDamage = enemies.get(0).health;
        int aantalKeerSchieten = /*aantalKogels*/ weapon.magazine;
        int i;
        int aantalKeerGeschoten=0;
        boolean raak;
        boolean critical;
        int aantalRaak=0;
        int aantalCritical=0;
        double raakKans=0;
        double criticalKans=0;
        double raakRol;//dit beslist ofdat de accuracy genoeg is of niet
        double criticalRol;
        int hitDamage;


        double rangeModifier = 1;
        double afstand = enemies.get(0).location - player.location;

        if(afstand!=weapon.range){
            if(afstand>weapon.range){
                rangeModifier=rangeModifier - ((afstand-weapon.range)/weapon.range);
            }else{
                rangeModifier=rangeModifier + ((weapon.range-afstand)/weapon.range);
            }
        }

        double resistance=1;

        //***********
        if(weapon.soort.equals("normal")){
            resistance = enemies.get(0).normalRes;
        }
        if(weapon.soort.equals("accurate")){
            resistance = enemies.get(0).accurateRes;
        }
        if(weapon.soort.equals("explosive")){
            resistance = enemies.get(0).explosiveRes;
        }
        if(weapon.soort.equals("energy")){
            resistance = enemies.get(0).energyRes;
        }
        //***********

        if(weapon.critChance!=0){
            if(enemies.get(0).critChance!=0){
                criticalKans=weapon.critChance * player.critBonus * enemies.get(0).critChance;
            }
        }
        raakKans = weapon.accuracy * player.accuracyBonus * rangeModifier * enemies.get(0).evasion;



        for(i=0;i<aantalKeerSchieten;i++){
            if(totalDamage<neededDamage){
                aantalKeerGeschoten++;
                raak=false;
                critical=false;
                raakRol = random.nextDouble(); //

                if(raakRol<=raakKans){
                    raak=true;
                    aantalRaak++;
                }

                if(raak){
                    criticalRol=random.nextDouble();
                    if(criticalRol<=criticalKans){
                        critical = true;
                        aantalCritical++;
                    }
                    //resistance meerekenen damage berekenen
                    hitDamage =  (int)Math.round(weapon.damage * player.damageBonus * resistance);
                    if(critical){
                        hitDamage*=2;
                    }
                    totalDamage+=hitDamage;
                }
            }
        }
        playerToEnemy = new DamageReport(true,raakKans,criticalKans,resistance,weapon.name,totalDamage,aantalKeerGeschoten,player.name,enemies.get(0).name,aantalKeerGeschoten-aantalRaak,aantalRaak,aantalCritical,(int)afstand);
        return totalDamage;
    }

    public int berekenEnemyDamage(Weapon weapon, boolean dood){
        int totalDamage=0;
        int neededDamage = player.health;

        int aantalKeerSchieten = /*aantalKogels*/ weapon.magazine;//volledig magazijn schieten of minder???
        if(dood){
            double percentageHealth = ((double)enemies.get(0).health / (double)enemies.get(0).maxHealth);
            aantalKeerSchieten = (int)Math.round(weapon.magazine * percentageHealth);
        }
        int i;
        int aantalKeerGeschoten=0;
        boolean raak;
        boolean critical;
        int aantalRaak=0;
        int aantalCritical=0;
        double raakKans=0;
        double criticalKans=0;
        double raakRol;//dit beslist ofdat de accuracy genoeg is of niet
        double criticalRol;
        int hitDamage;


        int afstand = enemies.get(0).location - player.location;


        double rangeModifier = 1;

        if(afstand!=weapon.range){
            if(afstand>weapon.range){
                rangeModifier=rangeModifier - ((afstand-weapon.range)/weapon.range);
            }else{
                rangeModifier=rangeModifier + ((weapon.range-afstand)/weapon.range);
            }
        }

        if(weapon.critChance!=0){
            if(player.critChance!=0){
                criticalKans = weapon.critChance * enemies.get(0).critBonus * player.critChance;
            }
        }


        raakKans = weapon.accuracy * enemies.get(0).accuracyBonus * rangeModifier * player.evasion;

        for(i=0;i<aantalKeerSchieten;i++){
            if(totalDamage<neededDamage){
                aantalKeerGeschoten++;
                raak=false;
                critical=false;

                raakRol = random.nextDouble();

                if(raakRol<=raakKans){
                    raak=true;
                    aantalRaak++;
                }

                if(raak){
                    criticalRol=random.nextDouble();
                    if(criticalRol<=criticalKans){
                        critical = true;
                        aantalCritical++;
                    }

                    hitDamage =  (int)Math.round(weapon.damage * enemies.get(0).damageBonus);
                    if(critical){
                        hitDamage*=2;
                    }
                    totalDamage+=hitDamage;
                }
            }
        }
        enemyToPlayer = new DamageReport(false,raakKans,criticalKans,0,weapon.name,totalDamage,aantalKeerGeschoten,enemies.get(0).name,player.name,aantalKeerGeschoten-aantalRaak,aantalRaak,aantalCritical,(int)afstand);
        return totalDamage;
    }
    /*@Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

    }  */
}
