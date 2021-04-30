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
        login(korisnickoIme, lozinka);
    }

    public static void login(String korisnickoIme, String lozinka) {
        Korisnik korisnik = null;
        File file = new File("src\\Data\\korisnici.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineParts = line.split(",");
//                System.out.println(lineParts.toString());
                if (korisnickoIme.equals(lineParts[2]) && lozinka.equals(lineParts[3])) {
                    switch (lineParts[0]) {
                        case "musterija":
                            korisnik = new Musterija(Long.parseLong(lineParts[1]), lineParts[2], lineParts[3], lineParts[4], lineParts[5], lineParts[6], lineParts[7], lineParts[8]);
                            break;
                        case "vozac":
                            korisnik = new Vozac(Long.parseLong(lineParts[1]), lineParts[2], lineParts[3], lineParts[4], lineParts[5], lineParts[6], lineParts[7], lineParts[8], Double.parseDouble(lineParts[9]), Integer.parseInt(lineParts[10]));
                            break;

                        case "dispecer":
                            korisnik = new Dispecer(Long.parseLong(lineParts[1]), lineParts[2], lineParts[3], lineParts[4], lineParts[5], lineParts[6], lineParts[7], lineParts[8], Double.parseDouble(lineParts[9]), lineParts[10], lineParts[11]);
                            break;

                    }
                    System.out.println("Uspesno ste se ulogovali");
                    break;
                }
            }
            bufferedReader.close();

        } catch (FileNotFoundException exception) {
            System.out.println("Fajl nije pronadjen");
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Greska pri citanju datoteke");
        } catch (Exception e) {
            System.out.println("Niste uneli tacne podatke, molimo Vas pokusajte ponovo.");
        }

        if (korisnik instanceof Dispecer) {
            Scanner sc = new Scanner(System.in);
            try {
                while (true) {
                    Dispecer.prikaziMeni();
                    System.out.print("Unesi opciju: ");
                    String line = sc.nextLine();
                    switch (Integer.parseInt(line)) {

                        case 1 -> Dispecer.prikazPodatakaOTaksiSluzbi();
                        case 2 -> Dispecer.izmenaPodatakaTaksiSluzbe();
                        case 3 -> Dispecer.prikaziVozace();
                        case 4 -> Dispecer.dodajVozaca();
                        case 5 -> Dispecer.izmeniVozaca();
                        case 6 -> Dispecer.izbrisiVozaca();
                        case 7 -> Dispecer.dodajAutomobil();
                        case 8 -> Dispecer.prikaziAutomobile();
                        case 11 -> Voznja.prikaziSveVoznje();

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
            System.out.println(vozac.toString());
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
