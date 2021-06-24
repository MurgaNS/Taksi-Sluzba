package Gui.FormeZaPrikaz;

import Gui.GlavniProzor;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PrikazVoznjiPutemTelefonaProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmePrihvati = new JButton("Prihvati");
    private JButton dugmeOdbij = new JButton("Odbij");
    private List<Voznja> listaVoznji;
    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;


    public PrikazVoznjiPutemTelefonaProzor() {
        listaVoznji = Voznja.voznjeNarucenePutemTelefona();
        setTitle("Prikaz voznji putem telefona");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setSize(600, 600);
        setVisible(true);
        initGui();
        initActions();
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
        tabelaPodataka.setBounds(30, 40, 500, 500);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        tabelaPodataka.setRowSelectionAllowed(true);
        tabelaPodataka.setColumnSelectionAllowed(false);
        tabelaPodataka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPodataka.getTableHeader().setReorderingAllowed(false);
        tabelaPodataka.setAutoCreateRowSorter(true);
    }

    public void initActions() {
        dugmePrihvati.addActionListener(e -> {
            int red = tabelaPodataka.getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red.", "Greska", JOptionPane.WARNING_MESSAGE);
            } else {
                String voznjaId = tabelaModel.getValueAt(red, 0).toString();
                Voznja voznja = Voznja.pronadjiPoId(Long.parseLong(voznjaId));
                voznja.setStatusVoznje(Voznja.StatusVoznje.PRIHVACENA);
                voznja.setVozacJMBG(GlavniProzor.getPrijavljeniKorisnik().getJMBG());
                Voznja.izmeniStatusVoznje(voznja);
                PrikazVoznjiPutemTelefonaProzor.this.dispose();
                PrikazVoznjiPutemTelefonaProzor.this.setVisible(false);
                PrikazVoznjiPutemTelefonaProzor prikazVoznjiPutemTelefona = new PrikazVoznjiPutemTelefonaProzor();
            }
        });
        dugmeOdbij.addActionListener(e -> {
            int red = tabelaPodataka.getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red.", "Greska", JOptionPane.WARNING_MESSAGE);
            } else {
                String voznjaId = tabelaModel.getValueAt(red, 0).toString();
                Voznja voznja = Voznja.pronadjiPoId(Long.parseLong(voznjaId));
                voznja.setStatusVoznje(Voznja.StatusVoznje.ODBIJENA);
                voznja.setVozacJMBG(GlavniProzor.getPrijavljeniKorisnik().getJMBG());
                Voznja.izmeniStatusVoznje(voznja);
                PrikazVoznjiPutemTelefonaProzor.this.dispose();
                PrikazVoznjiPutemTelefonaProzor.this.setVisible(false);
                PrikazVoznjiPutemTelefonaProzor prikazVoznjiPutemTelefona = new PrikazVoznjiPutemTelefonaProzor();
            }
        });
    }

}
