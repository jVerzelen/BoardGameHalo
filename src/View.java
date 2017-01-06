import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 12/08/13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public class View {
    public static void main(String[] args) {

        System.out.println("het zou moeten werken");
        Scanner input = new Scanner(System.in);
        SpeelVeld speelVeld = new SpeelVeld("chief",1,200);


        System.out.println(speelVeld.showVeldRange());


        while (!speelVeld.endGame()){
            //beurt
            System.out.println(speelVeld.rol());
            while(!speelVeld.zetStappen(input.nextInt())){
                System.out.println("U kan zich slecht voor 0 en het gerolde aantal verplaatsen:");
            }
            //speelVeld.sortVijanden();
            if(speelVeld.checkBonussen()){
                if(speelVeld.bonusAlEquipped()){
                    System.out.println(speelVeld.raapOpOfEquip());
                }else{
                    System.out.print(speelVeld.vervangGevondenWapenVraag());
                    speelVeld.equipBonus(input.nextInt());
                }
                /*
                System.out.println(speelVeld.showBonus());
                speelVeld.equipBonus(input.nextInt()); */
            }
            //input.nextLine();
            System.out.println(speelVeld.showVeldRange());
            if(speelVeld.meleeOfNiet()){
                System.out.println(speelVeld.meleeFight());
            }else{
                input.nextLine();
                System.out.println("wenst u op de dichstbijzijnde vijand te schieten?\n(y/n): ");
                if(input.nextLine().equals("y")){
                    System.out.println(speelVeld.player.showBothWeapons());
                    System.out.println("Met welk wapen wenst u te vuren?\n(1 of 2):");
                    System.out.println(speelVeld.vuurOpVijand(input.nextInt()));
                    //input.nextLine();
                }else {
                    System.out.println(speelVeld.kanVijandVuren());
                    //input.nextLine();
                }
            }


            if(speelVeld.checkBonussen()){
                if(speelVeld.bonusAlEquipped()){
                    System.out.println(speelVeld.raapOpOfEquip());
                }else{
                    System.out.print(speelVeld.vervangGevondenWapenVraag());
                    speelVeld.equipBonus(input.nextInt());
                }
                /*
                System.out.println(speelVeld.showBonus());
                speelVeld.equipBonus(input.nextInt()); */
            }
            //---input.nextLine();
            System.out.println("***********************************************************************************************************");
            System.out.println(speelVeld.showVeldRange());
        }


    }
}
