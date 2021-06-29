package Gui.FormeZaPrikaz;

import Gui.FormeZaDodavanjeIIzmenu.Pretraga.PretragaVozilaForma;
import Gui.FormeZaDodavanjeIIzmenu.VoziloForma;
import Model.Vozilo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import StrukturePodataka.ArrayList;

public class VoziloProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeIzmena = new JButton("Izmena");
    private JButton dugmeDodaj = new JButton("Dodaj");
    private JButton dugmeIzbrisi = new JButton("Izbrisi");
    private JButton dugmePretraga = new JButton("Pretraga");
    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;
    private ArrayList<Vozilo> svaVozila = Vozilo.ucitajSvaVozila();
    private ArrayList<Vozilo> listaVozila;

    public VoziloProzor() {
        listaVozila = Vozilo.ucitajNeobrisanaVozila();
        if (!listaVozila.isEmpty()) {
            setTitle("Prikaz vozila");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocation(400,250);
            setSize(1000, 300);
            initGui();
            initActions();
        } else {
            JOptionPane.showMessageDialog(null, "Nema podataka!", "Greska", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public VoziloProzor(ArrayList<Vozilo> lista) {
        listaVozila = lista;
        if (!listaVozila.isEmpty()) {
            setTitle("Prikaz vozila");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocation(400,250);
            setSize(1000, 300);
            initGui();
            initActions();
            dugmeDodaj.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Nema podataka!", "Greska", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void initGui() {
        glavniToolBar.add(dugmeDodaj);
        glavniToolBar.add(dugmeIzmena);
        glavniToolBar.add(dugmeIzbrisi);
        glavniToolBar.add(dugmePretraga);
        add(glavniToolBar, BorderLayout.NORTH);
        String[] zaglavlja = new String[]{"Br. vozila", "Proizvodjac", "Model", "God. proizvodnje", "Br. Reg. Oznake", "Vrsta", "Vozac JMBG"};
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
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        tabelaPodataka.setRowSelectionAllowed(true);
        tabelaPodataka.setColumnSelectionAllowed(false);
        tabelaPodataka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPodataka.setDefaultEditor(Object.class, null);
        tabelaPodataka.getTableHeader().setReorderingAllowed(false);
        tabelaPodataka.setAutoCreateRowSorter(true);
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

    public void initActions() {
        dugmePretraga.addActionListener(e -> {
            PretragaVozilaForma pretragaVozilaForma = new PretragaVozilaForma();
            VoziloProzor.this.dispose();
            VoziloProzor.this.setVisible(false);
        });
        dugmeIzbrisi.addActionListener(e -> {
            int red = tabelaPodataka.getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
            } else {
                String voziloId = tabelaModel.getValueAt(red, 0).toString();
//                binarna pretraga
                Vozilo vozilo = Vozilo.pretraziPoId(svaVozila,Long.parseLong(voziloId));
                if (vozilo.getVozacId() == null) {
                    int izbor = JOptionPane.showConfirmDialog(null,
                            "Da li ste sigurni da zelite da obrisete vozilo?",
                            vozilo.getBrRegistarskeOznake() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                    if (izbor == JOptionPane.YES_OPTION) {
                        vozilo.setObrisan(true);
                        tabelaModel.removeRow(red);
                        Vozilo.sacuvajListuVozilaUFajl(svaVozila);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nije moguce izbrisati vozilo koje je dodeljeno vozacu.", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dugmeDodaj.addActionListener(e -> {
            VoziloForma vf = new VoziloForma(null);
            vf.setVisible(true);
            VoziloProzor.this.setVisible(false);
            VoziloProzor.this.dispose();
        });
        dugmeIzmena.addActionListener(e -> {
            int red = tabelaPodataka.getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
            } else {
                String voziloId = tabelaModel.getValueAt(red, 0).toString();
//                binarna pretraga
                Vozilo vozilo = Vozilo.pretraziPoId(svaVozila,Long.parseLong(voziloId));
                VoziloForma voziloForma = new VoziloForma(vozilo);
                voziloForma.setVisible(true);
                VoziloProzor.this.setVisible(false);
                VoziloProzor.this.dispose();
            }
        });
    }
}
