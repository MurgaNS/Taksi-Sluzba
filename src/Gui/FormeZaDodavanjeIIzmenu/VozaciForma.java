package Gui.FormeZaDodavanjeIIzmenu;

import Gui.FormeZaPrikaz.TaksiSluzbaProzor;
import Gui.FormeZaPrikaz.VozaciProzor;
import Gui.FormeZaPrikaz.VoziloProzor;
import Model.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import StrukturePodataka.List;


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
    private JComboBox<Korisnik.Pol> cbPol = new JComboBox<>();
    private JLabel lblBrojTelefona = new JLabel("Broj telefona");
    private JTextField txtBrojTelefona = new JTextField(20);
    private JLabel lblPlata = new JLabel("Plata");
    private JTextField txtPlata = new JTextField(20);
    private JLabel lblBrojClanskeKarte = new JLabel("Broj clanske karte");
    private JTextField txtBrojClanskeKarte = new JTextField(20);
    private JButton dugmeOk = new JButton("Sacuvaj");
    private JButton dugmePonisti = new JButton("Ponisti");
    private JLabel lblAuto = new JLabel("Automobil");
    private JComboBox<Long> cbAutomobil = new JComboBox<>();

    private Vozac vozac;
    List<Vozac> listaVozaca = Vozac.ucitajSveVozace();
    List<Vozilo> listaVozila = (List<Vozilo>) Vozilo.ucitajSvaVozila();

    public VozaciForma(Vozac v) {
        try {
            vozac = (Vozac) Vozac.nadjiKorisnikaPrekoJMBG((v.getJMBG()));
//            vozac = Vozac.pronadjiPoJMBG(v.getJMBG());
        } catch (NullPointerException ignored) {
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
        cbPol.setModel(new DefaultComboBoxModel<>(Korisnik.Pol.values()));
        for (Vozilo auto : listaVozila ) {
            cbAutomobil.addItem(auto.getBrTaksiVozila());
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
        add(cbPol);
        add(lblBrojTelefona);
        add(txtBrojTelefona);
        add(lblPlata);
        add(txtPlata);
        add(lblBrojClanskeKarte);
        add(txtBrojClanskeKarte);
        add(lblAuto);
        add(cbAutomobil);
        add(new JLabel());
        add(dugmeOk, "split 2");
        add(dugmePonisti);
    }

    public void initActions() {
        dugmeOk.addActionListener(e -> {
            if (validacija()) {
                List<Vozac> vozaci = Vozac.ucitajSveVozace();
                long id = 1;
                if(!vozaci.isEmpty()) {
                    id = vozaci.get(vozaci.size() - 1).getId() + 1;
                }
                long JMBG = Long.parseLong(txtJMBG.getText().trim());
                String korisnickoIme = txtKorisnickoIme.getText().trim();
                String lozinka = txtLozinka.getText().trim();
                String ime = txtIme.getText().trim();
                String prezime = txtPrezime.getText().trim();
                String adresa = txtAdresa.getText().trim();
                Korisnik.Pol pol = Korisnik.Pol.valueOf(cbPol.getSelectedItem().toString());
                String brojTelefona = txtBrojTelefona.getText().trim();
                double plata = Double.parseDouble(txtPlata.getText().trim());
//                    Long automobil = (Long) cbAutomobil.getSelectedItem();
                int brojClanskeKarte = Integer.parseInt(txtBrojClanskeKarte.getText().trim());
                if (vozac == null) {
                    Vozac vozac = new Vozac(id,JMBG,korisnickoIme,lozinka,ime,prezime, adresa,pol,brojTelefona,false,plata,brojClanskeKarte);
                    //Vozac.upisiVozaca(vozac);
                    List<Korisnik> korisnici = Korisnik.ucitajSveKorisnike();
                    korisnici.add(vozac);
                    Korisnik.upisiSveKorisnike(korisnici);
                    JOptionPane.showMessageDialog(null, "Uspesno kreiran vozac!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    vozac.setJMBG(JMBG);
                    vozac.setKorisnickoIme(korisnickoIme);
                    vozac.setLozinka(lozinka);
                    vozac.setIme(ime);
                    vozac.setPrezime(prezime);
                    vozac.setAdresa(adresa);
                    vozac.setPol(pol);
                    vozac.setBrojTelefona(brojTelefona);
                    vozac.setPlata(plata);
                    vozac.setBrojClanskeKarte(brojClanskeKarte);
                    JOptionPane.showMessageDialog(null, "Izmene su sacuvane!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                }
                VozaciForma.this.dispose();
                VozaciForma.this.setVisible(false);
                Vozac.upisiVozaca(vozac);
            }
        });
        dugmePonisti.addActionListener(e -> {
            VozaciForma.this.dispose();
            VozaciForma.this.setVisible(false);
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
//            Vozac postojiVozac = (Vozac) Vozac.nadjiKorisnikaPrekoJMBG(vozac.getJMBG());
//            if (postojiVozac != null) {
//                poruka += "- Vozac sa unetim ID vec postoji\n";
//                ok = false;
//            }
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

        if (!ok) {
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
        cbPol.setSelectedItem(vozac.getPol());
        txtBrojTelefona.setText(vozac.getBrojTelefona());
        txtPlata.setText(String.valueOf(vozac.getPlata()));
        txtBrojClanskeKarte.setText(String.valueOf(vozac.getBrojClanskeKarte()));


    }
}
