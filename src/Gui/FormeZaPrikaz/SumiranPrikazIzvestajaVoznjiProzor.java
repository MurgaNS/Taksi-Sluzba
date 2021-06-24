package Gui.FormeZaPrikaz;

import Gui.GlavniProzor;
import Model.Vozac;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SumiranPrikazIzvestajaVoznjiProzor extends JFrame {

    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeIzvestajJedanDan = new JButton("Jedan dan");
    private JButton dugmeIzvestajSedamDana = new JButton("Sedam dana");
    private JButton dugmeIzvestajMesecDana = new JButton("Mesec dana");
    private JButton dugmeIzvestajGodinuDana = new JButton("Godinu dana");
    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;
    private Vozac vozac;

    public SumiranPrikazIzvestajaVoznjiProzor() {
        setTitle("Prikaz vozila");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 300);
        initGui();
        initActions();
        setVisible(true);
    }

    public void initGui() {
        glavniToolBar.add(dugmeIzvestajJedanDan);
        glavniToolBar.add(dugmeIzvestajSedamDana);
        glavniToolBar.add(dugmeIzvestajMesecDana);
        glavniToolBar.add(dugmeIzvestajGodinuDana);
        add(glavniToolBar, BorderLayout.NORTH);
        String[] zaglavlja = new String[]{"Ukupan broj voznji", "Broj voznji porucenih aplikacijom", "Broj voznji porucenih telefonom", "Broj aktivnih vozaca", "Prosecno trajanje voznje",
                "Prosecan broj predjenih kilometara", "Ukupna zarada za sve voznje", "Prosecna zarada po voznji"};
        Object[][] sadrzaj = new Object[1][zaglavlja.length];
        vozac = (Vozac) GlavniProzor.getPrijavljeniKorisnik();
        tabelaModel = new DefaultTableModel(sadrzaj, zaglavlja);
        tabelaPodataka = new JTable(tabelaModel);
        tabelaPodataka.setBounds(30, 40, 500, 500);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        tabelaPodataka.setRowSelectionAllowed(true);
        tabelaPodataka.setColumnSelectionAllowed(false);
        tabelaPodataka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPodataka.setDefaultEditor(Object.class, null);
        tabelaPodataka.getTableHeader().setReorderingAllowed(false);
    }

    public void initActions() {
        dugmeIzvestajJedanDan.addActionListener(e -> {
            tabelaModel.setValueAt(Voznja.ukupanBrojVoznji(Voznja.BrojDana.JEDAN_DAN), 0, 0);
            tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemAplikacije(Voznja.BrojDana.JEDAN_DAN), 0, 1);
            tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemTelefona(Voznja.BrojDana.JEDAN_DAN), 0, 2);
//            uraditi ukupan broj aktivnih vozaca
            tabelaModel.setValueAt(Voznja.ukupnoProsecnoTrajanjeVoznje(Voznja.BrojDana.JEDAN_DAN), 0, 4);
            tabelaModel.setValueAt(Voznja.prosecanBrojPredjenihKilometara(Voznja.BrojDana.JEDAN_DAN), 0, 5);
            tabelaModel.setValueAt(Voznja.sumaUkupneZarade(Voznja.BrojDana.JEDAN_DAN), 0, 6);
            tabelaModel.setValueAt(Voznja.sumaProsecneZaradePoVoznji(Voznja.BrojDana.JEDAN_DAN), 0, 7);



        });
        dugmeIzvestajSedamDana.addActionListener(e -> {
            tabelaModel.setValueAt(Voznja.ukupanBrojVoznji(Voznja.BrojDana.SEDAM_DANA), 0, 0);
            tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemAplikacije(Voznja.BrojDana.SEDAM_DANA), 0, 1);
            tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemTelefona(Voznja.BrojDana.SEDAM_DANA), 0, 2);
//            uraditi ukupan broj aktivnih vozaca
            tabelaModel.setValueAt(Voznja.ukupnoProsecnoTrajanjeVoznje(Voznja.BrojDana.SEDAM_DANA), 0, 4);
            tabelaModel.setValueAt(Voznja.prosecanBrojPredjenihKilometara(Voznja.BrojDana.SEDAM_DANA), 0, 5);
            tabelaModel.setValueAt(Voznja.sumaUkupneZarade(Voznja.BrojDana.SEDAM_DANA), 0, 6);
            tabelaModel.setValueAt(Voznja.sumaProsecneZaradePoVoznji(Voznja.BrojDana.SEDAM_DANA), 0, 7);



        });
        dugmeIzvestajMesecDana.addActionListener(e -> {
            tabelaModel.setValueAt(Voznja.ukupanBrojVoznji(Voznja.BrojDana.MESEC_DANA), 0, 0);
            tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemAplikacije(Voznja.BrojDana.MESEC_DANA), 0, 1);
            tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemTelefona(Voznja.BrojDana.MESEC_DANA), 0, 2);
//            uraditi ukupan broj aktivnih vozaca
            tabelaModel.setValueAt(Voznja.ukupnoProsecnoTrajanjeVoznje(Voznja.BrojDana.MESEC_DANA), 0, 4);
            tabelaModel.setValueAt(Voznja.prosecanBrojPredjenihKilometara(Voznja.BrojDana.MESEC_DANA), 0, 5);
            tabelaModel.setValueAt(Voznja.sumaUkupneZarade(Voznja.BrojDana.MESEC_DANA), 0, 6);
            tabelaModel.setValueAt(Voznja.sumaProsecneZaradePoVoznji(Voznja.BrojDana.MESEC_DANA), 0, 7);



        });
        dugmeIzvestajGodinuDana.addActionListener(e -> {
            tabelaModel.setValueAt(Voznja.ukupanBrojVoznji(Voznja.BrojDana.GODINU_DANA), 0, 0);
            tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemAplikacije(Voznja.BrojDana.GODINU_DANA), 0, 1);
            tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemTelefona(Voznja.BrojDana.GODINU_DANA), 0, 2);
//            uraditi ukupan broj aktivnih vozaca
            tabelaModel.setValueAt(Voznja.ukupnoProsecnoTrajanjeVoznje(Voznja.BrojDana.GODINU_DANA), 0, 4);
            tabelaModel.setValueAt(Voznja.prosecanBrojPredjenihKilometara(Voznja.BrojDana.GODINU_DANA), 0, 5);
            tabelaModel.setValueAt(Voznja.sumaUkupneZarade(Voznja.BrojDana.GODINU_DANA), 0, 6);
            tabelaModel.setValueAt(Voznja.sumaProsecneZaradePoVoznji(Voznja.BrojDana.GODINU_DANA), 0, 7);



        });
    }
}
