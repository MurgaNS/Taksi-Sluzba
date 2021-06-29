package Gui.FormeZaDodavanjeIIzmenu;

import Gui.FormeZaPrikaz.PrikazVoznji.AukcijaVoznjeProzor;
import Model.Aukcija;
import Model.Vozac;
import Model.Vozilo;
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
        setLocation(400, 250);
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
        dugmeOk.addActionListener(e -> {
            double minutaDoDestinacije = Double.parseDouble(txtMinutaDoDestinacije.getText().trim());
            long vozacId = vozac.getId();
            long voznjaId = voznja.getId();
            long aukcijaId = Aukcija.generisiIdAukcije();
            int brojVoznji = Vozac.brojVoznji(vozac);
            double vremeDolaska = Double.parseDouble(txtMinutaDoDestinacije.getText().trim());
            Vozilo vozilo = Vozilo.pretraziPoId(Vozilo.ucitajSvaVozila(), vozac.getBrTaksiVozila());
            int godinaProizvodnjeVozila = vozilo.getGodProizvodnje();
            double ocenaVozaca = 1;
            double ocena = Aukcija.izracunajOcenu(brojVoznji, vremeDolaska, godinaProizvodnjeVozila, ocenaVozaca);
            Aukcija.sacuvajAukciju(new Aukcija(aukcijaId, vozacId, voznjaId, minutaDoDestinacije, ocena));
            AukcijaVozacForma.this.dispose();
            AukcijaVozacForma.this.setVisible(false);
            new AukcijaVoznjeProzor();
        });
        dugmePonisti.addActionListener(e -> {
            AukcijaVozacForma.this.dispose();
            AukcijaVozacForma.this.setVisible(false);
            new AukcijaVoznjeProzor();
        });
    }
}
