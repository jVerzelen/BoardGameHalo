import SpelElementen.Enemy;
import SpelElementen.WeaponPickup;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.security.PublicKey;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 13/08/13
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
public class UISpel extends JFrame {

    double schermBreedte;
    double schermHoogte;

    JPanel groot;
    JPanel boven;
    JPanel midden;
    JPanel onder;

    JPanel playerPanel;
    JPanel playerWeapon1Panel;
    JPanel playerWeapon2Panel;
    JPanel lastPickupAndCombatChoicePanel;
    JPanel enemyWeaponPanel;
    JPanel enemyPanel;

    JTextArea playerText;
    JButton playerTextButton;
    JTextArea playerWeapon1Text;
    JButton playerWeapon1TextButton;
    JTextArea playerWeapon2Text;
    JButton playerWeapon2TextButton;
    JTextArea lastPickupText;
    JTextArea enemyWeaponText;
    JButton enemyWeaponTextButton;
    JTextArea enemyText;
    JButton enemyTextButton;

    JButton playerButton;
    JButton playerWeapon1Button;
    JButton playerWeapon2Button;
    //TODO: buttons voor vechten bij zetten
    JButton lastPickupButton;
    JButton enemyWeaponButton;
    JButton enemyButton;



    SpeelVeld speelVeld;

    ArrayList<WeaponPickup> weaponPickups;
    ArrayList<Enemy> enemies;


    Image master;
    Image odst;
    Image elite;

    Dimension veldButtonGrootte = new Dimension(68,128);



    Dimension middenDimensie = new Dimension((int)schermBreedte/6,(int)schermHoogte/3);
    Dimension middenButtonDimensie = new Dimension(150,100);
    Dimension onderButtonDimensie = new Dimension(80,80);



    public JButton shop;
    public JButton checkDamageReport;
    public JButton showEnemyVision;
    public JButton showEnemyRange;
    public JButton showNumbersButton;


    Border greenBorder = new LineBorder(Color.green, 3);
    Border redBorder = new LineBorder(Color.red, 3);
    Border purpleBorder = new LineBorder(new Color(160,32,240),3);


    ActionListener vervangWapen1;
    ActionListener vervangWapen2;
    ActionListener upgradeWapen1;
    ActionListener upgradeWapen2;
    ActionListener fireWapen1;
    ActionListener fireWapen2;

    JButton dynamicPanelBoven;
    JButton dynamicPanelOnder;

    ActionListener compareWeapon1;
    ActionListener compareWeapon2;
    ActionListener compareWeaponPickup;
    ActionListener compareWeaponEnemy;
    ActionListener equipWeapon3;
    ActionListener laatWapenLiggen;
    ActionListener fight;
    ActionListener takeCover;
    ActionListener toggleVision;
    ActionListener toggleRange;
    ActionListener kanVijandAanvallen;
    ActionListener showLastReport;


    boolean showVision = false;
    boolean showRange = false;
    boolean showNumbers = false;


    boolean beurtOm = false;



    JPanel bovenGroot;
    JLabel bovenInfo;





