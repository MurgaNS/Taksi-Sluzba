package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vozilo {

    private String brTaksiVozila;
    private String model;
    private String proizvodjac;
    private int godProizvodnje;
    private String brRegistarskeOznake;
    private String vrsta;
    private Long vozacId;
    private boolean obrisan;

    public Vozilo(String brTaksiVozila, String model, String proizvodjac, int godProizvodnje, String brRegistarskeOznake, String vrsta, Boolean obrisan, Long vozacId) {
        this.brTaksiVozila = brTaksiVozila;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godProizvodnje = godProizvodnje;
        this.brRegistarskeOznake = brRegistarskeOznake;
        this.vrsta = vrsta;
        this.obrisan = obrisan;
        this.vozacId = vozacId;
    }

    public Vozilo(String brTaksiVozila, String model, String proizvodjac, int godProizvodnje, String brRegistarskeOznake, String vrsta, Boolean obrisan) {
        this.brTaksiVozila = brTaksiVozila;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godProizvodnje = godProizvodnje;
        this.brRegistarskeOznake = brRegistarskeOznake;
        this.obrisan = obrisan;
        this.vrsta = vrsta;
    }

    public static void izmeniBrojRegistarskeOznake(Vozilo vozilo) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Novi registarski broj vozila:");
        String noviRegBroj = sc.nextLine();
        vozilo.setBrRegistarskeOznake(noviRegBroj);
    }

    public static List<Vozilo> ucitajSveAutomobile() {
        List<Vozilo> automobili = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/vozila.csv"));
            while ((red = bf.readLine()) != null) {
                Vozilo vozilo = automobilDTO(red);
                automobili.add(vozilo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return automobili;
    }

    public static Vozilo automobilDTO(String automobilString) {
        String[] wordsList = automobilString.split(",");
        Vozilo vozilo;
        try {
            vozilo = new Vozilo(wordsList[0], wordsList[1], wordsList[2], Integer.parseInt(wordsList[3]), wordsList[4], wordsList[5], Boolean.parseBoolean(wordsList[6]), Long.parseLong(wordsList[7]));
        } catch (NumberFormatException e) {
            vozilo = new Vozilo(wordsList[0], wordsList[1], wordsList[2], Integer.parseInt(wordsList[3]), wordsList[4], wordsList[5], Boolean.parseBoolean(wordsList[6]));

        }
        return vozilo;
    }

    public static Vozilo pronadjiPoBrojuTaksiVozila(String brTaksiVozila) {
        List<Vozilo> automobili = ucitajSveAutomobile();
        for (Vozilo vozilo :
                automobili) {
            if (vozilo.getBrTaksiVozila().equals(brTaksiVozila)) {
                return vozilo;
            }
        }
        return null;
    }

    public static void ispisiSvaSlobodnaVozila() {
        List<Vozilo> automobili = ucitajSveAutomobile();
        for (Vozilo vozilo : automobili) {
            if (vozilo.getVozacId() == null) {
                System.out.println(vozilo.getProizvodjac() + " " + vozilo.getModel() + " " + vozilo.getBrRegistarskeOznake());
            }
        }
    }

    public static void sacuvajAutomobilUFajl(Vozilo vozilo) throws IOException {
        File file = new File("src/Data/vozila.csv");
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(vozilo.stringToSave());
        fileWriter.close();
    }

    public static void prikaziAutomobile() {
        List<Vozilo> automobili = Vozilo.ucitajSveAutomobile();
        for (Vozilo vozilo : automobili) {
            if (!vozilo.isObrisan()) {
                System.out.println(vozilo);
            }
        }
    }

    public static void prikaziAutomobileBezVozaca() {
        List<Vozilo> automobili = Vozilo.ucitajSveAutomobile();
        for (Vozilo vozilo : automobili) {
            if (!vozilo.isObrisan() && vozilo.getVozacId() == null) {
                System.out.println(vozilo);
            }
        }
    }

    public static Vozilo nadjiPoBrojuRegistarskeOznake(String brojRegistarskeOznake) {
        List<Vozilo> vozila = Vozilo.ucitajSveAutomobile();
        for (Vozilo vozilo : vozila) {
            if (vozilo.getBrRegistarskeOznake().equals(brojRegistarskeOznake)) {
                return vozilo;
            }
        }
        return null;

    }

    public static void sacuvajListuAutomobilaUFajl(List<Vozilo> automobili) {
        File file = new File("src\\Data\\vozila.csv");
        try {
            PrintWriter writer = new PrintWriter(file);
            for (Vozilo vozilo : automobili) {
                writer.write(vozilo.stringToSave());
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nepostojeći fajl");
        }
    }

    public static void izbrisiAutomobil() throws IOException {
        prikaziAutomobileBezVozaca();
        Scanner sc = new Scanner(System.in);
        System.out.println("Izaberi broj taksi vozila: ");
        String brTaksiVozila = sc.nextLine();
        Vozilo voziloZaBrisanje = Vozilo.pronadjiPoBrojuTaksiVozila(brTaksiVozila);
        List<Vozilo> automobili = ucitajSveAutomobile();
        for (Vozilo a : automobili) {
            if (voziloZaBrisanje != null && a.getVozacId() == null && !a.isObrisan() && a.getBrTaksiVozila().equals(voziloZaBrisanje.getBrTaksiVozila())) {
                System.out.println("Izbrisan");
                a.setObrisan(true);
                sacuvajListuAutomobilaUFajl(automobili);
                break;
            }
        }

    }

    public static Vozilo izaberiAutomobil() {
        prikaziAutomobile();
        Scanner sc = new Scanner(System.in);
        System.out.println("Izaberite broj taksi vozila: ");
        Vozilo vozilo = pronadjiPoBrojuTaksiVozila(sc.nextLine());
        return vozilo;
    }

    public static void izmeniAutomobil() throws IOException {
        Vozilo vozilo = izaberiAutomobil();
        izmeniBrojRegistarskeOznake(vozilo);
        List<Vozilo> automobili = ucitajSveAutomobile();
        for (Vozilo a : automobili) {
            if (a.getBrTaksiVozila().equals(vozilo.getBrTaksiVozila())) {
                a.setBrRegistarskeOznake(vozilo.getBrRegistarskeOznake());
            }
        }
        sacuvajListuAutomobilaUFajl(automobili);
    }

    private static void dodajAutomobiluVozaca(Vozilo vozilo) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Vozac> vozaci = Vozac.vozaciBezAutomobila();
        if (!vozaci.isEmpty()) {
            for (Vozac vozac : vozaci) {
                if (vozac.getRegOznakaVozila() == null) {
                    System.out.println(vozac);
                }
            }
            System.out.println("Izaberite JMBG vozaca kojem dodeljujete automobil: ");
            String JMBG = sc.nextLine();
            Vozac vozac;
            if ((vozac = Vozac.pronadjiPoJMBG(Long.parseLong(JMBG))) != null) {
                vozilo.setVozacId(vozac.getJMBG());
                System.out.println("Uspesno ste dodali vozaca");
            }
        } else {
            vozilo.setVozacId(null);
            System.out.println("Vozac ne postoji.");
        }
    }

    public static void dodajAutomobil() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Broj taksi vozila: ");
        String brTaksiVozila = sc.nextLine();
        System.out.println("Model: ");
        String model = sc.nextLine();
        System.out.println("Proizvodjac: ");
        String proizvodjac = sc.nextLine();
        System.out.println("Godina proizvodnje: ");
        int godinaProizvodnje = Integer.parseInt(sc.nextLine());
        System.out.println("Registarska oznaka: ");
        String regOznaka = sc.nextLine();
        System.out.println("Vrsta: ");
        String vrsta = sc.nextLine();
        Vozilo vozilo = new Vozilo(brTaksiVozila, model, proizvodjac, godinaProizvodnje, regOznaka, vrsta, false);
        System.out.println("Da li zelite da dodate vozaca ovom automobilu? [Y/N]");
        if (sc.nextLine().equals("Y")) {
            dodajAutomobiluVozaca(vozilo);
        }
        Vozilo.sacuvajAutomobilUFajl(vozilo);
    }

    @Override
    public String toString() {
        return "Vozilo{" +
               "brTaksiVozila='" + brTaksiVozila + '\'' +
               ", model='" + model + '\'' +
               ", proizvodjac='" + proizvodjac + '\'' +
               ", godProizvodnje=" + godProizvodnje +
               ", brRegistarskeOznake='" + brRegistarskeOznake + '\'' +
               ", vrsta='" + vrsta + '\'' +
               ", vozac=" + vozacId +
               '}';
    }

    public String stringToSave() {
        return brTaksiVozila + ',' + model + ',' + proizvodjac + ',' + godProizvodnje + ',' + brRegistarskeOznake + ',' + vrsta + ',' + obrisan + ',' + vozacId + '\n';
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }

    public String getBrTaksiVozila() {
        return brTaksiVozila;
    }

    public void setBrTaksiVozila(String brTaksiVozila) {
        this.brTaksiVozila = brTaksiVozila;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public int getGodProizvodnje() {
        return godProizvodnje;
    }

    public void setGodProizvodnje(int godProizvodnje) {
        this.godProizvodnje = godProizvodnje;
    }

    public String getBrRegistarskeOznake() {
        return brRegistarskeOznake;
    }

    public void setBrRegistarskeOznake(String brRegistarskeOznake) {
        this.brRegistarskeOznake = brRegistarskeOznake;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public void setVozacId(Long vozacId) {
        this.vozacId = vozacId;
    }

    public Long getVozacId() {
        return vozacId;
    }
}