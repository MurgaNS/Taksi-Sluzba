package Gui.FormeZaDodavanjeIIzmenu;


import Gui.FormeZaPrikaz.TaksiSluzbaProzor;
import Gui.FormeZaPrikaz.ZavrsiVoznjuProzor;
import Model.TaksiSluzba;
import Model.Voznja;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.List;

public class ZavrsiVoznjuForma extends JFrame {
    private JLabel lblBrojPredjenihKilometara = new JLabel("Broj predjenih kilometara");
    private JTextField txtBrojPredjenihKilometara = new JTextField(20);
    private JLabel lblTrajanjeVoznje = new JLabel("Trajanje voznje u minutama");
    private JTextField txtTrajanjeVoznje = new JTextField(20);
    private JButton dugmeOk = new JButton("Sacuvaj");
    private JButton dugmePonisti = new JButton("Ponisti");
    private Voznja voznja;
    private List<Voznja> listaVoznji = Voznja.ucitajSveVoznje();

    public ZavrsiVoznjuForma(String voznjaId) {
        voznja = Voznja.pronadjiPoId(Long.parseLong(voznjaId));
        setTitle("Zavrsavanje voznje");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 300);
        initGUI();
        initActions();
    }

    public void initGUI() {
        MigLayout layout = new MigLayout("wrap 1");
        setLayout(layout);
        add(lblBrojPredjenihKilometara);
        add(txtBrojPredjenihKilometara);
        add(lblTrajanjeVoznje);
        add(txtTrajanjeVoznje);
        add(dugmeOk, "split 2");
        add(dugmePonisti);
    }

    public void initActions() {
        dugmeOk.addActionListener(e -> {
            for (Voznja v : listaVoznji) {
                if (v.getId() == voznja.getId()) {
                    v.setStatusVoznje(Voznja.StatusVoznje.ZAVRSENA);
                    double brojPredjenihKilometara = Double.parseDouble(txtBrojPredjenihKilometara.getText().trim());
                    double trajanjeVoznje = Double.parseDouble(txtTrajanjeVoznje.getText().trim());
                    v.setBrojPredjenihKilometara(brojPredjenihKilometara);
                    v.setTrajanjeVoznjeUMinutama(trajanjeVoznje);
                    v.setNaplacenIznos(TaksiSluzba.preuzmiPodatkeOTaksiSluzbi().getCenaStarta() + TaksiSluzba.preuzmiPodatkeOTaksiSluzbi().getCenaPoKilometru() * brojPredjenihKilometara);
                }
            }
            Voznja.upisiVoznje(listaVoznji);
            JOptionPane.showMessageDialog(null, "Uspesno ste zavrsili voznju", "Voznja zavrsena", JOptionPane.INFORMATION_MESSAGE);
            ZavrsiVoznjuForma.this.dispose();
            ZavrsiVoznjuForma.this.setVisible(false);
            ZavrsiVoznjuProzor zavrsiVoznjuProzor = new ZavrsiVoznjuProzor();
        });
        dugmePonisti.addActionListener(e -> {
            ZavrsiVoznjuForma.this.dispose();
            ZavrsiVoznjuForma.this.setVisible(false);
        });
    }
}
