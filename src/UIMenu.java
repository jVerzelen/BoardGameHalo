import characters.Elite;
import characters.MasterChief;
import characters.OdstSoldier;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 13/08/13
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public class UIMenu extends JFrame {
    private JButton spelen;
    private JButton instellingen;
    private JButton chooseCharacter;
    private JPanel panel;
    JPanel panel2;

    private String naam;
    private int character=1;
    private int veldLengte=200;

    private double schermBreedte;
    private double schermHoogte;

    MasterChief masterChief = new MasterChief("Spartan 117");
    Elite elite = new Elite("Elite");
    OdstSoldier odst = new OdstSoldier("ODST soldier");

   /*
    Image master = ImageIO.read(getClass().getResource("characters/charactersAfbeeldingen/masterChief.jpg"));
    Image odst = ImageIO.read(getClass().getResource("characters/charactersAfbeeldingen/odst.jpg"));
    Image elite = ImageIO.read(getClass().getResource("characters/charactersAfbeeldingen/elite.png"));
    */




    public UIMenu() throws IOException {
        schermBreedte =  java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        schermHoogte =  java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        maakFrame();
        maakComponenten();
        maakLayout();
        voegListenersToe();
        validate();
    }

    public void maakPanel1()  {
        remove(panel2);
        maakFrame();
        maakComponenten();
        maakLayout();
        voegListenersToe();
        revalidate();
    }

    public ImageIcon resize(Image img) throws IOException {

        //Image img = ImageIO.read(getClass().getResource("SpelElementen.images/charactersAfbeeldingen/masterChief.jpg"));


        javax.swing.ImageIcon imageIcon = new javax.swing.ImageIcon(img);
        java.awt.Image originalImage = imageIcon.getImage();
        java.awt.Image scaledImage = originalImage.getScaledInstance((int)schermBreedte/3,(int)schermHoogte-65, java.awt.Image.SCALE_SMOOTH);
        javax.swing.ImageIcon newIconImage = new javax.swing.ImageIcon(scaledImage);
        return newIconImage;
    }


    public void maakFrame(){
        setTitle("Menu");
        setSize(250,250);
        setLocationRelativeTo(getContentPane());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }



    public void maakComponenten()  {
        spelen = new JButton("speel");
        instellingen = new JButton("instellingen");

        try{
        chooseCharacter = new JButton();
        if(character==1){
            chooseCharacter.setIcon(resize(masterChief.afbeelding));
        }
        if(character==2){
            chooseCharacter.setIcon(resize(odst.afbeelding));
        }
        if(character==3){
            chooseCharacter.setIcon(resize(elite.afbeelding));
        }

        panel = new JPanel(new GridLayout(1,3));
        }catch (IOException e){

        }
    }

    public void maakPanel2(){
        remove(panel);

        panel2 = new JPanel(new GridLayout(1,3));
        JButton kmaster = new JButton();
        JButton kodst = new JButton();
        JButton kelite = new JButton();


        try {
            kmaster.setIcon(resize(masterChief.afbeelding));
            kelite.setIcon(resize(elite.afbeelding));
            kodst.setIcon(resize(odst.afbeelding));
        } catch (IOException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        };
        panel2.add(kmaster);
        panel2.add(kodst);
        panel2.add(kelite);
        add(panel2);

        kmaster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int check=1;
                check = JOptionPane.showConfirmDialog(null,masterChief.toString()+"\n\nWilt u met dit personage spelen?");
                if(check==0){
                    character=1;
                    maakPanel1();
                }

            }
        });
        kodst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int check=1;
                check = JOptionPane.showConfirmDialog(null, odst.toString()+"\n\nWilt u met dit personage spelen?");
                if(check==0){
                    character=2;
                    maakPanel1();
                }
            }
        });
        kelite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int check=1;                   //TODO: alle info gwn uit instantie van klasse halen!!!!!!!!!!
                check = JOptionPane.showConfirmDialog(null, elite.toString() +"\n\nWilt u met dit personage spelen?");
                if(check==0){
                    character=3;
                    maakPanel1();
                }
            }
        });

        revalidate();
    }


    public void maakLayout()  {




        panel.add(spelen);
        panel.add(chooseCharacter);
        panel.add(instellingen);

        add(panel);


    }

    public void voegListenersToe(){
        spelen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                naam = JOptionPane.showInputDialog(null,"Geef uw naam in: "); //TODO nog opzetten
                new UISpel(naam,character,veldLengte);
                setVisible(false);
            }
        });

        chooseCharacter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maakPanel2();
            }
        });

        instellingen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean fout;
                do{
                    fout=false;
                    try{
                        veldLengte = Integer.parseInt(JOptionPane.showInputDialog(null,"Hoe lang wilt u dat het veld is?(tussen 100 en 500)"));
                    }catch (NumberFormatException x){
                        JOptionPane.showMessageDialog(null,"Gelieve een getal in te voeren");
                        fout=true;
                    }
                }while (veldLengte>500||veldLengte<100||fout);

            }
        });
        /*addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {

            }
        }); */


    }


}
