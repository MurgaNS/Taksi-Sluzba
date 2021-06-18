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
        initGUI();
        initActions();
        setResizable(false);
        pack();
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
        dugmeOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long PIB = Long.parseLong(txtPIB.getText().trim());
                String naziv = txtNaziv.getText().trim();
                String adresa = txtAdresa.getText().trim();
                Double cenaPoKm = Double.parseDouble(txtCenaPoKilometru.getText().trim());
                Double cenaStarta = Double.parseDouble(txtCenaStarta.getText().trim());

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
        dugmePonisti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaksiSluzbaForma.this.dispose();
                TaksiSluzbaForma.this.setVisible(false);
                TaksiSluzbaProzor tsp = new TaksiSluzbaProzor();
                tsp.setVisible(true);
            }
        });
    }

    private void popuniPolja() {
        txtPIB.setText(String.valueOf(taksiSluzba.getPIB()));
        txtAdresa.setText(taksiSluzba.getAdresa());
        txtNaziv.setText(taksiSluzba.getNaziv());
        txtCenaPoKilometru.setText(String.valueOf(taksiSluzba.getCenaPoKilometru()));
        txtCenaStarta.setText(String.valueOf(taksiSluzba.getCenaStarta()));
    }
}
