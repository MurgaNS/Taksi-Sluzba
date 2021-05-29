package Gui.FormeZaDodavanjeIIzmenu;

import Model.Vozilo;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class VoziloForma extends JFrame {
    private JLabel lblBrTaksiVozila = new JLabel("Br. taksi vozila");
    private JTextField txtBrTaksiVozila = new JTextField(20);
    private JLabel lblProizvodjac = new JLabel("Proizvodjac");
    private JTextField txtProizvodjac = new JTextField(20);
    private JLabel lblModel = new JLabel("Model");
    private JTextField txtModel = new JTextField(20);
    private JLabel lblGodProizvodnje = new JLabel("God. proizvodnje");
    private JTextField txtGodProizvodnje = new JTextField(20);
    private JLabel lblBrRegOznake = new JLabel("Br. reg. oznake");
    private JTextField txtBrRegOznake = new JTextField(20);
    private JLabel lblVrsta = new JLabel("Vrsta");
    private JTextField txtVrsta = new JTextField(20);
    private JLabel lblVozac = new JLabel("VozacId");
    private JTextField txtVozac = new JTextField(20);
    private JButton dugmeOk = new JButton("Sacuvaj");
    private JButton dugmePonisti = new JButton("Ponisti");

    private Vozilo vozilo;
    List<Vozilo> listaVozila = Vozilo.ucitajSveAutomobile();

    public VoziloForma(Vozilo v) {
        try {
            vozilo = Vozilo.pronadjiPoBrojuTaksiVozila(v.getBrTaksiVozila(), listaVozila);
        } catch (NullPointerException e) {
        }
        if (vozilo == null) {
            setTitle("Dodavanje vozila");
        } else {
            setTitle("Izmena podataka za vozilo " + vozilo.getBrRegistarskeOznake() + " " + vozilo.getProizvodjac() + " " + vozilo.getModel());
        }
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
        if (vozilo != null) {
            popuniPolja();
            txtBrTaksiVozila.setEnabled(false);
        }
        add(lblBrTaksiVozila);
        add(txtBrTaksiVozila);
        add(lblProizvodjac);
        add(txtProizvodjac);
        add(lblModel);
        add(txtModel);
        add(lblGodProizvodnje);
        add(txtGodProizvodnje);
        add(lblBrRegOznake);
        add(txtBrRegOznake);
        add(lblVrsta);
        add(txtVrsta);
        add(lblVozac);
        add(txtVozac);
        add(new JLabel());
        add(dugmeOk, "split 2");
        add(dugmePonisti);
    }

    public void initActions() {
        dugmeOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija()) {
                    String brTaksiVozila = txtBrTaksiVozila.getText().trim();
                    String proizvodjac = txtProizvodjac.getText().trim();
                    String model = txtModel.getText().trim();
                    String godProizvodnje = txtGodProizvodnje.getText().trim();
                    String brRegOznake = txtBrRegOznake.getText().trim();
                    String vrsta = txtVrsta.getText().trim();
                    String vozac = txtVozac.getText().trim();
                    if (vozilo == null) {
                        Vozilo novoVozilo = new Vozilo(brTaksiVozila, model, proizvodjac, Integer.parseInt(godProizvodnje), brRegOznake, vrsta, false, Long.parseLong(vozac));
                        listaVozila.add(novoVozilo);
                    } else {
                        vozilo.setBrTaksiVozila(brTaksiVozila);
                        vozilo.setProizvodjac(proizvodjac);
                        vozilo.setModel(model);
                        vozilo.setGodProizvodnje(Integer.parseInt(godProizvodnje));
                        vozilo.setBrRegistarskeOznake(brRegOznake);
                        vozilo.setVrsta(vrsta);
                        vozilo.setVozacId(Long.parseLong(vozac));
                    }
                    Vozilo.sacuvajListuAutomobilaUFajl(listaVozila);
                    VoziloForma.this.dispose();
                    VoziloForma.this.setVisible(false);
                }
            }
        });
    }

    private Boolean validacija() {
        return true;
    }

    private void popuniPolja() {
        txtBrTaksiVozila.setText(vozilo.getBrTaksiVozila());
        txtProizvodjac.setText(vozilo.getProizvodjac());
        txtModel.setText(vozilo.getModel());
        txtGodProizvodnje.setText(String.valueOf(vozilo.getGodProizvodnje()));
        txtBrRegOznake.setText(vozilo.getBrRegistarskeOznake());
        txtVrsta.setText(vozilo.getVrsta());
        txtVozac.setText(String.valueOf(vozilo.getVozacId()));
    }

}
