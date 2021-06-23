package Gui.FormeZaDodavanjeIIzmenu;

import Gui.GlavniProzor;
import Model.Korisnik;
import Model.Voznja;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.Date;

public class NarucivanjeVoznjeForma extends JFrame {
    private JLabel lblAdresaPolaska = new JLabel("Adresa polaska");
    private JTextField txtAdresaPolaska = new JTextField(30);
    private JLabel lblAdresaDolaska = new JLabel("Adresa dolaska");
    private JTextField txtAdresaDolaska = new JTextField(30);
    private JLabel lblDatum = new JLabel("Datum");
    private JFormattedTextField txtDatum = new JFormattedTextField("yyyy-MM-dd");
    private JLabel lblNapomena = new JLabel("Napomena");
    private JTextField txtNapomena = new JTextField(50);

    private JButton dugmeOk = new JButton("Sacuvaj");
    private JButton dugmePonisti = new JButton("Ponisti");

    public NarucivanjeVoznjeForma() {
        setTitle("Narucivanje voznje");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setSize(600, 300);
        initGui();
        initActions();
    }

    private void initGui() {
        MigLayout migLayout = new MigLayout("wrap 2");
        setLayout(migLayout);
        add(lblAdresaPolaska);
        add(txtAdresaPolaska);
        add(lblAdresaDolaska);
        add(txtAdresaDolaska);
        add(lblDatum);
        add(txtDatum);
        add(lblNapomena);
        add(txtNapomena);
        add(dugmeOk);
        add(dugmePonisti);
    }

    private void initActions() {
        dugmeOk.addActionListener(e -> {
            Date date = java.util.Calendar.getInstance().getTime();
            String adresaPolaska = txtAdresaPolaska.getText().trim();
            String adresaDolaska = txtAdresaDolaska.getText().trim();
            String napomena = txtNapomena.getText().trim();
            Voznja.StatusVoznje statusVoznje = Voznja.StatusVoznje.KREIRANA_NA_CEKANJU;
            Voznja.NacinPorudzbine nacinPorudzbine = Voznja.NacinPorudzbine.APLIKACIJOM;
            Korisnik musterija = GlavniProzor.getPrijavljeniKorisnik();
            Voznja voznja = new Voznja(Voznja.preuzmiPoslednjiId() + 1, date, adresaPolaska, adresaDolaska, 0, 0, statusVoznje, nacinPorudzbine, null, musterija.getJMBG(), 0, napomena);
            Voznja.sacuvajVoznju(voznja);
            NarucivanjeVoznjeForma.this.dispose();
            NarucivanjeVoznjeForma.this.setVisible(false);
            JOptionPane.showMessageDialog(null, "Uspesno ste porucili voznju.", "Uspesno porucivanje", JOptionPane.INFORMATION_MESSAGE);
        });
        dugmePonisti.addActionListener(e -> {
            NarucivanjeVoznjeForma.this.dispose();
            NarucivanjeVoznjeForma.this.setVisible(false);
        });
    }
}