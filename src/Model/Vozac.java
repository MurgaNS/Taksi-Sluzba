package Model;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Vozac extends Korisnik {


    public static Korisnik prijavljeniKorisnik = null;
    private double plata;
    private int brojClanskeKarte;
    private List<Voznja> listaVoznji;
    private Long brTaksiVozila;


    public Vozac(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, boolean obrisan, double plata, int brojClanskeKarte, List<Voznja> listaVoznji, Long brTaksiVozila) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan);
        this.plata = plata;
        this.brojClanskeKarte = brojClanskeKarte;
        this.listaVoznji = listaVoznji;
        this.brTaksiVozila = brTaksiVozila;
    }

    public Vozac(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, boolean obrisan, double plata, int brojClanskeKarte) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan);
        this.plata = plata;
        this.brojClanskeKarte = brojClanskeKarte;
    }

    public String korisnikUString() {
        return "vozac," + super.korisnikUString() + "," + plata + "," + brojClanskeKarte;
    }

    public static ArrayList<Vozac> vozaciBezAutomobila() {
        ArrayList<Vozac> vozaci = ucitajSveVozace();
        ArrayList<Vozac> retVal = new ArrayList<>();
        for (Vozac vozac : vozaci) {
            if (vozac.getBrTaksiVozila() == null) {
                retVal.add(vozac);
            }
        }
        return retVal;
    }

    public static ArrayList<Vozac> ucitajSveVozace() {
        ArrayList<Vozac> vozaci = new ArrayList<>();
        List<Vozilo> listaVozila = Vozilo.ucitajSvaVozila();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/korisnici.csv"));
            while ((red = bf.readLine()) != null) {
                String[] tmp = red.split(",");
                Pol pol = ucitajPol(tmp[7]);
                if (tmp[0].equals("vozac")) {
                    Vozilo vozilo = null;
                    Long voziloId;
                    try {
                        if (Vozilo.pronadjiPoBrojuTaksiVozila(Long.parseLong(tmp[11]), listaVozila) != null) {
                            vozilo = Vozilo.pronadjiPoBrojuTaksiVozila(Long.parseLong(tmp[11]), listaVozila);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                    try {
                        voziloId = vozilo.getBrTaksiVozila();
                    } catch (NullPointerException e) {
                        voziloId = null;
                    }
                    Vozac vozac = new Vozac(Long.parseLong(tmp[1]), tmp[2], tmp[3], tmp[4], tmp[5], tmp[6], pol, tmp[8], Boolean.parseBoolean(tmp[9]), Double.parseDouble(tmp[10]), Integer.parseInt(tmp[11]), null, voziloId);
                    vozac.setListaVoznji(ucitajListuVoznji(vozac));
                    vozaci.add(vozac);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vozaci;
    }

    public static List<Voznja> ucitajListuVoznji(Vozac vozac) {
        List<Voznja> listaVoznji = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/voznje.csv"));
            while ((red = bf.readLine()) != null) {
                String[] tmp = red.split(",");
                Voznja.NacinPorudzbine nacinPorudzbine;
                nacinPorudzbine = Voznja.ucitajNacinPorudzbine(tmp[7]);
                Voznja.StatusVoznje statusVoznje;
                statusVoznje = Voznja.ucitajStatusVoznje(tmp[6]);
                if (tmp[8].equals(String.valueOf(vozac.getJMBG()))) {
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date date = formatter.parse(tmp[1]);

                    Voznja voznja = new Voznja(Long.parseLong(tmp[0]), date, tmp[2], tmp[3], Double.parseDouble(tmp[4]), Double.parseDouble(tmp[5]), statusVoznje, nacinPorudzbine, Long.parseLong(tmp[8]), Long.parseLong(tmp[9]), Double.parseDouble(tmp[10]));
                    listaVoznji.add(voznja);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listaVoznji;
    }


    public static void upisiVozaca(Vozac vozac) {
        File file = new File("src\\Data\\korisnici.csv");
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
            writer.append("\nvozac," + vozac.getJMBG() + "," + vozac.getKorisnickoIme() + ","
                    + vozac.getLozinka() + "," + vozac.getIme() + "," +
                    vozac.getPrezime() + "," + vozac.getAdresa() + "," + vozac.getPol() + "," + vozac.getBrojTelefona() + "," +
                    vozac.getPlata() + "," + vozac.getBrojClanskeKarte());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nepostojeći fajl");
        }
    }

    public static List<Vozac> pretragaPoImenu(String ime) {
        List<Vozac> listaVozaca = ucitajSveVozace();
        List<Vozac> pronadjeniVozaci = new ArrayList<>();
        for (Vozac vozac : listaVozaca) {
            if (vozac.getIme().toLowerCase().contains(ime.toLowerCase())) {
                pronadjeniVozaci.add(vozac);
            }
        }
        return pronadjeniVozaci;
    }

    public static List<Vozac> pretragaPoPrezimenu(String prezime) {
        List<Vozac> listaVozaca = ucitajSveVozace();
        List<Vozac> pronadjeniVozaci = new ArrayList<>();
        for (Vozac vozac : listaVozaca) {
            if (vozac.getPrezime().toLowerCase().contains(prezime.toLowerCase())) {
                pronadjeniVozaci.add(vozac);
            }
        }
        return pronadjeniVozaci;
    }

    public static List<Vozac> pretragaPoPlati(double plata) {
        List<Vozac> listaVozaca = ucitajSveVozace();
        List<Vozac> pronadjeniVozaci = new ArrayList<>();
        for (Vozac vozac : listaVozaca) {
            if (vozac.getPlata() == plata) {
                pronadjeniVozaci.add(vozac);
            }
        }
        return pronadjeniVozaci;
    }


//        public static void sacuvajListuVozacaUFajl(List<Vozac> vozaci) {
//        File file = new File("src\\Data\\korisnici.csv");
//        try {
//            PrintWriter writer = new PrintWriter(file);
//            for (Vozac vozac : vozaci) {
//                writer.write(vozac.stringToSave());
//            }
//            writer.flush();
//            writer.close();
//        } catch (FileNotFoundException exception) {
//            System.out.println("Nepostojeći fajl");
//        }
//    }
//
//    public String stringToSave(){
//        return  JMBG+ ',' + korisnickoIme+ ',' + lozinka+ ',' + ime+ ',' + prezime+ ',' +
//                adresa+ ',' + pol+ ',' + brojTelefona+ ',' + obrisan+ ',' + plata+ ',' + brojClanskeKarte
//                + ','  + brTaksiVozila + '\n';
//    }

    @Override
    public String toString() {
        return "Vozac{" +
               "JMBG=" + JMBG +
               ", korisnickoIme='" + korisnickoIme + '\'' +
               ", lozinka='" + lozinka + '\'' +
               ", ime='" + ime + '\'' +
               ", prezime='" + prezime + '\'' +
               ", adresa='" + adresa + '\'' +
               ", pol='" + pol + '\'' +
               ", brojTelefona='" + brojTelefona + '\'' +
               ", plata=" + plata +
               ", brojClanskeKarte=" + brojClanskeKarte +
               ", listaVoznji=" + listaVoznji +
               ", automobil=" + brTaksiVozila +
               '}';
    }


    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public int getBrojClanskeKarte() {
        return brojClanskeKarte;
    }

    public void setBrojClanskeKarte(int brojClanskeKarte) {
        this.brojClanskeKarte = brojClanskeKarte;
    }

    public List<Voznja> getListaVoznji() {
        return listaVoznji;
    }

    public void setListaVoznji(List<Voznja> listaVoznji) {
        this.listaVoznji = listaVoznji;
    }

    public Long getBrTaksiVozila() {
        return brTaksiVozila;
    }

    public void setBrTaksiVozila(Long brTaksiVozila) {
        this.brTaksiVozila = brTaksiVozila;
    }

}