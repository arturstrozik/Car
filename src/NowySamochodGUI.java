import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NowySamochodGUI extends JFrame {
    private JTextField nrRejst;
    private JTextField model;
    private JTextField marka;
    private JRadioButton piecBiegówManualna;
    private JRadioButton szescBiegówManualna;
    private JRadioButton benzynaRadioButton;
    private JRadioButton dieselRadioButton;
    private JTextField pred_max;
    private JButton dodajButton;
    private JButton anulujButton;
    private JPanel panel2;

    public NowySamochodGUI(JComboBox comboBox) {
        setContentPane(panel2);

        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkrzyniaBiegow skrzynia = new SkrzyniaBiegow("domyslna 5-biegow", 200, 500, 5,
                        new Sprzeglo("OK", 50, 100));

                if (piecBiegówManualna.isSelected()) {
                     skrzynia = new SkrzyniaBiegow("5-biegow", 200, 500, 5,
                            new Sprzeglo("OK", 50, 100));
                }
                else  if (szescBiegówManualna.isSelected()) {
                     skrzynia = new SkrzyniaBiegow("6-biegow", 200, 500, 6,
                            new Sprzeglo("OK", 50, 100));
                }

                Silnik silnik = new Silnik("domyslny benzyna", 400, 800, 7000);

                if (benzynaRadioButton.isSelected()) {
                    silnik = new Silnik("benzyna", 400, 800, 7000);
                }
                else if (dieselRadioButton.isSelected()) {
                    silnik = new Silnik("diesel", 500, 900, 5000);
                }

                Pozycja pozycjaStartowa = new Pozycja(0, 0);
                double predkoscMaxPodana = 200;
                try {
                    predkoscMaxPodana = Double.valueOf(pred_max.getText());
                } catch (Exception e1) {
                    System.out.println("Zla wartosc predkosci lub jej brak, moga byc tylko liczby.");
                    System.out.println("Zostala ustawiona domyslna maksymalna predkosc rowan 200");
                }

                Samochod samochod = new Samochod(pozycjaStartowa, skrzynia, silnik, model.getText(), nrRejst.getText(),
                         marka.getText(), predkoscMaxPodana);

                comboBox.addItem(samochod);
                comboBox.setRenderer(new ListaCombobox());
                dispose();
            }
        });
    }
}
