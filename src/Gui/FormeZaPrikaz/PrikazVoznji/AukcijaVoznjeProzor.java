package Gui.FormeZaPrikaz.PrikazVoznji;

import Gui.FormeZaDodavanjeIIzmenu.AukcijaVozacForma;
import Gui.GlavniProzor;
import Model.Aukcija;
import Model.Dispecer;
import Model.Vozac;
import Model.Voznja;
import StrukturePodataka.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AukcijaVoznjeProzor extends JFrame {
    private DefaultTableModel tabelaModel;
    private JTable tabelaPodataka;
    private JToolBar glavniToolBar = new JToolBar();
    private JButton aukcijaDugme = new JButton("Prijavi se za voznju");
    private JButton zavrsiAukcijuDugme = new JButton("Zavrsi aukciju");
    private ArrayList<Voznja> listaVoznji;

    public AukcijaVoznjeProzor() {
        if (GlavniProzor.getPrijavljeniKorisnik() instanceof Dispecer) {
            glavniToolBar.add(zavrsiAukcijuDugme);
            listaVoznji = Voznja.ucitajKreiraneVoznje();
        } else {
            glavniToolBar.add(aukcijaDugme);
            listaVoznji = Aukcija.ucitajVoznjeZaAukciju();
        }
        if (!listaVoznji.isEmpty()) {
            setTitle("Prikaz voznji");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocation(400, 250);
            setVisible(true);
            setSize(1000, 600);
            initGui();
            initActions();
        } else {
            JOptionPane.showMessageDialog(null, "Nema podataka!", "Greska", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void initGui() {
        add(glavniToolBar, BorderLayout.NORTH);
        String[] zaglavlja = new String[]{"Voznja Id", "Datum porudzbine", "Adresa polaska", "Adresa destinacije", "Status voznje", "Nacin porudzbine", "Naplacen iznos"};
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
                Voznja voznja = Voznja.binarnaPretraga(voznjaId,Voznja.ucitajSveVoznje());
                if (voznja != null) {
                    Vozac v = (Vozac) GlavniProzor.getPrijavljeniKorisnik();
                    if (v.getBrTaksiVozila() != null) {
                        new AukcijaVozacForma(voznja, v);
                    } else {
                        JOptionPane.showMessageDialog(null, "Vozac nema automobil", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ne postoji voznja", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
            AukcijaVoznjeProzor.this.dispose();
            AukcijaVoznjeProzor.this.setVisible(false);
        });
        zavrsiAukcijuDugme.addActionListener(e -> {
            int red = tabelaPodataka.getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
            } else {
                long voznjaId = Long.parseLong(tabelaModel.getValueAt(red, 0).toString());
                Voznja voznja = Voznja.binarnaPretraga(voznjaId,Voznja.ucitajSveVoznje());
                if (voznja != null) {
                    Aukcija.zavrsiAukciju(voznja);
                    AukcijaVoznjeProzor.this.dispose();
                    AukcijaVoznjeProzor.this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Ne postoji voznja", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
