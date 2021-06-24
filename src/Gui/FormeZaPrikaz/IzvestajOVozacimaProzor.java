package Gui.FormeZaPrikaz;

import Model.Vozac;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class IzvestajOVozacimaProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeIzvestajJedanDan = new JButton("Dnevni izvestaj");
    private JButton dugmeIzvestajSedamDana = new JButton("Nedeljni izvestaj");
    private JButton dugmeIzvestajMesecDana = new JButton("Mesecni izvestaj");
    private JButton dugmeIzvestajGodinuDana = new JButton("Godisnji izvestaj");

    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;

    private List<Vozac> listaVozaca;

    public IzvestajOVozacimaProzor() {
        listaVozaca = Vozac.ucitajSveVozace();
        setTitle("Prikaz izvestaja");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1000, 500);
        initGui();
        initActions();
    }

    public void initGui() {
        glavniToolBar.add(dugmeIzvestajJedanDan);
        glavniToolBar.add(dugmeIzvestajSedamDana);
        glavniToolBar.add(dugmeIzvestajMesecDana);
        glavniToolBar.add(dugmeIzvestajGodinuDana);
        add(glavniToolBar, BorderLayout.NORTH);
        String[] zaglavlja = new String[]{"Id", "Ime i prezime", "Br. voznji", "Br. predjnih km", "Ukupno trajanje voznji", "Prosecan br. predjenih km po voznji", "Prosecno trajanje voznje", "Uk. zarada", "Prosecna zarada po voznji", "Prosecno vremena bez voznje"};
        Object[][] sadrzaj = new Object[listaVozaca.size()][zaglavlja.length];
        for (int i = 0; i < listaVozaca.size(); i++) {
            Vozac vozac = listaVozaca.get(i);
            sadrzaj[i][0] = vozac.getJMBG();
            sadrzaj[i][1] = vozac.getIme() + ' ' + vozac.getPrezime();
        }

        tabelaModel = new DefaultTableModel(sadrzaj, zaglavlja);
        tabelaPodataka = new JTable(tabelaModel);
        tabelaPodataka.setBounds(100, 40, 1000, 500);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        tabelaPodataka.setRowSelectionAllowed(true);
        tabelaPodataka.setColumnSelectionAllowed(false);
        tabelaPodataka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPodataka.setDefaultEditor(Object.class, null);
        tabelaPodataka.getTableHeader().setReorderingAllowed(false);
        tabelaPodataka.setAutoCreateRowSorter(true);
        setVisible(true);
    }

    public void initActions() {
        dugmeIzvestajJedanDan.addActionListener(e -> {
            for (int i = 0; i < listaVozaca.size(); i++) {
                Vozac vozac = listaVozaca.get(i);
                tabelaModel.setValueAt(Voznja.brojVoznji(vozac, Voznja.BrojDana.JEDAN_DAN), i, 2);
                tabelaModel.setValueAt(Voznja.brojPredjenihKilometara(vozac, Voznja.BrojDana.JEDAN_DAN), i, 3);
                tabelaModel.setValueAt(Voznja.ukupnoTrajanjeVoznji(vozac, Voznja.BrojDana.JEDAN_DAN), i, 4);
                tabelaModel.setValueAt(Voznja.prosecanBrojKilometaraPoVoznji(vozac, Voznja.BrojDana.JEDAN_DAN), i, 5);
                tabelaModel.setValueAt(Voznja.prosecnoTrajanjeVoznje(vozac, Voznja.BrojDana.JEDAN_DAN), i, 6);
                tabelaModel.setValueAt(Voznja.prosecnoVremeBezVoznje(vozac, Voznja.BrojDana.JEDAN_DAN), i, 7);
                tabelaModel.setValueAt(Voznja.prosecnaZaradaPoVoznji(vozac, Voznja.BrojDana.JEDAN_DAN), i, 8);
                tabelaModel.setValueAt(Voznja.ukupnaZarada(vozac, Voznja.BrojDana.JEDAN_DAN), i, 9);
            }
        });
        dugmeIzvestajSedamDana.addActionListener(e -> {
            for (int i = 0; i < listaVozaca.size(); i++) {
                Vozac vozac = listaVozaca.get(i);
                tabelaModel.setValueAt(Voznja.brojVoznji(vozac, Voznja.BrojDana.SEDAM_DANA), i, 2);
                tabelaModel.setValueAt(Voznja.brojPredjenihKilometara(vozac, Voznja.BrojDana.SEDAM_DANA), i, 3);
                tabelaModel.setValueAt(Voznja.ukupnoTrajanjeVoznji(vozac, Voznja.BrojDana.SEDAM_DANA), i, 4);
                tabelaModel.setValueAt(Voznja.prosecanBrojKilometaraPoVoznji(vozac, Voznja.BrojDana.SEDAM_DANA), i, 5);
                tabelaModel.setValueAt(Voznja.prosecnoTrajanjeVoznje(vozac, Voznja.BrojDana.SEDAM_DANA), i, 6);
                tabelaModel.setValueAt(Voznja.prosecnoVremeBezVoznje(vozac, Voznja.BrojDana.SEDAM_DANA), i, 7);
                tabelaModel.setValueAt(Voznja.prosecnaZaradaPoVoznji(vozac, Voznja.BrojDana.SEDAM_DANA), i, 8);
                tabelaModel.setValueAt(Voznja.ukupnaZarada(vozac, Voznja.BrojDana.SEDAM_DANA), i, 9);
            }
        });
        dugmeIzvestajMesecDana.addActionListener(e -> {
            for (int i = 0; i < listaVozaca.size(); i++) {
                Vozac vozac = listaVozaca.get(i);
                tabelaModel.setValueAt(Voznja.brojVoznji(vozac, Voznja.BrojDana.MESEC_DANA), i, 2);
                tabelaModel.setValueAt(Voznja.brojPredjenihKilometara(vozac, Voznja.BrojDana.MESEC_DANA), i, 3);
                tabelaModel.setValueAt(Voznja.ukupnoTrajanjeVoznji(vozac, Voznja.BrojDana.MESEC_DANA), i, 4);
                tabelaModel.setValueAt(Voznja.prosecanBrojKilometaraPoVoznji(vozac, Voznja.BrojDana.MESEC_DANA), i, 5);
                tabelaModel.setValueAt(Voznja.prosecnoTrajanjeVoznje(vozac, Voznja.BrojDana.MESEC_DANA), i, 6);
                tabelaModel.setValueAt(Voznja.prosecnoVremeBezVoznje(vozac, Voznja.BrojDana.MESEC_DANA), i, 7);
                tabelaModel.setValueAt(Voznja.prosecnaZaradaPoVoznji(vozac, Voznja.BrojDana.MESEC_DANA), i, 8);
                tabelaModel.setValueAt(Voznja.ukupnaZarada(vozac, Voznja.BrojDana.MESEC_DANA), i, 9);
            }
        });
        dugmeIzvestajGodinuDana.addActionListener(e -> {
            for (int i = 0; i < listaVozaca.size(); i++) {
                Vozac vozac = listaVozaca.get(i);
                tabelaModel.setValueAt(Voznja.brojVoznji(vozac, Voznja.BrojDana.GODINU_DANA), i, 2);
                tabelaModel.setValueAt(Voznja.brojPredjenihKilometara(vozac, Voznja.BrojDana.GODINU_DANA), i, 3);
                tabelaModel.setValueAt(Voznja.ukupnoTrajanjeVoznji(vozac, Voznja.BrojDana.GODINU_DANA), i, 4);
                tabelaModel.setValueAt(Voznja.prosecanBrojKilometaraPoVoznji(vozac, Voznja.BrojDana.GODINU_DANA), i, 5);
                tabelaModel.setValueAt(Voznja.prosecnoTrajanjeVoznje(vozac, Voznja.BrojDana.GODINU_DANA), i, 6);
                tabelaModel.setValueAt(Voznja.prosecnoVremeBezVoznje(vozac, Voznja.BrojDana.GODINU_DANA), i, 7);
                tabelaModel.setValueAt(Voznja.prosecnaZaradaPoVoznji(vozac, Voznja.BrojDana.GODINU_DANA), i, 8);
                tabelaModel.setValueAt(Voznja.ukupnaZarada(vozac, Voznja.BrojDana.GODINU_DANA), i, 9);
            }
        });
    }
}
