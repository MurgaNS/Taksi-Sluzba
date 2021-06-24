package Gui.FormeZaPrikaz;

import Gui.GlavniProzor;
import Model.Vozac;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PrikazIzvestajaVoznjiProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeIzvestajJedanDan = new JButton("Jedan dan");
    private JButton dugmeIzvestajSedamDana = new JButton("Sedam dana");
    private JButton dugmeIzvestajMesecDana = new JButton("Mesec dana");
    private JButton dugmeIzvestajGodinuDana = new JButton("Godinu dana");
    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;
    private Vozac vozac;

    public PrikazIzvestajaVoznjiProzor() {
        setTitle("Prikaz vozila");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
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
        for(int k=0; k<zaglavlja.length; k++) {
            tabelaPodataka.getColumnModel().getColumn(k).setPreferredWidth(150);
        }
        for (int j = 0; j<tabelaPodataka.getColumnCount();j++) {
            tabelaPodataka.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
    }

    public void initActions() {
        dugmeIzvestajJedanDan.addActionListener(e -> {
            tabelaModel.setValueAt(Voznja.brojVoznji(vozac, Voznja.BrojDana.JEDAN_DAN), 0, 0);
            tabelaModel.setValueAt(Voznja.brojPredjenihKilometara(vozac, Voznja.BrojDana.JEDAN_DAN), 0, 1);
            tabelaModel.setValueAt(Voznja.ukupnoTrajanjeVoznji(vozac, Voznja.BrojDana.JEDAN_DAN), 0, 2);
            tabelaModel.setValueAt(Voznja.prosecanBrojKilometaraPoVoznji(vozac, Voznja.BrojDana.JEDAN_DAN), 0, 3);
            tabelaModel.setValueAt(Voznja.prosecnoTrajanjeVoznje(vozac, Voznja.BrojDana.JEDAN_DAN), 0, 4);
            tabelaModel.setValueAt(Voznja.prosecnoVremeBezVoznje(vozac, Voznja.BrojDana.JEDAN_DAN), 0, 5);
            tabelaModel.setValueAt(Voznja.prosecnaZaradaPoVoznji(vozac, Voznja.BrojDana.JEDAN_DAN), 0, 6);
            tabelaModel.setValueAt(Voznja.ukupnaZarada(vozac, Voznja.BrojDana.JEDAN_DAN), 0, 7);
        });
        dugmeIzvestajSedamDana.addActionListener(e -> {
            tabelaModel.setValueAt(Voznja.brojVoznji(vozac, Voznja.BrojDana.SEDAM_DANA), 0, 0);
            tabelaModel.setValueAt(Voznja.brojPredjenihKilometara(vozac, Voznja.BrojDana.SEDAM_DANA), 0, 1);
            tabelaModel.setValueAt(Voznja.ukupnoTrajanjeVoznji(vozac, Voznja.BrojDana.SEDAM_DANA), 0, 2);
            tabelaModel.setValueAt(Voznja.prosecanBrojKilometaraPoVoznji(vozac, Voznja.BrojDana.SEDAM_DANA), 0, 3);
            tabelaModel.setValueAt(Voznja.prosecnoTrajanjeVoznje(vozac, Voznja.BrojDana.SEDAM_DANA), 0, 4);
            tabelaModel.setValueAt(Voznja.prosecnoVremeBezVoznje(vozac, Voznja.BrojDana.SEDAM_DANA), 0, 5);
            tabelaModel.setValueAt(Voznja.prosecnaZaradaPoVoznji(vozac, Voznja.BrojDana.SEDAM_DANA), 0, 6);
            tabelaModel.setValueAt(Voznja.ukupnaZarada(vozac, Voznja.BrojDana.SEDAM_DANA), 0, 7);
        });
        dugmeIzvestajMesecDana.addActionListener(e -> {
            tabelaModel.setValueAt(Voznja.brojVoznji(vozac, Voznja.BrojDana.MESEC_DANA), 0, 0);
            tabelaModel.setValueAt(Voznja.brojPredjenihKilometara(vozac, Voznja.BrojDana.MESEC_DANA), 0, 1);
            tabelaModel.setValueAt(Voznja.ukupnoTrajanjeVoznji(vozac, Voznja.BrojDana.MESEC_DANA), 0, 2);
            tabelaModel.setValueAt(Voznja.prosecanBrojKilometaraPoVoznji(vozac, Voznja.BrojDana.MESEC_DANA), 0, 3);
            tabelaModel.setValueAt(Voznja.prosecnoTrajanjeVoznje(vozac, Voznja.BrojDana.MESEC_DANA), 0, 4);
            tabelaModel.setValueAt(Voznja.prosecnoVremeBezVoznje(vozac, Voznja.BrojDana.MESEC_DANA), 0, 5);
            tabelaModel.setValueAt(Voznja.prosecnaZaradaPoVoznji(vozac, Voznja.BrojDana.MESEC_DANA), 0, 6);
            tabelaModel.setValueAt(Voznja.ukupnaZarada(vozac, Voznja.BrojDana.MESEC_DANA), 0, 7);
        });
        dugmeIzvestajGodinuDana.addActionListener(e -> {
            tabelaModel.setValueAt(Voznja.brojVoznji(vozac, Voznja.BrojDana.GODINU_DANA), 0, 0);
            tabelaModel.setValueAt(Voznja.brojPredjenihKilometara(vozac, Voznja.BrojDana.GODINU_DANA), 0, 1);
            tabelaModel.setValueAt(Voznja.ukupnoTrajanjeVoznji(vozac, Voznja.BrojDana.GODINU_DANA), 0, 2);
            tabelaModel.setValueAt(Voznja.prosecanBrojKilometaraPoVoznji(vozac, Voznja.BrojDana.GODINU_DANA), 0, 3);
            tabelaModel.setValueAt(Voznja.prosecnoTrajanjeVoznje(vozac, Voznja.BrojDana.GODINU_DANA), 0, 4);
            tabelaModel.setValueAt(Voznja.prosecnoVremeBezVoznje(vozac, Voznja.BrojDana.GODINU_DANA), 0, 5);
            tabelaModel.setValueAt(Voznja.prosecnaZaradaPoVoznji(vozac, Voznja.BrojDana.GODINU_DANA), 0, 6);
            tabelaModel.setValueAt(Voznja.ukupnaZarada(vozac, Voznja.BrojDana.GODINU_DANA), 0, 7);
        });
    }
}
