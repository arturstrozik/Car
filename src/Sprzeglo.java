public class Sprzeglo extends Komponent{

    private boolean stanSprzegla;

    public void wcisnij() {
        System.out.println("Sprzeglo zostalo wcisniete.");
        this.stanSprzegla = true;

    }

    public void zwolnij() {
        System.out.println("Sprzeglo zostalo zwolnione.");
        this.stanSprzegla = false;

    }

    public boolean getStanSprzegla() {
        return stanSprzegla;
    }

    public String getStanSprzeglaString() {
        if (stanSprzegla) {
            return "Wcisniete";
        }
        else {
            return "Zwolnione";
        }
    }

    public Sprzeglo(String nazwa, double waga, double cena) {
        super(nazwa, waga, cena);
    }
}
