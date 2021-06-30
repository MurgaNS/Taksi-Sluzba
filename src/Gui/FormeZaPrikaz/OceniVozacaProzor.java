package Gui.FormeZaPrikaz;

import Gui.GlavniProzor;
import Model.Korisnik;
import Model.Vozac;
import Model.Voznja;
import StrukturePodataka.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OceniVozacaProzor extends JFrame {

    private JToolBar glavniToolBar = new JToolBar();
    private JButton oceniVoznjuDugme = new JButton("Oceni vožnju");
    private ArrayList<Voznja> listaVoznji;
    private JTable tabelaPodataka;
    private DefaultTableModel tabelaModel;


    public OceniVozacaProzor() {
        this.listaVoznji = Voznja.ucitajVoznje(Voznja.StatusVoznje.ZAVRSENA, GlavniProzor.getPrijavljeniKorisnik());
        setTitle("Prikaz voznji");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(400, 250);
        setSize(1000, 300);
        initGUI();
        initActions();
    }

    public void initGUI() {
        add(glavniToolBar, BorderLayout.NORTH);
        glavniToolBar.add(oceniVoznjuDugme);
        String[] zaglavlja = new String[]{"ID voznje", "Datum porudzbine", "Adresa polaska", "Adresa destinacije", "Broj predjenih kilometara", "Trajanje voznje u minutama", "Naplacen iznos"};
        Object[][] sadrzaj = new Object[listaVoznji.size()][zaglavlja.length];
        for (int i = 0; i < listaVoznji.size(); i++) {
            Voznja voznja = listaVoznji.get(i);
            sadrzaj[i][0] = voznja.getId();
            sadrzaj[i][1] = voznja.getDatumPorudzbine();
            sadrzaj[i][2] = voznja.getAdresaPolaska();
            sadrzaj[i][3] = voznja.getAdresaDestinacije();
            sadrzaj[i][4] = voznja.getBrojPredjenihKilometara();
            sadrzaj[i][5] = voznja.getTrajanjeVoznjeUMinutama();
            sadrzaj[i][6] = voznja.getNaplacenIznos();
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

    private void initActions() {
        oceniVoznjuDugme.addActionListener(e -> {
            int red = tabelaPodataka.getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
            } else {
                long voznjaId = Long.parseLong(tabelaModel.getValueAt(red, 0).toString());
                Voznja voznja = Voznja.binarnaPretraga(voznjaId, Voznja.ucitajSveVoznje());
                Vozac vozac = (Vozac) Korisnik.nadjiKorisnikaPrekoJMBG(voznja.getVozacJMBG());
                vozac.setBrojUspesnihVoznji(vozac.getBrojUspesnihVoznji() + 1);
                Vozac.sacuvajVozaca(vozac);
                JOptionPane.showMessageDialog(null,"Uspesno ste ocenili voznju.");
            }
        });
    }

}
