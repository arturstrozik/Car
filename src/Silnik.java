public class Silnik extends Komponent{


    private int maxObroty;
    private int obroty;
    private boolean silnikPracuje = false;

    public void uruchom() {
        System.out.println("Silnik zostal uruchomiony.");
        this.obroty = 1000;
        this.silnikPracuje = true;
    }

    public void zatrzymaj() {
        System.out.println("Silnik zostal zatrzymany.");
        this.obroty = 0;
        this.silnikPracuje = false;
    }

    public int getObroty() {
        return this.obroty;
    }

    public void setObroty(int obroty) {
        this.obroty = obroty;
    }

    public void zwiekszObroty() {
        if (this.silnikPracuje) {
            if (this.obroty < this.maxObroty) {
                this.obroty += 250;
                if (this.obroty > this.maxObroty) {
                    this.obroty = this.maxObroty;
                }
            } else {
                System.out.println("Odciecie paliwa.");
            }
        }
    }

    public void zmniejszObroty() {
        if (this.silnikPracuje) {
            if (this.obroty > 1000) {
                this.obroty -= 250;
                if (this.obroty < 1000) {
                    this.obroty = 1000;
                }
            } else {
                System.out.println("Nie mozna ustawic nizszych obrotow, bo silnik zgasnie");
            }
        }
    }

    public int getMaxObroty() {
        return maxObroty;
    }

    public Silnik(String nazwa, double waga, double cena, int maxObroty) {
        super(nazwa, waga, cena);
        this.maxObroty = maxObroty;
    }
}
