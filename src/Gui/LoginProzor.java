package Gui;

import Model.Korisnik;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class LoginProzor extends JFrame {

    private JLabel lblGreeting = new JLabel("Dobrodosli, molimo da se prijavite. ");
    private JLabel lblUsername = new JLabel("Korisnicko ime: ");
    private JTextField txtKorisnickoIme = new JTextField(20);
    private JLabel lblPassword = new JLabel("Sifra: ");
    private JPasswordField pfPassword = new JPasswordField(20);
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancel = new JButton("Cancel");


    public LoginProzor() {
        setTitle("Prijava");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        initActions();
        pack();

    }

    public void initGUI() {
        MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
        setLayout(mig);

        add(lblGreeting, "span 2");
        add(lblUsername);
        add(txtKorisnickoIme);
        add(lblPassword);
        add(pfPassword);
        add(new JLabel());
        add(btnOk, "split 2");
        add(btnCancel);

        getRootPane().setDefaultButton(btnOk);

    }

    public void initActions() {
        btnCancel.addActionListener(e -> {
            LoginProzor.this.dispose();
            LoginProzor.this.setVisible(false);
        });

        btnOk.addActionListener(e -> {
            String korisnickoIme = txtKorisnickoIme.getText().trim();
            String sifra = new String(pfPassword.getPassword()).trim();
            Korisnik korisnik = Korisnik.postojiKorisnik(korisnickoIme, sifra);
            if (korisnik != null) {
                LoginProzor.this.dispose();
                LoginProzor.this.setVisible(false);
                GlavniProzor glavniProzor = new GlavniProzor(korisnik);
                glavniProzor.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Korisnicko ime ili lozinka su pogresni.", "Prijava", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
