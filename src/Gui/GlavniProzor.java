package Gui;

import Gui.FormeZaDodavanjeIIzmenu.NarucivanjeVoznjeForma;
import Gui.FormeZaDodavanjeIIzmenu.NarucivanjeVoznjeTelefonomForma;
import Gui.FormeZaPrikaz.*;
import Model.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GlavniProzor extends JFrame {
    private JMenuBar mainMenu = new JMenuBar();
    public static Korisnik prijavljeniKorisnik;
    private TaksiSluzba taksiSluzba;


    public GlavniProzor(Korisnik korisnik) {
        this.taksiSluzba = TaksiSluzba.preuzmiPodatkeOTaksiSluzbi();
        prijavljeniKorisnik = korisnik;
        setTitle("Taksi sluzba " + taksiSluzba.getNaziv());
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setJMenuBar(mainMenu);
        init();
        setVisible(true);
    }

    public void init() {
        if (prijavljeniKorisnik instanceof Dispecer) {
            dispecerMenuAndActions();
        } else if (prijavljeniKorisnik instanceof Vozac) {
            vozacMenuAndActions();
        } else if (prijavljeniKorisnik instanceof Musterija) {
            musterijaMenuAndActions();
        }
    }

    public void dispecerMenuAndActions() {
        JMenuItem otvoriTaksiSluzbaProzor;
        JMenuItem otvoriVozacProzor;
        JMenuItem otvoriVozilaProzor;
        JMenuItem otvoriVoznjaProzor;
        JMenuItem otvoriIzvestajOVozacimaProzor;

        JMenu taksiSluzbaMenu = new JMenu("Taksi sluzba");
        otvoriTaksiSluzbaProzor = new JMenuItem("Prikazi podatke");
        taksiSluzbaMenu.add(otvoriTaksiSluzbaProzor);

        JMenu vozaciMenu = new JMenu("Vozaci");
        otvoriVozacProzor = new JMenuItem("Prikazi podatke");
        vozaciMenu.add(otvoriVozacProzor);

        JMenu vozilaMenu = new JMenu("Vozila");
        otvoriVozilaProzor = new JMenuItem("Lista svih vozila");
        vozilaMenu.add(otvoriVozilaProzor);

        JMenu voznjaMenu = new JMenu("Voznje");
        otvoriVoznjaProzor = new JMenuItem("Prikazi podatke");
        voznjaMenu.add(otvoriVoznjaProzor);

        JMenu izvestajMenu = new JMenu("Izvestaji");
        otvoriIzvestajOVozacimaProzor = new JMenuItem("Prikaz izvestaja o vozacima");
        izvestajMenu.add(otvoriIzvestajOVozacimaProzor);

        mainMenu.add(taksiSluzbaMenu);
        mainMenu.add(vozaciMenu);
        mainMenu.add(vozilaMenu);
        mainMenu.add(voznjaMenu);
        mainMenu.add(izvestajMenu);

        otvoriTaksiSluzbaProzor.addActionListener(e -> {
            TaksiSluzbaProzor taksiSluzbaProzor = new TaksiSluzbaProzor();
            taksiSluzbaProzor.setVisible(true);
        });
        otvoriVozilaProzor.addActionListener(e -> {
            VoziloProzor vozilaProzor = new VoziloProzor();
            vozilaProzor.setVisible(true);
        });
        otvoriIzvestajOVozacimaProzor.addActionListener(e -> {
            IzvestajOVozacimaProzor izvestajOVozacimaProzor = new IzvestajOVozacimaProzor();
            izvestajOVozacimaProzor.setVisible(true);
        });
    }


    public void musterijaMenuAndActions() {
        MigLayout mig = new MigLayout();
        setLayout(mig);
        JButton narucivanjeVoznjeDugme = new JButton("Naruci voznju putem aplikacije");
        narucivanjeVoznjeDugme.setBounds(50, 100, 95, 30);
        JButton narucivanjeVoznjeTelefonomDugme = new JButton("Naruci voznju putem telefona");
        narucivanjeVoznjeDugme.setBounds(50, 100, 95, 30);

        JButton prikazVoznjiDugme = new JButton("Prikaz sopstvenih voznji");
        prikazVoznjiDugme.setBounds(50, 100, 95, 30);
        this.add(prikazVoznjiDugme);
        this.add(narucivanjeVoznjeDugme);
        this.add(narucivanjeVoznjeTelefonomDugme);

        narucivanjeVoznjeDugme.addActionListener(e -> {
            NarucivanjeVoznjeForma narucivanjeVoznje = new NarucivanjeVoznjeForma();
            narucivanjeVoznje.setVisible(true);
        });
        prikazVoznjiDugme.addActionListener(e -> {
            PrikazSopstvenihVoznjiProzor prikazVoznjiProzor = new PrikazSopstvenihVoznjiProzor();
            prikazVoznjiProzor.setVisible(true);
        });


        narucivanjeVoznjeTelefonomDugme.addActionListener(e -> {
            NarucivanjeVoznjeTelefonomForma narucivanjeVoznjeTelefonom = new NarucivanjeVoznjeTelefonomForma();
            narucivanjeVoznjeTelefonom.setVisible(true);

        });
    }

    public void vozacMenuAndActions() {
        MigLayout mig = new MigLayout();
        setLayout(mig);
        JButton prikazIstorijeVoznjiDugme = new JButton("Prikaz istorije sopstvenih voznji");
        prikazIstorijeVoznjiDugme.setBounds(50, 100, 95, 30);
        JButton statistikaDugme = new JButton("Statistika voznji");
        statistikaDugme.setBounds(50, 100, 95, 30);
        JButton prikazVoznjiPutemAplikacijeDugme = new JButton("Prikaz voznji putem aplikacije");
        prikazVoznjiPutemAplikacijeDugme.setBounds(50, 100, 95, 30);
        this.add(prikazIstorijeVoznjiDugme);
        this.add(prikazVoznjiPutemAplikacijeDugme);
        this.add(statistikaDugme);

        prikazIstorijeVoznjiDugme.addActionListener(e -> {
            PrikazSopstvenihVoznjiProzor prikazVoznjiProzor = new PrikazSopstvenihVoznjiProzor();
        });
        prikazVoznjiPutemAplikacijeDugme.addActionListener(e -> {
            PrikazVoznjiPutemAplikacijeProzor prikazVoznjiPutemAplikacije = new PrikazVoznjiPutemAplikacijeProzor();
        });
        statistikaDugme.addActionListener(e -> {
            PrikazIzvestajaVoznjiProzor prikazIzvestaja = new PrikazIzvestajaVoznjiProzor();
        });
    }

    public static Korisnik getPrijavljeniKorisnik() {
        return prijavljeniKorisnik;
    }
}
