package Gui.FormeZaDodavanjeIIzmenu;

import Gui.FormeZaPrikaz.TaksiSluzbaProzor;
import Gui.FormeZaPrikaz.VozaciProzor;
import Gui.FormeZaPrikaz.VoziloProzor;
import Model.Korisnik;
import Model.TaksiSluzba;
import Model.Vozac;
import Model.Vozilo;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class VozaciForma extends JFrame {
    private JLabel lblJMBG = new JLabel("JMBG");
    private JTextField txtJMBG = new JTextField(20);
    private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
    private JTextField txtKorisnickoIme = new JTextField(20);
    private JLabel lblLozinka = new JLabel("Lozinka");
    private JTextField txtLozinka = new JTextField(20);
    private JLabel lblIme = new JLabel("Ime");
    private JTextField txtIme = new JTextField(20);
    private JLabel lblPrezime = new JLabel("Prezime");
    private JTextField txtPrezime = new JTextField(20);
    private JLabel lblAdresa = new JLabel("Adresa");
    private JTextField txtAdresa = new JTextField(20);
    private JLabel lblPol = new JLabel("Pol");
    private JTextField txtPol = new JTextField(20);
    private JLabel lblBrojTelefona = new JLabel("Broj telefona");
    private JTextField txtBrojTelefona = new JTextField(20);
    private JLabel lblPlata = new JLabel("Plata");
    private JTextField txtPlata = new JTextField(20);
    private JLabel lblBrojClanskeKarte = new JLabel("Broj clanske karte");
    private JTextField txtBrojClanskeKarte = new JTextField(20);
    private JButton dugmeOk = new JButton("Sacuvaj");
    private JButton dugmePonisti = new JButton("Ponisti");

    private Vozac vozac;
    List<Vozac> listaVozaca = Vozac.ucitajSveVozace();

    public VozaciForma(Vozac v) {
        try {
            vozac = (Vozac) Vozac.nadjiKorisnikaPrekoJMBG((v.getJMBG()));
//            vozac = Vozac.pronadjiPoJMBG(v.getJMBG());
        } catch (NullPointerException e) {
        }
        if (vozac == null) {
            setTitle("Dodavanje vozaca");
        } else {
            setTitle("Izmena podataka za vozaca " + vozac.getJMBG() + " " + vozac.getIme() + " " + vozac.getPrezime());
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        setSize(600, 300);
        initActions();
        pack();
    }
    public void initGUI() {
        MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][]20[]");
        setLayout(layout);
        if (vozac != null) {
            popuniPolja();
            txtJMBG.setEnabled(false);
        }
        add(lblJMBG);
        add(txtJMBG);
        add(lblKorisnickoIme);
        add(txtKorisnickoIme);
        add(lblLozinka);
        add(txtLozinka);
        add(lblIme);
        add(txtIme);
        add(lblPrezime);
        add(txtPrezime);
        add(lblAdresa);
        add(txtAdresa);
        add(lblPol);
        add(txtPol);
        add(lblBrojTelefona);
        add(txtBrojTelefona);
        add(lblPlata);
        add(txtPlata);
        add(lblBrojClanskeKarte);
        add(txtBrojClanskeKarte);
        add(new JLabel());
        add(dugmeOk, "split 2");
        add(dugmePonisti);
    }

    public void initActions() {
        dugmeOk.addActionListener(e -> {
            if (validacija()) {
                Long JMBG = Long.parseLong(txtJMBG.getText().trim());
                String korisnickoIme = txtKorisnickoIme.getText().trim();
                String lozinka = txtLozinka.getText().trim();
                String ime = txtIme.getText().trim();
                String prezime = txtPrezime.getText().trim();
                String adresa = txtAdresa.getText().trim();
                String polTxt = txtPol.getSelectedText().toString();
                Korisnik.Pol pol;
                if (polTxt.equalsIgnoreCase("MUSKI")){
                    pol = Korisnik.Pol.MUSKI;
                } else {
                    pol = Korisnik.Pol.ZENSKI;
                }
                String brojTelefona = txtBrojTelefona.getText().trim();
                Double plata = Double.parseDouble(txtPlata.getText().trim());
                Integer brojClanskeKarte = Integer.parseInt(txtBrojClanskeKarte.getText().trim());






//                String vozac = txtVozac.getText().trim();
//                if (vozilo == null) {
//                    Long vozacId;
//                    try {
//                        vozacId = Long.parseLong(vozac);
//                    } catch (NumberFormatException numberFormatException) {
//                        vozacId = null;
//                    }
//                    Vozac noviVozac = new Vozac()
//                    listaVozila.add(novoVozilo);
//                } else {
//                    vozilo.setBrTaksiVozila(brTaksiVozila);
//                    vozilo.setProizvodjac(proizvodjac);
//                    vozilo.setModel(model);
//                    vozilo.setGodProizvodnje(Integer.parseInt(godProizvodnje));
//                    vozilo.setBrRegistarskeOznake(brRegOznake);
//                    vozilo.setVrsta(vrsta);
//                    try {
//                        vozilo.setVozacId(Long.parseLong(vozac));
//                    } catch (NumberFormatException numberFormatException) {
//                        vozilo.setVozacId(null);
//                    }
//                }
                Vozac.upisiVozaca((Vozac) listaVozaca);
                VozaciForma.this.dispose();
                VozaciForma.this.setVisible(false);
                VozaciProzor vp = new VozaciProzor();
                vp.setVisible(true);
            }
        });
        dugmePonisti.addActionListener(e -> {
            VozaciForma.this.dispose();
            VozaciForma.this.setVisible(false);
            VozaciProzor vp = new VozaciProzor();
            vp.setVisible(true);
        });
    }

    private Boolean validacija() {
        boolean ok = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";

        if (txtJMBG.getText().trim().equals("")) {
            poruka += "- Morate uneti ID\n";
            ok = false;
        } else if (vozac == null) {
            String id = txtJMBG.getText().trim();
            List<Vozac> listaVozaca = Vozac.ucitajSveVozace();
            Vozac postojiVozac = (Vozac) Vozac.nadjiKorisnikaPrekoJMBG(vozac.getJMBG());
            if (postojiVozac != null) {
                poruka += "- Vozac sa unetim ID vec postoji\n";
                ok = false;
            }
        }


        if (txtKorisnickoIme.getText().trim().equals("")) {
            poruka += "- Morate uneti korisnicko ime\n";
            ok = false;
        }

        if (txtLozinka.getText().trim().equals("")) {
            poruka += "- Morate uneti lozinku\n";
            ok = false;
        }

        if (txtIme.getText().trim().equals("")) {
            poruka += "- Morate uneti ime\n";
            ok = false;
        }
        if (txtPrezime.getText().trim().equals("")) {
            poruka += "- Morate uneti prezime\n";
            ok = false;
        }
        if (txtAdresa.getText().trim().equals("")) {
            poruka += "- Morate uneti adresu\n";
            ok = false;
        }
        if (txtPol.getText().trim().equals("")) {
            poruka += "- Morate uneti pol\n";
            ok = false;
        }
        if (txtBrojTelefona.getText().trim().equals("")) {
            poruka += "- Morate uneti broj telefona\n";
            ok = false;
        }
        try {
            Double.parseDouble(txtPlata.getText().trim());
        } catch (NumberFormatException e) {
            poruka += "- Plata mora biti broj\n";
            ok = false;
        }
        try {
            Integer.parseInt(txtBrojClanskeKarte.getText().trim());
        } catch (NumberFormatException e) {
            poruka += "- Broj clanske karte mora biti broj\n";
            ok = false;
        }

        if (ok == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }

    private void popuniPolja() {
        txtJMBG.setText(String.valueOf(vozac.getJMBG()));
        txtKorisnickoIme.setText(vozac.getKorisnickoIme());
        txtLozinka.setText(vozac.getLozinka());
        txtIme.setText(vozac.getIme());
        txtPrezime.setText(vozac.getPrezime());
        txtAdresa.setText(vozac.getAdresa());
        txtPol.setText(String.valueOf(vozac.getPol()));
        txtBrojTelefona.setText(vozac.getBrojTelefona());
        txtPlata.setText(String.valueOf(vozac.getPlata()));
        txtBrojClanskeKarte.setText(String.valueOf(vozac.getPlata()));


    }
}
