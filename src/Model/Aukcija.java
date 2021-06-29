package Model;

import Gui.GlavniProzor;
import StrukturePodataka.ArrayList;

import java.io.*;
import java.util.Calendar;

public class Aukcija {
    long voznjaId;
    long vozacId;
    double minutaDoDestinacije;
    double ocena;

    public Aukcija(long voznjaId, long vozacId, double minutaDoDestinacije, double ocena) {
        this.voznjaId = voznjaId;
        this.vozacId = vozacId;
        this.minutaDoDestinacije = minutaDoDestinacije;
        this.ocena = ocena;
    }

    public static ArrayList<Aukcija> ucitajAukcije() {
        ArrayList<Aukcija> aukcije = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/aukcije.csv"));
            while ((red = bf.readLine()) != null) {
                aukcije.add(aukcijaDTO(red));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aukcije;
    }

    public static ArrayList<Voznja> ucitajVoznjeZaAukciju() {
        ArrayList<Aukcija> aukcije = ucitajAukcije();
        ArrayList<Voznja> voznje = Voznja.ucitajKreiraneVoznje();
        Vozac vozac = (Vozac) GlavniProzor.getPrijavljeniKorisnik();
        ArrayList<Voznja> filtriraneVoznje = new ArrayList<>();
        for (Voznja voznja : voznje) {
            filtriraneVoznje.add(voznja);
            for (Aukcija aukcija : aukcije) {
                if (aukcija.getVoznjaId() == voznja.getId() && aukcija.getVozacId() == vozac.getId()) {
                    filtriraneVoznje.remove(Voznja.pronadjiPoId(aukcija.getVoznjaId()));
                }
            }
        }
        if (!filtriraneVoznje.isEmpty()) {
            return filtriraneVoznje;
        }
        return voznje;
    }

    public static Aukcija aukcijaDTO(String aukcijaString) {
        String[] lineParts = aukcijaString.split(",");
        long voznjaId = Long.parseLong(lineParts[0].trim());
        long vozacId = Long.parseLong(lineParts[1].trim());
        double minutaDoDestinacije = Double.parseDouble(lineParts[2].trim());
        double ocena = Double.parseDouble(lineParts[3].trim());
        return new Aukcija(voznjaId, vozacId, minutaDoDestinacije, ocena);
    }

    public static double izracunajOcenu(double brojVoznji, double vremeDolaska, double godinaProizvodnjeVozila,
                                        double ocenaVozaca) {
        if (brojVoznji < 1) {
            brojVoznji = 1;
        }
        if (ocenaVozaca < 1) {
            ocenaVozaca = 1;
        }
        double brojVoznjiSuma = (1 / brojVoznji) * 2;
        double vremeDolaskaSuma = (1 / vremeDolaska) * 4;
        int trenutnaGodina = Calendar.getInstance().get(Calendar.YEAR);
        double starostVozilaSuma = (godinaProizvodnjeVozila / trenutnaGodina) * 2;
        double prosecnaOcenaVozaca = (ocenaVozaca / 5) * 2;

        return brojVoznjiSuma + vremeDolaskaSuma + starostVozilaSuma + prosecnaOcenaVozaca;
    }

    public static void sacuvajAukciju(Aukcija aukcija) {
        ArrayList<Aukcija> aukcije = ucitajAukcije();
        aukcije.add(aukcija);
        File file = new File("src//Data//aukcije.csv");
        try {
            PrintWriter writer = new PrintWriter(file);
            for (Aukcija a : aukcije) {
                writer.write(a.stringToSave());
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nepostojeći fajl");
        }
    }

    @Override
    public String toString() {
        return "Aukcija{voznjaId=" + voznjaId +
               ", vozacId=" + vozacId +
               ", minutaDoDestinacije=" + minutaDoDestinacije +
               ", ocena=" + ocena +
               '}';
    }

    public String stringToSave() {
        return vozacId + "," + voznjaId + "," + minutaDoDestinacije + "," + ocena + "\n";
    }

    public long getVoznjaId() {
        return voznjaId;
    }

    public void setVoznjaId(long voznjaId) {
        this.voznjaId = voznjaId;
    }

    public long getVozacId() {
        return vozacId;
    }

    public void setVozacId(long vozacId) {
        this.vozacId = vozacId;
    }

    public double getMinutaDoDestinacije() {
        return minutaDoDestinacije;
    }

    public void setMinutaDoDestinacije(double minutaDoDestinacije) {
        this.minutaDoDestinacije = minutaDoDestinacije;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }
}
