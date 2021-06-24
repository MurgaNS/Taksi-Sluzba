package Model;


import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public static Voznja pronadjiPoId(long id) {
        return ucitajSveVoznje().get(BinarnaPretraga.find(ucitajVoznjaId(), id));
    }

    public static List<Long> ucitajVoznjaId() {
        List<Long> listaId = new ArrayList<>();
        for (Voznja voznja : ucitajSveVoznje()) {
            listaId.add(voznja.getId());
        }
        return listaId;
    }

    public static List<Voznja> voznjeNarucenePutemAplikacije() {
        List<Voznja> voznjePutemAplikacije = new ArrayList<>();
        List<Voznja> listaVoznji = ucitajSveVoznje();
        for (Voznja voznja : listaVoznji) {
            if (voznja.getStatusVoznje().equals(StatusVoznje.KREIRANA_NA_CEKANJU) && voznja.getNacinPorudzbine().equals(NacinPorudzbine.APLIKACIJOM)) {
                voznjePutemAplikacije.add(voznja);
            }
        }
        return voznjePutemAplikacije;
    }

    public static List<Voznja> sveVoznjeNarucenePutemAplikacije(BrojDana brojDana) {
        List<Voznja> sveVoznjePutemAplikacije = new ArrayList<>();
        List<Voznja> listaVoznji = ucitajSveVoznje();
        for (Voznja voznja : listaVoznji) {
            if (voznja.getStatusVoznje().equals(StatusVoznje.KREIRANA_NA_CEKANJU) && voznja.getNacinPorudzbine().equals(NacinPorudzbine.APLIKACIJOM)) {
                sveVoznjePutemAplikacije.add(voznja);
            }
        }
        return sveVoznjePutemAplikacije;
    }

    public static List<Voznja> sveVoznjeNarucenePutemTelefona(BrojDana brojDana) {
        List<Voznja> voznjePutemTelefona = new ArrayList<>();
        List<Voznja> listaVoznji = ucitajSveVoznje();
        for (Voznja voznja : listaVoznji) {
            if (voznja.getStatusVoznje().equals(StatusVoznje.KREIRANA) && voznja.getNacinPorudzbine().equals(NacinPorudzbine.TELEFONOM)) {
                voznjePutemTelefona.add(voznja);
            }
        }
        return voznjePutemTelefona;
    }

    //
    public static List<Voznja> voznjeNarucenePutemTelefona() {
        List<Voznja> voznjePutemTelefona = new ArrayList<>();
        List<Voznja> listaVoznji = ucitajSveVoznje();
        for (Voznja voznja : listaVoznji) {
            if (voznja.getStatusVoznje().equals(StatusVoznje.KREIRANA) && voznja.getNacinPorudzbine().equals(NacinPorudzbine.TELEFONOM)) {
                voznjePutemTelefona.add(voznja);
            }
        }
        return voznjePutemTelefona;
    }

    public static List<Voznja> ucitajVoznje(StatusVoznje statusVoznje, Vozac vozac) {
        List<Voznja> voznje = new ArrayList<>();
        List<Voznja> listaVoznji = ucitajListuVoznji(vozac);
        for (Voznja voznja : listaVoznji) {
            if (voznja.getStatusVoznje().equals(statusVoznje)) {
                voznje.add(voznja);
            }
        }
        return voznje;
    }

    public static List<Voznja> ucitajVoznjuPoStatusu(StatusVoznje statusVoznje) {
        List<Voznja> voznje = new ArrayList<>();
        List<Voznja> listaVoznji = ucitajSveVoznje();
        for (Voznja voznja : listaVoznji) {
            if (voznja.getStatusVoznje().equals(statusVoznje)) {
                voznje.add(voznja);
            }
        }
        return voznje;
    }

    public static NacinPorudzbine ucitajNacinPorudzbine(String nacinPorudzbine) {
        if (nacinPorudzbine.trim().equals("TELEFONOM")) {
            return NacinPorudzbine.TELEFONOM;
        } else if (nacinPorudzbine.trim().equals("APLIKACIJOM")) {
            return NacinPorudzbine.APLIKACIJOM;
        }
        return null;
    }

    public static List<Voznja> ucitajListuVoznji(Korisnik korisnik) {
        List<Voznja> voznje = ucitajSveVoznje();
        List<Voznja> listaVoznji = new ArrayList<>();
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
        if (listaVoznji.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ne postoji lista voznji za ovog korisnika", "Greska", JOptionPane.WARNING_MESSAGE);
        }
        return listaVoznji;
    }

    public static double brojPredjenihKilometara(Vozac vozac, BrojDana brojDana) {
        List<Voznja> voznje = ucitajSveVoznje();
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
        List<Voznja> voznje = ucitajSveVoznje();
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
        List<Voznja> voznje = ucitajSveVoznje();
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
        List<Voznja> voznje = ucitajSveVoznje();
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
        List<Voznja> voznje = ucitajSveVoznje();
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
        return trajanjeVoznje / brojac;
    }


    public static double ukupnaZarada(Vozac vozac, BrojDana brojDana) {
        List<Voznja> voznje = ucitajSveVoznje();
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

    public static double sumaUkupneZarade(BrojDana brojDana) {
        List<Voznja> voznje = ucitajSveVoznje();
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
        List<Voznja> voznje = ucitajSveVoznje();
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
        List<Voznja> voznje = ucitajSveVoznje();
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
        List<Voznja> voznje = ucitajSveVoznje();
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
        List<Voznja> voznje = ucitajSveVoznje();
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

    public static int brojVoznji(Vozac vozac, BrojDana brojDana) {
        try {
            List<Voznja> voznje = ucitajSveVoznje();
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
            List<Voznja> voznje = ucitajSveVoznje();
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


    public static List<Voznja> ucitajSveVoznje() {
        List<Voznja> sveVoznje = new ArrayList<>();
        File file = new File("src//Data//voznje.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineParts = line.split(",");
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
                    Voznja voznja = new Voznja(id, datumPorudzbine, adresaPolaska, adresaDestinacije, brojPredjenihKilometara, trajanjeVoznjeUMin, statusVoznje, nacinPorudzbine, vozacId, musterijaId, naplacenIznos);
                    sveVoznje.add(voznja);
                    return sveVoznje;
                }
                Voznja voznja = new Voznja(id, datumPorudzbine, adresaPolaska, adresaDestinacije, brojPredjenihKilometara, trajanjeVoznjeUMin, statusVoznje, nacinPorudzbine, vozacId, musterijaId, naplacenIznos, napomena);
                sveVoznje.add(voznja);
            }
            bufferedReader.close();
        } catch (IOException | ParseException | NumberFormatException ignored) {
        }
        return sveVoznje;
    }

    public static void upisiVoznje(List<Voznja> voznje) {
        File file = new File("src\\Data\\voznje.csv");
        try {
            PrintWriter writer = new PrintWriter(file);
            for (Voznja voznja : voznje) {
                if (!voznja.getNapomena().isEmpty() || voznja.getNapomena() != null) {
                    writer.write(voznja.stringZaCuvanjeSaNapomenom());
                } else {
                    writer.write(voznja.stringZaCuvanje());
                }
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nepostojeći fajl");
        } catch (NullPointerException ignore) {
        }
    }

    public static long preuzmiPoslednjiId() {
        long id = 0;
        try {
            List<Voznja> listaVoznji = ucitajSveVoznje();
            id = listaVoznji.get(listaVoznji.size() - 1).getId();
        } catch (NumberFormatException | IndexOutOfBoundsException ignored) {
        }
        return id;
    }

    public static void izmeniStatusVoznje(Voznja voznja) {
        List<Voznja> listaVoznji = ucitajSveVoznje();
        for (Voznja v : listaVoznji) {
            if (v.getId() == voznja.getId()) {
                v.setStatusVoznje(voznja.getStatusVoznje());
                v.setVozacJMBG(voznja.getVozacJMBG());
                break;
            }
        }
        upisiVoznje(listaVoznji);
    }

    public static void sacuvajVoznju(Voznja voznja) {
        List<Voznja> voznje = ucitajSveVoznje();
        voznje.add(voznja);
        upisiVoznje(voznje);

    }

    public String stringZaCuvanje() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = dateFormat.format(datumPorudzbine);
        return id + "," + strDate + "," + adresaPolaska + "," +
               adresaDestinacije + "," + brojPredjenihKilometara + "," +
               trajanjeVoznjeUMinutama + "," + statusVoznje + "," + nacinPorudzbine + ","
               + vozacJMBG + "," + musterijaJMBG + "," + naplacenIznos + "\n";
    }

    public String stringZaCuvanjeSaNapomenom() {
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