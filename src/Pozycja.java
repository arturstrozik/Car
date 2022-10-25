import static java.lang.Math.sqrt;

public class Pozycja {

    private double x;
    private double y;

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void przemiesc(double predkosc, double czas, Pozycja cel){
        if (this.x != cel.getX() || this.y != cel.getY()) {

            double droga = predkosc * czas;
            double odleglosc = sqrt(((cel.x - this.x) * (cel.x - this.x)) + ((cel.y - this.y) * (cel.y - this.y)));
            double roznicaPion = cel.y - this.y;
            double roznicaPoziom = cel.x - this.x;
            double przesuniecieY = (droga / odleglosc) * roznicaPion;
            double przesuniecieX = (droga / odleglosc) * roznicaPoziom;

            if (Math.abs(przesuniecieX) >= Math.abs(cel.getX() - this.x)) {
                this.x = cel.getX();
            }
            else {
                this.x = this.x + przesuniecieX;
            }

            if (Math.abs(przesuniecieY) >= Math.abs(cel.getY() - this.y)) {
                this.y = cel.getY();
            }
            else {
                this.y = this.y + przesuniecieY;
            }

            /*
            if (Math.abs(przesuniecieX) >= Math.abs(cel.getX() - this.x) && Math.abs(przesuniecieY) >= Math.abs(cel.getY() - this.y)) {
                this.x = cel.getX();
                this.y = cel.getY();
            }
            else {
                this.x = this.x + przesuniecieX;
                this.y = this.y + przesuniecieY;
            }
            */
        }
    }
}
