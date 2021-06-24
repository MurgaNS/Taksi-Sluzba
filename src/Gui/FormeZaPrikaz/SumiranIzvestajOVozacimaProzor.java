package Gui.FormeZaPrikaz;

import Model.Vozac;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SumiranIzvestajOVozacimaProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeIzvestajJedanDan = new JButton("Dnevni izvestaj");
    private JButton dugmeIzvestajSedamDana = new JButton("Nedeljni izvestaj");
    private JButton dugmeIzvestajMesecDana = new JButton("Mesecni izvestaj");
    private JButton dugmeIzvestajGodinuDana = new JButton("Godisnji izvestaj");

    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;

    private List<Vozac> listaVozaca;

    public SumiranIzvestajOVozacimaProzor() {
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
        String[] zaglavlja = new String[]{"Id", "Ime i prezime","Ukupan broj voznji", "Broj voznji porucenih aplikacijom", "Broj voznji porucenih telefonom", "Broj aktivnih vozaca", "Prosecno trajanje voznje",
                "Prosecan broj predjenih kilometara", "Ukupna zarada za sve voznje", "Prosecna zarada po voznji"};
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
                tabelaModel.setValueAt(Voznja.ukupanBrojVoznji(Voznja.BrojDana.JEDAN_DAN), 0, 2);
                tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemAplikacije(Voznja.BrojDana.JEDAN_DAN).size(), 0, 3);
                tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemTelefona(Voznja.BrojDana.JEDAN_DAN).size(), 0, 4);
                tabelaModel.setValueAt(Voznja.brojAktivnihVozaca(Voznja.BrojDana.JEDAN_DAN),0,5);
                tabelaModel.setValueAt(Voznja.ukupnoProsecnoTrajanjeVoznje(Voznja.BrojDana.JEDAN_DAN), 0, 6);
                tabelaModel.setValueAt(Voznja.prosecanBrojPredjenihKilometara(Voznja.BrojDana.JEDAN_DAN), 0, 7);
                tabelaModel.setValueAt(Voznja.sumaUkupneZarade(Voznja.BrojDana.JEDAN_DAN), 0, 8);
                tabelaModel.setValueAt(Voznja.sumaProsecneZaradePoVoznji(Voznja.BrojDana.JEDAN_DAN), 0, 9);
            }
        });
        dugmeIzvestajSedamDana.addActionListener(e -> {
            for (int i = 0; i < listaVozaca.size(); i++) {
                Vozac vozac = listaVozaca.get(i);
                tabelaModel.setValueAt(Voznja.ukupanBrojVoznji(Voznja.BrojDana.SEDAM_DANA), 0, 2);
                tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemAplikacije(Voznja.BrojDana.SEDAM_DANA).size(), 0, 3);
                tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemTelefona(Voznja.BrojDana.SEDAM_DANA).size(), 0, 4);
                tabelaModel.setValueAt(Voznja.brojAktivnihVozaca(Voznja.BrojDana.SEDAM_DANA),0,5);
                tabelaModel.setValueAt(Voznja.ukupnoProsecnoTrajanjeVoznje(Voznja.BrojDana.SEDAM_DANA), 0, 6);
                tabelaModel.setValueAt(Voznja.prosecanBrojPredjenihKilometara(Voznja.BrojDana.SEDAM_DANA), 0, 7);
                tabelaModel.setValueAt(Voznja.sumaUkupneZarade(Voznja.BrojDana.SEDAM_DANA), 0, 8);
                tabelaModel.setValueAt(Voznja.sumaProsecneZaradePoVoznji(Voznja.BrojDana.SEDAM_DANA), 0, 9);
            }
        });
        dugmeIzvestajMesecDana.addActionListener(e -> {
            for (int i = 0; i < listaVozaca.size(); i++) {
                Vozac vozac = listaVozaca.get(i);
                tabelaModel.setValueAt(Voznja.ukupanBrojVoznji(Voznja.BrojDana.MESEC_DANA), 0, 2);
                tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemAplikacije(Voznja.BrojDana.MESEC_DANA).size(), 0, 3);
                tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemTelefona(Voznja.BrojDana.MESEC_DANA).size(), 0, 4);
                tabelaModel.setValueAt(Voznja.brojAktivnihVozaca(Voznja.BrojDana.MESEC_DANA),0,5);
                tabelaModel.setValueAt(Voznja.ukupnoProsecnoTrajanjeVoznje(Voznja.BrojDana.MESEC_DANA), 0, 6);
                tabelaModel.setValueAt(Voznja.prosecanBrojPredjenihKilometara(Voznja.BrojDana.MESEC_DANA), 0, 7);
                tabelaModel.setValueAt(Voznja.sumaUkupneZarade(Voznja.BrojDana.MESEC_DANA), 0, 8);
                tabelaModel.setValueAt(Voznja.sumaProsecneZaradePoVoznji(Voznja.BrojDana.MESEC_DANA), 0, 9);
            }
        });
        dugmeIzvestajGodinuDana.addActionListener(e -> {
            for (int i = 0; i < listaVozaca.size(); i++) {
                Vozac vozac = listaVozaca.get(i);
                tabelaModel.setValueAt(Voznja.ukupanBrojVoznji(Voznja.BrojDana.GODINU_DANA), 0, 2);
                tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemAplikacije(Voznja.BrojDana.GODINU_DANA).size(), 0, 3);
                tabelaModel.setValueAt(Voznja.sveVoznjeNarucenePutemTelefona(Voznja.BrojDana.GODINU_DANA).size(), 0, 4);
                tabelaModel.setValueAt(Voznja.brojAktivnihVozaca(Voznja.BrojDana.GODINU_DANA),0,5);
                tabelaModel.setValueAt(Voznja.ukupnoProsecnoTrajanjeVoznje(Voznja.BrojDana.GODINU_DANA), 0, 6);
                tabelaModel.setValueAt(Voznja.prosecanBrojPredjenihKilometara(Voznja.BrojDana.GODINU_DANA), 0, 7);
                tabelaModel.setValueAt(Voznja.sumaUkupneZarade(Voznja.BrojDana.GODINU_DANA), 0, 8);
                tabelaModel.setValueAt(Voznja.sumaProsecneZaradePoVoznji(Voznja.BrojDana.GODINU_DANA), 0, 9);
            }
        });
    }
}
