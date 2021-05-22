package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Automobil {

    private String brTaksiVozila;
    private String model;
    private String proizvodjac;
    private int godProizvodnje;
    private String brRegistarskeOznake;
    private String vrsta;
    private Long vozac;
    private boolean obrisan;

    public Automobil() {
    }

    public Automobil(String brTaksiVozila, String model, String proizvodjac, int godProizvodnje, String brRegistarskeOznake, String vrsta, Boolean obrisan, Long vozac) {
        this.brTaksiVozila = brTaksiVozila;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godProizvodnje = godProizvodnje;
        this.brRegistarskeOznake = brRegistarskeOznake;
        this.vrsta = vrsta;
        this.obrisan = obrisan;
        this.vozac = vozac;
    }

    public Automobil(String brTaksiVozila, String model, String proizvodjac, int godProizvodnje, String brRegistarskeOznake, String vrsta, Boolean obrisan) {
        this.brTaksiVozila = brTaksiVozila;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godProizvodnje = godProizvodnje;
        this.brRegistarskeOznake = brRegistarskeOznake;
        this.obrisan = obrisan;
        this.vrsta = vrsta;
    }

    public static void izmeniBrojRegistarskeOznake(Automobil automobil) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Novi registarski broj vozila:");
        String noviRegBroj = sc.nextLine();
        automobil.setBrRegistarskeOznake(noviRegBroj);
    }

    protected static List<Automobil> ucitajSveAutomobile() {
        List<Automobil> automobili = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/automobili.csv"));
            while ((red = bf.readLine()) != null) {
                Automobil automobil = automobilDTO(red);
                automobili.add(automobil);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return automobili;
    }

    public static Automobil automobilDTO(String automobilString) {
        String[] wordsList = automobilString.split(",");
        Automobil automobil;
        try {
            automobil = new Automobil(wordsList[0], wordsList[1], wordsList[2], Integer.parseInt(wordsList[3]), wordsList[4], wordsList[5], Boolean.parseBoolean(wordsList[6]), Long.parseLong(wordsList[7]));
        } catch (NumberFormatException e) {
            automobil = new Automobil(wordsList[0], wordsList[1], wordsList[2], Integer.parseInt(wordsList[3]), wordsList[4], wordsList[5], Boolean.parseBoolean(wordsList[6]));

        }
        return automobil;
    }

    public static Automobil pronadjiPoBrojuTaksiVozila(String brTaksiVozila) {
        List<Automobil> automobili = ucitajSveAutomobile();
        for (Automobil automobil :
                automobili) {
            if (automobil.getBrTaksiVozila().equals(brTaksiVozila)) {
                return automobil;
            }
        }
        return null;
    }

    public static void ispisiSvaSlobodnaVozila() {
        List<Automobil> automobili = ucitajSveAutomobile();
        for (Automobil automobil : automobili) {
            if (automobil.getVozac() == null) {
                System.out.println(automobil.getProizvodjac() + " " + automobil.getModel() + " " + automobil.getBrRegistarskeOznake());
            }
        }
    }

    public static void sacuvajAutomobilUFajl(Automobil automobil) throws IOException {
        File file = new File("src/Data/automobili.csv");
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(automobil.stringToSave());
        fileWriter.close();
    }

    public static void prikaziAutomobile() throws IOException {
        List<Automobil> automobili = Automobil.ucitajSveAutomobile();
        for (Automobil automobil : automobili) {
            if (!automobil.isObrisan()) {
                System.out.println(automobil);
            }
        }
    }

    public static void sacuvajListuAutomobilaUFajl(List<Automobil> automobili) {
        File file = new File("src\\Data\\automobili.csv");
        try {
            PrintWriter writer = new PrintWriter(file);
            for (Automobil automobil : automobili) {
                writer.write(automobil.stringToSave());
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nepostojeći fajl");
        }
    }

    public static void izbrisiAutomobil(Automobil automobilZaBrisanje) {
        List<Automobil> automobili = ucitajSveAutomobile();
        for (Automobil automobil : automobili) {
            if (automobil.getVozac() != null && !automobil.isObrisan()) {
                if (automobil.getBrTaksiVozila().equals(automobilZaBrisanje.getBrTaksiVozila())) {
                    System.out.println("Izbrisan");
                    automobil.setObrisan(true);
                    sacuvajListuAutomobilaUFajl(automobili);
                    break;
                }
            }
        }
    }

    public static Automobil izaberiAutomobil() {
        try {
            prikaziAutomobile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Izaberite broj taksi vozila: ");
        Automobil automobil = pronadjiPoBrojuTaksiVozila(sc.nextLine());
        return automobil;
    }

    public static void izmeniAutomobil() throws IOException {
        Automobil automobil = izaberiAutomobil();
        izmeniBrojRegistarskeOznake(automobil);
        List<Automobil> automobili = ucitajSveAutomobile();
        for (Automobil a : automobili) {
            if (a.getBrTaksiVozila().equals(automobil.getBrTaksiVozila())) {
                a.setBrRegistarskeOznake(automobil.getBrRegistarskeOznake());
            }
        }
        sacuvajListuAutomobilaUFajl(automobili);
    }


    @Override
    public String toString() {
        return "Automobil{" +
                "brTaksiVozila='" + brTaksiVozila + '\'' +
                ", model='" + model + '\'' +
                ", proizvodjac='" + proizvodjac + '\'' +
                ", godProizvodnje=" + godProizvodnje +
                ", brRegistarskeOznake='" + brRegistarskeOznake + '\'' +
                ", vrsta='" + vrsta + '\'' +
                ", vozac=" + vozac +
                '}';
    }

    public String stringToSave() {
        return brTaksiVozila + ',' + model + ',' + proizvodjac + ',' + godProizvodnje + ',' + brRegistarskeOznake + ',' + vrsta + ',' + obrisan + ',' + vozac + '\n';
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

    public void setVozac(Long vozac) {
        this.vozac = vozac;
    }

    public Long getVozac() {
        return vozac;
    }
}