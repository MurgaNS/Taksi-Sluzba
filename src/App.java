import Gui.IzmenaTaksiSluzbeProzor;
import Model.*;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Unesite korisnicko ime: ");
        String korisnickoIme = scanner.nextLine();

        System.out.println("Unesite lozinku: ");
        String lozinka = scanner.nextLine();

        Login l = new Login();
        Korisnik korisnik = l.login(korisnickoIme,lozinka);

        if (korisnik instanceof Dispecer) {
            Scanner sc = new Scanner(System.in);
            try {
                while (true) {
                    Dispecer.prikaziMeni();
                    System.out.print("Unesi opciju: ");
                    String line = sc.nextLine();
                    switch (Integer.parseInt(line)) {

                        case 1 -> TaksiSluzba.prikazPodatakaOTaksiSluzbi();
                        case 2 -> TaksiSluzba.izmenaPodatakaTaksiSluzbe();
                        case 3 -> Dispecer.prikaziVozace();
                        case 4 -> Dispecer.dodajVozaca();
                        case 5 -> Dispecer.izmeniVozaca();
                        case 6 -> Dispecer.izbrisiVozaca();
                        case 7 -> Dispecer.dodajAutomobil();
                        case 8 -> Dispecer.prikaziAutomobile();
                        case 11 -> Voznja.prikaziSveVoznje();
                        case 12 -> Dispecer.dodeljivanjeVoznjiKreiranihTelefonom();
                        case 13 -> Dispecer.kombinovanaPretragaVozaca();


                    }
                }
            } catch (IllegalStateException | NoSuchElementException | IOException e) {
                System.out.println("Doslo je do greske! System.in je zatvoren.");
            }
        } else if (korisnik instanceof Musterija) {
            Scanner sc = new Scanner(System.in);
            try {
                while (true) {
                    Musterija.prikaziMeni();
                    System.out.print("Unesi opciju: ");
                    String line = sc.nextLine();
                    switch (Integer.parseInt(line)) {
                        case 1 -> Musterija.narucivanjePutemTelefona();
                        case 2 -> Musterija.narucivanjePutemAplikacije();
                    }
                }
            } catch (IllegalStateException | NoSuchElementException e) {
                System.out.println("Doslo je do greske! System.in je zatvoren.");
            }
        } else if (korisnik instanceof Vozac) {
            Vozac vozac = (Vozac) korisnik;
            System.out.println(vozac);
            Scanner sc = new Scanner(System.in);
            try {
                while (true) {
                    Vozac.prikaziMeni();
                    System.out.print("Unesi opciju: ");
                    String line = sc.nextLine();
                    switch (Integer.parseInt(line)) {
                        case 1 -> Vozac.prikaziListuVoznji(Vozac.ucitajListuVoznji(vozac));
                        case 2 -> Vozac.prikazVoznjiPutemAplikacije();
                    }
                }
            } catch (IllegalStateException | NoSuchElementException e) {
                System.out.println("Doslo je do greske! System.in je zatvoren.");
            }
        }
    }
}