    public UISpel(String nm,int character,int veldgrootte){

        try {
            master = ImageIO.read(getClass().getResource("characters/charactersAfbeeldingen/masterChief.jpg"));
            odst = ImageIO.read(getClass().getResource("characters/charactersAfbeeldingen/odst.jpg"));
            elite = ImageIO.read(getClass().getResource("characters/charactersAfbeeldingen/elite.png"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        speelVeld = new SpeelVeld(nm,character,veldgrootte);
        schermBreedte =  java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        schermHoogte =  java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        maakFrame();
        maakComponenten();
        maakLayout();
        voegListenersToe();
        weaponPickups = new ArrayList<WeaponPickup>();
        enemies = new ArrayList<Enemy>();
        validate();
        laadMidden();
        //laadOnder();
        //laadSpelerEnVijandInfo();
        revalidate();


        //dice.setText(speelVeld.rol());



        bovenInfo.setText(speelVeld.rol());
        laadVeld();
        revalidate();
        setVisible(true);


    }



    public void laadVeld(){
        //System.out.println(speelVeld.showVeldRange());
        //weaponPickups = speelVeld.weaponPickups;
        //enemies = speelVeld.enemies;
        int i;
        int veldloc;
        int rol = speelVeld.dobbel1;

        /*for (ActionListener actionListener : dynamicPanelBoven.getActionListeners()) {
            dynamicPanelBoven.removeActionListener(actionListener);
        } */
        //TODO : alleen op fight als er geen weaponpickup is ?????
        dynamicPanelBoven.addActionListener(fight);

        //JOptionPane.showMessageDialog(null,"int rol = " + rol);

        boven.removeAll();
        for(i=-5;i<15;i++){
            try{
            try{
            JButton button = new JButton();
            button.setActionCommand(i+6+"");
            button.setPreferredSize(veldButtonGrootte);

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //JOptionPane.showMessageDialog(null, Integer.parseInt(e.getActionCommand())-6);


                            if(beurtOm==false){
                                if(speelVeld.zetStappen(Integer.parseInt(e.getActionCommand())-6)){
                                    if(speelVeld.enemies.get(0).location-speelVeld.enemies.get(0).vision <= speelVeld.player.location){
                                        beurtOm=true;//TODO: als player schiet op true zetten
                                        bovenInfo.setText("klik op veld voor je beurt te skippen of op fight of op cover");
                                    }
                                    laadVeld();//TODO: kan pas na u volledige beurt om is

                                    /*showBattlePanel();
                                    if(speelVeld.checkBonussen()){
                                        if(speelVeld.bonusAlEquipped()){
                                            speelVeld.raapOpOfEquip();
                                            laadMidden();
                                        }else{
                                            showPickupPanel();
                                        }
                                    } */

                                     //TODO: op false zetten wanneer speler op skip of fight of cover drukt
                                    //todo: nog niet rollen
                                   if(speelVeld.meleeOfNiet()){
                                        JOptionPane.showMessageDialog(null,speelVeld.meleeFight(),"Melee fight",JOptionPane.PLAIN_MESSAGE);
                                   }else{ //TODO: alleen als er geen weaponpickup is
                                       //dynamicPanelBoven.addActionListener(fight);
                                       showDynamicPanel();
                                   }

                                    /*
                                    if(speelVeld.checkBonussen()){
                                        if(speelVeld.bonusAlEquipped()){
                                            speelVeld.raapOpOfEquip();
                                            laadMidden();
                                        }else{
                                            showPickupPanel();
                                        }
                                    }  */
                                    if (!beurtOm){
                                        bovenInfo.setText(speelVeld.rol());
                                        //TODO : laad veld maar alleen borders met boolean
                                        laadVeld();
                                    }
                                }
                            }else {
                                bovenInfo.setText(convertToMultiline(speelVeld.kanVijandVuren()));
                                beurtOm = false;
                                laadMidden();
                                laadVeld();
                            }
                        }
                    });



                //***************


                //***************************
            veldloc=speelVeld.player.location+i;
            //wat moet er op de eerste knop komen de player locatie -5
            if(veldloc<0){
                //button.setBackground(new Color(50,205,50));
            }else{
                if(showNumbers){
                    button.setText(""+i);
                }
                for (WeaponPickup weaponPickup : speelVeld.weaponPickups) {
                    if(veldloc==weaponPickup.location){
                        button.setText(null);
                        button.setIcon(resize(weaponPickup.afbeelding, button));
                    }
                }
                for(Enemy enemy : speelVeld.enemies){
                    if (veldloc==enemy.location){
                        button.setText(null);
                        button.setIcon(makeresize(enemy.afbeelding, 20, 6));
                    }
                }
                if(veldloc==speelVeld.player.location){
                    button.setText(null);
                    button.setIcon(makeresize(speelVeld.player.afbeelding, 20, 6));
                }

                if (!beurtOm){//TODO: speler krijgt geen omcirkeling te zien als ze niet kunnen bewegen maar dan ook

                    if((veldloc<=speelVeld.player.location+rol)&&(veldloc>=speelVeld.player.location-rol)&&(veldloc<=speelVeld.enemies.get(0).location)){
                        button.setBorder(greenBorder);
                    }
                    if(showRange){
                        if((veldloc>speelVeld.enemies.get(0).location-speelVeld.enemies.get(0).weapon.range-1)&&(veldloc<speelVeld.enemies.get(0).location)){
                            if(button.getBorder()==greenBorder){
                                button.setBorder(purpleBorder);
                            }else {
                                button.setBorder(redBorder);
                            }

                        }
                    }
                    if(showVision){
                        if((veldloc>speelVeld.enemies.get(0).location-speelVeld.enemies.get(0).vision-1)&&(veldloc<speelVeld.enemies.get(0).location)){
                            if(button.getBorder()==greenBorder){
                                button.setBorder(purpleBorder);
                            }else {
                                button.setBorder(redBorder);
                            }
                        }
                    }
                }else {

                }

            }
                //bovenInfo.setText("Je hebt " + rol + " gerold");
                boven.add(button);
            }catch (IOException err){

            }
            }catch (NullPointerException edslkfj){

            }
        }
        revalidate();
    }

