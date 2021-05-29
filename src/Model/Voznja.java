package Model;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public Voznja(long id, Date datumPorudzbine, String adresaPolaska, String adresaDestinacije, double brojPredjenihKilometara, double trajanjeVoznjeUMinutama, StatusVoznje statusVoznje, NacinPorudzbine nacinPorudzbine, Long vozacId, Long musterijaId) {
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
               ", statusVoznje='" + statusVoznje + '\'' +
               ", nacinPorudzbine='" + nacinPorudzbine + '\'' +
               ", vozac=" + vozacId +
               ", musterija=" + musterijaId +
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
                        Long.parseLong(lineParts[9]));
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

    // @murga
    public String stringToSave() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = dateFormat.format(date);

        return id + "," + strDate + "," + adresaPolaska + "," +
               adresaDestinacije + "," + brojPredjenihKilometara + "," +
               trajanjeVoznjeUMinutama + "," + statusVoznje + "," + nacinPorudzbine + ","
               + vozacId + "," + musterijaId;
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

}