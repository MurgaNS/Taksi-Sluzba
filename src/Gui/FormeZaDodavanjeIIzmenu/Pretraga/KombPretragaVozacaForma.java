package Gui.FormeZaDodavanjeIIzmenu.Pretraga;

import Gui.FormeZaPrikaz.VozaciProzor;
import Model.Vozac;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;
import StrukturePodataka.List;


public class KombPretragaVozacaForma extends JFrame {
    private JLabel lblIme = new JLabel("Ime");
    private JTextField txtIme = new JTextField(30);
    private JLabel lblPrezime = new JLabel("Prezime");
    private JTextField txtPrezime = new JTextField(30);
    private JLabel lblPlata= new JLabel("Plata");
    private JTextField txtPlata = new JTextField(30);
    private JLabel lblAutomobil= new JLabel("Automobil");
    private JTextField txtAutomobil = new JTextField(30);

    private JButton dugmeOk = new JButton("Pretrazi");
    private JButton dugmePonisti = new JButton("Ponisti");

    public KombPretragaVozacaForma() {
        setTitle("Pretraga vozaca");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setVisible(true);
        setSize(600, 600);
        initGui();
        initActions();
    }
    public void initGui() {
        MigLayout migLayout = new MigLayout("wrap2");
        setLayout(migLayout);
        add(lblIme);
        add(txtIme);
        add(lblPrezime);
        add(txtPrezime);
        add(lblPlata);
        add(txtPlata);
        add(lblAutomobil);
        add(txtAutomobil);
        add(dugmeOk);
        add(dugmePonisti);
    }
    public static boolean vozacPostoji(List<Vozac> vozaci, long vozacJmbg){
        for(Vozac vozac : vozaci){
            if(vozac.getJMBG() == vozacJmbg){
                return true;
            }
        }
        return false;

    }
    public void initActions() {
        dugmeOk.addActionListener(e -> {
            List<Vozac> listaPronadjenihVozaca = new List<>();

            String ime = txtIme.getText().trim();
            String prezime = txtPrezime.getText().trim();
            String plataString = txtPlata.getText().trim();
            String automobilIdString = txtAutomobil.getText().trim();

            if (!ime.isEmpty()) {
                List<Vozac> vozaci = Vozac.pretragaPoImenu(ime);
                for(Vozac vozac : vozaci){
                    if(!vozacPostoji(listaPronadjenihVozaca, vozac.getJMBG())){
                        listaPronadjenihVozaca.add(vozac);
                    }
                }
            }
            if (!prezime.isEmpty()) {
                List<Vozac> vozaci = Vozac.pretragaPoPrezimenu(prezime);
                for(Vozac vozac : vozaci){
                    if(!vozacPostoji(listaPronadjenihVozaca, vozac.getJMBG())){
                        listaPronadjenihVozaca.add(vozac);
                    }
                }
            }
            if (!plataString.isEmpty()) {
                double plata = Double.parseDouble(plataString);
                List<Vozac> vozaci = Vozac.pretragaPoPlati(plata);
                for(Vozac vozac : vozaci){
                    if(!vozacPostoji(listaPronadjenihVozaca, vozac.getJMBG())){
                        listaPronadjenihVozaca.add(vozac);
                    }
                }
            }
            if (!automobilIdString.isEmpty()) {
                long automobilId = Long.parseLong(automobilIdString);
                Vozac vozac = Vozac.pretragaPoAutomobilu(automobilId);
                if(!vozacPostoji(listaPronadjenihVozaca, vozac.getJMBG())){
                    listaPronadjenihVozaca.add(vozac);
                }
            }

            if (listaPronadjenihVozaca != null) {
                VozaciProzor vozaciProzor = new VozaciProzor(listaPronadjenihVozaca);
                vozaciProzor.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Ne postoje rezultati pretrage.", "Greska!", JOptionPane.ERROR_MESSAGE);
            }
        });
        dugmePonisti.addActionListener(e -> {
            KombPretragaVozacaForma.this.dispose();
            KombPretragaVozacaForma.this.setVisible(false);
        });
    }





}