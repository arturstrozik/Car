public class Samochod extends Thread{

    Pozycja pozycja;
    SkrzyniaBiegow skrzynia;
    Silnik silnik;
    Pozycja wskazanyCel = new Pozycja(0,0);

    private boolean stanWlaczenia = false;
    private String nrRejst;
    private String model;
    private double predkoscMax;
    private double waga;
    private double aktPredkosc;
    private String marka;
    private int przechwyconyBiegInt;
    private int roznicaBiegow;
    private boolean definitywneZnieszczenie = false;

    public void wlacz() {
        if (!definitywneZnieszczenie) {
            if (!stanWlaczenia) {
                if (this.skrzynia.sprzeglo.getStanSprzegla()) {
                    if (this.skrzynia.getAktualnyBieg() == 0) {
                        System.out.println("Samochod zostal wlaczony.");
                        stanWlaczenia = true;
                        this.silnik.uruchom();
                    } else {
                        System.out.println("Wrzuc na luz.");
                    }
                } else if (this.skrzynia.getAktualnyBieg() == 0) {
                    System.out.println("Samochod zostal wlaczony.");
                    stanWlaczenia = true;
                    this.silnik.uruchom();
                } else {
                    System.out.println("Wcisnij sprzeglo.");
                }
            } else {
                System.out.println("Samochod jest juz wlaczony.");
            }
        } else {
            System.out.println("Samochod jest kompletnie zniszczony.");
        }
    }

    public void wylacz() {
        if (stanWlaczenia) {
            System.out.println("Samochod zostal wylaczony.");
            stanWlaczenia = false;
            this.silnik.zatrzymaj();
        }
    }

    public void run(){
        while (true) {
            this.getAktPredkosc();
            this.jedzDo(wskazanyCel);
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                ;
            }
        }
    }

    public void jedzDo( Pozycja cel) {
        Pozycja start = this.getPozycja();
        if (!this.skrzynia.sprzeglo.getStanSprzegla()){
            start.przemiesc(getAktPredkosc(), 0.1, cel);
        }
    }

    public Pozycja getPozycja() {
        return this.pozycja;
    }

    public double getWaga() {
        this.waga = 1000 + this.skrzynia.getWaga() + this.skrzynia.sprzeglo.getWaga() + this.silnik.getWaga();
        return this.waga;
    }

    public String getModel() {
        return this.model;
    }

    public String getNrRejst() {
        return  this.nrRejst;
    }

    public String getMarka() {
        return this.marka;
    }

    public String getNazwaListaCombobox() {
        String nazwa = getNrRejst() + ", " + getMarka() + ", " + getModel();
        return nazwa;
    }

    public double getAktPredkosc(){
        if (this.pozycja == wskazanyCel) {
            this.aktPredkosc = 0;
            return this.aktPredkosc;
        } else if (this.pozycja.equals(wskazanyCel)) {
            this.aktPredkosc = 0;
            return this.aktPredkosc;
        }else if (wskazanyCel.getX() == this.getPozycja().getX() && wskazanyCel.getY() == this.getPozycja().getY()) {
            this.aktPredkosc = 0;
            return aktPredkosc;
        } else {
            double stala = 0.03;
            this.aktPredkosc = this.silnik.getObroty() * this.skrzynia.getAktualnePrzelozenie() * stala;
            if (this.aktPredkosc > predkoscMax){
                this.aktPredkosc = predkoscMax;
                return this.aktPredkosc;
            } else {
                return this.aktPredkosc;
            }
        }
    }

    public void przechwycBieg() {
        przechwyconyBiegInt = skrzynia.getAktualnyBieg();
    }

    public void zmianaObrotowPrzyZmianieBiegu() {
        if (this.stanWlaczenia){
            this.roznicaBiegow = skrzynia.getAktualnyBieg() - przechwyconyBiegInt;

            if (this.skrzynia.getAktualnyBieg() == -1) {
                silnik.zmniejszObroty();
                silnik.zmniejszObroty();
                silnik.zmniejszObroty();
            } else if (this.skrzynia.getAktualnyBieg() == 0) {
                silnik.setObroty(1000);
            }

            if (this.roznicaBiegow < 0) {
                if (this.skrzynia.getAktualnyBieg() >= 1 && this.skrzynia.getAktualnyBieg() <= this.skrzynia.getIloscBiegow()) {
                    if (silnik.getObroty() + 750 * (-1) * roznicaBiegow <= silnik.getMaxObroty()) {
                        for (int i = 0; i < Math.abs(roznicaBiegow); i++) {
                            silnik.zwiekszObroty();
                            silnik.zwiekszObroty();
                            silnik.zwiekszObroty();
                        }
                    } else {
                        this.wylacz();
                        definitywneZnieszczenie = true;
                        System.out.println("Skrzynia nie nadaje sie do uzytku");
                    }
                }
            } else if (this.roznicaBiegow > 0) {
                if (this.skrzynia.getAktualnyBieg() >= 1 && this.skrzynia.getAktualnyBieg() <= this.skrzynia.getIloscBiegow()) {
                    if (silnik.getObroty() - 750 * roznicaBiegow >= 1000) {
                        for (int i = 0; i < Math.abs(roznicaBiegow); i++) {
                            silnik.zmniejszObroty();
                            silnik.zmniejszObroty();
                            silnik.zmniejszObroty();
                        }
                    } else {
                        this.wylacz();
                        System.out.println("Zbyt niskie obroty.");
                    }
                }
            }
        }
    }

    public Samochod(Pozycja pozycja, SkrzyniaBiegow skrzynia, Silnik silnik, String model, String nrRejst, double predkoscMax) {
        this.pozycja = pozycja;
        this.skrzynia = skrzynia;
        this.silnik = silnik;
        this.model = model;
        this.nrRejst = nrRejst;
        this.predkoscMax = predkoscMax;
        start();
    }

    public Samochod(Pozycja pozycja, SkrzyniaBiegow skrzynia, Silnik silnik, String model, String nrRejst, String marka, double predkoscMax) {
        this.pozycja = pozycja;
        this.skrzynia = skrzynia;
        this.silnik = silnik;
        this.model = model;
        this.nrRejst = nrRejst;
        this.predkoscMax = predkoscMax;
        this.marka = marka;
        this.start();
    }

    public Samochod() {
        start();
    }

    public static void main(String[] args) {

    }
}
