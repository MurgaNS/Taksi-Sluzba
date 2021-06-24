package Gui.FormeZaDodavanjeIIzmenu;

import Model.Vozac;
import Model.Voznja;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class AukcijaVozacForma extends JFrame {
    private JLabel lblMinutaDoDestinacije = new JLabel("Minuta do destinacije");
    private JTextField txtMinutaDoDestinacije = new JTextField(20);
    private JButton dugmeOk = new JButton("Sacuvaj");
    private JButton dugmePonisti = new JButton("Ponisti");
    private Voznja voznja;
    private Vozac vozac;

    public AukcijaVozacForma(Voznja selektovanaVoznja, Vozac selektovanVozac) {
        vozac = selektovanVozac;
        voznja = selektovanaVoznja;
        setVisible(true);
        setTitle("Aukcija vozac");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 300);
        initGUI();
        initActions();
    }

    public void initGUI() {
        MigLayout layout = new MigLayout("wrap 1");
        setLayout(layout);
        add(lblMinutaDoDestinacije);
        add(txtMinutaDoDestinacije);
        add(dugmeOk);
        add(dugmePonisti);
    }

    public void initActions() {
    }
}