    public void showDynamicPanel(){
        if (speelVeld.checkBonussen()){
            if(speelVeld.bonusAlEquipped()){
                speelVeld.raapOpOfEquip();
                laadMidden();
                showBattlePanel2();
            }else{
                showPickupPanel2();
            }
        }else {
            showBattlePanel2();
        }
    }

    public void showPickupPanel2(){
        dynamicPanelBoven.setBackground(Color.black);
        for (ActionListener actionListener : dynamicPanelBoven.getActionListeners()) {
            dynamicPanelBoven.removeActionListener(actionListener);
        }

        dynamicPanelBoven.addActionListener(equipWeapon3);
        dynamicPanelBoven.setIcon(resize(speelVeld.lastWeaponPickup.afbeelding, playerButton));
        dynamicPanelOnder.setText(convertToMultiline(speelVeld.lastWeaponPickup.toString()));
        dynamicPanelOnder.setHorizontalAlignment(SwingConstants.LEFT);
        dynamicPanelOnder.setVerticalAlignment(SwingConstants.TOP);
        dynamicPanelOnder.addActionListener(compareWeaponPickup);
    }

    public void showBattlePanel2(){
        dynamicPanelBoven.setBackground(null);
        for (ActionListener actionListener : dynamicPanelBoven.getActionListeners()) {
            dynamicPanelBoven.removeActionListener(actionListener);
        }

        for (ActionListener actionListener : playerWeapon1Button.getActionListeners()) {
            playerWeapon1Button.removeActionListener(actionListener);
        }

        for (ActionListener actionListener : playerWeapon2Button.getActionListeners()) {
            playerWeapon2Button.removeActionListener(actionListener);
        }

        for (ActionListener actionListener : dynamicPanelOnder.getActionListeners()) {
            dynamicPanelOnder.removeActionListener(actionListener);
        }

        playerWeapon1Button.addActionListener(upgradeWapen1);
        playerWeapon2Button.addActionListener(upgradeWapen2);

        dynamicPanelOnder.setHorizontalAlignment(SwingConstants.CENTER);
        dynamicPanelOnder.setVerticalAlignment(SwingConstants.CENTER);


        dynamicPanelBoven.setIcon(null);
        dynamicPanelBoven.setText("FIGHT");
        dynamicPanelOnder.setText("TAKE COVER");

        //TODO: fight op dynamicpanel boven zetten?
        dynamicPanelBoven.addActionListener(fight);
    }




