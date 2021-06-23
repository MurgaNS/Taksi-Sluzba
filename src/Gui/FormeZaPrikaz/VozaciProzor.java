package Gui.FormeZaPrikaz;

import Gui.FormeZaDodavanjeIIzmenu.VozaciForma;
import Gui.FormeZaDodavanjeIIzmenu.VoziloForma;
import Model.Korisnik;
import Model.TaksiSluzba;
import Model.Vozac;
import Model.Vozilo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VozaciProzor extends JFrame {

    private JToolBar glavniToolBar = new JToolBar();
    private JButton dugmeIzmena = new JButton("Izmena");
    private JButton dugmeBrisanje = new JButton("Obrisi");
    private JButton dugmeDodaj = new JButton("Dodaj");

    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;
    private List<Vozac> listaVozaca;


    public VozaciProzor() {
        this.listaVozaca = Vozac.ucitajSveVozace();
        setTitle("Prikaz vozaca");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 400);
        initGUI();
        initActions();
    }

    public void initGUI() {
        glavniToolBar.add(dugmeIzmena);
        glavniToolBar.add(dugmeBrisanje);
        glavniToolBar.add(dugmeDodaj);
        add(glavniToolBar, BorderLayout.NORTH);

        String[] zaglavlja = new String[]{"JMBG", "Korisnicko ime", "Lozinka", "Ime", "Prezime", "Adresa", "Pol", "Broj telefona", "Plata", "Broj clanske karte"};
        Object[][] sadrzaj = new Object[listaVozaca.size()][zaglavlja.length];
        for (int i = 0; i < listaVozaca.size(); i++) {
            Vozac vozac = listaVozaca.get(i);
            sadrzaj[0][0] = vozac.getJMBG();
            sadrzaj[i][1] = vozac.getKorisnickoIme();
            sadrzaj[i][2] = vozac.getLozinka();
            sadrzaj[i][3] = vozac.getIme();
            sadrzaj[i][4] = vozac.getPrezime();
            sadrzaj[i][5] = vozac.getAdresa();
            sadrzaj[i][6] = vozac.getPol();
            sadrzaj[i][7] = vozac.getBrojTelefona();
            sadrzaj[i][8] = vozac.getPlata();
            sadrzaj[i][8] = vozac.getBrojClanskeKarte();
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
    public void initActions() {
        dugmeBrisanje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = tabelaPodataka.getSelectedRow();
                if (red == -1 ){
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);

                }else {
                    String vozacId = tabelaModel.getValueAt(red, 0).toString();

                    Vozac vozac = (Vozac) Vozac.nadjiKorisnikaPrekoJMBG(Long.parseLong(vozacId));
                    if(vozac.getJMBG() == null ) {
                        int izbor = JOptionPane.showConfirmDialog(null,
                                "Da li ste sigurni da zelite da obrisete vozaca?",
                                vozac.getJMBG() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                        if (izbor == JOptionPane.YES_OPTION) {
                            vozac.setObrisan(true);
                            tabelaModel.removeRow(red);
                            try {
                                Vozac.upisiVozaca(vozac);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"Nije moguce izbrisati vozaca","Greska",0);

                    }

                    dugmeDodaj.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            VozaciForma vf = new VozaciForma(null);
                            vf.setVisible(true);
                            VozaciProzor.this.setVisible(false);
                            VozaciProzor.this.dispose();
                        }
                    });

                    dugmeIzmena.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int red = tabelaPodataka.getSelectedRow();
                            if (red == -1) {
                                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                            } else {
                                String vozacId = tabelaModel.getValueAt(red, 0).toString();
                                Vozac vozac = (Vozac) Vozac.nadjiKorisnikaPrekoJMBG(Long.parseLong(vozacId));
                                VozaciForma vozaciForma = new VozaciForma(vozac);
                                vozaciForma.setVisible(true);
                                VozaciProzor.this.setVisible(false);
                                VozaciProzor.this.dispose();
                            }
                        }
                    });




//                    Vozac vozac = Vozac.prikaziVozace();
//                    VozaciForma vozaciForma = new VozaciForma(vozac);
//                    vozaciForma.setVisible(true);
//                    VozaciProzor.this.setVisible(false);
//                    VozaciProzor.this.dispose();
                }
            }
        });
    }
}
