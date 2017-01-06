import java.security.PublicKey;

/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 13/08/13
 * Time: 0:05
 * To change this template use File | Settings | File Templates.
 */
public class DamageReport {
    public String wapen;
    public int totalDamage;
    public int afstand;
    public int aantalGeschoten;
    public boolean player;

    public int accuracy;
    public int critchance;
    public int resistance;

    public String damageDealer;
    public String damageTaker;

    public int aantalGemist;
    public int aantalRaak;
    public int aantalCritical;

    public int remainingPlayerHealth;
    public int remainingEnemyHealth;

    public DamageReport(boolean player,double accuracy,double critchance,double resistance,String wapen, int totalDamage, int aantalGeschoten, String damageDealer, String damageTaker, int aantalGemist, int aantalRaak, int aantalCritical,int afstand) {
        this.wapen = wapen;
        this.totalDamage = totalDamage;
        this.aantalGeschoten = aantalGeschoten;
        this.damageDealer = damageDealer;
        this.damageTaker = damageTaker;
        this.aantalGemist = aantalGemist;
        this.aantalRaak = aantalRaak;
        this.aantalCritical = aantalCritical;
        this.accuracy = (int)Math.round(accuracy*100);
        this.critchance = (int)Math.round(critchance*100);
        this.resistance= (int)Math.round(resistance*100);
        this.player = player;
        if(this.accuracy<0){
            this.accuracy=0;
        }
        if(this.accuracy>100){
            this.accuracy=100;
        }
        if(this.critchance<0){
            this.critchance=0;
        }
        if(this.critchance>100){
            this.critchance=100;
        }
        this.afstand = afstand;
    }

    public void setRemainingPlayerHealth(int hp){
        this.remainingPlayerHealth=hp;
    }
    public void setRemainingEnemyHealth(int hp){
        this.remainingEnemyHealth=hp;
    }

    @Override
    public String toString() {
        if(player){
            return "________________________________________________PLAYER DAMAGE REPORT________________________________________________\n"
                    +"\ndamage van: '"+damageDealer+"' tegen '"+ damageTaker+ "'\n\nwapen: " + wapen + "\nafstand: " + afstand +"\ndamage("+resistance+"%): "+totalDamage
                    +"\n\naantal keer geschoten: "+aantalGeschoten+"\naantal raak("+accuracy+"%): "+aantalRaak+"\naantal critical("+critchance+"%): "
                    +aantalCritical+"\n\naantal gemist: "+aantalGemist+
                    "\n\n" + damageDealer + " health: " + remainingPlayerHealth + "\n"+damageTaker+" health: " + remainingEnemyHealth + "\n" +
                    "________________________________________________PLAYER DAMAGE REPORT________________________________________________\n";
        }else{
            return "________________________________________________ENEMY DAMAGE REPORT________________________________________________\n"
                    +"\ndamage van: '"+damageDealer+"' tegen '"+ damageTaker+ "'\n\nwapen: " + wapen + "\nafstand: " + afstand +"\ndamage: "+totalDamage
                    +"\n\naantal keer geschoten: "+aantalGeschoten+"\naantal raak("+accuracy+"%): "+aantalRaak+"\naantal critical("+critchance+"%): "
                    +aantalCritical+"\n\naantal gemist: "+aantalGemist+
                    "\n\n" + damageTaker + " health: " + remainingPlayerHealth + "\n"+damageDealer+" health: " + remainingEnemyHealth + "\n" +
                    "________________________________________________ENEMY DAMAGE REPORT________________________________________________\n";
        }

    }
}
