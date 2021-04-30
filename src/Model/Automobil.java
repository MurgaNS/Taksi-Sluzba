package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    public Automobil(String brTaksiVozila, String model, String proizvodjac, int godProizvodnje, String brRegistarskeOznake, String vrsta, Long vozac) {
        this.brTaksiVozila = brTaksiVozila;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godProizvodnje = godProizvodnje;
        this.brRegistarskeOznake = brRegistarskeOznake;
        this.vrsta = vrsta;
        this.vozac = vozac;
    }

    public Automobil(String brTaksiVozila, String model, String proizvodjac, int godProizvodnje, String brRegistarskeOznake, String vrsta) {
        this.brTaksiVozila = brTaksiVozila;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godProizvodnje = godProizvodnje;
        this.brRegistarskeOznake = brRegistarskeOznake;
        this.vrsta = vrsta;
    }

    protected static List<Automobil> ucitajSveAutomobile() {
        List<Automobil> automobili = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/automobili.csv"));
            while ((red = bf.readLine()) != null) {
                String[] tmp = red.split(",");
                Automobil automobil = null;
                try {
                    automobil = new Automobil(tmp[0], tmp[1], tmp[2], Integer.parseInt(tmp[3]), tmp[4], tmp[5], Long.parseLong(tmp[6]));
                } catch (NumberFormatException e) {
                    automobil = new Automobil(tmp[0], tmp[1], tmp[2], Integer.parseInt(tmp[3]), tmp[4], tmp[5]);
                }
                automobili.add(automobil);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return automobili;
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

    public static void sacuvajAutomobilUFajl(Automobil automobil) throws IOException {
        File file = new File("src/Data/automobili.csv");
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(automobil.stringToSave());
        fileWriter.close();
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
        return brTaksiVozila + ',' + model + ',' + proizvodjac + ',' + godProizvodnje + ',' + brRegistarskeOznake + ',' + vrsta + ',' + vozac + '\n';
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