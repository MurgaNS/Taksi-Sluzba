package Gui.FormeZaPrikaz;

import Gui.FormeZaDodavanjeIIzmenu.VoziloForma;
import Model.Vozilo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VoziloProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeIzmena = new JButton("Izmena");
    private JButton dugmeDodaj = new JButton("Dodaj");
    private JButton dugmeIzbrisi = new JButton("Izbrisi");
    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;
    private List<Vozilo> listaVozila;

    public VoziloProzor() {
        listaVozila = Vozilo.ucitajSveAutomobile();
        setTitle("Vozila");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 300);
        initGui();
    }

    public void initGui() {
        glavniToolBar.add(dugmeDodaj);
        glavniToolBar.add(dugmeIzmena);
        glavniToolBar.add(dugmeIzbrisi);
        add(glavniToolBar, BorderLayout.NORTH);
        String[] zaglavlja = new String[]{"Br. vozila", "Proizvodjac", "Model", "God. proizvodnje", "Br. Reg. Oznake", "Vrsta", "Vozac"};
        Object[][] sadrzaj = new Object[listaVozila.size()][zaglavlja.length];
        for (int i = 0; i < listaVozila.size(); i++) {
            Vozilo vozilo = listaVozila.get(i);
            sadrzaj[i][0] = vozilo.getBrTaksiVozila();
            sadrzaj[i][1] = vozilo.getProizvodjac();
            sadrzaj[i][2] = vozilo.getModel();
            sadrzaj[i][3] = vozilo.getGodProizvodnje();
            sadrzaj[i][4] = vozilo.getBrRegistarskeOznake();
            sadrzaj[i][5] = vozilo.getVrsta();
            sadrzaj[i][6] = vozilo.getVozacId();
        }
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
        setVisible(true);
    }

    public void initActions() {
        dugmeIzmena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = tabelaPodataka.getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                    String voziloId = tabelaModel.getValueAt(red, 0).toString();
                    Vozilo vozilo = Vozilo.pronadjiPoBrojuTaksiVozila(voziloId);
                    VoziloForma voziloForma = new VoziloForma(vozilo);
                    voziloForma.setVisible(true);
                }
            }
        });
    }
}
