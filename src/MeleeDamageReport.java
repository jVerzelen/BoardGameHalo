/**
 * Created with IntelliJ IDEA.
 * User: joni thuis
 * Date: 13/08/13
 * Time: 11:01
 * To change this template use File | Settings | File Templates.
 */
public class MeleeDamageReport {

    public String report;

    public MeleeDamageReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {

            return "________________________________________________MELEE DAMAGE REPORT________________________________________________\n"
                    +"\n"+report+
                    "________________________________________________MELEE DAMAGE REPORT________________________________________________\n";


        }

    }

