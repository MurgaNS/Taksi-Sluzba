package Gui;

import Gui.FormeZaDodavanjeIIzmenu.Pretraga.KombPretragaVozacaForma;
import Gui.FormeZaDodavanjeIIzmenu.NarucivanjeVoznje.NarucivanjeVoznjeForma;
import Gui.FormeZaDodavanjeIIzmenu.NarucivanjeVoznje.NarucivanjeVoznjeTelefonomForma;
import Gui.FormeZaPrikaz.*;
import Gui.FormeZaPrikaz.PrikazIzvestaja.IzvestajOVozacimaProzor;
import Gui.FormeZaPrikaz.PrikazIzvestaja.PrikazIzvestajaVoznjiProzor;
import Gui.FormeZaPrikaz.PrikazIzvestaja.SumiranIzvestajOVozacimaProzor;
import Gui.FormeZaPrikaz.PrikazIzvestaja.SumiranPrikazIzvestajaVoznjiProzor;
import Gui.FormeZaPrikaz.PrikazVoznji.AukcijaVoznjeProzor;
import Gui.FormeZaPrikaz.PrikazVoznji.PrikazSopstvenihVoznjiProzor;
import Gui.FormeZaPrikaz.PrikazVoznji.PrikazVoznjiPutemAplikacijeProzor;
import Gui.FormeZaPrikaz.PrikazVoznji.PrikazVoznjiPutemTelefonaProzor;
import Model.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import StrukturePodataka.ArrayList;

public class GlavniProzor extends JFrame {
    private JMenuBar mainMenu = new JMenuBar();
    public static Korisnik prijavljeniKorisnik;
    private JMenuItem otvoriVozacProzor = new JMenuItem("Prikazi podatke");
    private JMenuItem kombPretragaVozacaProzor = new JMenuItem("Kombinovana pretraga vozaca");


    public GlavniProzor(Korisnik korisnik) {
        TaksiSluzba taksiSluzba = TaksiSluzba.preuzmiPodatkeOTaksiSluzbi();
        prijavljeniKorisnik = korisnik;
        setTitle("Taksi sluzba " + taksiSluzba.getNaziv());
        setSize(500, 500);
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
        otvoriVozacProzor.addActionListener(e -> {
            VozaciProzor vp = new VozaciProzor();
            vp.setVisible(true);
        });

        JMenuItem otvoriTaksiSluzbaProzor;
        JMenuItem otvoriVozilaProzor;
        JMenuItem otvoriVoznjaProzor;
        JMenuItem otvoriIzvestajOVozacimaProzor;
        JMenuItem otvoriSumiranIzvestajProzor;

        JMenu taksiSluzbaMenu = new JMenu("Taksi sluzba");
        otvoriTaksiSluzbaProzor = new JMenuItem("Prikazi podatke");
        taksiSluzbaMenu.add(otvoriTaksiSluzbaProzor);

        JMenu vozaciMenu = new JMenu("Vozaci");
        vozaciMenu.add(otvoriVozacProzor);
        vozaciMenu.add(kombPretragaVozacaProzor);

        JMenu vozilaMenu = new JMenu("Vozila");
        otvoriVozilaProzor = new JMenuItem("Lista svih vozila");
        vozilaMenu.add(otvoriVozilaProzor);

        JMenu voznjaMenu = new JMenu("Voznje");
        otvoriVoznjaProzor = new JMenuItem("Prikazi podatke");
        voznjaMenu.add(otvoriVoznjaProzor);

        JMenu izvestajMenu = new JMenu("Izvestaji");
        otvoriIzvestajOVozacimaProzor = new JMenuItem("Prikaz izvestaja o vozacima");
        izvestajMenu.add(otvoriIzvestajOVozacimaProzor);
        otvoriSumiranIzvestajProzor = new JMenuItem("Sumiran izvestaj");
        izvestajMenu.add(otvoriSumiranIzvestajProzor);

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
        kombPretragaVozacaProzor.addActionListener(e -> {
            KombPretragaVozacaForma pretragaVozacaForma = new KombPretragaVozacaForma();
        });

        otvoriSumiranIzvestajProzor.addActionListener(e -> {
            SumiranIzvestajOVozacimaProzor sumiranIzvestajOVozacimaProzor = new SumiranIzvestajOVozacimaProzor();
            sumiranIzvestajOVozacimaProzor.setVisible(true);
        });

        otvoriVoznjaProzor.addActionListener(e -> {
            VoznjaProzor voznjaProzor = new VoznjaProzor();
            voznjaProzor.setVisible(true);
        });
    }


