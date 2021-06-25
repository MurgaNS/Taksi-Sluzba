package Gui.FormeZaDodavanjeIIzmenu;

import Gui.FormeZaPrikaz.VoziloProzor;
import Model.Vozilo;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PretragaVozilaForma extends JFrame {
    private JLabel lblModel = new JLabel("Model");
    private JTextField txtModel = new JTextField(30);
    private JLabel lblProizvodjac = new JLabel("Proizvodjac");
    private JTextField txtProizvodjac = new JTextField(30);
    private JLabel lvlGodinaProizvodnje = new JLabel("Godina proizvodnje");
    private JTextField txtGodinaProizvodnje = new JTextField(30);
    private JLabel lblBrojRegOznake = new JLabel("Broj registarske oznake");
    private JTextField txtBrojRegOznake = new JTextField(30);
    private JLabel lblBrojTaksiVozila = new JLabel("Broj taksi vozila");
    private JTextField txtBrojTaksiVozila = new JTextField(30);

    private JButton dugmeOk = new JButton("Pretrazi");
    private JButton dugmePonisti = new JButton("Ponisti");

    public PretragaVozilaForma() {
        setTitle("Pretraga vozila");
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
        add(lblModel);
        add(txtModel);
        add(lblProizvodjac);
        add(txtProizvodjac);
        add(lvlGodinaProizvodnje);
        add(txtGodinaProizvodnje);
        add(lblBrojRegOznake);
        add(txtBrojRegOznake);
        add(lblBrojTaksiVozila);
        add(txtBrojTaksiVozila);
        add(dugmeOk);
        add(dugmePonisti);
    }

    public void initActions() {
        txtBrojTaksiVozila.setText("0");
        txtGodinaProizvodnje.setText("0");

        List<Vozilo> listaPronadjenihVozila = new ArrayList<>();
        dugmeOk.addActionListener(e -> {
            if (validacija()) {
                String model = txtModel.getText().trim();
                String proizvodjac = txtProizvodjac.getText().trim();
                String brojRegOznake = txtBrojRegOznake.getText().trim();
                long brojTaksiVozila = Long.parseLong(txtBrojTaksiVozila.getText().trim());
                int godinaProizvodnje = Integer.parseInt(txtGodinaProizvodnje.getText().trim());
                if (!model.isEmpty()) {
                    listaPronadjenihVozila.addAll(Vozilo.pretragaPoModelu(model));
                }
                if (!proizvodjac.isEmpty()) {
                    listaPronadjenihVozila.addAll(Vozilo.pretragaPoProizvodjacu(proizvodjac));
                }
                if (godinaProizvodnje != 0) {
                    listaPronadjenihVozila.addAll(Vozilo.pretragaPoGodiniProizvodnje(godinaProizvodnje));
                }
                if (!brojRegOznake.isEmpty()) {
                    listaPronadjenihVozila.addAll(Vozilo.pretragaPoBrojuRegOznake(brojRegOznake));
                }
                if (brojTaksiVozila != 0) {
                    listaPronadjenihVozila.addAll(Vozilo.pretragaPoBrojuTaksiVozila(brojTaksiVozila));
                }
                if (!listaPronadjenihVozila.isEmpty()) {
                    VoziloProzor prikazPretrageVozilaProzor = new VoziloProzor(listaPronadjenihVozila);
                    prikazPretrageVozilaProzor.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Ne postoje rezultati pretrage.", "Greska!", JOptionPane.ERROR_MESSAGE);
                }
                PretragaVozilaForma.this.dispose();
                PretragaVozilaForma.this.setVisible(false);
            }
        });
        dugmePonisti.addActionListener(e -> {
            VoziloProzor voziloProzor = new VoziloProzor();
            PretragaVozilaForma.this.dispose();
            PretragaVozilaForma.this.setVisible(false);
        });
    }

    private Boolean validacija() {
        boolean ok = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";

        try {
            Integer.parseInt(txtGodinaProizvodnje.getText().trim());
        } catch (NumberFormatException e) {
            poruka += "- Godina proizvodnje vozila mora biti broj\n";
            ok = false;
        }
        try {
            Long.parseLong(txtBrojTaksiVozila.getText().trim());
        } catch (NumberFormatException e) {
            poruka += "- Broj taksi vozila mora biti broj\n";
            ok = false;
        }
//        if (!txtBrojTaksiVozila.getText().trim().isEmpty()) {
//            try {
//                Long.parseLong(txtBrojTaksiVozila.getText().trim());
//            } catch (NumberFormatException e) {
//                poruka += "- Broj taksi vozila mora biti broj\n";
//                ok = false;
//            }
//        }
//        if (!txtGodinaProizvodnje.getText().trim().isEmpty()) {
//            try {
//                Long.parseLong(txtGodinaProizvodnje.getText().trim());
//            } catch (NumberFormatException e) {
//                poruka += "- Broj taksi vozila mora biti broj\n";
//                ok = false;
//            }
//        }

        if (!ok) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
