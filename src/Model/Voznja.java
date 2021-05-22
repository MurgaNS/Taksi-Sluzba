package Model;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.Date;
import java.util.List;

public class Voznja {

    private long id;
    private Date datumPorudzbine;
    private String adresaPolaska;
    private String adresaDestinacije;
    private double brojPredjenihKilometara;
    private double trajanjeVoznjeUMinutama;
    private String statusVoznje;
    private String nacinPorudzbine;
    private Long vozacId;
    private Long musterijaId;

    public Voznja(long id, Date datumPorudzbine, String adresaPolaska, String adresaDestinacije, double brojPredjenihKilometara, double trajanjeVoznjeUMinutama, String statusVoznje, String nacinPorudzbine, Long vozacId, Long musterijaId) {
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
    public Voznja(long id, Date datumPorudzbine, String adresaPolaska, String adresaDestinacije, double brojPredjenihKilometara, double trajanjeVoznjeUMinutama, String statusVoznje, String nacinPorudzbine) {
        this.id = id;
        this.datumPorudzbine = datumPorudzbine;
        this.adresaPolaska = adresaPolaska;
        this.adresaDestinacije = adresaDestinacije;
        this.brojPredjenihKilometara = brojPredjenihKilometara;
        this.trajanjeVoznjeUMinutama = trajanjeVoznjeUMinutama;
        this.statusVoznje = statusVoznje;
        this.nacinPorudzbine = nacinPorudzbine;
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


    public static List<Voznja> ucitajSveVoznje() {
        List<Voznja> sveVoznje = new ArrayList<>();
        File file = new File("src\\Data\\voznje.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Voznja voznja = null;
                String[] lineParts = line.split(",");
                voznja = new Voznja(Long.parseLong(lineParts[0]), new Date(), lineParts[2], lineParts[3], Double.parseDouble(lineParts[4]),
                        Double.parseDouble(lineParts[5]),
                        lineParts[6], lineParts[7],
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

    public static void upisiVoznje(List<Voznja> voznje){
        File file = new File("src\\Data\\voznje.csv");
        try {
            PrintWriter writer = new PrintWriter(file);
            for (Voznja voznja : voznje) {
                writer.write(voznja.stringToSave()+"\n");
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nepostojeći fajl");
        }
    }
// @murga
    public String stringToSave(){
//        long vozacJMBG = 0;
//        long musterijaJMBG = 0;
//        if(vozac != null){
//            vozacJMBG = vozac.getJMBG();
//        }
//        if(musterija != null){
//            musterijaJMBG = musterija.getJMBG();
//        }
//        return id + "," + datumPorudzbine + "," + adresaPolaska + "," +
//                adresaDestinacije + "," + brojPredjenihKilometara + "," +
//                trajanjeVoznjeUMinutama + "," + statusVoznje + "," + nacinPorudzbine + ","
//                + vozacJMBG + "," + musterijaJMBG;
        return "";
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

    public String getStatusVoznje() {
        return statusVoznje;
    }

    public void setStatusVoznje(String statusVoznje) {
        this.statusVoznje = statusVoznje;
    }

    public String getNacinPorudzbine() {
        return nacinPorudzbine;
    }

    public void setNacinPorudzbine(String nacinPorudzbine) {
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