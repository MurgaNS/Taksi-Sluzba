package Gui.FormeZaDodavanjeIIzmenu;

import Gui.FormeZaPrikaz.VoziloProzor;
import Model.Vozac;
import Model.Vozilo;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.List;

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
    String[] vrste = {"AUTOMOBIL", "KOMBI"};
    private JComboBox<String> txtVrsta = new JComboBox<>(vrste);
    private JLabel lblVozac = new JLabel("JMBG Vozaca");
    private JTextField txtVozac = new JTextField(20);
    private JButton dugmeOk = new JButton("Sacuvaj");
    private JButton dugmePonisti = new JButton("Ponisti");

    private Vozilo vozilo;
    List<Vozilo> listaVozila = Vozilo.ucitajSvaVozila();

    public VoziloForma(Vozilo v) {
        try {
            vozilo = Vozilo.pronadjiPoBrojuTaksiVozila(v.getBrTaksiVozila(), listaVozila);
        } catch (NullPointerException ignored) {
        }
        if (vozilo == null) {
            setTitle("Dodavanje vozila");
        } else {
            setTitle("Izmena podataka za vozilo " + vozilo.getBrRegistarskeOznake() + " " + vozilo.getProizvodjac() + " " + vozilo.getModel());
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        setSize(600, 300);
        initActions();
        pack();
    }

    public void initGUI() {
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        if (vozilo != null) {
            popuniPolja();
        }
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
        dugmeOk.addActionListener(e -> {
            if (validacija()) {
                String proizvodjac = txtProizvodjac.getText().trim();
                String model = txtModel.getText().trim();
                String godProizvodnje = txtGodProizvodnje.getText().trim();
                String brRegOznake = txtBrRegOznake.getText().trim();
                String vrstaTxt = txtVrsta.getSelectedItem().toString();
                Vozilo.VrstaVozila vrsta = Vozilo.ucitajVrstuVozila(vrstaTxt);
                String vozac = txtVozac.getText().trim();
                if (vozilo == null) {
                    Long vozacId;
                    try {
                        vozacId = Long.parseLong(vozac);
                    } catch (NumberFormatException numberFormatException) {
                        vozacId = null;
                    }
                    Long brTaksiVozila = Vozilo.generisiIdVozila();
                    Vozilo novoVozilo = new Vozilo(brTaksiVozila, model, proizvodjac, Integer.parseInt(godProizvodnje), brRegOznake, vrsta, false, vozacId);
                    listaVozila.add(novoVozilo);
                } else {
                    vozilo.setProizvodjac(proizvodjac);
                    vozilo.setModel(model);
                    vozilo.setGodProizvodnje(Integer.parseInt(godProizvodnje));
                    vozilo.setBrRegistarskeOznake(brRegOznake);
                    vozilo.setVrsta(vrsta);
                    try {
                        Vozac v = Vozac.pronadjiPoJmbg(Long.parseLong(vozac));
                        if (v != null) {
                            boolean vozacPostoji = !v.isObrisan();
                            boolean vozacNemaVozilo = v.getBrTaksiVozila() == null;
                            boolean vozacImaVoziloKojeSeMenja = Vozilo.voziloPripadaVozacu(v, vozilo);
                            if (vozacPostoji && (vozacNemaVozilo || vozacImaVoziloKojeSeMenja)) {
                                vozilo.setVozacId(Long.parseLong(vozac));
                            } else {
                                vozilo.setVozacId(null);
                                JOptionPane.showMessageDialog(null, "Vozac ne postoji ili vec ima dodeljen automobil", "Greska", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    } catch (NumberFormatException numberFormatException) {
                        vozilo.setVozacId(null);
                    }
                }
                Vozilo.sacuvajListuVozilaUFajl(listaVozila);
                VoziloForma.this.dispose();
                VoziloForma.this.setVisible(false);
                VoziloProzor vp = new VoziloProzor();
                vp.setVisible(true);
            }
        });
        dugmePonisti.addActionListener(e -> {
            VoziloForma.this.dispose();
            VoziloForma.this.setVisible(false);
            VoziloProzor vp = new VoziloProzor();
            vp.setVisible(true);
        });
    }

    private Boolean validacija() {
        boolean ok = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";

        try {
            Double.parseDouble(txtGodProizvodnje.getText().trim());
        } catch (NumberFormatException e) {
            poruka += "- Godina proizvodnje mora biti broj\n";
            ok = false;
        }

        if (txtProizvodjac.getText().trim().equals("")) {
            poruka += "- Morate uneti proizvodjaca\n";
            ok = false;
        }

        if (txtModel.getText().trim().equals("")) {
            poruka += "- Morate uneti model\n";
            ok = false;
        }

        if (txtBrRegOznake.getText().trim().equals("")) {
            poruka += "- Morate uneti broj registarske oznake\n";
            ok = false;
        }

        if (!ok) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }

    private void popuniPolja() {
        txtProizvodjac.setText(vozilo.getProizvodjac());
        txtModel.setText(vozilo.getModel());
        txtGodProizvodnje.setText(String.valueOf(vozilo.getGodProizvodnje()));
        txtBrRegOznake.setText(vozilo.getBrRegistarskeOznake());
        if (vozilo.getVrsta() == Vozilo.VrstaVozila.AUTOMOBIL) {
            txtVrsta.setSelectedIndex(0);
        } else {
            txtVrsta.setSelectedIndex(1);
        }
        txtVozac.setText(String.valueOf(vozilo.getVozacId()));
    }

}
