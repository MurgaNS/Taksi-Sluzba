package Gui.FormeZaPrikaz;

import Gui.FormeZaPrikaz.PrikazVoznji.DodeliVoznjuVozacuProzor;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VoznjaProzor extends JFrame {

    private JToolBar glavniToolBar = new JToolBar();
    private JButton dodeliBtn = new JButton("Dodeli vožnju");
    private final List<Voznja> listaVoznji;
    private JTable tabelaPodataka;
    private DefaultTableModel tabelaModel;


    public VoznjaProzor() {
        this.listaVoznji = Voznja.ucitajSveVoznje();
        setTitle("Prikaz voznji");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 400);
        initGUI();
        initActions();
    }

    public void initGUI() {
        add(glavniToolBar, BorderLayout.NORTH);
        glavniToolBar.add(dodeliBtn);
        String[] zaglavlja = new String[]{"ID voznje", "Datum porudzbine", "Adresa polaska", "Adresa destinacije", "Broj predjenih kilometara", "Trajanje voznje u minutama", "Status voznje", "Nacin porudzbine", "Vozac JMBG", "Musterija JMBG","Naplacen iznos"};
        Object[][] sadrzaj = new Object[listaVoznji.size()][zaglavlja.length];
        for (int i = 0; i < listaVoznji.size(); i++) {
            Voznja voznja = listaVoznji.get(i);
            sadrzaj[i][0] = voznja.getId();
            sadrzaj[i][1] = voznja.getDatumPorudzbine();
            sadrzaj[i][2] = voznja.getAdresaPolaska();
            sadrzaj[i][3] = voznja.getAdresaDestinacije();
            sadrzaj[i][4] = voznja.getBrojPredjenihKilometara();
            sadrzaj[i][5] = voznja.getTrajanjeVoznjeUMinutama();
            sadrzaj[i][6] = voznja.getStatusVoznje();
            sadrzaj[i][7] = voznja.getNacinPorudzbine();
            sadrzaj[i][8] = voznja.getVozacJMBG();
            sadrzaj[i][9] = voznja.getMusterijaJMBG();
            sadrzaj[i][10] = voznja.getNaplacenIznos();
        }
        tabelaModel = new DefaultTableModel(sadrzaj, zaglavlja);
        tabelaPodataka = new JTable(tabelaModel);
        tabelaPodataka.setBounds(30, 40, 200, 300);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        tabelaPodataka.setRowSelectionAllowed(true);
        tabelaPodataka.setColumnSelectionAllowed(false);
        tabelaPodataka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPodataka.setDefaultEditor(Object.class, null);
        tabelaPodataka.getTableHeader().setReorderingAllowed(false);
        setVisible(true);
    }

    private void initActions(){
        dodeliBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Voznja voznja = izabranaVoznja();
                if (voznja.getStatusVoznje() == Voznja.StatusVoznje.KREIRANA || voznja.getStatusVoznje() == Voznja.StatusVoznje.ODBIJENA) {
                    DodeliVoznjuVozacuProzor dodeliVoznjuVozacuProzor = new DodeliVoznjuVozacuProzor(voznja);
                    dodeliVoznjuVozacuProzor.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Ne možete dodeliti ovu vožnju.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    public Voznja izabranaVoznja(){
        int red = tabelaPodataka.getSelectedRow();
        long idVoznje = (long) tabelaPodataka.getValueAt(red, 0);
        Voznja voznja = Voznja.pronadjiPoId(idVoznje);
        return voznja;
    }
}
