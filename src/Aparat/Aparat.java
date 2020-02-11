package Aparat;

import java.util.ArrayList;


public class Aparat {

    /*Lista kovanica samo trenutnog unosa, izbrisu se sve kovanice nakon izdavanja trenutne karte, dakle vezana je samo
      za jednu kartu */
    private ArrayList<Kovanica> listaKovanica = new ArrayList<>();
    /*Ova lista se ne brise nego se cuva zauvijek i koristi se za skladistenje svih izdatih karti */
    private static ArrayList<Karta> listaKarti = new ArrayList<>();
    private double ukupnoNovca;
    private static double cijenaSata = 1;

    Aparat(){

    }



    //Postavljanje cijene jednog sata parkinga
    public void setCijenaSata(double cijenaSata){
        Aparat.cijenaSata = cijenaSata;
    }

    //Postavljanje cijene dnevne karte
    public void setCijenaDnevne(double cijenaDnevne){
        DnevnaKarta.setCijena(cijenaDnevne);
    }

    //Dodjeljivanje ukupne sume novca pripadajucem data fieldu
    private void setUkupnoNovca(double uneseniNovac){
        this.ukupnoNovca = uneseniNovac;
    }

    double getUkupnoNovca(){
        return ukupnoNovca;
    }

    //Provjera da li su kovanice validne (Primaju se samo 0.5KM, 1KM i 2KM)
    public boolean provjeraVrijednostiKovanice (double vrijednostKovanice) {
        return vrijednostKovanice == 0.5 || vrijednostKovanice == 1.0 || vrijednostKovanice == 2.0 || vrijednostKovanice == 0;
    }

    //Pracenje unosa svih kovanica u aparat
    public void unosKovanica(int brojKovanica, double vrijednostKovanice){
        Kovanica kovanica = new Kovanica(brojKovanica, vrijednostKovanice);
        listaKovanica.add(kovanica);
    }

    //Racunanje svih unesenih kovanica/sumu novca od strane trenutne musterije/korisnika
    public void racunanjeUnesenogNovca(){
        double kolicina05 = 0;
        double kolicina1 = 0;
        double kolicina2 = 0;
        double suma;

        for (Kovanica kovanica : listaKovanica){
            if (kovanica.getVrijednostKovanice() == 0.5){
                kolicina05 = 0.5 * kovanica.getBrojKovanica();
            }
            else if (kovanica.getVrijednostKovanice() == 1){
                kolicina1 = kovanica.getBrojKovanica();
            }
            else if (kovanica.getVrijednostKovanice() == 2){
                kolicina2 = 2 * kovanica.getBrojKovanica();
            }
        }

        suma = kolicina1 + kolicina2 + kolicina05;
        setUkupnoNovca(suma);
        System.out.println("Suma unesena: " + suma);
    }

    //Test metoda za provjeru toka programa jer sam imao nekih problema
    public void printKarte (){
        System.out.println("Sve izdate karte: " + "\n====================================");
        for (Karta karta : listaKarti){
            System.out.println(karta.toString() + "\n====================================");
        }
    }

    //Racuna koliko minuta se dobije na ukupnu kolicinu unesenog novca
    public int racunanjeVremenaTrajanja(){
        return (int) Math.round(60 * ukupnoNovca / cijenaSata);
    }

    //Brisanje citave liste unesenih kovanica nakon sto trenutni korisnik dobije kartu te je vraca u prvobitni oblik
    public void dekonstrukcijaListeKovanica(){
        listaKovanica.clear();
    }

    //Metoda koja izdaje satnu kartu korisniku i sprema je u listu svih izdatih karti
    public void izdavanjeSatneKarte(){
        Karta karta = new Karta();
        karta.setPocetakTrajanja();
        karta.setVrijemeTrajanja(racunanjeVremenaTrajanja());
        karta.setKrajTrajanja();
        karta.setId(karta.hashCode());
        listaKarti.add(karta);
        System.out.println(karta.toString());
    }

    //Metoda koja izdaje dnevnu kartu te i nju sprema u listu svih izdatih karti
    public void izdavanjeDnevneKarte(){
        DnevnaKarta dk = new DnevnaKarta();
        dk.setPocetakTrajanja();
        dk.setKrajTrajanja();
        dk.setid(dk.hashCode());
        listaKarti.add(dk);
        System.out.println(dk.toString());
    }
}
