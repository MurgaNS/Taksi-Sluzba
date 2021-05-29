package Gui;

import Gui.FormeZaPrikaz.TaksiSluzbaProzor;
import Gui.FormeZaPrikaz.VoziloProzor;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlavniProzor extends JFrame {

    private JMenuBar mainMenu = new JMenuBar();
    private Korisnik prijavljeniKorisnik;
    private TaksiSluzba taksiSluzba;

    //Dispecer
    JMenuItem otvoriTaksiSluzbaProzor;
    JMenuItem otvoriVozacProzor;
    JMenuItem otvoriVozilaProzor;
    JMenuItem otvoriVoznjaProzor;
    JMenuItem otvoriIzvestajProzor;


    public GlavniProzor(Korisnik korisnik) {
        this.taksiSluzba = TaksiSluzba.preuzmiPodatkeOTaksiSluzbi();
        this.prijavljeniKorisnik = korisnik;
        setTitle("Taksi sluzba " + taksiSluzba.getNaziv());
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setJMenuBar(mainMenu);
        init();
        setVisible(true);
    }

    public void init() {
        if (this.prijavljeniKorisnik instanceof Dispecer) {
            dispecerMenu();
            dispecerActions();

        } else if (this.prijavljeniKorisnik instanceof Vozac) {
            vozacMenu();
            vozacActions();
        } else if (this.prijavljeniKorisnik instanceof Musterija) {
            musterijaMenu();
            musterijaActions();
        }
    }

    public void dispecerMenu() {

        JMenu taksiSluzbaMenu = new JMenu("Taksi sluzba");
        otvoriTaksiSluzbaProzor = new JMenuItem("Prikazi podatke");
        taksiSluzbaMenu.add(otvoriTaksiSluzbaProzor);

        JMenu vozaciMenu = new JMenu("Vozaci");
        otvoriVozacProzor = new JMenuItem("Prikazi podatke");
        vozaciMenu.add(otvoriVozacProzor);

        JMenu vozilaMenu = new JMenu("Vozila");
        otvoriVozilaProzor = new JMenuItem("Prikazi podatke");
        vozilaMenu.add(otvoriVozilaProzor);

        JMenu voznjaMenu = new JMenu("Voznje");
        otvoriVoznjaProzor = new JMenuItem("Prikazi podatke");
        voznjaMenu.add(otvoriVoznjaProzor);

        JMenu izvestajMenu = new JMenu("Izvestaji");
        otvoriIzvestajProzor = new JMenuItem("Kreiraj izvestaj");
        izvestajMenu.add(otvoriIzvestajProzor);

        mainMenu.add(taksiSluzbaMenu);
        mainMenu.add(vozaciMenu);
        mainMenu.add(vozilaMenu);
        mainMenu.add(voznjaMenu);
        mainMenu.add(izvestajMenu);
    }

    public void dispecerActions() {
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
    }


    public void musterijaMenu() {

    }

    public void musterijaActions() {

    }

    public void vozacMenu() {

    }

    public void vozacActions() {

    }

}
