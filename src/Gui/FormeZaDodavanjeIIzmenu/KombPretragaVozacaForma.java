package Gui.FormeZaDodavanjeIIzmenu;

import Gui.FormeZaPrikaz.VozaciProzor;
import Gui.FormeZaPrikaz.VoziloProzor;
import Model.Vozac;
import Model.Vozilo;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class KombPretragaVozacaForma extends JFrame {
    private JLabel lblIme = new JLabel("Ime");
    private JTextField txtIme = new JTextField(30);
    private JLabel lblPrezime = new JLabel("Prezime");
    private JTextField txtPrezime = new JTextField(30);
    private JLabel lblPlata= new JLabel("Plata");
    private JTextField txtPlata = new JTextField(30);
    private JLabel lblAutomobil= new JLabel("Automobil(broj reg oznake)");
    private JTextField txtAutomobil = new JTextField(30);

    private JButton dugmeOk = new JButton("Pretrazi");
    private JButton dugmePonisti = new JButton("Ponisti");

    public KombPretragaVozacaForma() {
        setTitle("Pretraga vozaca");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setVisible(true);
        setSize(600, 600);
        initGui();
        initActions();
    }
    public void initGui() {
        MigLayout migLayout = new MigLayout("wrap2");
        setLayout(migLayout);
        add(lblIme);
        add(txtIme);
        add(lblPrezime);
        add(txtPrezime);
        add(lblPlata);
        add(txtPlata);
        add(lblAutomobil);
        add(txtAutomobil);
        add(dugmeOk);
        add(dugmePonisti);
    }

    public void initActions() {
        List<Vozac> listaPronadjenihVozaca = new ArrayList<>();
        dugmeOk.addActionListener(e -> {
            String ime = txtIme.getText().trim();
            String prezime = txtPrezime.getText().trim();
            double plata = 0;
            long automobil = 0;

            if (!ime.isEmpty()) {
                listaPronadjenihVozaca.addAll(Vozac.pretragaPoImenu(ime));
            }
            if (!prezime.isEmpty()) {
                listaPronadjenihVozaca.addAll(Vozac.pretragaPoPrezimenu(prezime));
            }
            if (plata != 0) {
                listaPronadjenihVozaca.addAll(Vozac.pretragaPoPlati(plata));
            }
//            if (automobil != 0) {
//                listaPronadjenihVozaca.addAll(Vozac.pretragaPoAutomobilu(automobil));
//            }

            if (listaPronadjenihVozaca != null) {
                VozaciProzor vozaciProzor = new VozaciProzor(listaPronadjenihVozaca);
                vozaciProzor.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Ne postoje rezultati pretrage.", "Greska!", JOptionPane.ERROR_MESSAGE);
            }
        });
        dugmePonisti.addActionListener(e -> {
            KombPretragaVozacaForma.this.dispose();
            KombPretragaVozacaForma.this.setVisible(false);
        });
    }





}