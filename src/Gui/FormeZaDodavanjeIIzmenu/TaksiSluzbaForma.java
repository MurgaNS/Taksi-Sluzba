package Gui.FormeZaDodavanjeIIzmenu;

import Model.TaksiSluzba;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

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
        add(dugmeOk,"split 2");
        add(dugmePonisti);
    }

    public void initActions() {
    }

    private void popuniPolja() {
        txtPIB.setText(String.valueOf(taksiSluzba.getPIB()));
        txtAdresa.setText(taksiSluzba.getAdresa());
        txtNaziv.setText(taksiSluzba.getNaziv());
        txtCenaPoKilometru.setText(String.valueOf(taksiSluzba.getCenaPoKilometru()));
        txtCenaStarta.setText(String.valueOf(taksiSluzba.getCenaStarta()));
    }
}
