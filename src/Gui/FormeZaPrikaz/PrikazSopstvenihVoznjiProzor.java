package Gui.FormeZaPrikaz;

import Gui.GlavniProzor;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PrikazSopstvenihVoznjiProzor extends JFrame {

    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;
    private List<Voznja> listaVoznji;

    public PrikazSopstvenihVoznjiProzor() {
        listaVoznji = Voznja.ucitajListuVoznji(GlavniProzor.getPrijavljeniKorisnik());
        if (!listaVoznji.isEmpty()) {
            setTitle("Prikaz voznji");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(this);
            setSize(600, 600);
            initGui();
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
        tabelaPodataka.setBounds(30, 40, 500, 500);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        tabelaPodataka.setRowSelectionAllowed(false);
        tabelaPodataka.setColumnSelectionAllowed(false);
        setVisible(true);
    }

}
