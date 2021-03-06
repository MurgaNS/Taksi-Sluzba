package Model;


import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import BinarnaPretraga.BinarnaPretraga;
import Gui.FormeZaPrikaz.VoznjaProzor;
import StrukturePodataka.ArrayList;

public class Voznja {

    private long id;
    private Date datumPorudzbine;
    private String adresaPolaska;
    private String adresaDestinacije;
    private double brojPredjenihKilometara;
    private double trajanjeVoznjeUMinutama;
    private StatusVoznje statusVoznje;
    private NacinPorudzbine nacinPorudzbine;
    private Long vozacJMBG;
    private Long musterijaJMBG;
    private double naplacenIznos;
    private String napomena;

    public enum NacinPorudzbine {
        APLIKACIJOM,
        TELEFONOM
    }

    public enum StatusVoznje {
        KREIRANA,
        KREIRANA_NA_CEKANJU,
        DODELJENA,
        PRIHVACENA,
        ZAVRSENA,
        ODBIJENA
    }

    public enum BrojDana {
        JEDAN_DAN,
        SEDAM_DANA,
        MESEC_DANA,
        GODINU_DANA
    }

    public Voznja(long id, Date datumPorudzbine, String adresaPolaska, String adresaDestinacije, double brojPredjenihKilometara, double trajanjeVoznjeUMinutama, StatusVoznje statusVoznje, NacinPorudzbine nacinPorudzbine, Long vozacJMBG, Long musterijaJMBG, double naplacenIznos) {
        this.id = id;
        this.datumPorudzbine = datumPorudzbine;
        this.adresaPolaska = adresaPolaska;
        this.adresaDestinacije = adresaDestinacije;
        this.brojPredjenihKilometara = brojPredjenihKilometara;
        this.trajanjeVoznjeUMinutama = trajanjeVoznjeUMinutama;
        this.statusVoznje = statusVoznje;
        this.nacinPorudzbine = nacinPorudzbine;
        this.vozacJMBG = vozacJMBG;
        this.musterijaJMBG = musterijaJMBG;
        this.naplacenIznos = naplacenIznos;
    }

    public Voznja(long id, Date datumPorudzbine, String adresaPolaska, String adresaDestinacije, double brojPredjenihKilometara, double trajanjeVoznjeUMinutama, StatusVoznje statusVoznje, NacinPorudzbine nacinPorudzbine, Long vozacJMBG, Long musterijaJMBG, double naplacenIznos, String napomena) {
        this.id = id;
        this.datumPorudzbine = datumPorudzbine;
        this.adresaPolaska = adresaPolaska;
        this.adresaDestinacije = adresaDestinacije;
        this.brojPredjenihKilometara = brojPredjenihKilometara;
        this.trajanjeVoznjeUMinutama = trajanjeVoznjeUMinutama;
        this.statusVoznje = statusVoznje;
        this.nacinPorudzbine = nacinPorudzbine;
        this.vozacJMBG = vozacJMBG;
        this.musterijaJMBG = musterijaJMBG;
        this.naplacenIznos = naplacenIznos;
        this.napomena = napomena;
    }

    @Override
    public String toString() {
        return "Voznja{" +
               "id=" + id +
               ", datumPorudzbine=" + datumPorudzbine +
               ", adresaPolaska='" + adresaPolaska + '\'' +
               ", adresaDestinacije='" + adresaDestinacije + '\'' +
               ", brojPredjenihKilometara=" + brojPredjenihKilometara +
               ", trajanjeVoznjeUMinutama=" + trajanjeVoznjeUMinutama +
               ", statusVoznje=" + statusVoznje +
               ", nacinPorudzbine=" + nacinPorudzbine +
               ", vozacId=" + vozacJMBG +
               ", musterijaId=" + musterijaJMBG +
               ", naplacenIznos=" + naplacenIznos +
               '}';
    }

    public static StatusVoznje ucitajStatusVoznje(String statusVoznje) {
        return switch (statusVoznje.trim()) {
            case "KREIRANA" -> StatusVoznje.KREIRANA;
            case "KREIRANA_NA_CEKANJU" -> StatusVoznje.KREIRANA_NA_CEKANJU;
            case "DODELJENA" -> StatusVoznje.DODELJENA;
            case "PRIHVACENA" -> StatusVoznje.PRIHVACENA;
            case "ZAVRSENA" -> StatusVoznje.ZAVRSENA;
            case "ODBIJENA" -> StatusVoznje.ODBIJENA;
            default -> null;
        };
    }

