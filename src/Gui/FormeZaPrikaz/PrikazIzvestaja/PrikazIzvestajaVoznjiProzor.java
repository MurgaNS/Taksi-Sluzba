package Gui.FormeZaPrikaz.PrikazIzvestaja;

import Gui.GlavniProzor;
import Model.Vozac;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;

public class PrikazIzvestajaVoznjiProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeIzvestajJedanDan = new JButton("Jedan dan");
    private JButton dugmeIzvestajSedamDana = new JButton("Sedam dana");
    private JButton dugmeIzvestajMesecDana = new JButton("Mesec dana");
    private JButton dugmeIzvestajGodinuDana = new JButton("Godinu dana");
    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;
    private Vozac vozac;
    private static DecimalFormat df = new DecimalFormat("0.00");

    public PrikazIzvestajaVoznjiProzor() {
        setTitle("Prikaz izvestaja");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(400, 250);
        setSize(1000, 300);
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
        String[] zaglavlja = new String[]{"Broj voznji", "Broj predjenih kilometara", "Trajanje voznji", "Prosecan broj predjenih kilometara", "Prosecno trajanje voznji", "Prosecno vreme bez voznje", "Prosecna zarada", "Ukupna zarada"};
        Object[][] sadrzaj = new Object[1][zaglavlja.length];
        vozac = (Vozac) GlavniProzor.getPrijavljeniKorisnik();
        tabelaModel = new DefaultTableModel(sadrzaj, zaglavlja);
        tabelaPodataka = new JTable(tabelaModel);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        tabelaPodataka.setRowSelectionAllowed(true);
        tabelaPodataka.setColumnSelectionAllowed(false);
        tabelaPodataka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPodataka.setDefaultEditor(Object.class, null);
        tabelaPodataka.setAutoCreateRowSorter(true);
        tabelaPodataka.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int k = 0; k < zaglavlja.length; k++) {
            tabelaPodataka.getColumnModel().getColumn(k).setPreferredWidth(150);
        }
        for (int j = 0; j < tabelaPodataka.getColumnCount(); j++) {
            tabelaPodataka.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
    }

    public void popuniTabelu(Voznja.BrojDana brojDana) {
        int brZavrsenihVoznji = Voznja.brojZavrsenihVoznji(vozac, brojDana);
        String brPredjenihKilometara = df.format(Voznja.brojPredjenihKilometara(vozac, brojDana));
        String ukTrajanjeVoznji = df.format(Voznja.ukupnoTrajanjeVoznji(vozac, brojDana));
        String prosecanBrKilometaraPoVoznji = df.format(Voznja.prosecanBrojKilometaraPoVoznji(vozac, brojDana));
        String prosecnoTrajanjeVoznje = df.format(Voznja.prosecnoTrajanjeVoznje(vozac, brojDana));
        String prosecnoVremeBezVoznje = df.format(Voznja.prosecnoVremeBezVoznje(vozac, brojDana));
        String prosecnaZaradaPoVoznji = df.format(Voznja.prosecnaZaradaPoVoznji(vozac, brojDana));
        String ukupnaZarada = df.format(Voznja.ukupnaZarada(vozac, brojDana));
        tabelaModel.setValueAt(brZavrsenihVoznji, 0, 0);
        tabelaModel.setValueAt(brPredjenihKilometara, 0, 1);
        tabelaModel.setValueAt(ukTrajanjeVoznji, 0, 2);
        tabelaModel.setValueAt(prosecanBrKilometaraPoVoznji, 0, 3);
        tabelaModel.setValueAt(prosecnoTrajanjeVoznje, 0, 4);
        tabelaModel.setValueAt(prosecnoVremeBezVoznje, 0, 5);
        tabelaModel.setValueAt(prosecnaZaradaPoVoznji, 0, 6);
        tabelaModel.setValueAt(ukupnaZarada, 0, 7);
    }

    public void initActions() {
        dugmeIzvestajJedanDan.addActionListener(e -> popuniTabelu(Voznja.BrojDana.JEDAN_DAN));
        dugmeIzvestajSedamDana.addActionListener(e -> popuniTabelu(Voznja.BrojDana.SEDAM_DANA));
        dugmeIzvestajMesecDana.addActionListener(e -> popuniTabelu(Voznja.BrojDana.MESEC_DANA));
        dugmeIzvestajGodinuDana.addActionListener(e -> popuniTabelu(Voznja.BrojDana.GODINU_DANA));
    }
}
