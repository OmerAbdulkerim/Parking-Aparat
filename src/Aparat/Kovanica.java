package Aparat;

public class Kovanica {
    
    private double vrijednostKovanice;
    private int brojKovanica;

    Kovanica(int brojKovanica, double vrijednostKovanice){
        this.vrijednostKovanice = vrijednostKovanice;
        this.brojKovanica = brojKovanica;
    }

    public double getVrijednostKovanice() {
        return vrijednostKovanice;
    }

    public int getBrojKovanica() {
        return brojKovanica;
    }

    @Override
    public String toString(){
        return "Kovanica: " + getVrijednostKovanice() + "  Kolicina kovanica: " + getBrojKovanica();
    }

}
