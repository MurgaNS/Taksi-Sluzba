package Gui;

import Gui.FormeZaPrikaz.TaksiSluzbaProzor;
import Model.Korisnik;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlavniProzor extends JFrame {

    private JMenuBar mainMenu = new JMenuBar();
    private JMenu taksiSluzbaMenu = new JMenu("Taksi sluzba");
    private JMenuItem taksiSluzbaIzmena = new JMenuItem("Izmeni podatke");
    private Korisnik prijavljeniKorisnik;

    public GlavniProzor(Korisnik korisnik) {
        this.prijavljeniKorisnik = korisnik;
        setTitle("Taksi sluzba");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initMenu();
        initActions();
        setVisible(true);
    }

    public void initMenu() {
        setJMenuBar(mainMenu);
        mainMenu.add(taksiSluzbaMenu);
        taksiSluzbaMenu.add(taksiSluzbaIzmena);
    }

    ;

    public void initActions() {
        taksiSluzbaIzmena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaksiSluzbaProzor taksiSluzbaProzor = new TaksiSluzbaProzor();
                taksiSluzbaProzor.setVisible(true);
            }
        });
    }

    ;

}
