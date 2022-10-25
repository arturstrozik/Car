import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SamochodGUI extends Thread {
    private JTextField samochodModel;
    private JTextField samochodNrRej;
    private JTextField samochodWaga;
    private JTextField samochodPredkosc;
    private JTextField skrzyniaNazwa;
    private JTextField skrzyniaCena;
    private JTextField skrzyniaWaga;
    private JTextField skrzyniaBieg;
    private JTextField silnikNazwa;
    private JTextField silnikCena;
    private JTextField silnikWaga;
    private JTextField silnikObroty;
    private JTextField sprzegloNazwa;
    private JTextField sprzegloCena;
    private JTextField sprzegloWaga;
    private JTextField sprzegloStan;
    private JButton wylacz;
    private JButton wlacz;
    private JButton usunButton;
    private JButton dodajNowyButton;
    private JComboBox comboBox1;
    private JPanel panel;
    private JButton zwiekszBieg;
    private JButton zmniejszBieg;
    private JButton dodajGazu;
    private JButton ujmijGazu;
    private JButton nacisnijSprzeglo;
    private JButton zwolnijSprzeglo;
    private JPanel mapa;
    private JLabel samIcon;
    private Samochod samochod;

    public void run() {
        while(true) {
            refresh();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                ;
            }
        }
    }

    public SamochodGUI(Samochod s){
        samochod = s;
        refresh();

        wlacz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.wlacz();
                refresh();
            }
        });
        wylacz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.wylacz();
                refresh();
            }
        });
        zwiekszBieg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.skrzynia.zwiekszBieg();
                refresh();
            }
        });
        zmniejszBieg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.skrzynia.zmniejszBieg();
                refresh();
            }
        });
        dodajGazu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.silnik.zwiekszObroty();
                refresh();
            }
        });
        ujmijGazu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.silnik.zmniejszObroty();
                refresh();
            }
        });
        nacisnijSprzeglo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.skrzynia.sprzeglo.wcisnij();
                samochod.przechwycBieg();
                refresh();
            }
        });
        zwolnijSprzeglo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod.skrzynia.sprzeglo.zwolnij();
                samochod.zmianaObrotowPrzyZmianieBiegu();
                refresh();
            }
        });
        mapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                try {
                    samochod.wskazanyCel = new Pozycja(e.getX(), e.getY());
                    refresh();
                } catch (NullPointerException e1) {
                    System.out.println("Troche ciezko jechac jak nie ma samochodu.");
                }
            }
        });

        dodajNowyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new NowySamochodGUI(comboBox1);
                f.pack();
                f.setResizable(false);
                f.setVisible(true);
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochod = (Samochod) comboBox1.getSelectedItem();
                samIcon.isVisible();
            }
        });
        usunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox1.removeItem(samochod);
                try {
                    samochod.interrupt();
                } catch (NullPointerException n) {
                    return;
                }
            }
        });
        start();
    }

    public void refresh() {
        if (comboBox1.getItemCount() != 0) {
            samochodModel.setText(samochod.getModel());
            samochodNrRej.setText(samochod.getNrRejst());
            samochodWaga.setText(Double.toString(samochod.getWaga()));
            samochodPredkosc.setText(Double.toString(samochod.getAktPredkosc()));

            skrzyniaNazwa.setText(samochod.skrzynia.getNazwa());
            skrzyniaCena.setText(Double.toString(samochod.skrzynia.getCena()));
            skrzyniaWaga.setText(Double.toString(samochod.skrzynia.getWaga()));
            skrzyniaBieg.setText(Double.toString(samochod.skrzynia.getAktualnyBieg()));

            silnikNazwa.setText(samochod.silnik.getNazwa());
            silnikCena.setText(Double.toString(samochod.silnik.getCena()));
            silnikObroty.setText(Double.toString(samochod.silnik.getObroty()));
            silnikWaga.setText(Double.toString(samochod.silnik.getWaga()));

            sprzegloNazwa.setText(samochod.skrzynia.sprzeglo.getNazwa());
            sprzegloCena.setText(Double.toString(samochod.skrzynia.sprzeglo.getCena()));
            sprzegloStan.setText(samochod.skrzynia.sprzeglo.getStanSprzeglaString());
            sprzegloWaga.setText(Double.toString(samochod.skrzynia.sprzeglo.getWaga()));

            samIcon.setVisible(true);
            samIcon.setLocation((int) samochod.getPozycja().getX(), (int) samochod.getPozycja().getY());
        }
        else {
            samochodModel.setText("");
            samochodNrRej.setText("");
            samochodWaga.setText("");
            samochodPredkosc.setText("");

            skrzyniaNazwa.setText("");
            skrzyniaCena.setText("");
            skrzyniaWaga.setText("");
            skrzyniaBieg.setText("");

            silnikNazwa.setText("");
            silnikCena.setText("");
            silnikObroty.setText("");
            silnikWaga.setText("");

            sprzegloNazwa.setText("");
            sprzegloCena.setText("");
            sprzegloStan.setText("");
            sprzegloWaga.setText("");

            samIcon.setVisible(false);
        }
    }

    public static void main(String[] args) {
        Samochod sam = new Samochod(new Pozycja(0,0),
                new SkrzyniaBiegow("5-biegow domyslna", 400, 500, 5,
                new Sprzeglo("domyslne", 100, 10)),
                new Silnik("benzyna", 1000, 800, 7000),
                "Corsa", "K1 OPEL", "Opel", 150);

        JFrame frame = new JFrame("SamochodGUI");
        frame.setContentPane(new SamochodGUI(sam).panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
