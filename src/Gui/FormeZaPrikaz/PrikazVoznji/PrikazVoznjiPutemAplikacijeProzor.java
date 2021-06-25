package Gui.FormeZaPrikaz.PrikazVoznji;

import Gui.GlavniProzor;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import StrukturePodataka.List;

public class PrikazVoznjiPutemAplikacijeProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmePrihvati = new JButton("Prihvati");
    private JButton dugmeOdbij = new JButton("Odbij");
    private List<Voznja> listaVoznji;
    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;

    public PrikazVoznjiPutemAplikacijeProzor() {
        listaVoznji = Voznja.ucitajVoznje(Voznja.StatusVoznje.KREIRANA_NA_CEKANJU, Voznja.NacinPorudzbine.APLIKACIJOM);
        if (!listaVoznji.isEmpty()) {
            setTitle("Prikaz voznji putem aplikacije");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocation(400,250);
            setSize(1000, 600);
            setVisible(true);
            initGui();
            initActions();
        } else {
            JOptionPane.showMessageDialog(null, "Nema podataka!", "Greska", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void initGui() {
        glavniToolBar.add(dugmeOdbij);
        glavniToolBar.add(dugmePrihvati);
        add(glavniToolBar, BorderLayout.NORTH);

        String[] zaglavlja = new String[]{"Id", "Datum porudzbine", "Adresa polaska", "Adresa destinacije"};
        Object[][] sadrzaj = new Object[listaVoznji.size()][zaglavlja.length];
        for (int i = 0; i < listaVoznji.size(); i++) {
            Voznja voznja = listaVoznji.get(i);
            sadrzaj[i][0] = voznja.getId();
            sadrzaj[i][1] = voznja.getDatumPorudzbine();
            sadrzaj[i][2] = voznja.getAdresaPolaska();
            sadrzaj[i][3] = voznja.getAdresaDestinacije();
        }
        tabelaModel = new DefaultTableModel(sadrzaj, zaglavlja);
        tabelaPodataka = new JTable(tabelaModel);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        tabelaPodataka.setRowSelectionAllowed(true);
        tabelaPodataka.setColumnSelectionAllowed(false);
        tabelaPodataka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
    }

    public void initActions() {
        dugmePrihvati.addActionListener(e -> {
            int red = tabelaPodataka.getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
            } else {
                String voznjaId = tabelaModel.getValueAt(red, 0).toString();
                Voznja voznja = Voznja.pronadjiPoId(Long.parseLong(voznjaId));
                voznja.setStatusVoznje(Voznja.StatusVoznje.PRIHVACENA);
                voznja.setVozacJMBG(GlavniProzor.getPrijavljeniKorisnik().getJMBG());
                Voznja.izmeniStatusVoznje(voznja);
                PrikazVoznjiPutemAplikacijeProzor.this.dispose();
                PrikazVoznjiPutemAplikacijeProzor.this.setVisible(false);
                PrikazVoznjiPutemAplikacijeProzor prikazVoznjiPutemAplikacije = new PrikazVoznjiPutemAplikacijeProzor();
            }
        });
        dugmeOdbij.addActionListener(e -> {
            int red = tabelaPodataka.getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
            } else {
                String voznjaId = tabelaModel.getValueAt(red, 0).toString();
                Voznja voznja = Voznja.pronadjiPoId(Long.parseLong(voznjaId));
                voznja.setStatusVoznje(Voznja.StatusVoznje.ODBIJENA);
                voznja.setVozacJMBG(GlavniProzor.getPrijavljeniKorisnik().getJMBG());
                Voznja.izmeniStatusVoznje(voznja);
                PrikazVoznjiPutemAplikacijeProzor.this.dispose();
                PrikazVoznjiPutemAplikacijeProzor.this.setVisible(false);
                new PrikazVoznjiPutemAplikacijeProzor();
            }
        });
    }
}
