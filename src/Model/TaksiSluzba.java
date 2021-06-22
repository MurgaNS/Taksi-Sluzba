package Model;

import java.io.*;

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
        } catch (FileNotFoundException e) {
            System.out.println("Fajl ne postoji.");
        } catch (IOException e) {
            System.out.println("Doslo je do greske prilikom ucitavanja fajla.");
        }
        return taksiSluzba;
    }

    public static void sacuvajPodatkeUFajl(TaksiSluzba taksiSluzba) throws IOException {
        File file = new File("src/Data/taksiSluzba.csv");
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(taksiSluzba.stringToSave());
        fileWriter.close();
        System.out.println("Uspesno ste izmenili podatke o taksi sluzbi.");
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