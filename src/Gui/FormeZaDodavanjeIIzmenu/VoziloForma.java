package Gui.FormeZaDodavanjeIIzmenu;

import Model.Vozilo;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class VoziloForma extends JFrame {
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

    public VoziloForma(Vozilo v) {
        this.vozilo = v;
        setTitle("Izmena podataka za vozilo " + vozilo.getBrRegistarskeOznake() + " " + vozilo.getProizvodjac() + " " + vozilo.getModel());
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
    }

    private void popuniPolja() {
        txtProizvodjac.setText(vozilo.getProizvodjac());
        txtModel.setText(vozilo.getModel());
        txtGodProizvodnje.setText(String.valueOf(vozilo.getGodProizvodnje()));
        txtBrRegOznake.setText(vozilo.getBrRegistarskeOznake());
        txtVrsta.setText(vozilo.getVrsta());
        txtVozac.setText(String.valueOf(vozilo.getVozacId()));
    }

}
