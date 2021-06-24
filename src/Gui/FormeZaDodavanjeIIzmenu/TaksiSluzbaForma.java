package Gui.FormeZaDodavanjeIIzmenu;

import Gui.FormeZaPrikaz.TaksiSluzbaProzor;
import Model.TaksiSluzba;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TaksiSluzbaForma extends JFrame {
    private JLabel lblPIB = new JLabel("PIB");
    private JTextField txtPIB = new JTextField(20);
    private JLabel lblNaziv = new JLabel("Naziv");
    private JTextField txtNaziv = new JTextField(20);
    private JLabel lblAdresa = new JLabel("Adresa");
    private JTextField txtAdresa = new JTextField(20);
    private JLabel lblCenaStarta = new JLabel("Cena starta");
    private JTextField txtCenaStarta = new JTextField(20);
    private JLabel lblCenaPoKilometru = new JLabel("Cena po kilometru");
    private JTextField txtCenaPoKilometru = new JTextField(20);
    private JButton dugmeOk = new JButton("Sacuvaj");
    private JButton dugmePonisti = new JButton("Ponisti");

    private TaksiSluzba taksiSluzba;

    public TaksiSluzbaForma(TaksiSluzba taksiSluzba) {
        this.taksiSluzba = taksiSluzba;
        setTitle("Izmena podataka taksi sluzbe " + taksiSluzba.getNaziv());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 300);
        initGUI();
        initActions();
    }

    public void initGUI() {
        MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][]20[]");
        setLayout(layout);
        popuniPolja();
        add(lblPIB);
        add(txtPIB);
        add(lblNaziv);
        add(txtNaziv);
        add(lblAdresa);
        add(txtAdresa);
        add(lblCenaPoKilometru);
        add(txtCenaPoKilometru);
        add(lblCenaStarta);
        add(txtCenaStarta);
        add(new JLabel());
        add(dugmeOk, "split 2");
        add(dugmePonisti);
    }

    public void initActions() {
        dugmeOk.addActionListener(e -> {
            if (validacija()) {
                long PIB = Long.parseLong(txtPIB.getText().trim());
                String naziv = txtNaziv.getText().trim();
                String adresa = txtAdresa.getText().trim();
                double cenaPoKm = Double.parseDouble(txtCenaPoKilometru.getText().trim());
                double cenaStarta = Double.parseDouble(txtCenaStarta.getText().trim());

                taksiSluzba.setPIB(PIB);
                taksiSluzba.setNaziv(naziv);
                taksiSluzba.setAdresa(adresa);
                taksiSluzba.setCenaStarta(cenaStarta);
                taksiSluzba.setCenaPoKilometru(cenaPoKm);

                try {
                    TaksiSluzba.sacuvajPodatkeUFajl(taksiSluzba);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                TaksiSluzbaForma.this.dispose();
                TaksiSluzbaForma.this.setVisible(false);
                TaksiSluzbaProzor tsp = new TaksiSluzbaProzor();
                tsp.setVisible(true);
            }
        });
        dugmePonisti.addActionListener(e -> {
            TaksiSluzbaForma.this.dispose();
            TaksiSluzbaForma.this.setVisible(false);
            TaksiSluzbaProzor tsp = new TaksiSluzbaProzor();
            tsp.setVisible(true);
        });
    }

    private Boolean validacija() {
        boolean ok = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";

        try {
            Double.parseDouble(txtCenaStarta.getText().trim());
        } catch (NumberFormatException e) {
            poruka += "- Cena starta voznje mora biti broj\n";
            ok = false;
        }
        try {
            Double.parseDouble(txtCenaPoKilometru.getText().trim());
        } catch (NumberFormatException e) {
            poruka += "- Cena po kilometru mora biti broj\n";
            ok = false;
        }
        try {
            Long.parseLong(txtPIB.getText().trim());
        } catch (NumberFormatException e) {
            poruka += "- PIB mora biti broj\n";
            ok = false;
        }
        if (txtNaziv.getText().trim().equals("")) {
            poruka += "- Morate uneti naziv taksi sluzbe\n";
            ok = false;
        }

        if (txtAdresa.getText().trim().equals("")) {
            poruka += "- Morate uneti adresu\n";
            ok = false;
        }

        if (!ok) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }

    private void popuniPolja() {
        txtPIB.setText(String.valueOf(taksiSluzba.getPIB()));
        txtAdresa.setText(taksiSluzba.getAdresa());
        txtNaziv.setText(taksiSluzba.getNaziv());
        txtCenaPoKilometru.setText(String.valueOf(taksiSluzba.getCenaPoKilometru()));
        txtCenaStarta.setText(String.valueOf(taksiSluzba.getCenaStarta()));
    }
}