    public void maakFrame(){
        setTitle("Game");
        setSize(250,250);
        setLocationRelativeTo(getContentPane());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void laadMidden(){
        playerText.setText(speelVeld.player.toString());
        playerWeapon1Text.setText(speelVeld.player.weapon1.toString());
        try{
            playerWeapon2Text.setText(speelVeld.player.weapon2.toString());
        }catch (NullPointerException esljfd){
            playerWeapon2Text.setText("NOT EQUIPPED");
        }
        //TODO: lastpickup ....
        enemyWeaponText.setText(speelVeld.enemies.get(0).weapon.toString());
        enemyText.setText(speelVeld.enemies.get(0).toString());

        playerTextButton.setText(convertToMultiline(speelVeld.player.toString()));
        playerWeapon1TextButton.setText(convertToMultiline(speelVeld.player.weapon1.toString()));
        try{
        playerWeapon2TextButton.setText(convertToMultiline(speelVeld.player.weapon2.toString()));
        }catch (NullPointerException slkjf){
            playerWeapon2TextButton.setText("NOT EQUIPPED");
        }
        enemyWeaponTextButton.setText(convertToMultiline(speelVeld.enemies.get(0).weapon.toString()));
        enemyTextButton.setText(convertToMultiline(speelVeld.enemies.get(0).toString()));



        //TODO: buttons laden

        playerButton.setPreferredSize(new Dimension(playerButton.getWidth(),playerButton.getHeight()));
        playerWeapon1Button.setPreferredSize(new Dimension(playerButton.getWidth(),playerButton.getHeight()));
        playerWeapon2Button.setPreferredSize(new Dimension(playerButton.getWidth(),playerButton.getHeight()));
        enemyWeaponButton.setPreferredSize(new Dimension(playerButton.getWidth(), playerButton.getHeight()));
        enemyButton.setPreferredSize(new Dimension(playerButton.getWidth(),playerButton.getHeight()));

        playerButton.setIcon(resize(speelVeld.player.afbeelding, playerButton));
        playerWeapon1Button.setIcon(resize(speelVeld.player.weapon1.afbeelding, playerButton));
        try{
            playerWeapon2Button.setIcon(resize(speelVeld.player.weapon2.afbeelding, playerButton));
        }catch (NullPointerException lksjfd){
        }
        enemyWeaponButton.setIcon(resize(speelVeld.enemies.get(0).weapon.afbeelding, playerButton));
        enemyButton.setIcon(resize(speelVeld.enemies.get(0).afbeelding, playerButton));
    }




    public ImageIcon resize(Image img, JButton button){

        javax.swing.ImageIcon imageIcon = new javax.swing.ImageIcon(img);
        java.awt.Image originalImage = imageIcon.getImage();
        java.awt.Image scaledImage = originalImage.getScaledInstance(button.preferredSize().width,button.preferredSize().height, java.awt.Image.SCALE_SMOOTH);
        javax.swing.ImageIcon newIconImage = new javax.swing.ImageIcon(scaledImage);
        return newIconImage;
    }

    public void maakComponenten(){
        groot = new JPanel(new BorderLayout(0,25));
        bovenGroot = new JPanel(new BorderLayout());
        bovenInfo = new JLabel("dit is een test");
        boven = new JPanel(new GridLayout(1,20));

        GridLayout layout = new GridLayout(1,6);
        layout.setHgap(20);
        midden = new JPanel(layout);
        onder = new JPanel(new GridLayout(1,6));

        playerPanel = new JPanel(new BorderLayout());
        playerText = new JTextArea();
        playerTextButton = new JButton();
        playerWeapon1Panel = new JPanel(new BorderLayout());
        playerWeapon1Text = new JTextArea();
        playerWeapon1TextButton = new JButton();
        playerWeapon2Panel = new JPanel(new BorderLayout());
        playerWeapon2Text = new JTextArea();
        playerWeapon2TextButton = new JButton();
        lastPickupAndCombatChoicePanel = new JPanel(new BorderLayout());
        lastPickupText = new JTextArea();
        enemyWeaponPanel = new JPanel(new BorderLayout());
        enemyWeaponText = new JTextArea();
        enemyWeaponTextButton = new JButton();
        enemyPanel = new JPanel(new BorderLayout());
        enemyText = new JTextArea();
        enemyTextButton = new JButton();


        playerText.setEnabled(false);
        playerWeapon1Text.setEnabled(false);
        playerWeapon2Text.setEnabled(false);
        lastPickupText.setEnabled(false);
        enemyWeaponText.setEnabled(false);
        enemyText.setEnabled(false);

        playerTextButton.setHorizontalAlignment(SwingConstants.LEFT);
        playerTextButton.setVerticalAlignment(SwingConstants.TOP);
        playerWeapon1TextButton.setHorizontalAlignment(SwingConstants.LEFT);
        playerWeapon1TextButton.setVerticalAlignment(SwingConstants.TOP);
        playerWeapon2TextButton.setHorizontalAlignment(SwingConstants.LEFT);
        playerWeapon2TextButton.setVerticalAlignment(SwingConstants.TOP);
        enemyWeaponTextButton.setHorizontalAlignment(SwingConstants.LEFT);
        enemyWeaponTextButton.setVerticalAlignment(SwingConstants.TOP);
        enemyTextButton.setHorizontalAlignment(SwingConstants.LEFT);
        enemyTextButton.setVerticalAlignment(SwingConstants.TOP);



        playerText.setOpaque(false);
        playerText.setDisabledTextColor(Color.black);
        playerWeapon1Text.setOpaque(false);
        playerWeapon1Text.setDisabledTextColor(Color.black);
        playerWeapon2Text.setOpaque(false);
        playerWeapon2Text.setDisabledTextColor(Color.black);
        lastPickupText.setOpaque(false);
        lastPickupText.setDisabledTextColor(Color.black);
        enemyWeaponText.setOpaque(false);
        enemyWeaponText.setDisabledTextColor(Color.black);
        enemyText.setOpaque(false);
        enemyText.setDisabledTextColor(Color.black);

        playerButton = new JButton();
        playerWeapon1Button = new JButton();
        playerWeapon2Button = new JButton();
        lastPickupButton = new JButton();
        enemyWeaponButton = new JButton();
        enemyButton = new JButton();



        shop = new JButton("SHOP");
        checkDamageReport = new JButton("CHECK DAMAGE REPORT");
        showNumbersButton = new JButton("SHOW FIELD NUMBERS");
        showEnemyVision = new JButton("SHOW ENEMY VISION");
        showEnemyRange = new JButton("SHOW ENEMY RANGE");

        playerButton.setPreferredSize(middenButtonDimensie);
        playerWeapon1Button.setPreferredSize(middenButtonDimensie);
        playerWeapon2Button.setPreferredSize(middenButtonDimensie);
        lastPickupButton.setPreferredSize(middenButtonDimensie);
        enemyWeaponButton.setPreferredSize(middenButtonDimensie);
        enemyButton.setPreferredSize(middenButtonDimensie);



        shop.setPreferredSize(onderButtonDimensie);
        checkDamageReport.setPreferredSize(onderButtonDimensie);
        showNumbersButton.setPreferredSize(onderButtonDimensie);
        showEnemyVision.setPreferredSize(onderButtonDimensie);
        showEnemyRange.setPreferredSize(onderButtonDimensie);


        playerButton.setBackground(Color.black);
        playerWeapon1Button.setBackground(Color.black);
        playerWeapon2Button.setBackground(Color.black);
        lastPickupButton.setBackground(Color.black);
        enemyWeaponButton.setBackground(Color.black);
        enemyButton.setBackground(Color.black);




        dynamicPanelBoven = new JButton();
        dynamicPanelOnder = new JButton();

        //dice.setText("test");

        /*try {
            dice.setIcon(makeresize(master,6,5));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }  */

        //TODO: lastpickup en fight buttons

        laadVeld();
        /*try {



            for(int i = 0;i<6;i++){
                JButton button = new JButton();
                button.setIcon(makeresize(elite, 6, 5));
                onder.add(button);
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } */
    }

    public void showBattlePanel(){

        dynamicPanelBoven.setBackground(null);
        dynamicPanelBoven.removeActionListener(equipWeapon3);
        for (ActionListener actionListener : dynamicPanelBoven.getActionListeners()) {
            dynamicPanelBoven.removeActionListener(actionListener);
        }
        playerWeapon1Button.addActionListener(upgradeWapen1);
        playerWeapon1Button.removeActionListener(vervangWapen1);
        playerWeapon2Button.addActionListener(upgradeWapen2);
        playerWeapon2Button.removeActionListener(vervangWapen2);
        playerWeapon1Button.removeActionListener(fireWapen1);
        playerWeapon2Button.removeActionListener(fireWapen2);


        dynamicPanelOnder.setHorizontalAlignment(SwingConstants.CENTER);
        dynamicPanelOnder.setVerticalAlignment(SwingConstants.CENTER);


        dynamicPanelBoven.setIcon(null);
        dynamicPanelBoven.setText("FIGHT");
        dynamicPanelOnder.setText("TAKE COVER");
        dynamicPanelOnder.removeActionListener(compareWeaponPickup);

    }
    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }

