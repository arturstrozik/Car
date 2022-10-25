import javax.swing.*;

public class ListaCombobox extends DefaultListCellRenderer {
    public ListaCombobox getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        String samochodNazwaLista = "";
        if (value instanceof Samochod) {
            samochodNazwaLista = ((Samochod)value).getNazwaListaCombobox();
        }
        super.getListCellRendererComponent(list, samochodNazwaLista, index, isSelected, cellHasFocus);
        return this;
    }
}
