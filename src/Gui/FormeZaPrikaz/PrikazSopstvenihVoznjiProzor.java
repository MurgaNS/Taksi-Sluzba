package Gui.FormeZaPrikaz;

import Gui.FormeZaDodavanjeIIzmenu.AukcijaVozacForma;
import Gui.GlavniProzor;
import Model.Vozac;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PrikazSopstvenihVoznjiProzor extends JFrame {

    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;
    private List<Voznja> listaVoznji;
    private JToolBar glavniToolBar = new JToolBar();
    private JButton aukcijaDugme = new JButton("Prijavi se za voznju");

    public PrikazSopstvenihVoznjiProzor() {
        listaVoznji = Voznja.ucitajVoznje(GlavniProzor.getPrijavljeniKorisnik());
        if (!listaVoznji.isEmpty()) {
            setTitle("Prikaz voznji");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
            setSize(1000, 600);
            initGui();
        } else {
            JOptionPane.showMessageDialog(null, "Nema podataka!", "Greska", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public PrikazSopstvenihVoznjiProzor(List<Voznja> lista) {
        listaVoznji = lista;
        if (!listaVoznji.isEmpty()) {
            setTitle("Prikaz voznji");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
            setSize(1000, 600);
            initGui();
            glavniToolBar.add(aukcijaDugme);
            add(glavniToolBar, BorderLayout.NORTH);
            initActions();
        } else {
            JOptionPane.showMessageDialog(null, "Nema podataka!", "Greska", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void initGui() {
        String[] zaglavlja = new String[]{"Id", "Datum porudzbine", "Adresa polaska", "Adresa destinacije", "Status voznje", "Nacin porudzbine", "Naplacen iznos"};
        Object[][] sadrzaj = new Object[listaVoznji.size()][zaglavlja.length];
        for (int i = 0; i < listaVoznji.size(); i++) {
            Voznja voznja = listaVoznji.get(i);
            sadrzaj[i][0] = voznja.getId();
            sadrzaj[i][1] = voznja.getDatumPorudzbine();
            sadrzaj[i][2] = voznja.getAdresaPolaska();
            sadrzaj[i][3] = voznja.getAdresaDestinacije();
            sadrzaj[i][4] = voznja.getStatusVoznje();
            sadrzaj[i][5] = voznja.getNacinPorudzbine();
            sadrzaj[i][6] = voznja.getNaplacenIznos();
        }
        tabelaModel = new DefaultTableModel(sadrzaj, zaglavlja);
        tabelaPodataka = new JTable(tabelaModel);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        tabelaPodataka.setRowSelectionAllowed(true);
        tabelaPodataka.setColumnSelectionAllowed(false);
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
        aukcijaDugme.addActionListener(e -> {
            int red = tabelaPodataka.getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
            } else {
                long voznjaId = Long.parseLong(tabelaModel.getValueAt(red, 0).toString());
                Voznja voznja = Voznja.pronadjiPoId(voznjaId);
                if (voznja != null) {
                    Vozac v = (Vozac) GlavniProzor.getPrijavljeniKorisnik();
                    if (v.getBrTaksiVozila() != null) {
                        AukcijaVozacForma aukcijaVozacForma = new AukcijaVozacForma(voznja, v);
                    } else {
                        JOptionPane.showMessageDialog(null, "Vozac nema automobil", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ne postoji voznja", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
