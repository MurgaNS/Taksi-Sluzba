package Model;

import Gui.FormeZaPrikaz.PrikazVoznji.AukcijaVoznjeProzor;
import Gui.GlavniProzor;
import StrukturePodataka.ArrayList;

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

    public static long generisiIdAukcije() {
        ArrayList<Aukcija> aukcije = ucitajAukcije();
        if (aukcije.isEmpty()) {
            System.out.println("Aukcija.csv prazno -> generisiIdAukcije()");
            return 1;
        }
        return aukcije.get(aukcije.size() - 1).getAukcijaId() + 1;
    }

    public static ArrayList<Voznja> ucitajVoznjeZaAukciju() {
        ArrayList<Aukcija> aukcije = ucitajAukcije();
        ArrayList<Voznja> sveVoznje = Voznja.ucitajSveVoznje();
        Vozac vozac = (Vozac) GlavniProzor.getPrijavljeniKorisnik();
        for (Aukcija aukcija : aukcije) {
            System.out.println("Aukcija -> " + aukcija);
            Voznja voznja = Voznja.pronadjiVoznjuPoId(aukcija.getVoznjaId(), sveVoznje);
            if (voznja != null && aukcija.getVoznjaId() == voznja.getId() && aukcija.getVozacId() == vozac.getJMBG()) {
                sveVoznje.remove(sveVoznje.indexOf(voznja) - 1);
            }
        }
        return sveVoznje;
    }

    public static Aukcija aukcijaDTO(String aukcijaString) {
        String[] lineParts = aukcijaString.split(",");
        long id = Long.parseLong(lineParts[0].trim());
        long voznjaId = Long.parseLong(lineParts[1].trim());
        long vozacId = Long.parseLong(lineParts[2].trim());
        double minutaDoDestinacije = Double.parseDouble(lineParts[3].trim());
        double ocena = Double.parseDouble(lineParts[4].trim());
        return new Aukcija(id, voznjaId, vozacId, minutaDoDestinacije, ocena);
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
        boolean postoji = false;
        for (Aukcija a : aukcije) {
            if (a.getVoznjaId() == aukcija.getVoznjaId() && a.getVozacId() == aukcija.getVozacId()) {
                postoji = true;
                break;
            }
        }
        if (!postoji) {
            aukcije.add(aukcija);
        }
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

    public static void zavrsiAukciju(Voznja v) {
        ArrayList<Aukcija> aukcije = ucitajAukcije();
        ArrayList<Voznja> listaVoznji = Voznja.ucitajSveVoznje();
        Voznja voznja = Voznja.pronadjiPoId(v.getId(), listaVoznji);
        Vozac pobednik = null;
        for (Aukcija aukcija : aukcije) {
            if (aukcija.getVoznjaId() == voznja.getId()) {
                if (pobednik == null) {
                    pobednik = Vozac.pronadjiPoJmbg(aukcija.getVozacId());
                } else {
                    Vozac vozac = Vozac.pronadjiPoJmbg(aukcija.getVozacId());
                    if (vozac.getBrojUspesnihVoznji() > pobednik.getBrojUspesnihVoznji()) {
                        pobednik = vozac;
                    }
                }
            }
        }
        voznja.setVozacJMBG(pobednik.getJMBG());
        voznja.setStatusVoznje(Voznja.StatusVoznje.DODELJENA);
        Voznja.upisiVoznje(listaVoznji);
        new AukcijaVoznjeProzor();
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
        return aukcijaId + "," + voznjaId + "," + vozacId + "," + minutaDoDestinacije + "," + ocena + "\n";
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

    public long getAukcijaId() {
        return aukcijaId;
    }

    public void setAukcijaId(long aukcijaId) {
        this.aukcijaId = aukcijaId;
    }
}
