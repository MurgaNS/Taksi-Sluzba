package Model;

import java.io.*;

import BinarnaPretraga.BinarnaPretraga;
import StrukturePodataka.ArrayList;

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

    public static ArrayList<Vozilo> ucitajNeobrisanaVozila() {
        ArrayList<Vozilo> vozila = new ArrayList<>();
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
        ArrayList<Vozilo> vozila = ucitajSvaVozila();
        if (vozila.isEmpty()) {
            return 1;
        }
        return vozila.get(vozila.size() - 1).getBrTaksiVozila() + 1;
    }

    public static ArrayList<Long> listaIdVozila(ArrayList<Vozilo> listaVozila) {
        ArrayList<Long> listaId = new ArrayList<>();
        for (Vozilo vozilo : listaVozila) {
            listaId.add(vozilo.getBrTaksiVozila());
        }
        return listaId;
    }

    public static Vozilo pretraziPoId(ArrayList<Vozilo> listaVozila, Long id) {
        return listaVozila.get(BinarnaPretraga.find(listaIdVozila(listaVozila), id));
    }

    public static ArrayList<Vozilo> ucitajSvaVozila() {
        ArrayList<Vozilo> vozila = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/vozila.csv"));
            while ((red = bf.readLine()) != null) {
                Vozilo vozilo = voziloDTO(red);
                vozila.add(vozilo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vozila;
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
        String model = lineParts[1];
        String proizvodjac = lineParts[2];
        int godinaProizvodnje = Integer.parseInt(lineParts[3]);
        String brRegOznake = lineParts[4];
        Boolean obrisan = Boolean.parseBoolean(lineParts[6]);
        try {
            Long vozacId = Long.parseLong(lineParts[7]);
            vozilo = new Vozilo(brTaksiVozila, model, proizvodjac, godinaProizvodnje, brRegOznake, vrsta, obrisan, vozacId);
        } catch (NumberFormatException e) {
            vozilo = new Vozilo(brTaksiVozila, model, proizvodjac, godinaProizvodnje, brRegOznake, vrsta, obrisan);

        }
        return vozilo;
    }

    public static boolean voziloPripadaVozacu(Vozac vozac, Vozilo vozilo) {
        ArrayList<Vozilo> listaVozila = ucitajSvaVozila();
        for (Vozilo v : listaVozila) {
            if (v.getVozacId() != null) {
                if (v.getBrTaksiVozila().equals(vozilo.getBrTaksiVozila()) && v.getVozacId().equals(vozac.getJMBG())) {
                    return true;
                }
            }
        }
        return false;
    }


    public static Long pronadjiVoziloPoVozacu(Long vozacId) {
        ArrayList<Vozilo> listaVozila = ucitajSvaVozila();
        try {
            for (Vozilo v : listaVozila) {
                if (v.getVozacId() != null && v.getVozacId().equals(vozacId)) {
                    return v.getBrTaksiVozila();
                }
            }
        }catch (NullPointerException ignored){
//            kada vozilo nema vozaca izbaci gresku i pukne program
        }
        return null;
    }

    public static Vozilo pronadjiPoBrojuTaksiVozila(Long brTaksiVozila, ArrayList<Vozilo> listaVozila) {
        for (Vozilo vozilo :
                listaVozila) {
            if (vozilo.getBrTaksiVozila().equals(brTaksiVozila)) {
                return vozilo;
            }
        }
        return null;
    }

    public static ArrayList<Vozilo> pretragaPoModelu(String model) {
        ArrayList<Vozilo> listaVozila = ucitajNeobrisanaVozila();
        ArrayList<Vozilo> pronadjenaVozila = new ArrayList<>();
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.getModel().toLowerCase().contains(model.toLowerCase())) {
                pronadjenaVozila.add(vozilo);
            }
        }
        return pronadjenaVozila;
    }

    public static ArrayList<Vozilo> pretragaPoProizvodjacu(String proizvodjac) {
        ArrayList<Vozilo> listaVozila = ucitajNeobrisanaVozila();
        ArrayList<Vozilo> pronadjenaVozila = new ArrayList<>();
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.getProizvodjac().toLowerCase().contains(proizvodjac.toLowerCase())) {
                pronadjenaVozila.add(vozilo);
            }
        }
        return pronadjenaVozila;
    }

    public static ArrayList<Vozilo> pretragaPoGodiniProizvodnje(int godinaProizvodnje) {
        ArrayList<Vozilo> listaVozila = ucitajNeobrisanaVozila();
        ArrayList<Vozilo> pronadjenaVozila = new ArrayList<>();
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.getGodProizvodnje() == godinaProizvodnje) {
                pronadjenaVozila.add(vozilo);
            }
        }
        return pronadjenaVozila;
    }

    public static ArrayList<Vozilo> pretragaPoBrojuRegOznake(String brojRegOznake) {
        ArrayList<Vozilo> listaVozila = ucitajNeobrisanaVozila();
        ArrayList<Vozilo> pronadjenaVozila = new ArrayList<>();
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.getBrRegistarskeOznake().toLowerCase().contains(brojRegOznake.toLowerCase())) {
                pronadjenaVozila.add(vozilo);
            }
        }
        return pronadjenaVozila;
    }

    public static void sacuvajVoziloUFajl(Vozilo vozilo) throws IOException {
        ArrayList<Vozilo> vozila = ucitajSvaVozila();
        for(Vozilo v: vozila){
            if(v.getBrTaksiVozila().equals(vozilo.getBrTaksiVozila())){
                v.setVozacId(vozilo.getVozacId());
                v.setVrsta(vozilo.getVrsta());
                v.setModel(vozilo.getModel());
                v.setProizvodjac(vozilo.getProizvodjac());
                v.setObrisan(vozilo.isObrisan());
                v.setBrRegistarskeOznake(vozilo.getBrRegistarskeOznake());
                v.setGodProizvodnje(vozilo.getGodProizvodnje());
                sacuvajListuVozilaUFajl(vozila);
                break;
            }
        }
        vozila.add(vozilo);
        sacuvajListuVozilaUFajl(vozila);
    }

    public static void sacuvajListuVozilaUFajl(ArrayList<Vozilo> vozila) {
        File file = new File("src\\Data\\vozila.csv");
        try {
            PrintWriter writer = new PrintWriter(file);
            for (Vozilo vozilo : vozila) {
                writer.write(vozilo.stringToSave());
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nepostoje??i fajl");
        }
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