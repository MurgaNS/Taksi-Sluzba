package Gui.FormeZaPrikaz;

import Gui.FormeZaDodavanjeIIzmenu.VoziloForma;
import Model.Vozilo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
        listaVozila = Vozilo.ucitajNeobrisaneAutomobile();
        setTitle("Prikaz vozila");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 300);
        initGui();
        initActions();
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
        dugmeIzbrisi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = tabelaPodataka.getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                    String voziloId = tabelaModel.getValueAt(red, 0).toString();
                    Vozilo vozilo = Vozilo.pronadjiPoBrojuTaksiVozila(voziloId,listaVozila);
                    if(vozilo.getVozacId() == null) {
                        int izbor = JOptionPane.showConfirmDialog(null,
                                "Da li ste sigurni da zelite da obrisete vozilo?",
                                vozilo.getBrRegistarskeOznake() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                        if (izbor == JOptionPane.YES_OPTION) {
                            vozilo.setObrisan(true);
                            tabelaModel.removeRow(red);
                            try {
                                Vozilo.sacuvajAutomobilUFajl(vozilo);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Nije moguce izbrisati vozilo koje je dodeljeno vozacu.","Greska",0);
                    }
                }
            }
        });

        dugmeDodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VoziloForma vf = new VoziloForma(null);
                vf.setVisible(true);
            }
        });
        dugmeIzmena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = tabelaPodataka.getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                    String voziloId = tabelaModel.getValueAt(red, 0).toString();
                    Vozilo vozilo = Vozilo.pronadjiPoBrojuTaksiVozila(voziloId,listaVozila);
                    VoziloForma voziloForma = new VoziloForma(vozilo);
                    voziloForma.setVisible(true);
                }
            }
        });
    }
}
