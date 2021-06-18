package Model;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Voznja {

    private long id;
    private Date datumPorudzbine;
    private String adresaPolaska;
    private String adresaDestinacije;
    private double brojPredjenihKilometara;
    private double trajanjeVoznjeUMinutama;
    private StatusVoznje statusVoznje;
    private NacinPorudzbine nacinPorudzbine;
    private Long vozacId;
    private Long musterijaId;
    private double naplacenIznos;

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

    public Voznja(long id, Date datumPorudzbine, String adresaPolaska, String adresaDestinacije, double brojPredjenihKilometara, double trajanjeVoznjeUMinutama, StatusVoznje statusVoznje, NacinPorudzbine nacinPorudzbine, Long vozacId, Long musterijaId, double naplacenIznos) {
        this.id = id;
        this.datumPorudzbine = datumPorudzbine;
        this.adresaPolaska = adresaPolaska;
        this.adresaDestinacije = adresaDestinacije;
        this.brojPredjenihKilometara = brojPredjenihKilometara;
        this.trajanjeVoznjeUMinutama = trajanjeVoznjeUMinutama;
        this.statusVoznje = statusVoznje;
        this.nacinPorudzbine = nacinPorudzbine;
        this.vozacId = vozacId;
        this.musterijaId = musterijaId;
        this.naplacenIznos = naplacenIznos;
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
               ", vozacId=" + vozacId +
               ", musterijaId=" + musterijaId +
               ", naplacenIznos=" + naplacenIznos +
               '}';
    }

    public static void prikaziSveVoznje() {
        List<Voznja> voznje = ucitajSveVoznje();
        for (Voznja voznja : voznje) {
            System.out.println(voznja);
        }
    }

    public static StatusVoznje ucitajStatusVoznje(String statusVoznje) {
        if (statusVoznje.trim().equals("KREIRANA")) {
            return StatusVoznje.KREIRANA;
        } else if (statusVoznje.trim().equals("KREIRANA_NA_CEKANJU")) {
            return StatusVoznje.KREIRANA_NA_CEKANJU;
        } else if (statusVoznje.trim().equals("DODELJENA")) {
            return StatusVoznje.DODELJENA;
        } else if (statusVoznje.trim().equals("PRIHVACENA")) {
            return StatusVoznje.PRIHVACENA;
        } else if (statusVoznje.trim().equals("ZAVRSENA")) {
            return StatusVoznje.ZAVRSENA;
        } else if (statusVoznje.trim().equals("ODBIJENA")) {
            return StatusVoznje.ODBIJENA;
        }
        return null;
    }

    public static NacinPorudzbine ucitajNacinPorudzbine(String nacinPorudzbine) {
        if (nacinPorudzbine.trim().equals("TELEFONOM")) {
            return NacinPorudzbine.TELEFONOM;
        } else if (nacinPorudzbine.trim().equals("APLIKACIJOM")) {
            return NacinPorudzbine.APLIKACIJOM;
        }
        return null;
    }

    public static List<Voznja> ucitajListuVoznji(Vozac vozac) {
        List<Voznja> voznje = ucitajSveVoznje();
        List<Voznja> vozaceveVoznje = new ArrayList<>();
        for (Voznja voznja : voznje) {
            if (voznja.getVozacId().equals(vozac.getJMBG())) {
                vozaceveVoznje.add(voznja);
            }
        }
        return vozaceveVoznje;
    }

    public static double brojPredjenihKilometara(Vozac vozac) {
        List<Voznja> voznje = ucitajSveVoznje();
        double brKilometara = 0;
        for (Voznja voznja : voznje) {
            if (voznja.getVozacId().equals(vozac.getJMBG())) {
                brKilometara = brKilometara + voznja.getBrojPredjenihKilometara();
            }
        }
        return brKilometara;
    }

    public static double prosecanBrojKilometaraPoVoznji(Vozac vozac) {
        List<Voznja> voznje = ucitajSveVoznje();
        double brKilometara = 0;
        int brojac = 0;
        for (Voznja voznja : voznje) {
            if (voznja.getVozacId().equals(vozac.getJMBG())) {
                brKilometara = brKilometara + voznja.getBrojPredjenihKilometara();
                brojac += 1;
            }
        }
        return brKilometara / brojac;
    }

    public static double prosecnoTrajanjeVoznje(Vozac vozac) {
        List<Voznja> voznje = ucitajSveVoznje();
        double trajanjeVoznje = 0;
        int brojac = 0;
        for (Voznja voznja : voznje) {
            if (voznja.getVozacId().equals(vozac.getJMBG())) {
                trajanjeVoznje = trajanjeVoznje + voznja.getTrajanjeVoznjeUMinutama();
                brojac += 1;
            }
        }
        return trajanjeVoznje / brojac;
    }

    public static double ukupnaZarada(Vozac vozac) {
        List<Voznja> voznje = ucitajSveVoznje();
        double zarada = 0;
        for (Voznja voznja : voznje) {
            if (voznja.getVozacId().equals(vozac.getJMBG())) {
                zarada += voznja.getNaplacenIznos();
            }
        }
        return zarada;
    }

    public static double prosecnoVremeBezVoznje(Vozac vozac) {
        List<Voznja> voznje = ucitajSveVoznje();
        double radnoVreme = 0;
        double trajanjeVoznji = 0;
        for (Voznja voznja : voznje) {
            if (voznja.getVozacId().equals(vozac.getJMBG())) {
                radnoVreme += TaksiSluzba.preuzmiPodatkeOTaksiSluzbi().getRADNOVREMEUMINUTAMA();
                trajanjeVoznji += voznja.getTrajanjeVoznjeUMinutama();
            }
        }
        return radnoVreme - trajanjeVoznji;
    }

    public static double prosecnaZaradaPoVoznji(Vozac vozac) {
        List<Voznja> voznje = ucitajSveVoznje();
        double zarada = 0;
        int brojac = 0;
        for (Voznja voznja : voznje) {
            if (voznja.getVozacId().equals(vozac.getJMBG())) {
                zarada += voznja.getNaplacenIznos();
                brojac += 1;
            }
        }
        return zarada / brojac;
    }

    public static double ukupnoTrajanjeVoznji(Vozac vozac) {
        List<Voznja> voznje = ucitajSveVoznje();
        double ukupnoTrajanjeVoznji = 0;
        for (Voznja voznja : voznje) {
            if (voznja.getVozacId().equals(vozac.getJMBG())) {
                ukupnoTrajanjeVoznji = ukupnoTrajanjeVoznji + voznja.getTrajanjeVoznjeUMinutama();
            }
        }
        return ukupnoTrajanjeVoznji;
    }


    public static int brojVoznji(Vozac vozac) {
        List<Voznja> voznje = ucitajSveVoznje();
        int brVoznji = 0;
        for (Voznja voznja : voznje) {
            if (voznja.getVozacId().equals(vozac.getJMBG())) {
                brVoznji = brVoznji + 1;
            }
        }
        return brVoznji;
    }

    public static List<Voznja> ucitajSveVoznje() {
        List<Voznja> sveVoznje = new ArrayList<>();
        File file = new File("src\\Data\\voznje.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Voznja voznja = null;
                NacinPorudzbine nacinPorudzbine = NacinPorudzbine.APLIKACIJOM;
                String[] lineParts = line.split(",");
                StatusVoznje statusVoznje = ucitajStatusVoznje(lineParts[6]);
                nacinPorudzbine = ucitajNacinPorudzbine(lineParts[7]);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                Date date = simpleDateFormat.parse(lineParts[1]);
                voznja = new Voznja(Long.parseLong(lineParts[0]), date, lineParts[2], lineParts[3], Double.parseDouble(lineParts[4]),
                        Double.parseDouble(lineParts[5]),
                        statusVoznje, nacinPorudzbine,
                        Long.parseLong(lineParts[8]),
                        Long.parseLong(lineParts[9]),
                        Double.parseDouble(lineParts[10]));
                sveVoznje.add(voznja);
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
        return sveVoznje;
    }

    public static void upisiVoznje(List<Voznja> voznje) {
        File file = new File("src\\Data\\voznje.csv");
        try {
            PrintWriter writer = new PrintWriter(file);
            for (Voznja voznja : voznje) {
                writer.write(voznja.stringToSave() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nepostojeći fajl");
        }
    }

    public String stringToSave() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = dateFormat.format(datumPorudzbine);

        return id + "," + strDate + "," + adresaPolaska + "," +
               adresaDestinacije + "," + brojPredjenihKilometara + "," +
               trajanjeVoznjeUMinutama + "," + statusVoznje + "," + nacinPorudzbine + ","
               + vozacId + "," + musterijaId + "," + naplacenIznos;
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

    public Long getVozacId() {
        return vozacId;
    }

    public void setVozacId(Long vozacId) {
        this.vozacId = vozacId;
    }

    public Long getMusterijaId() {
        return musterijaId;
    }

    public void setMusterijaId(Long musterijaId) {
        this.musterijaId = musterijaId;
    }

    public double getNaplacenIznos() {
        return naplacenIznos;
    }

    public void setNaplacenIznos(double naplacenIznos) {
        this.naplacenIznos = naplacenIznos;
    }
}