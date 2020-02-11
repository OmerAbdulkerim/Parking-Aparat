package Aparat;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        Aparat aparat = new Aparat();
        Scanner unos = new Scanner(System.in);

        System.out.println("Dobro dosli " +
                "\n1) Nova karta: " + "\n2) Nova dnevna karta: " +
                "\n3) Postavite cijenu 1h parkinga: " + "\n4) Postavite cijenu 24h karte: "
                + "\n5) Isprintaj sve karte: ");

        int selekcija = unos.nextInt();

        switch (selekcija){
            case 1:
                System.out.println("Unos se prekida kad kada unesete 0 kao vrijednost kovanice.");
                kovaniceUnos(aparat, unos, false);

                aparat.racunanjeUnesenogNovca();
                if (aparat.getUkupnoNovca() > 0){
                    aparat.izdavanjeSatneKarte();
                    aparat.dekonstrukcijaListeKovanica();
                }
                else
                    System.out.println("Niste unijeli dovoljno novca!");
                menu();
                break;

            case 2:
                kovaniceUnos(aparat, unos, false);
                aparat.racunanjeUnesenogNovca();
                if (aparat.getUkupnoNovca() >= DnevnaKarta.getCijena()){
                    aparat.izdavanjeDnevneKarte();
                }
                else{
                    System.out.println("Niste unijeli dovoljno novca!");
                }
                menu();
                break;

            case 3:
                System.out.println("Unesite novu cijenu jednog sata parkinga: ");
                double cijenaSata = unos.nextDouble();
                aparat.setCijenaSata(cijenaSata);
                menu();
                break;

            case 4:
                System.out.println("Unesite novu cijenu dnevne parking karte: ");
                double novaCijenaDnevne = unos.nextDouble();
                aparat.setCijenaDnevne(novaCijenaDnevne);
                menu();
                break;
            case 5:
                aparat.printKarte();
                menu();
                break;
        }
    }

    protected static void kovaniceUnos(Aparat aparat, Scanner unos, boolean prekidUnosa) {
        do {
            System.out.println("Unesite vrijednost kovanice: ");
            double vrijednostKovanice = unos.nextDouble();

            if (!aparat.provjeraVrijednostiKovanice(vrijednostKovanice)){
                System.out.println("Kovanica se ne prepoznaje. Unesite drugu kovanicu: ");
                vrijednostKovanice = unos.nextDouble();
            }
            else {
                System.out.println("Unesite broj kovanica:");
                int brojKovanica = unos.nextInt();
                aparat.unosKovanica(brojKovanica, vrijednostKovanice);
            }

            if (vrijednostKovanice == 0){
                prekidUnosa = true;
            }
        }
        while (!prekidUnosa);
    }
}