    public void musterijaMenuAndActions() {
        MigLayout mig = new MigLayout("wrap1");
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
        prikazVoznjiDugme.addActionListener(e -> new PrikazSopstvenihVoznjiProzor());

        narucivanjeVoznjeTelefonomDugme.addActionListener(e -> {
            NarucivanjeVoznjeTelefonomForma narucivanjeVoznjeTelefonom = new NarucivanjeVoznjeTelefonomForma();
            narucivanjeVoznjeTelefonom.setVisible(true);

        });
    }

    public void vozacMenuAndActions() {
        MigLayout mig = new MigLayout("wrap1");
        setLayout(mig);
        JButton prikazIstorijeVoznjiDugme = new JButton("Prikaz istorije sopstvenih voznji");
        prikazIstorijeVoznjiDugme.setBounds(50, 100, 95, 30);
        JButton statistikaDugme = new JButton("Statistika voznji");
        statistikaDugme.setBounds(50, 100, 95, 30);
        JButton sumiranaStatistikaDugme = new JButton("Sumirana statistika voznji");
        statistikaDugme.setBounds(50, 100, 95, 30);
        JButton prikazVoznjiPutemAplikacijeDugme = new JButton("Prikaz voznji putem aplikacije");
        prikazVoznjiPutemAplikacijeDugme.setBounds(50, 100, 95, 30);
        JButton prikazVoznjiPutemTelefonaDugme = new JButton("Prikaz voznji putem telefona");
        prikazVoznjiPutemTelefonaDugme.setBounds(50, 100, 95, 30);
        JButton zavrsiVoznjuDugme = new JButton("Zavrsi voznju");
        zavrsiVoznjuDugme.setBounds(50, 100, 95, 30);
        JButton aukcijaDugme = new JButton("Aukcija");
        zavrsiVoznjuDugme.setBounds(50, 100, 95, 30);
        this.add(prikazIstorijeVoznjiDugme);
        this.add(prikazVoznjiPutemAplikacijeDugme);
        this.add(prikazVoznjiPutemTelefonaDugme);
        this.add(zavrsiVoznjuDugme);
        this.add(statistikaDugme);
        this.add(sumiranaStatistikaDugme);
        this.add(aukcijaDugme);

        zavrsiVoznjuDugme.addActionListener(e -> new ZavrsiVoznjuProzor());
        prikazIstorijeVoznjiDugme.addActionListener(e -> new PrikazSopstvenihVoznjiProzor());
        prikazVoznjiPutemAplikacijeDugme.addActionListener(e -> new PrikazVoznjiPutemAplikacijeProzor());
        statistikaDugme.addActionListener(e -> new PrikazIzvestajaVoznjiProzor());
        prikazVoznjiPutemTelefonaDugme.addActionListener(e -> new PrikazVoznjiPutemTelefonaProzor());
        sumiranaStatistikaDugme.addActionListener(e -> {
            SumiranPrikazIzvestajaVoznjiProzor sumiranPrikazIzvestaja = new SumiranPrikazIzvestajaVoznjiProzor();
        });
        aukcijaDugme.addActionListener(e -> new AukcijaVoznjeProzor());
    }

    public static Korisnik getPrijavljeniKorisnik() {
        return prijavljeniKorisnik;
    }
}
