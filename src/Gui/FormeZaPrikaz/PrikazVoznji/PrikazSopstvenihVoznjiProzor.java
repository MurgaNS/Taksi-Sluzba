package Gui.FormeZaPrikaz.PrikazVoznji;

import Gui.GlavniProzor;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import StrukturePodataka.ArrayList;

public class PrikazSopstvenihVoznjiProzor extends JFrame {

    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;
    private ArrayList<Voznja> listaVoznji;
    public PrikazSopstvenihVoznjiProzor() {
        listaVoznji = Voznja.ucitajVoznje(GlavniProzor.getPrijavljeniKorisnik());
        if (!listaVoznji.isEmpty()) {
            setTitle("Prikaz voznji");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocation(400,250);
            setVisible(true);
            setSize(1000, 600);
            initGui();
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


}