    public static Voznja binarnaPretraga(long id, ArrayList<Voznja> listaVoznji) {
        return listaVoznji.get(BinarnaPretraga.find(ucitajVoznjaId(), id));
    }

    public static Voznja pronadjiVoznjuPoId(Long id, ArrayList<Voznja> listaVoznji) {
        for (Voznja v : listaVoznji) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    public static ArrayList<Long> ucitajVoznjaId() {
        ArrayList<Long> listaId = new ArrayList<>();
        for (Voznja voznja : ucitajSveVoznje()) {
            listaId.add(voznja.getId());
        }
        return listaId;
    }

    public static ArrayList<Voznja> ucitajVoznje(StatusVoznje statusVoznje, NacinPorudzbine nacinPorudzbine) {
        ArrayList<Voznja> voznje = new ArrayList<>();
        ArrayList<Voznja> listaVoznji = ucitajSveVoznje();
        for (Voznja voznja : listaVoznji) {
            if (voznja.getStatusVoznje().equals(statusVoznje) && voznja.getNacinPorudzbine().equals(nacinPorudzbine)) {
                voznje.add(voznja);
            }
        }
        return voznje;
    }

    public static ArrayList<Voznja> ucitajKreiraneVoznje() {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        ArrayList<Voznja> kreiraneVoznje = new ArrayList<>();
        for (Voznja voznja : voznje) {
            if (voznja.getStatusVoznje().equals(StatusVoznje.KREIRANA_NA_CEKANJU) || voznja.getStatusVoznje().equals(StatusVoznje.KREIRANA)) {
                kreiraneVoznje.add(voznja);
            }
        }
        return kreiraneVoznje;
    }

    public static ArrayList<Voznja> ucitajVoznje(StatusVoznje statusVoznje, Korisnik korisnik) {
        ArrayList<Voznja> voznje = new ArrayList<>();
        ArrayList<Voznja> listaVoznji = ucitajSveVoznje();
        try {
            for (Voznja voznja : listaVoznji) {
                if (voznja.getStatusVoznje().equals(statusVoznje) && (voznja.getVozacJMBG().equals(korisnik.getJMBG()) || voznja.getMusterijaJMBG().equals(korisnik.getJMBG()))) {
                    voznje.add(voznja);
                }
            }
        } catch (NullPointerException ignored) {
//            voznja nema dodeljenog vozaca i izbacuje gresku
        }
        return voznje;
    }

    public static ArrayList<Voznja> sveVoznjeNarucenePutemAplikacije(BrojDana brojDana) {
        ArrayList<Voznja> sveVoznjePutemAplikacije = new ArrayList<>();
        ArrayList<Voznja> listaVoznji = ucitajSveVoznje();
        for (Voznja voznja : listaVoznji) {
            if (voznja.getNacinPorudzbine().equals(NacinPorudzbine.APLIKACIJOM) && voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA)) {
                sveVoznjePutemAplikacije.add(voznja);
            }
        }
        return sveVoznjePutemAplikacije;
    }