    public void showPickupPanel(){
        //TODO: switchen tussen de twee

        dynamicPanelBoven.setBackground(Color.black);
        //dynamicPanelBoven.removeActionListener(fight);
        for (ActionListener actionListener : dynamicPanelBoven.getActionListeners()) {
            dynamicPanelBoven.removeActionListener(actionListener);
        }
        dynamicPanelBoven.addActionListener(equipWeapon3);
        dynamicPanelBoven.setIcon(resize(speelVeld.lastWeaponPickup.afbeelding, playerButton));
        dynamicPanelOnder.setText(convertToMultiline(speelVeld.lastWeaponPickup.toString()));
        dynamicPanelOnder.setHorizontalAlignment(SwingConstants.LEFT);
        dynamicPanelOnder.setVerticalAlignment(SwingConstants.TOP);
        dynamicPanelOnder.addActionListener(compareWeaponPickup);
    }


    public void maakLayout(){

        playerPanel.setPreferredSize(middenDimensie);
        playerWeapon1Panel.setPreferredSize(middenDimensie);
        playerWeapon2Panel.setPreferredSize(middenDimensie);
        lastPickupAndCombatChoicePanel.setPreferredSize(middenDimensie);
        enemyWeaponPanel.setPreferredSize(middenDimensie);
        enemyPanel.setPreferredSize(middenDimensie);

        dynamicPanelBoven.setPreferredSize(middenButtonDimensie);


        playerPanel.add(playerButton,BorderLayout.PAGE_START);
        playerWeapon1Panel.add(playerWeapon1Button,BorderLayout.PAGE_START);
        playerWeapon2Panel.add(playerWeapon2Button,BorderLayout.PAGE_START);
        //TODO: lastpickup panel moet wisselen
        //lastPickupAndCombatChoicePanel.add(lastPickupButton,BorderLayout.PAGE_START);
        //lastPickupAndCombatChoicePanel.add(lastPickupText);
        enemyWeaponPanel.add(enemyWeaponButton,BorderLayout.PAGE_START);
        enemyPanel.add(enemyButton,BorderLayout.PAGE_START);

        lastPickupAndCombatChoicePanel.add(dynamicPanelBoven,BorderLayout.PAGE_START);
        lastPickupAndCombatChoicePanel.add(dynamicPanelOnder,BorderLayout.CENTER);
        /*
        playerPanel.add(playerText);
        playerWeapon1Panel.add(playerWeapon1Text);
        playerWeapon2Panel.add(playerWeapon2Text);
        enemyWeaponPanel.add(enemyWeaponText);
        enemyPanel.add(enemyText);
        */
        playerPanel.add(playerTextButton);
        playerWeapon1Panel.add(playerWeapon1TextButton);
        playerWeapon2Panel.add(playerWeapon2TextButton);
        enemyWeaponPanel.add(enemyWeaponTextButton);
        enemyPanel.add(enemyTextButton);


        midden.add(playerPanel);
        midden.add(playerWeapon1Panel);
        midden.add(playerWeapon2Panel);
        midden.add(lastPickupAndCombatChoicePanel);
        midden.add(enemyWeaponPanel);
        midden.add(enemyPanel);


        onder.add(shop);
        onder.add(checkDamageReport);
        onder.add(showNumbersButton);
        onder.add(showEnemyVision);
        onder.add(showEnemyRange);

        bovenInfo.setHorizontalAlignment(SwingConstants.CENTER);
        bovenGroot.add(boven,BorderLayout.PAGE_START);
        bovenGroot.add(bovenInfo,BorderLayout.PAGE_END);


        groot.add(bovenGroot,BorderLayout.PAGE_START);
        groot.add(midden,BorderLayout.CENTER);
        groot.add(onder,BorderLayout.PAGE_END);
        add(groot);
    }

