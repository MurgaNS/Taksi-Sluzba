package Gui.FormeZaPrikaz.PrikazVoznji;

import Model.Musterija;
import Model.Vozac;
import Model.Vozilo;
import Model.Voznja;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DodeliVozacu extends JFrame {
    private JLabel lblAdresaPolaska = new JLabel("Adresa polaska");
    private JTextField txtAdresaPolaska = new JTextField(30);
    private JLabel lblAdresaDolaska = new JLabel("Adresa dolaska");
    private JTextField txtAdresaDolaska = new JTextField(30);
    private JLabel lblMusterija = new JLabel("Musterija");
    private JTextField txtMusterija = new JTextField(20);
    private JLabel lblVozaci = new JLabel("Izaberi vozaca: ");
    private JComboBox<String> comboBoxVozaci = new JComboBox<>();
    private JButton dugmeOk = new JButton("Dodeli");


    public DodeliVozacu(Voznja voznja){
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
        List<Vozilo> vozila = Vozilo.ucitajSvaVozila();
        for (Vozilo vozilo : vozila){
            if(vozilo.getVozacId() != null){
                comboBoxVozaci.addItem(String.valueOf(vozilo.getVozacId()));
                System.out.println(vozilo.getVozacId());
            }
        }

    }

    private void initActions(Voznja voznja){
        dugmeOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long idVozaca = Long.parseLong(comboBoxVozaci.getSelectedItem().toString());
                voznja.setVozacJMBG(idVozaca);
                voznja.setStatusVoznje(Voznja.StatusVoznje.DODELJENA);
                Voznja.izmeniStatusVoznje(voznja);
            }
        });
    }
}
