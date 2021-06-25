package Gui.FormeZaPrikaz.PrikazIzvestaja;

import Model.Vozac;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;

import StrukturePodataka.List;


public class IzvestajOVozacimaProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeIzvestajJedanDan = new JButton("Dnevni izvestaj");
    private JButton dugmeIzvestajSedamDana = new JButton("Nedeljni izvestaj");
    private JButton dugmeIzvestajMesecDana = new JButton("Mesecni izvestaj");
    private JButton dugmeIzvestajGodinuDana = new JButton("Godisnji izvestaj");
    private static DecimalFormat df = new DecimalFormat("0.00");

    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;

    private List<Vozac> listaVozaca;

    public IzvestajOVozacimaProzor() {
        listaVozaca = Vozac.ucitajSveVozace();
        setTitle("Prikaz izvestaja");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(400, 250);
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
        tabelaModel = new DefaultTableModel(sadrzaj, zaglavlja);
        tabelaPodataka = new JTable(tabelaModel);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        tabelaPodataka.setVisible(false);
        tabelaPodataka.setRowSelectionAllowed(true);
        tabelaPodataka.setColumnSelectionAllowed(false);
        tabelaPodataka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPodataka.setDefaultEditor(Object.class, null);
        tabelaPodataka.getTableHeader().setReorderingAllowed(false);
        tabelaPodataka.setAutoCreateRowSorter(true);
        tabelaPodataka.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int k = 0; k < zaglavlja.length; k++) {
            tabelaPodataka.getColumnModel().getColumn(k).setPreferredWidth(150);
        }
        for (int j = 0; j < tabelaPodataka.getColumnCount(); j++) {
            tabelaPodataka.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
        setVisible(true);
    }

    public void popuniTabelu(Vozac vozac, int i, Voznja.BrojDana brojDana) {
        tabelaPodataka.setVisible(true);
        int brZavrsenihVoznji = Voznja.brojZavrsenihVoznji(vozac, brojDana);
        String brPredjenihKilometara = df.format(Voznja.brojPredjenihKilometara(vozac, brojDana));
        String ukTrajanjeVoznji = df.format(Voznja.ukupnoTrajanjeVoznji(vozac, brojDana));
        String prosecanBrKilometaraPoVoznji = df.format(Voznja.prosecanBrojKilometaraPoVoznji(vozac, brojDana));
        String prosecnoTrajanjeVoznje = df.format(Voznja.prosecnoTrajanjeVoznje(vozac, brojDana));
        String prosecnoVremeBezVoznje = df.format(Voznja.prosecnoVremeBezVoznje(vozac, brojDana));
        String prosecnaZaradaPoVoznji = df.format(Voznja.prosecnaZaradaPoVoznji(vozac, brojDana));
        String ukupnaZarada = df.format(Voznja.ukupnaZarada(vozac, brojDana));
        tabelaModel.setValueAt(vozac.getId(), i, 0);
        tabelaModel.setValueAt(vozac.getIme() + vozac.getPrezime(), i, 1);
        tabelaModel.setValueAt(brZavrsenihVoznji, i, 2);
        tabelaModel.setValueAt(brPredjenihKilometara, i, 3);
        tabelaModel.setValueAt(ukTrajanjeVoznji, i, 4);
        tabelaModel.setValueAt(prosecanBrKilometaraPoVoznji, i, 5);
        tabelaModel.setValueAt(prosecnoTrajanjeVoznje, i, 6);
        tabelaModel.setValueAt(prosecnoVremeBezVoznje, i, 7);
        tabelaModel.setValueAt(prosecnaZaradaPoVoznji, i, 8);
        tabelaModel.setValueAt(ukupnaZarada, i, 9);
    }

    public void initActions() {
        dugmeIzvestajJedanDan.addActionListener(e -> {
            for (int i = 0; i < listaVozaca.size(); i++) {
                Vozac vozac = listaVozaca.get(i);
                popuniTabelu(vozac, i, Voznja.BrojDana.JEDAN_DAN);
            }
        });
        dugmeIzvestajSedamDana.addActionListener(e -> {
            for (int i = 0; i < listaVozaca.size(); i++) {
                Vozac vozac = listaVozaca.get(i);
                popuniTabelu(vozac, i, Voznja.BrojDana.SEDAM_DANA);
            }
        });
        dugmeIzvestajMesecDana.addActionListener(e -> {
            for (int i = 0; i < listaVozaca.size(); i++) {
                Vozac vozac = listaVozaca.get(i);
                popuniTabelu(vozac, i, Voznja.BrojDana.MESEC_DANA);
            }
        });
        dugmeIzvestajGodinuDana.addActionListener(e -> {
            for (int i = 0; i < listaVozaca.size(); i++) {
                Vozac vozac = listaVozaca.get(i);
                popuniTabelu(vozac, i, Voznja.BrojDana.GODINU_DANA);
            }
        });
    }
}
