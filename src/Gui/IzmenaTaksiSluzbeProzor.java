package Gui;

import Model.TaksiSluzba;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class IzmenaTaksiSluzbeProzor extends JFrame {
   private JFrame jFrame = new JFrame();

    private JLabel lblPIB = new JLabel("PIB");
    private JTextField txtPIB = new JTextField(20);
    private JLabel lblNaziv = new JLabel("Naziv");
    private JTextField txtNaziv = new JTextField(20);
    private JLabel lblAdresa = new JLabel("Adresa");
    private JTextField txtAdresa = new JTextField(20);
    private JLabel lblCenaStarta = new JLabel("Cena starta");
    private JTextField txtCenaStarta = new JTextField(20);
    private JLabel lblCenaPoKm = new JLabel("Cena po kilometru");
    private JTextField txtCenaPoKm = new JTextField(20);
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private DefaultTableModel tableModel;
    private JTable tabelaPodataka;
    private TaksiSluzba taksiSluzba;

    public IzmenaTaksiSluzbeProzor() {
        this.taksiSluzba = TaksiSluzba.preuzmiPodatkeOTaksiSluzbi();
        setTitle("Izmena podataka taksi sluzbe");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 300);
        initGUI();
        initActions();
        pack();
    }

    public void initGUI() {
        List<TaksiSluzba> taksiSluzbaList = new ArrayList<>();
        taksiSluzbaList.add(taksiSluzba);
        System.out.println(taksiSluzba);
        String[] zaglavlja = new String[]{"PIB", "Naziv", "Adresa", "Cena starta", "Cena po kilometru"};
        Object[][] sadrzaj = new Object[taksiSluzbaList.size()][zaglavlja.length];
        sadrzaj[0][0] = taksiSluzba.getPIB();
        sadrzaj[0][1] = taksiSluzba.getNaziv();
        sadrzaj[0][2] = taksiSluzba.getAdresa();
        sadrzaj[0][3] = taksiSluzba.getCenaStarta();
        sadrzaj[0][4] = taksiSluzba.getCenaPoKilometru();
        tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
        tabelaPodataka = new JTable(tableModel);
        tabelaPodataka.setBounds(30,40,200,300);
        JScrollPane scrollPane = new JScrollPane(tabelaPodataka);
        jFrame.add(scrollPane);
        jFrame.setSize(300,400);
        tabelaPodataka.setRowSelectionAllowed(true);
        tabelaPodataka.setColumnSelectionAllowed(false);
        tabelaPodataka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPodataka.setDefaultEditor(Object.class, null);
        tabelaPodataka.getTableHeader().setReorderingAllowed(false);
        jFrame.setVisible(true);
    }

    public void initActions() {
    }
}
