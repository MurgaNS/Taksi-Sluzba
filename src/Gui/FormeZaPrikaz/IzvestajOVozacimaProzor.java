package Gui.FormeZaPrikaz;

import Gui.GlavniProzor;
import Model.Vozac;
import Model.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class IzvestajOVozacimaProzor extends JFrame {
    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeDnevniIzvestaj = new JButton("Dnevni izvestaj");
    private JButton dugmeNedeljniIzvestaj = new JButton("Nedeljni izvestaj");
    private JButton dugmeMesecniIzvestaj = new JButton("Mesecni izvestaj");
    private JButton dugmeGodisnjiIzvestaj = new JButton("Godisnji izvestaj");

    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;

    private List<Vozac> listaVozaca;

    public IzvestajOVozacimaProzor() {
        listaVozaca = Vozac.ucitajSveVozace();
        setTitle("Prikaz izvestaja");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1000, 500);
        initGui();
        initActions();
    }

    public void initGui() {
        glavniToolBar.add(dugmeDnevniIzvestaj);
        glavniToolBar.add(dugmeNedeljniIzvestaj);
        glavniToolBar.add(dugmeMesecniIzvestaj);
        glavniToolBar.add(dugmeGodisnjiIzvestaj);
        add(glavniToolBar, BorderLayout.NORTH);
        String[] zaglavlja = new String[]{"Id", "Ime i prezime", "Br. voznji", "Br. predjnih km", "Ukupno trajanje voznji", "Prosecan br. predjenih km po voznji", "Prosecno trajanje voznje", "Uk. zarada", "Prosecna zarada po voznji", "Prosecno vremena bez voznje"};
        Object[][] sadrzaj = new Object[listaVozaca.size()][zaglavlja.length];
        for (int i = 0; i < listaVozaca.size(); i++) {
            Vozac vozac = listaVozaca.get(i);
            sadrzaj[i][0] = vozac.getJMBG();
            sadrzaj[i][1] = vozac.getIme() + ' ' + vozac.getPrezime();
            sadrzaj[i][2] = Voznja.brojVoznji(vozac);
            sadrzaj[i][3] = Voznja.brojPredjenihKilometara(vozac);
            sadrzaj[i][4] = Voznja.ukupnoTrajanjeVoznji(vozac);
            sadrzaj[i][5] = Voznja.prosecanBrojKilometaraPoVoznji(vozac);
            sadrzaj[i][6] = Voznja.prosecnoTrajanjeVoznje(vozac);
            sadrzaj[i][7] = Voznja.ukupnaZarada(vozac);
            sadrzaj[i][8] = Voznja.prosecnaZaradaPoVoznji(vozac);
            sadrzaj[i][9] = Voznja.prosecnoVremeBezVoznje(vozac);

        }

        tabelaModel = new DefaultTableModel(sadrzaj, zaglavlja);
        tabelaPodataka = new JTable(tabelaModel);
        tabelaPodataka.setBounds(100, 40, 1000, 500);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        add(scrollPane);
        tabelaPodataka.setRowSelectionAllowed(true);
        tabelaPodataka.setColumnSelectionAllowed(false);
        tabelaPodataka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPodataka.setDefaultEditor(Object.class, null);
        tabelaPodataka.getTableHeader().setReorderingAllowed(false);
        setVisible(true);
    }

    public void initActions() {
    }
}
