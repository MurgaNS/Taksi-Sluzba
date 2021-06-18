package Model;

import java.io.*;
import java.util.Scanner;

public class TaksiSluzba {

    private long PIB;
    private String naziv;
    private String adresa;
    private double cenaStarta;
    private double cenaPoKilometru;
    public final double RADNOVREMEUMINUTAMA = 720;

    public TaksiSluzba(long PIB, String naziv, String adresa, double cenaStarta, double cenaPoKilometru) {
        this.PIB = PIB;
        this.naziv = naziv;
        this.adresa = adresa;
        this.cenaStarta = cenaStarta;
        this.cenaPoKilometru = cenaPoKilometru;
    }

    public static TaksiSluzba preuzmiPodatkeOTaksiSluzbi() {
        TaksiSluzba taksiSluzba = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Data/taksiSluzba.csv"));
            String[] red = br.readLine().split(",");
            taksiSluzba = new TaksiSluzba(Long.parseLong(red[0]), red[1], red[2], Double.parseDouble(red[3]), Double.parseDouble(red[4]));
            return taksiSluzba;
        } catch (FileNotFoundException e) {
            System.out.println("Fajl ne postoji.");
        } catch (IOException e) {
            System.out.println("Doslo je do greske prilikom ucitavanja fajla.");
        }
        return taksiSluzba;
    }

    public static void prikazPodatakaOTaksiSluzbi() {
        TaksiSluzba taksiSluzba = preuzmiPodatkeOTaksiSluzbi();
        System.out.println(taksiSluzba.toString());
    }

    public static void izmenaPodatakaTaksiSluzbeMenu() {
        System.out.println("1.PIB");
        System.out.println("2.Naziv");
        System.out.println("3.Adresa");
        System.out.println("4.Cena starta voznje");
        System.out.println("5.Cena po kilometru");
        System.out.println("6.Svi podaci");
    }

    public static void izmenaPodatakaTaksiSluzbe() throws IOException {
        TaksiSluzba taksiSluzba = preuzmiPodatkeOTaksiSluzbi();
        Scanner sc = new Scanner(System.in);
        izmenaPodatakaTaksiSluzbeMenu();
        int line = sc.nextInt();
        switch (line) {
            case 1 -> izmeniPIB(taksiSluzba);
            case 2 -> izmeniNaziv(taksiSluzba);
            case 3 -> izmeniAdresu(taksiSluzba);
            case 4 -> izmeniCenuStartaVoznje(taksiSluzba);
            case 5 -> izmeniCenuVoznjePoKilometru(taksiSluzba);
            case 6 -> izmeniSvePodatkeTaksiSluzbe(taksiSluzba);
        }
        sacuvajPodatkeUFajl(taksiSluzba);
    }

    public static void sacuvajPodatkeUFajl(TaksiSluzba taksiSluzba) throws IOException {
        File file = new File("src/Data/taksiSluzba.csv");
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(taksiSluzba.stringToSave());
        fileWriter.close();
        System.out.println("Uspesno ste izmenili podatke o taksi sluzbi.");
    }

    public static void izmeniSvePodatkeTaksiSluzbe(TaksiSluzba taksiSluzba) {
        izmeniPIB(taksiSluzba);
        izmeniNaziv(taksiSluzba);
        izmeniAdresu(taksiSluzba);
        izmeniCenuStartaVoznje(taksiSluzba);
        izmeniCenuVoznjePoKilometru(taksiSluzba);
    }

    private static void izmeniCenuVoznjePoKilometru(TaksiSluzba taksiSluzba) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cena voznje po kilometru:");
        double cenaVoznjePoKilometru = sc.nextDouble();
        taksiSluzba.setCenaPoKilometru(cenaVoznjePoKilometru);
    }

    private static void izmeniCenuStartaVoznje(TaksiSluzba taksiSluzba) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cena starta voznje:");
        double cenaStartaVoznje = sc.nextDouble();
        taksiSluzba.setCenaStarta(cenaStartaVoznje);
    }

    private static void izmeniAdresu(TaksiSluzba taksiSluzba) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Adresa:");
        String adresa = sc.nextLine();
        taksiSluzba.setAdresa(adresa);
    }

    private static void izmeniNaziv(TaksiSluzba taksiSluzba) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Naziv:");
        String naziv = sc.nextLine();
        taksiSluzba.setNaziv(naziv);
    }

    private static void izmeniPIB(TaksiSluzba taksiSluzba) {
        Scanner sc = new Scanner(System.in);
        System.out.println("PIB: ");
        long PIB = sc.nextInt();
        taksiSluzba.setPIB(PIB);
        System.out.println(taksiSluzba.toString());
    }

    @Override
    public String toString() {
        return "TaksiSluzba{" +
                "PIB=" + PIB +
                ", naziv='" + naziv + '\'' +
                ", adresa='" + adresa + '\'' +
                ", cenaStarta=" + cenaStarta +
                ", cenaPoKilometru=" + cenaPoKilometru +
                '}';
    }

    public String stringToSave() {
        return PIB + "," + naziv + "," + adresa + "," + cenaStarta + "," + cenaPoKilometru;
    }


    public long getPIB() {
        return PIB;
    }

    public void setPIB(long PIB) {
        this.PIB = PIB;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public double getCenaStarta() {
        return cenaStarta;
    }

    public void setCenaStarta(double cenaStarta) {
        this.cenaStarta = cenaStarta;
    }

    public double getCenaPoKilometru() {
        return cenaPoKilometru;
    }

    public void setCenaPoKilometru(double cenaPoKilometru) {
        this.cenaPoKilometru = cenaPoKilometru;
    }

    public double getRADNOVREMEUMINUTAMA() {
        return RADNOVREMEUMINUTAMA;
    }
}