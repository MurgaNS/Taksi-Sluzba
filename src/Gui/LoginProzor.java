package Gui;

import Model.Korisnik;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

        // default dugme (kad lupimo enter unutar prozora)
        getRootPane().setDefaultButton(btnOk);

    }

    public void initActions() {
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginProzor.this.dispose();  // da se oslobode memorijski resursi
                LoginProzor.this.setVisible(false);
            }
        });

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String korisnickoIme = txtKorisnickoIme.getText().trim();
                String sifra = new String(pfPassword.getPassword()).trim();
                if (Korisnik.postojiKorisnik(korisnickoIme,sifra)) {
                    JOptionPane.showMessageDialog(null,"Uspesna prijava","Prijava",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,"Korisnicko ime ili lozinka su pogresni.","Prijava",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
