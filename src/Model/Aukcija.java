package Model;

import Gui.GlavniProzor;
import StrukturePodataka.List;

import java.io.*;
import java.util.Calendar;

public class Aukcija {
    long aukcijaId;
    long voznjaId;
    long vozacId;
    double minutaDoDestinacije;
    double ocena;

    public Aukcija(long aukcijaId, long voznjaId, long vozacId, double minutaDoDestinacije, double ocena) {
        this.aukcijaId = aukcijaId;
        this.voznjaId = voznjaId;
        this.vozacId = vozacId;
        this.minutaDoDestinacije = minutaDoDestinacije;
        this.ocena = ocena;
    }

    public static List<Aukcija> ucitajAukcije() {
        List<Aukcija> aukcije = new List<>();
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

    public static List<Voznja> ucitajVoznjeZaAukciju() {
        List<Aukcija> aukcije = ucitajAukcije();
        List<Voznja> voznje = Voznja.ucitajVoznje(Voznja.StatusVoznje.KREIRANA, Voznja.NacinPorudzbine.TELEFONOM);
        voznje.addAll(Voznja.ucitajVoznje(Voznja.StatusVoznje.KREIRANA_NA_CEKANJU, Voznja.NacinPorudzbine.APLIKACIJOM));
        Vozac vozac = (Vozac) GlavniProzor.getPrijavljeniKorisnik();
        List<Integer> indeksiZaBrisanje = new List<>();
        if (aukcije.isEmpty()) {
            return voznje;
        }
        for (Voznja voznja : voznje) {
            for (Aukcija aukcija : aukcije) {
                try {
                    if (voznja.getId() == aukcija.getVoznjaId() && aukcija.getVozacId() == vozac.getId()) {
                        indeksiZaBrisanje.add(voznje.indexOf(voznja));
                        break;
                    }
                } catch (NullPointerException ignored) {
                }
            }
        }
        for (Integer i : indeksiZaBrisanje) {
            try {
                voznje.remove((i - 1));

            } catch (ArrayIndexOutOfBoundsException ignored) {
//                lista prazna
            }
        }
        return voznje;
    }

    public static Aukcija aukcijaDTO(String aukcijaString) {
        String[] lineParts = aukcijaString.split(",");
        long aukcijaId = Long.parseLong(lineParts[0]);
        long voznjaId = Long.parseLong(lineParts[1]);
        long vozacId = Long.parseLong(lineParts[2]);
        double minutaDoDestinacije = Double.parseDouble(lineParts[3]);
        double ocena = Double.parseDouble(lineParts[4]);
        return new Aukcija(aukcijaId, voznjaId, vozacId, minutaDoDestinacije, ocena);
    }

    public static long generisiIdAukcije() {
        List<Aukcija> aukcije = ucitajAukcije();
        if (aukcije.isEmpty()) {
            return 1;
        }
        return aukcije.get(aukcije.size() - 1).getAukcijaId() + 1;
    }

    public static double izracunajOcenu(double brojVoznji, double vremeDolaska, double godinaProizvodnjeVozila, double ocenaVozaca) {
        double brojVoznjiSuma = (1 / brojVoznji) * 2;
        double vremeDolaskaSuma = (1 / vremeDolaska) * 4;
        int trenutnaGodina = Calendar.getInstance().get(Calendar.YEAR);
        double starostVozilaSuma = (godinaProizvodnjeVozila / trenutnaGodina) * 2;
        double prosecnaOcenaVozaca = (ocenaVozaca / 5) * 2;
        return brojVoznjiSuma + vremeDolaskaSuma + starostVozilaSuma + prosecnaOcenaVozaca;
    }

    public static void sacuvajAukciju(Aukcija aukcija) {
        List<Aukcija> aukcije = ucitajAukcije();
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
        return "Aukcija{" +
               "aukcijaId=" + aukcijaId +
               ", voznjaId=" + voznjaId +
               ", vozacId=" + vozacId +
               ", minutaDoDestinacije=" + minutaDoDestinacije +
               ", ocena=" + ocena +
               '}';
    }

    public String stringToSave() {
        return aukcijaId + "," + vozacId + "," + voznjaId + "," + minutaDoDestinacije + "," + ocena + "\n";
    }

    public long getAukcijaId() {
        return aukcijaId;
    }

    public void setAukcijaId(long aukcijaId) {
        this.aukcijaId = aukcijaId;
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
