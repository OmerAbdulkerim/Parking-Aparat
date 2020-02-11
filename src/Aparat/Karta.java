package Aparat;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Karta {

    protected static long redniBroj;
    private int id;
    private Instant pocetakTrajanja;
    private long vrijemeTrajanja;
    private Instant krajTrajanja;

    Karta(){
        redniBroj = redniBroj++;
    }

    //Postvaljanje ID-a karte, a za to se koristi override-ana haschode metoda i seta se kroz aparat klasu
    public void setId(int id) {
        this.id = id;
    }

    //Postavlja se vrijeme kada je karta izdata odnosno kada pocinje da vazi
    public void setPocetakTrajanja() {
        long vrijeme= System.currentTimeMillis();
        Date date = new Date(vrijeme);
        this.pocetakTrajanja = date.toInstant();
    }

    /*U aparat klasi se racuna koliko korisnik unosi novca te se za tu svotu dodjeljuje vrijeme na karti
    prikazano i izracunato u minutama*/
    public void setVrijemeTrajanja(long trajanjePoMinutama) {
        this.vrijemeTrajanja = trajanjePoMinutama;
    }

    //Metoda kojom racunamo prestanak vazenja izdate karte
    public void setKrajTrajanja() {
        this.krajTrajanja = pocetakTrajanja.plus(vrijemeTrajanja, MINUTES);
    }

    @Override
    public int hashCode(){
        return Objects.hash(pocetakTrajanja, vrijemeTrajanja, krajTrajanja, redniBroj);
    }

    @Override
    public String toString() {
        return "Datum izdavanja: " + pocetakTrajanja + "\nSerijski broj=" + id
                + "\nUkupno trajanje:" + vrijemeTrajanja + " minuta" + "\nVrijedi do: " + krajTrajanja;
    }
}
