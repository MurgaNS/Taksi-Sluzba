package Gui.FormeZaDodavanjeIIzmenu.NarucivanjeVoznje;

import Gui.GlavniProzor;
import Model.Korisnik;
import Model.Voznja;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.Date;

public class NarucivanjeVoznjeTelefonomForma extends JFrame {

    private JLabel lblAdresaPolaska = new JLabel("Adresa polaska");
    private JTextField txtAdresaPolaska = new JTextField(30);
    private JLabel lblAdresaDolaska = new JLabel("Adresa dolaska");
    private JTextField txtAdresaDolaska = new JTextField(30);
    private JButton dugmeOk = new JButton("Sacuvaj");
    private JButton dugmePonisti = new JButton("Ponisti");

    public NarucivanjeVoznjeTelefonomForma() {
        setTitle("Narucivanje voznje telefonom");
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
        add(dugmeOk);
        add(dugmePonisti);

    }

    private void initActions() {
        dugmeOk.addActionListener(e -> {
            Date date = java.util.Calendar.getInstance().getTime();
            String adresaPolaska = txtAdresaPolaska.getText().trim();
            String adresaDolaska = txtAdresaDolaska.getText().trim();
            Voznja.StatusVoznje statusVoznje = Voznja.StatusVoznje.KREIRANA;
            Voznja.NacinPorudzbine nacinPorudzbine = Voznja.NacinPorudzbine.TELEFONOM;
            Korisnik musterija = GlavniProzor.getPrijavljeniKorisnik();
            Voznja voznja = new Voznja(Voznja.preuzmiPoslednjiId() + 1, date, adresaPolaska, adresaDolaska, 0, 0, statusVoznje, nacinPorudzbine, null, musterija.getJMBG(), 0,"null");
            Voznja.sacuvajNovuVoznju(voznja);
            NarucivanjeVoznjeTelefonomForma.this.dispose();
            NarucivanjeVoznjeTelefonomForma.this.setVisible(false);
            JOptionPane.showMessageDialog(null, "Uspesno ste porucili voznju putem telefona.");
        });
        dugmePonisti.addActionListener(e -> {
            NarucivanjeVoznjeTelefonomForma.this.dispose();
            NarucivanjeVoznjeTelefonomForma.this.setVisible(false);
        });
    }
}
