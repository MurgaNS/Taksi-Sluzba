package Gui.FormeZaDodavanjeIIzmenu.NarucivanjeVoznje;

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
        txtNapomena.setText("nema");
        add(lblAdresaPolaska);
        add(txtAdresaPolaska);
        add(lblAdresaDolaska);
        add(txtAdresaDolaska);
        add(lblNapomena);
        add(txtNapomena);
        add(dugmeOk);
        add(dugmePonisti);
    }

    private void initActions() {
        dugmeOk.addActionListener(e -> {
            if (validacija()) {
                Date date = java.util.Calendar.getInstance().getTime();
                String adresaPolaska = txtAdresaPolaska.getText().trim();
                String adresaDolaska = txtAdresaDolaska.getText().trim();
                String napomena = txtNapomena.getText().trim();
                Voznja.StatusVoznje statusVoznje = Voznja.StatusVoznje.KREIRANA_NA_CEKANJU;
                Voznja.NacinPorudzbine nacinPorudzbine = Voznja.NacinPorudzbine.APLIKACIJOM;
                Korisnik musterija = GlavniProzor.getPrijavljeniKorisnik();
                Voznja voznja = new Voznja(Voznja.preuzmiPoslednjiId() + 1, date, adresaPolaska, adresaDolaska, 0, 0, statusVoznje, nacinPorudzbine, null, musterija.getJMBG(), 0, napomena);
                Voznja.sacuvajNovuVoznju(voznja);
                NarucivanjeVoznjeForma.this.dispose();
                NarucivanjeVoznjeForma.this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Uspesno ste porucili voznju.", "Uspesno porucivanje", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        dugmePonisti.addActionListener(e -> {
            NarucivanjeVoznjeForma.this.dispose();
            NarucivanjeVoznjeForma.this.setVisible(false);
        });
    }

    private Boolean validacija() {
        boolean ok = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";

        if (txtNapomena.getText().trim().isEmpty()) {
            poruka += "- Napomena ne sme biti prazna\n";
            ok = false;
        }

        if (txtAdresaDolaska.getText().trim().isEmpty()) {
            poruka += "- Morate uneti adresu dolaska\n";
            ok = false;
        }

        if (txtAdresaPolaska.getText().trim().isEmpty()) {
            poruka += "- Morate uneti adresu polaska\n";
            ok = false;
        }
        if (!ok) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
