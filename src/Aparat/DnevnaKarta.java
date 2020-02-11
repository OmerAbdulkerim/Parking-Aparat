package Aparat;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.HOURS;

public class DnevnaKarta extends Karta {

    private int id;
    private static long vrijemeTrajanja = 24;
    private static double cijenaDnevne = 10;
    private Instant pocetakTrajanja;
    private Instant krajTrajanja;


    DnevnaKarta(){
        redniBroj = redniBroj++;
    }

    public void setid(int id){
        this.id = id;
    }

    public static void setCijena(double novaCijena){
        cijenaDnevne = novaCijena;
    }

    public static double getCijena(){
        return cijenaDnevne;
    }

    public void setPocetakTrajanja() {
        long vrijeme= System.currentTimeMillis();
        Date date = new Date(vrijeme);
        this.pocetakTrajanja = date.toInstant();
    }

    public void setKrajTrajanja() {
        this.krajTrajanja = pocetakTrajanja.plus(vrijemeTrajanja, HOURS);
    }

    @Override
    public int hashCode(){
        return Objects.hash(vrijemeTrajanja, pocetakTrajanja, krajTrajanja, redniBroj);
    }

    @Override
    public String toString(){
        return "Datum izdavanja: " + pocetakTrajanja + "\nSerijski broj=" + id
                + "\nUkupno sati:" + vrijemeTrajanja + "\nVrijedi do: " + krajTrajanja;
    }
}
