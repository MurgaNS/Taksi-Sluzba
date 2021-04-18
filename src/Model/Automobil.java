package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Automobil {

    protected String brTaksiVozila;
    protected String model;
    protected String proizvodjac;
    protected int godProizvodnje;
    protected String brRegistarskeOznake;
    protected String vrsta;
    protected Vozac vozac;

    public Automobil() {
    }

    public Automobil(String brTaksiVozila, String model, String proizvodjac, int godProizvodnje, String brRegistarskeOznake, String vrsta, Vozac vozac) {
        this.brTaksiVozila = brTaksiVozila;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godProizvodnje = godProizvodnje;
        this.brRegistarskeOznake = brRegistarskeOznake;
        this.vrsta = vrsta;
        this.vozac = vozac;
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

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    protected static List<Automobil> ucitajSveAutomobile() {
        List<Automobil> automobili = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/automobili.csv"));
            while ((red = bf.readLine()) != null) {
                String[] tmp = red.split(",");
                Vozac vozac = Vozac.pronadjiPoJMBG(tmp[6]);
                vozac.setAutomobil(null);
                vozac.setListaVoznji(null);
                Automobil automobil = new Automobil(tmp[0], tmp[1], tmp[2], Integer.parseInt(tmp[3]), tmp[4], tmp[5], vozac);
                automobili.add(automobil);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return automobili;
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
}