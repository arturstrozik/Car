public class SkrzyniaBiegow extends Komponent {

    private int aktualnyBieg;
    private int iloscBiegow;
    private double aktualnePrzelozenie;

    Sprzeglo sprzeglo;

    public int getAktualnyBieg() {
        return aktualnyBieg;
    }

    public double getAktualnePrzelozenie() {
        if (this.getAktualnyBieg() == -1){
            this.aktualnePrzelozenie = -0.27278;
        } else if (this.getAktualnyBieg() == 0) {
            this.aktualnePrzelozenie = 0;
        } else if (this.getAktualnyBieg() == 1) {
            this.aktualnePrzelozenie = 0.27278;
        } else if (this.getAktualnyBieg() == 2) {
            this.aktualnePrzelozenie = 0.48852;
        } else if (this.getAktualnyBieg() == 3) {
            this.aktualnePrzelozenie = 0.79491;
        } else if (this.getAktualnyBieg() == 4) {
            this.aktualnePrzelozenie = 1.15741;
        } else if (this.getAktualnyBieg() == 5) {
            this.aktualnePrzelozenie = 1.48368;
        } else if (this.getAktualnyBieg() == 6) {
            this.aktualnePrzelozenie = 1.89472;
        } else {
            System.out.println("Jest problem.");
        }

        return aktualnePrzelozenie;
    }

    public void zwiekszBieg() {
        if (sprzeglo.getStanSprzegla()) {
            if (this.aktualnyBieg < this.iloscBiegow) {
                this.aktualnyBieg += 1;
            } else {
                System.out.println("Nie ma juz wiecej biegow.");
            }
        } else {
            System.out.println("Wcisnij sprzeglo.");
        }
    }

    public void zmniejszBieg() {
        if (sprzeglo.getStanSprzegla()){
            if (this.aktualnyBieg > -1){
                this.aktualnyBieg -= 1;
            } else {
                System.out.println("Nie mozna bardziej zmniejszyc biegu.");
            }
        } else {
            System.out.println("Wcisnij sprzeglo.");
        }
    }

    public int getIloscBiegow() {
        return iloscBiegow;
    }

    public SkrzyniaBiegow(String nazwa, double waga, double cena, int iloscBiegow, Sprzeglo sprzeglo) {
        super(nazwa, waga, cena);
        this.iloscBiegow = iloscBiegow;
        this.sprzeglo = sprzeglo;
    }

}
