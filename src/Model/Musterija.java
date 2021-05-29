package Model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Musterija extends Korisnik {

    public Musterija(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona);
    }

    public static void narucivanjePutemTelefona() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adresa polaska:");
        String adresaPolaska = scanner.nextLine();
        System.out.println("Adresa destinacije");
        String adresaDestinacije = scanner.nextLine();
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        long idNoveVoznje = voznje.get(voznje.size() - 1).getId() + 1;
        Voznja voznja = new Voznja(idNoveVoznje, new Date(), adresaPolaska, adresaDestinacije, 0, 0, Voznja.StatusVoznje.KREIRANA, Voznja.NacinPorudzbine.TELEFONOM, Vozac.prijavljeniKorisnik.getJMBG(), (long)-1);
        voznje.add(voznja);
        Voznja.upisiVoznje(voznje);
        System.out.println("Vožnja je uspešno naručena!");
    }

    public static boolean narucivanjePutemAplikacije() {
        return false;
    }

    public static void prikazIstorijeVoznji() {
    }

    public static void prikaziMeni() {
        System.out.println("1. Narucivanje voznje putem telefona");
        System.out.println("2. Narucivanje voznje putem aplikacije");
        System.out.println("3. Prikaz svih voznji");
    }

    public ArrayList<Voznja> ucitajListuVoznji(Musterija musterija) throws IOException, ParseException {
        ArrayList<Voznja> listaVoznji = new ArrayList<Voznja>();
        BufferedReader bf = new BufferedReader(new FileReader("src/Data/voznje.csv"));
        String red;
        while ((red = bf.readLine()) != null) {
            String[] tmp = red.split(",");
            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
            Date date = (Date) formatter.parse(tmp[1]);
            Vozac vozac = Vozac.pronadjiPoJMBG(Long.parseLong(tmp[8]));
            Voznja.NacinPorudzbine nacinPorudzbine;
            Voznja.StatusVoznje statusVoznje;
            statusVoznje = Voznja.ucitajStatusVoznje(tmp[6]);
            nacinPorudzbine = Voznja.ucitajNacinPorudzbine(tmp[7]);
            Voznja voznja = new Voznja(Long.parseLong(tmp[0]), date, tmp[2], tmp[3], Double.parseDouble(tmp[4]), Double.parseDouble(tmp[5]), statusVoznje, nacinPorudzbine, null, null);
        }
        return listaVoznji;
    }


    public String korisnikUString() {
        return "musterija," + super.korisnikUString();
    }
}