    public void voegListenersToe(){

        fireWapen1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                beurtOm=false;
                bovenInfo.setText(convertToMultiline(speelVeld.vuurOpVijand(1)));
                //TODO: vijand doet iets hierna

                for (ActionListener actionListener : playerWeapon1Button.getActionListeners()) {
                    playerWeapon1Button.removeActionListener(actionListener);
                }

                for (ActionListener actionListener : playerWeapon2Button.getActionListeners()) {
                    playerWeapon2Button.removeActionListener(actionListener);
                }

                playerWeapon1Button.addActionListener(upgradeWapen1);
                playerWeapon2Button.addActionListener(upgradeWapen2);

                //beurtOm=!beurtOm;
                laadMidden();
                laadVeld();
                //System.out.println(speelVeld.playerToEnemy);

            }
        };

        fireWapen2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                beurtOm=false;
                bovenInfo.setText(convertToMultiline(speelVeld.vuurOpVijand(2)));

                for (ActionListener actionListener : playerWeapon1Button.getActionListeners()) {
                    playerWeapon1Button.removeActionListener(actionListener);
                }

                for (ActionListener actionListener : playerWeapon2Button.getActionListeners()) {
                    playerWeapon2Button.removeActionListener(actionListener);
                }

                playerWeapon1Button.addActionListener(upgradeWapen1);
                playerWeapon2Button.addActionListener(upgradeWapen2);

                //beurtOm=!beurtOm;
                laadMidden();
                laadVeld();
            }
        };

        fight = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (ActionListener actionListener : playerWeapon1Button.getActionListeners()) {
                    playerWeapon1Button.removeActionListener(actionListener);
                }

                for (ActionListener actionListener : playerWeapon2Button.getActionListeners()) {
                    playerWeapon2Button.removeActionListener(actionListener);
                }


                playerWeapon1Button.addActionListener(fireWapen1);
                playerWeapon2Button.addActionListener(fireWapen2);

                for (ActionListener actionListener : dynamicPanelBoven.getActionListeners()) {
                    dynamicPanelBoven.removeActionListener(actionListener);
                }

                dynamicPanelBoven.addActionListener(fight);
            }
        };


        toggleVision = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showVision){
                    showVision=false;
                }else {
                    showVision=true;
                    showRange=false;
                }
                laadVeld();
            }
        };

        toggleRange = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showRange){
                    showRange=false;
                }else{
                    showRange=true;
                    showVision=false;
                }
                laadVeld();
            }
        };


        upgradeWapen1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"upgrade");
            }
        };

        upgradeWapen2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"upgrade");
            }
        };

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                laadMidden();
            }
        });

        vervangWapen1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speelVeld.equipBonus(1);
                laadMidden();
                playerWeapon1Button.addActionListener(upgradeWapen1);
                playerWeapon1Button.removeActionListener(vervangWapen1);
                playerWeapon1Button.removeActionListener(fireWapen1);
                playerWeapon2Button.addActionListener(upgradeWapen2);
                playerWeapon2Button.removeActionListener(vervangWapen2);
                playerWeapon2Button.removeActionListener(fireWapen2);

                showBattlePanel2();
                revalidate();
            }
        };

        vervangWapen2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speelVeld.equipBonus(2);
                laadMidden();
                playerWeapon1Button.addActionListener(upgradeWapen1);
                playerWeapon1Button.removeActionListener(vervangWapen1);
                playerWeapon1Button.removeActionListener(fireWapen1);
                playerWeapon2Button.addActionListener(upgradeWapen2);
                playerWeapon2Button.removeActionListener(vervangWapen2);
                playerWeapon2Button.removeActionListener(fireWapen2);

                showBattlePanel2();
                revalidate();
            }
        };

        equipWeapon3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ActionListener actionListener : playerWeapon1Button.getActionListeners()) {
                    playerWeapon1Button.removeActionListener(actionListener);
                }
                playerWeapon1Button.addActionListener(vervangWapen1);

                for (ActionListener actionListener : playerWeapon2Button.getActionListeners()) {
                    playerWeapon2Button.removeActionListener(actionListener);
                }
                playerWeapon2Button.addActionListener(vervangWapen2);

                for (ActionListener actionListener : dynamicPanelBoven.getActionListeners()) {
                    dynamicPanelBoven.removeActionListener(actionListener);
                }
                dynamicPanelBoven.setIcon(null);
                dynamicPanelBoven.setText("LAAT LIGGEN");
                dynamicPanelBoven.addActionListener(laatWapenLiggen);
                dynamicPanelBoven.setBackground(null);
            }
        };

        laatWapenLiggen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speelVeld.equipBonus(0);
                for (ActionListener actionListener : dynamicPanelBoven.getActionListeners()) {
                    dynamicPanelBoven.removeActionListener(actionListener);
                }
                showBattlePanel2(); //TODO: battlepanel2
            }
        };

        compareWeapon1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speelVeld.setCompareWeapon(speelVeld.player.weapon1)){
                    JOptionPane.showMessageDialog(null,speelVeld.compareWeapons(),"Comparing",JOptionPane.PLAIN_MESSAGE);
                }
            }
        };

        compareWeapon2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speelVeld.setCompareWeapon(speelVeld.player.weapon2)){
                    JOptionPane.showMessageDialog(null,speelVeld.compareWeapons(),"Comparing",JOptionPane.PLAIN_MESSAGE);
                }
            }
        };

        compareWeaponPickup = new ActionListener() {//TODO: alleen als er er wapen is opgepakt
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speelVeld.setCompareWeapon(speelVeld.lastWeaponPickup)){
                    JOptionPane.showMessageDialog(null,speelVeld.compareWeapons(),"Comparing",JOptionPane.PLAIN_MESSAGE);
                }
            }
        };

        compareWeaponEnemy = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speelVeld.setCompareWeapon(speelVeld.enemies.get(0).weapon)){
                    JOptionPane.showMessageDialog(null,speelVeld.compareWeapons(),"Comparing",JOptionPane.PLAIN_MESSAGE);
                }
            }
        };

        showNumbersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNumbers=!showNumbers;
                laadVeld();
            }
        });

        kanVijandAanvallen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speelVeld.kanVijandVuren();
            }
        };

        showLastReport = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speelVeld.meleeDamageReport!=null){
                    JOptionPane.showMessageDialog(null,speelVeld.meleeDamageReport.toString(),"Melee fight report",JOptionPane.PLAIN_MESSAGE);
                }
                if(speelVeld.playerToEnemy!=null){
                    JOptionPane.showMessageDialog(null,speelVeld.playerToEnemy.toString(),"Player damage report",JOptionPane.PLAIN_MESSAGE);
                }
                if(speelVeld.enemyToPlayer!=null){
                    JOptionPane.showMessageDialog(null,speelVeld.enemyToPlayer.toString(),"Enemy damage report",JOptionPane.PLAIN_MESSAGE);
                }
            }
        };


        playerWeapon1Button.addActionListener(upgradeWapen1);
        playerWeapon2Button.addActionListener(upgradeWapen2);
        showEnemyRange.addActionListener(toggleRange);
        showEnemyVision.addActionListener(toggleVision);

        playerWeapon1TextButton.addActionListener(compareWeapon1);
        playerWeapon2TextButton.addActionListener(compareWeapon2);

        enemyWeaponTextButton.addActionListener(compareWeaponEnemy);
        checkDamageReport.addActionListener(showLastReport);



        /*shop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,speelVeld.enemies.get(0).location - speelVeld.player.location);
            }
        }); */
    }



    public ImageIcon makeresize(Image img, int horizontaal, int verticaal) throws IOException {

        //Image img = ImageIO.read(getClass().getResource("SpelElementen.images/charactersAfbeeldingen/masterChief.jpg"));


        javax.swing.ImageIcon imageIcon = new javax.swing.ImageIcon(img);
        java.awt.Image originalImage = imageIcon.getImage();
        java.awt.Image scaledImage = originalImage.getScaledInstance((int)schermBreedte/horizontaal,(int)schermHoogte/verticaal, java.awt.Image.SCALE_SMOOTH);
        javax.swing.ImageIcon newIconImage = new javax.swing.ImageIcon(scaledImage);
        return newIconImage;
    }
}
