package Model;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import StrukturePodataka.ArrayList;


public class Vozac extends Korisnik {

    private Long id;
    private double plata;
    private int brojClanskeKarte;
    private ArrayList<Voznja> listaVoznji;
    private Long brTaksiVozila;


    public Vozac(long id, long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, boolean obrisan, double plata, int brojClanskeKarte, ArrayList<Voznja> listaVoznji, Long brTaksiVozila) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan);
        this.id = id;
        this.plata = plata;
        this.brojClanskeKarte = brojClanskeKarte;
        this.listaVoznji = listaVoznji;
        this.brTaksiVozila = brTaksiVozila;
    }

    public Vozac(long id, long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, boolean obrisan, double plata, int brojClanskeKarte) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan);
        this.id = id;
        this.plata = plata;
        this.brojClanskeKarte = brojClanskeKarte;
    }


    public String korisnikUString() {
        return "vozac," + id + "," + super.korisnikUString() + "," + plata + "," + brojClanskeKarte;
    }


    public static ArrayList<Voznja> ucitajListuVoznji(Vozac vozac) {
        ArrayList<Voznja> listaVoznji = new ArrayList<>();
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

    public static Vozac pronadjiPoJmbg(Long vozacId) {
        ArrayList<Vozac> vozaci = ucitajSveVozace();
        for (Vozac vozac : vozaci) {
            if (vozac.getJMBG().equals(vozacId)) {
                return vozac;
            }
        }
        return null;
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

    public static ArrayList<Vozac> pretragaPoImenu(String ime) {
        ArrayList<Vozac> listaVozaca = ucitajSveVozace();
        ArrayList<Vozac> pronadjeniVozaci = new ArrayList<>();
        for (Vozac vozac : listaVozaca) {
            if (vozac.getIme().toLowerCase().contains(ime.toLowerCase())) {
                pronadjeniVozaci.add(vozac);
            }
        }
        return pronadjeniVozaci;
    }

    public static ArrayList<Vozac> pretragaPoPrezimenu(String prezime) {
        ArrayList<Vozac> listaVozaca = ucitajSveVozace();
        ArrayList<Vozac> pronadjeniVozaci = new ArrayList<>();
        for (Vozac vozac : listaVozaca) {
            if (vozac.getPrezime().toLowerCase().contains(prezime.toLowerCase())) {
                pronadjeniVozaci.add(vozac);
            }
        }
        return pronadjeniVozaci;
    }

    public static ArrayList<Vozac> pretragaPoPlati(double plata) {
        ArrayList<Vozac> listaVozaca = ucitajSveVozace();
        ArrayList<Vozac> pronadjeniVozaci = new ArrayList<>();
        for (Vozac vozac : listaVozaca) {
            if (vozac.getPlata() >= plata) {
                pronadjeniVozaci.add(vozac);
            }
        }
        return pronadjeniVozaci;
    }

    public static Vozac pretragaPoAutomobilu(long automobilId) {
        ArrayList<Vozilo> vozila = Vozilo.ucitajSvaVozila();
        for (Vozilo vozilo : vozila) {
            long vozacJMBG = vozilo.getVozacId();
            return Vozac.pronadjiPoJmbg(vozacJMBG);
        }
        return null;
    }

    public static ArrayList<Vozac> ucitajSveVozace() {
        ArrayList<Vozac> vozaci = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/korisnici.csv"));
            while ((red = bf.readLine()) != null) {
                String[] lineParts = red.split(",");
                if (lineParts[0].equals("vozac")) {
                    Vozac vozac = ucitajVozacaIzFajla(lineParts);
                    ucitajVoziloVozacu(vozac);
                    vozaci.add(vozac);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vozaci;
    }

    public static Vozac ucitajVoziloVozacu(Vozac v) {
        for (Vozilo vozilo : Vozilo.ucitajSvaVozila()) {
            if (vozilo.getVozacId() != null && vozilo.getVozacId().equals(v.getJMBG())) {
                v.setBrTaksiVozila(vozilo.getBrTaksiVozila());
            }
        }
        return v;
    }

    public static int brojVoznji(Vozac vozac) {
        ArrayList<Voznja> listaVoznji = ucitajListuVoznji(vozac);
        return listaVoznji.size();
    }

    public static ArrayList<Long> listaIdVozac() {
        ArrayList<Long> listaId = new ArrayList<>();
        for (Vozac vozac : ucitajSveVozace()) {
            listaId.add(vozac.getId());
        }
        return listaId;
    }

    public static Vozac ucitajVozacaIzFajla(String[] lineParts) {
        long id = Long.parseLong(lineParts[1]);
        long jmbg = Long.parseLong(lineParts[2]);
        String korisnickoIme = lineParts[3];
        String lozinka = lineParts[4];
        String ime = lineParts[5];
        String prezime = lineParts[6];
        String adresa = lineParts[7];
        Pol pol = ucitajPol(lineParts[8]);
        String brojTelefona = lineParts[9];
        boolean obrisan = Boolean.parseBoolean(lineParts[10]);
        double plata = Double.parseDouble(lineParts[11]);
        int brClanskeKarte = Integer.parseInt(lineParts[12]);
        Long voziloId = Vozilo.pronadjiVoziloPoVozacu(jmbg);
        Vozac vozac = new Vozac(id, jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan, plata, brClanskeKarte, null, voziloId);
        vozac.setListaVoznji(ucitajListuVoznji(vozac));
        return vozac;
    }


    @Override
    public String toString() {
        return "Vozac{" +
               "id=" + id +
               ", JMBG=" + JMBG +
               ", korisnickoIme='" + korisnickoIme + '\'' +
               ", lozinka='" + lozinka + '\'' +
               ", ime='" + ime + '\'' +
               ", prezime='" + prezime + '\'' +
               ", adresa='" + adresa + '\'' +
               ", pol=" + pol +
               ", brojTelefona='" + brojTelefona + '\'' +
               ", obrisan=" + obrisan +
               ", plata=" + plata +
               ", brojClanskeKarte=" + brojClanskeKarte +
               ", listaVoznji=" + listaVoznji +
               ", brTaksiVozila=" + brTaksiVozila +
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

    public ArrayList<Voznja> getListaVoznji() {
        return listaVoznji;
    }

    public void setListaVoznji(ArrayList<Voznja> listaVoznji) {
        this.listaVoznji = listaVoznji;
    }

    public Long getBrTaksiVozila() {
        return brTaksiVozila;
    }

    public void setBrTaksiVozila(Long brTaksiVozila) {
        this.brTaksiVozila = brTaksiVozila;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}