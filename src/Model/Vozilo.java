package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Vozilo {

    private Long brTaksiVozila;
    private String model;
    private String proizvodjac;
    private int godProizvodnje;
    private String brRegistarskeOznake;
    private VrstaVozila vrsta;
    private Long vozacId;
    private boolean obrisan;

    public enum VrstaVozila {
        AUTOMOBIL,
        KOMBI
    }

    public Vozilo(Long brTaksiVozila, String model, String proizvodjac, int godProizvodnje, String brRegistarskeOznake, VrstaVozila vrsta, Boolean obrisan, Long vozacId) {
        this.brTaksiVozila = brTaksiVozila;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godProizvodnje = godProizvodnje;
        this.brRegistarskeOznake = brRegistarskeOznake;
        this.vrsta = vrsta;
        this.obrisan = obrisan;
        this.vozacId = vozacId;
    }

    public Vozilo(Long brTaksiVozila, String model, String proizvodjac, int godProizvodnje, String brRegistarskeOznake, VrstaVozila vrsta, Boolean obrisan) {
        this.brTaksiVozila = brTaksiVozila;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godProizvodnje = godProizvodnje;
        this.brRegistarskeOznake = brRegistarskeOznake;
        this.obrisan = obrisan;
        this.vrsta = vrsta;
    }

    public static List<Vozilo> ucitajNeobrisanaVozila() {
        List<Vozilo> vozila = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/vozila.csv"));
            while ((red = bf.readLine()) != null) {
                Vozilo vozilo = voziloDTO(red);
                if (!vozilo.isObrisan()) {
                    vozila.add(vozilo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vozila;
    }

    public static long generisiIdVozila() {
        List<Vozilo> vozila = ucitajSvaVozila();
        return vozila.get(vozila.size() - 1).getBrTaksiVozila() + 1;
    }

    public static List<Vozilo> ucitajSvaVozila() {
        List<Vozilo> automobili = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/vozila.csv"));
            while ((red = bf.readLine()) != null) {
                Vozilo vozilo = voziloDTO(red);
                automobili.add(vozilo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return automobili;
    }

    public static VrstaVozila ucitajVrstuVozila(String vrstaVozila) {
        if (vrstaVozila.trim().equals("AUTOMOBIL")) {
            return Vozilo.VrstaVozila.AUTOMOBIL;
        } else if (vrstaVozila.trim().equals("KOMBI")) {
            return Vozilo.VrstaVozila.KOMBI;
        } else {
            return null;
        }
    }

    public static Vozilo voziloDTO(String voziloString) {
        String[] lineParts = voziloString.split(",");
        Vozilo vozilo;
        Long brTaksiVozila = Long.parseLong(lineParts[0]);
        VrstaVozila vrsta = ucitajVrstuVozila(lineParts[5]);

        try {
            vozilo = new Vozilo(brTaksiVozila, lineParts[1], lineParts[2], Integer.parseInt(lineParts[3]), lineParts[4], vrsta, Boolean.parseBoolean(lineParts[6]), Long.parseLong(lineParts[7]));
        } catch (NumberFormatException e) {
            vozilo = new Vozilo(brTaksiVozila, lineParts[1], lineParts[2], Integer.parseInt(lineParts[3]), lineParts[4], vrsta, Boolean.parseBoolean(lineParts[6]));

        }
        return vozilo;
    }

    public static Vozilo pronadjiPoBrojuTaksiVozila(Long brTaksiVozila, List<Vozilo> listaVozila) {
        for (Vozilo vozilo :
                listaVozila) {
            if (vozilo.getBrTaksiVozila().equals(brTaksiVozila)) {
                return vozilo;
            }
        }
        return null;
    }

    public static void sacuvajVoziloUFajl(Vozilo vozilo) throws IOException {
        List<Vozilo> vozila = ucitajSvaVozila();
        vozila.add(vozilo);
        sacuvajListuVozilaUFajl(vozila);
    }

    public static void sacuvajListuVozilaUFajl(List<Vozilo> vozila) {
        File file = new File("src\\Data\\vozila.csv");
        try {
            PrintWriter writer = new PrintWriter(file);
            for (Vozilo vozilo : vozila) {
                writer.write(vozilo.stringToSave());
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nepostojeći fajl");
        }
    }

    public static List<Vozilo> pretragaPoModelu(String model) {
        List<Vozilo> listaVozila = ucitajNeobrisanaVozila();
        List<Vozilo> pronadjenaVozila = new ArrayList<>();
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.getModel().toLowerCase().contains(model.toLowerCase())) {
                pronadjenaVozila.add(vozilo);
            }
        }
        return pronadjenaVozila;
    }

    public static List<Vozilo> pretragaPoProizvodjacu(String proizvodjac) {
        List<Vozilo> listaVozila = ucitajNeobrisanaVozila();
        List<Vozilo> pronadjenaVozila = new ArrayList<>();
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.getProizvodjac().toLowerCase().contains(proizvodjac.toLowerCase())) {
                pronadjenaVozila.add(vozilo);
            }
        }
        return pronadjenaVozila;
    }

    public static List<Vozilo> pretragaPoGodiniProizvodnje(int godinaProizvodnje) {
        List<Vozilo> listaVozila = ucitajNeobrisanaVozila();
        List<Vozilo> pronadjenaVozila = new ArrayList<>();
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.getGodProizvodnje() == godinaProizvodnje) {
                pronadjenaVozila.add(vozilo);
            }
        }
        return pronadjenaVozila;
    }

    public static List<Vozilo> pretragaPoBrojuRegOznake(String brojRegOznake) {
        List<Vozilo> listaVozila = ucitajNeobrisanaVozila();
        List<Vozilo> pronadjenaVozila = new ArrayList<>();
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.getBrRegistarskeOznake().toLowerCase().contains(brojRegOznake.toLowerCase())) {
                pronadjenaVozila.add(vozilo);
            }
        }
        return pronadjenaVozila;
    }

    public static List<Vozilo> pretragaPoBrojuTaksiVozila(Long brTaksiVozila) {
        List<Vozilo> listaVozila = ucitajNeobrisanaVozila();
        List<Vozilo> pronadjenaVozila = new ArrayList<>();
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.getBrTaksiVozila().equals(brTaksiVozila)) {
                pronadjenaVozila.add(vozilo);
            }
        }
        return pronadjenaVozila;
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
        return String.valueOf(brTaksiVozila) + ',' + model + ',' + proizvodjac + ',' + godProizvodnje + ',' + brRegistarskeOznake + ',' + vrsta + ',' + obrisan + ',' + vozacId + '\n';
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }

    public Long getBrTaksiVozila() {
        return brTaksiVozila;
    }

    public void setBrTaksiVozila(Long brTaksiVozila) {
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

    public VrstaVozila getVrsta() {
        return vrsta;
    }

    public void setVrsta(VrstaVozila vrsta) {
        this.vrsta = vrsta;
    }

    public void setVozacId(Long vozacId) {
        this.vozacId = vozacId;
    }

    public Long getVozacId() {
        return vozacId;
    }
}