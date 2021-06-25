package Gui.FormeZaPrikaz;

import Gui.FormeZaDodavanjeIIzmenu.TaksiSluzbaForma;
import Model.TaksiSluzba;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TaksiSluzbaProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeIzmena = new JButton("Izmena");
    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;
    private TaksiSluzba taksiSluzba;

    public TaksiSluzbaProzor() {
        this.taksiSluzba = TaksiSluzba.preuzmiPodatkeOTaksiSluzbi();
        setTitle("Informacije o taksi sluzbi " + taksiSluzba.getNaziv());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(400,250);
        setSize(1000, 400);
        initGUI();
        initActions();
    }

    public void initGUI() {
        glavniToolBar.add(dugmeIzmena);
        add(glavniToolBar, BorderLayout.NORTH);
        String[] zaglavlja = new String[]{"PIB", "Naziv", "Adresa", "Cena starta", "Cena po kilometru"};
        Object[][] sadrzaj = new Object[1][zaglavlja.length];
        sadrzaj[0][0] = taksiSluzba.getPIB();
        sadrzaj[0][1] = taksiSluzba.getNaziv();
        sadrzaj[0][2] = taksiSluzba.getAdresa();
        sadrzaj[0][3] = taksiSluzba.getCenaStarta();
        sadrzaj[0][4] = taksiSluzba.getCenaPoKilometru();
        tabelaModel = new DefaultTableModel(sadrzaj, zaglavlja);
        tabelaPodataka = new JTable(tabelaModel);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        tabelaPodataka.setRowSelectionAllowed(true);
        tabelaPodataka.setColumnSelectionAllowed(false);
        tabelaPodataka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPodataka.setDefaultEditor(Object.class, null);
        tabelaPodataka.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int k=0; k<zaglavlja.length; k++) {
            tabelaPodataka.getColumnModel().getColumn(k).setPreferredWidth(150);
        }
        for (int j = 0; j<tabelaPodataka.getColumnCount();j++) {
            tabelaPodataka.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
        setVisible(true);
    }

    public void initActions() {
        dugmeIzmena.addActionListener(e -> {
            int red = tabelaPodataka.getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
            } else {
                TaksiSluzba taksiSluzba = TaksiSluzba.preuzmiPodatkeOTaksiSluzbi();
                TaksiSluzbaForma taksiSluzbaForma = new TaksiSluzbaForma(taksiSluzba);
                taksiSluzbaForma.setVisible(true);
                TaksiSluzbaProzor.this.setVisible(false);
                TaksiSluzbaProzor.this.dispose();
            }
        });
    }
}
