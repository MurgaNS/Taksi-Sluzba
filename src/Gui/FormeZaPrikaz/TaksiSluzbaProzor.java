package Gui.FormeZaPrikaz;

import Gui.FormeZaDodavanjeIIzmenu.TaksiSluzbaForma;
import Model.TaksiSluzba;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaksiSluzbaProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeIzmena = new JButton("Izmena");
    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;
    private TaksiSluzba taksiSluzba;

    public TaksiSluzbaProzor() {
        this.taksiSluzba = TaksiSluzba.preuzmiPodatkeOTaksiSluzbi();
        setTitle("Izmena podataka taksi sluzbe");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 300);
        initGUI();
        initActions();
    }

    public void initGUI() {
        glavniToolBar.add(dugmeIzmena);
        add(glavniToolBar, BorderLayout.NORTH);
        List<TaksiSluzba> taksiSluzbaList = new ArrayList<>();
        taksiSluzbaList.add(taksiSluzba);
        System.out.println(taksiSluzba);
        String[] zaglavlja = new String[]{"PIB", "Naziv", "Adresa", "Cena starta", "Cena po kilometru"};
        Object[][] sadrzaj = new Object[taksiSluzbaList.size()][zaglavlja.length];
        sadrzaj[0][0] = taksiSluzba.getPIB();
        sadrzaj[0][1] = taksiSluzba.getNaziv();
        sadrzaj[0][2] = taksiSluzba.getAdresa();
        sadrzaj[0][3] = taksiSluzba.getCenaStarta();
        sadrzaj[0][4] = taksiSluzba.getCenaPoKilometru();
        tabelaModel = new DefaultTableModel(sadrzaj, zaglavlja);
        tabelaPodataka = new JTable(tabelaModel);
        tabelaPodataka.setBounds(30, 40, 200, 300);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        setSize(300, 400);
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
                    TaksiSluzba taksiSluzba = TaksiSluzba.preuzmiPodatkeOTaksiSluzbi();
                    TaksiSluzbaForma taksiSluzbaForma = new TaksiSluzbaForma(taksiSluzba);
                    taksiSluzbaForma.setVisible(true);
                }
            }
        });
    }
}