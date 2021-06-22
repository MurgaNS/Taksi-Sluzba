package Gui;

import Gui.FormeZaDodavanjeIIzmenu.NarucivanjeVoznjeForma;
import Gui.FormeZaDodavanjeIIzmenu.NarucivanjeVoznjeTelefonomForma;
import Gui.FormeZaPrikaz.IzvestajOVozacimaProzor;
import Gui.FormeZaPrikaz.PrikazVoznjiMusterijeProzor;
import Gui.FormeZaPrikaz.TaksiSluzbaProzor;
import Gui.FormeZaPrikaz.VoziloProzor;
import Model.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlavniProzor extends JFrame {
    private JMenuBar mainMenu = new JMenuBar();
    public static Korisnik prijavljeniKorisnik;
    private TaksiSluzba taksiSluzba;


    public GlavniProzor(Korisnik korisnik) {
        this.taksiSluzba = TaksiSluzba.preuzmiPodatkeOTaksiSluzbi();
        this.prijavljeniKorisnik = korisnik;
        setTitle("Taksi sluzba " + taksiSluzba.getNaziv());
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setJMenuBar(mainMenu);
        init();
        setVisible(true);
    }

    public void init() {
        if (this.prijavljeniKorisnik instanceof Dispecer) {
            dispecerMenuAndActions();

        } else if (this.prijavljeniKorisnik instanceof Vozac) {
            vozacMenu();
            vozacActions();
        } else if (this.prijavljeniKorisnik instanceof Musterija) {
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

        otvoriTaksiSluzbaProzor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaksiSluzbaProzor taksiSluzbaProzor = new TaksiSluzbaProzor();
                taksiSluzbaProzor.setVisible(true);
            }
        });
        otvoriVozilaProzor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VoziloProzor vozilaProzor = new VoziloProzor();
                vozilaProzor.setVisible(true);
            }
        });
        otvoriIzvestajOVozacimaProzor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IzvestajOVozacimaProzor izvestajOVozacimaProzor = new IzvestajOVozacimaProzor();
                izvestajOVozacimaProzor.setVisible(true);
            }
        });
    }


    public void musterijaMenuAndActions() {
        MigLayout mig = new MigLayout();
        setLayout(mig);
        JButton narucivanjeVoznjeDugme = new JButton("Naruci voznju");
        narucivanjeVoznjeDugme.setBounds(50, 100, 95, 30);
        JButton prikazVoznjiDugme = new JButton("Prikaz sopstvenih voznji");
        prikazVoznjiDugme.setBounds(50, 100, 95, 30);
        JButton narucivanjeVoznjeTelefonomDugme = new JButton("Naruci voznju telefonom");
        narucivanjeVoznjeTelefonomDugme.setBounds(50,100,95,30);

        this.add(prikazVoznjiDugme);
        this.add(narucivanjeVoznjeDugme);
        this.add(narucivanjeVoznjeTelefonomDugme);

        narucivanjeVoznjeDugme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NarucivanjeVoznjeForma narucivanjeVoznje = new NarucivanjeVoznjeForma();
                narucivanjeVoznje.setVisible(true);
            }
        });
        prikazVoznjiDugme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrikazVoznjiMusterijeProzor prikazVoznjiProzor = new PrikazVoznjiMusterijeProzor();
                prikazVoznjiProzor.setVisible(true);
            }
        });


        narucivanjeVoznjeTelefonomDugme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NarucivanjeVoznjeTelefonomForma narucivanjeVoznjeTelefonom = new NarucivanjeVoznjeTelefonomForma();
                narucivanjeVoznjeTelefonom.setVisible(true);

            }
        });
    }

    public void vozacMenu() {

    }

    public void vozacActions() {

    }

    public static Korisnik getPrijavljeniKorisnik() {
        return prijavljeniKorisnik;
    }
}