    public static ArrayList<Voznja> sveVoznjeNarucenePutemTelefona(BrojDana brojDana) {
        ArrayList<Voznja> voznjePutemTelefona = new ArrayList<>();
        ArrayList<Voznja> listaVoznji = ucitajSveVoznje();
        for (Voznja voznja : listaVoznji) {
            if (voznja.getNacinPorudzbine().equals(NacinPorudzbine.TELEFONOM) && voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA)) {
                voznjePutemTelefona.add(voznja);
            }
        }
        return voznjePutemTelefona;
    }

    //


    public static NacinPorudzbine ucitajNacinPorudzbine(String nacinPorudzbine) {
        if (nacinPorudzbine.trim().equals("TELEFONOM")) {
            return NacinPorudzbine.TELEFONOM;
        } else if (nacinPorudzbine.trim().equals("APLIKACIJOM")) {
            return NacinPorudzbine.APLIKACIJOM;
        }
        return null;
    }

    public static ArrayList<Voznja> ucitajVoznje(Korisnik korisnik) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        ArrayList<Voznja> listaVoznji = new ArrayList<>();
        for (Voznja voznja : voznje) {
            if (korisnik instanceof Musterija) {
                try {

                    if (voznja.getMusterijaJMBG().equals(korisnik.getJMBG())) {
                        listaVoznji.add(voznja);
                    }
                } catch (NullPointerException ignored) {
                }
            } else if (korisnik instanceof Vozac) {
                try {
                    if (voznja.getVozacJMBG().equals(korisnik.getJMBG())) {
                        listaVoznji.add(voznja);
                    }
                } catch (NullPointerException ignored) {
                }
            }

        }
        return listaVoznji;
    }

    public static double brojPredjenihKilometara(Vozac vozac, BrojDana brojDana) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        double brKilometara = 0;
        try {
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getVozacJMBG().equals(vozac.getJMBG()) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    brKilometara += voznja.getBrojPredjenihKilometara();
                }
            }

        } catch (NullPointerException ignored) {
        }
        return brKilometara;

    }

    public static double prosecanBrojKilometaraPoVoznji(Vozac vozac, BrojDana brojDana) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        double brKilometara = 0;
        int brojac = 0;
        try {
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getVozacJMBG().equals(vozac.getJMBG()) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    brKilometara += voznja.getBrojPredjenihKilometara();
                    brojac += 1;
                }
            }
        } catch (NullPointerException ignored) {
        }
        return brKilometara / brojac;
    }

    public static double prosecanBrojPredjenihKilometara(BrojDana brojDana) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        double prosecanBrKilometara = 0;
        int brojac = 0;
        try {
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    prosecanBrKilometara += voznja.getBrojPredjenihKilometara();
                    brojac += 1;
                }
            }
        } catch (NullPointerException ignored) {
        }
        return prosecanBrKilometara / brojac;
    }


    public static double prosecnoTrajanjeVoznje(Vozac vozac, BrojDana brojDana) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        double trajanjeVoznje = 0;
        int brojac = 0;
        try {
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getVozacJMBG().equals(vozac.getJMBG()) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    trajanjeVoznje = trajanjeVoznje + voznja.getTrajanjeVoznjeUMinutama();
                    brojac += 1;
                }
            }
        } catch (NullPointerException ignored) {
        }
        return trajanjeVoznje / brojac;
    }

    public static double ukupnoProsecnoTrajanjeVoznje(BrojDana brojDana) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        double trajanjeVoznje = 0;
        int brojac = 0;
        try {
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    trajanjeVoznje = trajanjeVoznje + voznja.getTrajanjeVoznjeUMinutama();
                    brojac += 1;
                }
            }
        } catch (NullPointerException ignored) {
        }
        return (trajanjeVoznje / brojac);
    }


    public static double ukupnaZarada(Vozac vozac, BrojDana brojDana) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        double zarada = 0;
        try {
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getVozacJMBG().equals(vozac.getJMBG()) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    zarada += voznja.getNaplacenIznos();
                }
            }
        } catch (NullPointerException ignored) {
        }
        return zarada;
    }

    public static int brojAktivnihVozaca(BrojDana brojDana) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        ArrayList<Long> vozaciJmbg = new ArrayList<>();
        for (Voznja voznja : voznje) {
            if (voznja.getVozacJMBG() != null && !vozaciJmbg.contains(voznja.getVozacJMBG())
                && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                vozaciJmbg.add(voznja.getVozacJMBG());
            }
        }
        return vozaciJmbg.size();


    }

    public static double sumaUkupneZarade(BrojDana brojDana) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        double sumaZarade = 0;
        try {
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    sumaZarade += voznja.getNaplacenIznos();
                }
            }
        } catch (NullPointerException ignored) {
        }
        return sumaZarade;
    }

    public static double prosecnoVremeBezVoznje(Vozac vozac, BrojDana brojDana) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        double radnoVreme = 0;
        double trajanjeVoznji = 0;
        try {
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getVozacJMBG().equals(vozac.getJMBG()) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    radnoVreme += TaksiSluzba.preuzmiPodatkeOTaksiSluzbi().getRADNOVREMEUMINUTAMA();
                    trajanjeVoznji += voznja.getTrajanjeVoznjeUMinutama();
                }
            }
        } catch (NullPointerException ignored) {
        }
        return radnoVreme - trajanjeVoznji;
    }

    public static double prosecnaZaradaPoVoznji(Vozac vozac, BrojDana brojDana) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        double zarada = 0;
        int brojac = 0;
        try {
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getVozacJMBG().equals(vozac.getJMBG()) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    zarada += voznja.getNaplacenIznos();
                    brojac += 1;
                }
            }
        } catch (NullPointerException ignored) {
        }
        return zarada / brojac;
    }

    public static double sumaProsecneZaradePoVoznji(BrojDana brojDana) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        double zarada = 0;
        int brojac = 0;
        try {
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    zarada += voznja.getNaplacenIznos();
                    brojac += 1;
                }
            }
        } catch (NullPointerException ignored) {
        }
        return zarada / brojac;
    }

    public static double ukupnoTrajanjeVoznji(Vozac vozac, BrojDana brojDana) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        double ukupnoTrajanjeVoznji = 0;
        try {
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getVozacJMBG().equals(vozac.getJMBG()) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    ukupnoTrajanjeVoznji = ukupnoTrajanjeVoznji + voznja.getTrajanjeVoznjeUMinutama();
                }
            }
        } catch (NullPointerException ignored) {
        }
        return ukupnoTrajanjeVoznji;
    }

    public static Date vratiDatum(BrojDana brDana) {
        Calendar cal = Calendar.getInstance();
        switch (brDana) {
            case JEDAN_DAN -> cal.add(Calendar.DAY_OF_YEAR, -1);
            case SEDAM_DANA -> cal.add(Calendar.DAY_OF_YEAR, -6);
            case MESEC_DANA -> cal.add(Calendar.MONTH, -1);
            case GODINU_DANA -> cal.add(Calendar.YEAR, -1);
        }
        return cal.getTime();
    }

    public static int brojZavrsenihVoznji(Vozac vozac, BrojDana brojDana) {
        try {
            ArrayList<Voznja> voznje = ucitajSveVoznje();
            int brVoznji = 0;
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getVozacJMBG().equals(vozac.getJMBG()) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    brVoznji = brVoznji + 1;
                }
            }
            return brVoznji;
        } catch (NullPointerException ignored) {
        }
        return 0;
    }

    public static int ukupanBrojVoznji(BrojDana brojDana) {
        try {
            ArrayList<Voznja> voznje = ucitajSveVoznje();
            int ukupanBrojVoznji = 0;
            for (Voznja voznja : voznje) {
                if (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getDatumPorudzbine().after(vratiDatum(brojDana))) {
                    ukupanBrojVoznji = ukupanBrojVoznji + 1;
                }
            }
            return ukupanBrojVoznji;
        } catch (NullPointerException ignored) {
        }
        return 0;
    }

    public static Voznja voznjaDTO(String[] lineParts) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long id = Long.parseLong(lineParts[0]);
        Date datumPorudzbine = simpleDateFormat.parse(lineParts[1]);
        String adresaPolaska = lineParts[2];
        String adresaDestinacije = lineParts[3];
        double brojPredjenihKilometara = Double.parseDouble(lineParts[4]);
        double trajanjeVoznjeUMin = Double.parseDouble(lineParts[5]);
        StatusVoznje statusVoznje = ucitajStatusVoznje(lineParts[6]);
        NacinPorudzbine nacinPorudzbine = ucitajNacinPorudzbine(lineParts[7]);
        Long vozacId = null;
        try {
            vozacId = Long.parseLong(lineParts[8]);
        } catch (NumberFormatException ignored) {

        }
        Long musterijaId = Long.parseLong(lineParts[9]);
        double naplacenIznos = Double.parseDouble(lineParts[10]);
        String napomena;
        try {
            napomena = lineParts[11];
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Voznja(id, datumPorudzbine, adresaPolaska, adresaDestinacije, brojPredjenihKilometara, trajanjeVoznjeUMin, statusVoznje, nacinPorudzbine, vozacId, musterijaId, naplacenIznos);
        }
        return new Voznja(id, datumPorudzbine, adresaPolaska, adresaDestinacije, brojPredjenihKilometara, trajanjeVoznjeUMin, statusVoznje, nacinPorudzbine, vozacId, musterijaId, naplacenIznos, napomena);
    }

    public static ArrayList<Voznja> ucitajSveVoznje() {
        ArrayList<Voznja> sveVoznje = new ArrayList<>();
        File file = new File("src//Data//voznje.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineParts = line.split(",");
                Voznja voznja = voznjaDTO(lineParts);
                sveVoznje.add(voznja);
            }
            bufferedReader.close();
        } catch (IOException | ParseException | NumberFormatException ignored) {
        }
        return sveVoznje;
    }

    public static void upisiVoznje(ArrayList<Voznja> voznje) {
        File file = new File("src\\Data\\voznje.csv");
        try {
            PrintWriter writer = new PrintWriter(file);
            for (Voznja voznja : voznje) {
                writer.write(voznja.stringZaCuvanje());
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nepostoje??i fajl");
        } catch (NullPointerException ignore) {
        }
    }

    public static long preuzmiPoslednjiId() {
        long id = 0;
        try {
            ArrayList<Voznja> listaVoznji = ucitajSveVoznje();
            id = listaVoznji.get(listaVoznji.size() - 1).getId();
        } catch (NumberFormatException | IndexOutOfBoundsException ignored) {
        }
        return id;
    }

    public static void izmeniStatusVoznje(Voznja voznja) {
        ArrayList<Voznja> listaVoznji = ucitajSveVoznje();
        for (Voznja v : listaVoznji) {
            if (v.getId() == voznja.getId()) {
                v.setStatusVoznje(voznja.getStatusVoznje());
                v.setVozacJMBG(voznja.getVozacJMBG());
                break;
            }
        }
        upisiVoznje(listaVoznji);
    }

    public static void sacuvajNovuVoznju(Voznja voznja) {
        ArrayList<Voznja> voznje = ucitajSveVoznje();
        voznje.add(voznja);
        upisiVoznje(voznje);
    }


    public String stringZaCuvanje() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = dateFormat.format(datumPorudzbine);
        return id + "," + strDate + "," + adresaPolaska + "," +
               adresaDestinacije + "," + brojPredjenihKilometara + "," +
               trajanjeVoznjeUMinutama + "," + statusVoznje + "," + nacinPorudzbine + ","
               + vozacJMBG + "," + musterijaJMBG + "," + naplacenIznos + "," + napomena + "\n";

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatumPorudzbine() {
        return datumPorudzbine;
    }

    public void setDatumPorudzbine(Date datumPorudzbine) {
        this.datumPorudzbine = datumPorudzbine;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getAdresaPolaska() {
        return adresaPolaska;
    }

    public void setAdresaPolaska(String adresaPolaska) {
        this.adresaPolaska = adresaPolaska;
    }

    public String getAdresaDestinacije() {
        return adresaDestinacije;
    }

    public void setAdresaDestinacije(String adresaDestinacije) {
        this.adresaDestinacije = adresaDestinacije;
    }

    public double getBrojPredjenihKilometara() {
        return brojPredjenihKilometara;
    }

    public void setBrojPredjenihKilometara(double brojPredjenihKilometara) {
        this.brojPredjenihKilometara = brojPredjenihKilometara;
    }

    public double getTrajanjeVoznjeUMinutama() {
        return trajanjeVoznjeUMinutama;
    }

    public void setTrajanjeVoznjeUMinutama(double trajanjeVoznjeUMinutama) {
        this.trajanjeVoznjeUMinutama = trajanjeVoznjeUMinutama;
    }

    public StatusVoznje getStatusVoznje() {
        return statusVoznje;
    }

    public void setStatusVoznje(StatusVoznje statusVoznje) {
        this.statusVoznje = statusVoznje;
    }

    public NacinPorudzbine getNacinPorudzbine() {
        return nacinPorudzbine;
    }

    public void setNacinPorudzbine(NacinPorudzbine nacinPorudzbine) {
        this.nacinPorudzbine = nacinPorudzbine;
    }

    public Long getVozacJMBG() {
        return vozacJMBG;
    }

    public void setVozacJMBG(Long vozacJMBG) {
        this.vozacJMBG = vozacJMBG;
    }

    public Long getMusterijaJMBG() {
        return musterijaJMBG;
    }

    public void setMusterijaJMBG(Long musterijaJMBG) {
        this.musterijaJMBG = musterijaJMBG;
    }

    public double getNaplacenIznos() {
        return naplacenIznos;
    }

    public void setNaplacenIznos(double naplacenIznos) {
        this.naplacenIznos = naplacenIznos;
    }
}