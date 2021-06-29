package Gui.FormeZaPrikaz.PrikazVoznji;

import Model.Musterija;
import Model.Vozilo;
import Model.Voznja;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import StrukturePodataka.ArrayList;

public class DodeliVoznjuVozacuProzor extends JFrame {
    private JLabel lblAdresaPolaska = new JLabel("Adresa polaska");
    private JTextField txtAdresaPolaska = new JTextField(30);
    private JLabel lblAdresaDolaska = new JLabel("Adresa dolaska");
    private JTextField txtAdresaDolaska = new JTextField(30);
    private JLabel lblMusterija = new JLabel("Musterija");
    private JTextField txtMusterija = new JTextField(20);
    private JLabel lblVozaci = new JLabel("Unesi vozaca(izaberite JMBG vozaca): ");
    private JComboBox<String> comboBoxVozaci = new JComboBox<>();
    private JButton dugmeOk = new JButton("Dodeli");


    public DodeliVoznjuVozacuProzor(Voznja voznja){
        setTitle("Dodeli voznju");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI(voznja);
        initActions(voznja);
    }

    private void initGUI(Voznja voznja){
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(lblAdresaPolaska);
        add(txtAdresaPolaska);
        txtAdresaPolaska.setEditable(false);
        txtAdresaPolaska.setText(voznja.getAdresaPolaska());
        add(lblAdresaDolaska);
        add(txtAdresaDolaska);
        txtAdresaDolaska.setEditable(false);
        txtAdresaDolaska.setText(voznja.getAdresaDestinacije());
        add(lblMusterija);
        add(txtMusterija);
        txtMusterija.setEditable(false);
        Musterija musterija = (Musterija) Musterija.nadjiKorisnikaPrekoJMBG(voznja.getMusterijaJMBG());
        txtMusterija.setText(musterija.getIme() + " " + musterija.getPrezime());
        add(lblVozaci);
        add(comboBoxVozaci);
        ArrayList<Vozilo> vozila = Vozilo.ucitajSvaVozila();
        for (Vozilo vozilo : vozila){
            if(vozilo.getVozacId() != null){
                comboBoxVozaci.addItem(String.valueOf(vozilo.getVozacId()));
            }
        }
        add(dugmeOk);
    }

    private void initActions(Voznja voznja){
        dugmeOk.addActionListener(e -> {
            long idVozaca = Long.parseLong(comboBoxVozaci.getSelectedItem().toString());
            voznja.setVozacJMBG(idVozaca);
            voznja.setStatusVoznje(Voznja.StatusVoznje.DODELJENA);
            Voznja.izmeniStatusVoznje(voznja);
            setVisible(false);

        });
    }
}
