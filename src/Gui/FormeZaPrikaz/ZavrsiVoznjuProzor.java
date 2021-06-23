package Gui.FormeZaPrikaz;

import Gui.FormeZaDodavanjeIIzmenu.VoziloForma;
import Gui.FormeZaDodavanjeIIzmenu.ZavrsiVoznjuForma;
import Gui.GlavniProzor;
import Model.Vozac;
import Model.Vozilo;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ZavrsiVoznjuProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeZavrsi = new JButton("Zavrsi voznju");
    private List<Voznja> listaVoznji;
    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;

    public ZavrsiVoznjuProzor() {
        listaVoznji = Voznja.ucitajPrihvaceneVoznje((Vozac) GlavniProzor.getPrijavljeniKorisnik());
        setTitle("Prikaz prihvacenih voznji");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setSize(600, 600);
        setVisible(true);
        initGui();
        initActions();
    }

    public void initGui() {
        glavniToolBar.add(dugmeZavrsi);
        add(glavniToolBar, BorderLayout.NORTH);

        String[] zaglavlja = new String[]{"Id", "Datum porudzbine", "Adresa polaska", "Adresa destinacije", "Status"};
        Object[][] sadrzaj = new Object[listaVoznji.size()][zaglavlja.length];
        for (int i = 0; i < listaVoznji.size(); i++) {
            Voznja voznja = listaVoznji.get(i);
            sadrzaj[i][0] = voznja.getId();
            sadrzaj[i][1] = voznja.getDatumPorudzbine();
            sadrzaj[i][2] = voznja.getAdresaPolaska();
            sadrzaj[i][3] = voznja.getAdresaDestinacije();
            sadrzaj[i][4] = voznja.getStatusVoznje();
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
    }

    public void initActions() {
        dugmeZavrsi.addActionListener(e -> {
            int red = tabelaPodataka.getSelectedRow();

            if (red == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
            } else {
                String voznjaId = tabelaModel.getValueAt(red, 0).toString();
                ZavrsiVoznjuForma zavrsiVoznjuForma = new ZavrsiVoznjuForma(voznjaId);
                zavrsiVoznjuForma.setVisible(true);
            }
            ZavrsiVoznjuProzor.this.dispose();
            ZavrsiVoznjuProzor.this.setVisible(false);
        });

    }
}
