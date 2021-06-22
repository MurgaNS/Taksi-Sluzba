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

    public static void ispisiSvaSlobodnaVozila() {
        List<Vozilo> vozila = ucitajSvaVozila();
        for (Vozilo vozilo : vozila) {
            if (vozilo.getVozacId() == null) {
                System.out.println(vozilo.getProizvodjac() + " " + vozilo.getModel() + " " + vozilo.getBrRegistarskeOznake());
            }
        }
    }

    public static void sacuvajVoziloUFajl(Vozilo vozilo) throws IOException {
        List<Vozilo> vozila = ucitajSvaVozila();
        for (Vozilo v : vozila) {
            if (v.getBrTaksiVozila().equals(vozilo.getBrTaksiVozila())) {
                v.setBrRegistarskeOznake(vozilo.getBrRegistarskeOznake());
                v.setProizvodjac(vozilo.getProizvodjac());
                v.setModel(vozilo.getModel());
                v.setVrsta(vozilo.getVrsta());
                v.setObrisan(vozilo.isObrisan());
                v.setVozacId(vozilo.getVozacId());
                sacuvajListuVozilaUFajl(vozila);
                break;
            }
        }
        vozila.add(vozilo);
        sacuvajListuVozilaUFajl(vozila);
    }

    public static Vozilo pronadjiPoBrojuRegistarskeOznake(String brojRegistarskeOznake) {
        List<Vozilo> vozila = Vozilo.ucitajSvaVozila();
        for (Vozilo vozilo : vozila) {
            if (vozilo.getBrRegistarskeOznake().equals(brojRegistarskeOznake)) {
                return vozilo;
            }
        }
        return null;

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
//    private static void dodajAutomobiluVozaca(Vozilo vozilo) {
//        Scanner sc = new Scanner(System.in);
//        ArrayList<Vozac> vozaci = Vozac.vozaciBezAutomobila();
//        if (!vozaci.isEmpty()) {
//            for (Vozac vozac : vozaci) {
//                if (vozac.getRegOznakaVozila() == null) {
//                    System.out.println(vozac);
//                }
//            }
//            System.out.println("Izaberite JMBG vozaca kojem dodeljujete automobil: ");
//            String JMBG = sc.nextLine();
//            Vozac vozac;
//            if ((vozac = Vozac.pronadjiPoJMBG(Long.parseLong(JMBG))) != null) {
//                vozilo.setVozacId(vozac.getJMBG());
//                System.out.println("Uspesno ste dodali vozaca");
//            }
//        } else {
//            vozilo.setVozacId(null);
//            System.out.println("Vozac ne postoji.");
//        }
//    }
//    public static void dodajAutomobil() throws IOException {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Broj taksi vozila: ");
//        String brTaksiVozila = sc.nextLine();
//        System.out.println("Model: ");
//        String model = sc.nextLine();
//        System.out.println("Proizvodjac: ");
//        String proizvodjac = sc.nextLine();
//        System.out.println("Godina proizvodnje: ");
//        int godinaProizvodnje = Integer.parseInt(sc.nextLine());
//        System.out.println("Registarska oznaka: ");
//        String regOznaka = sc.nextLine();
//        System.out.println("Vrsta: ");
//        String vrstaTxt = sc.nextLine();
//        VrstaVozila vrsta;
//        if (vrstaTxt.trim().equals("AUTOMOBIL")) {
//            vrsta = Vozilo.VrstaVozila.AUTOMOBIL;
//        } else {
//            vrsta = Vozilo.VrstaVozila.KOMBI;
//        }
//        Vozilo vozilo = new Vozilo(Long.parseLong(brTaksiVozila), model, proizvodjac, godinaProizvodnje, regOznaka, vrsta, false);
//        System.out.println("Da li zelite da dodate vozaca ovom automobilu? [Y/N]");
//        if (sc.nextLine().equals("Y")) {
//            dodajAutomobiluVozaca(vozilo);
//        }
//        Vozilo.sacuvajAutomobilUFajl(vozilo);
//    }
//    public static void prikaziAutomobile() {
//        List<Vozilo> automobili = Vozilo.ucitajSveAutomobile();
//        for (Vozilo vozilo : automobili) {
//            if (!vozilo.isObrisan()) {
//                System.out.println(vozilo);
//            }
//        }
//    }
//        public static void izmeniBrojRegistarskeOznake(Vozilo vozilo) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Novi registarski broj vozila:");
//        String noviRegBroj = sc.nextLine();
//        vozilo.setBrRegistarskeOznake(noviRegBroj);
//    }
//
//    public static void prikaziAutomobileBezVozaca() {
//        List<Vozilo> automobili = Vozilo.ucitajSveAutomobile();
//        for (Vozilo vozilo : automobili) {
//            if (!vozilo.isObrisan() && vozilo.getVozacId() == null) {
//                System.out.println(vozilo);
//            }
//        }
//    }
//    public static void izbrisiAutomobil() throws IOException {
//        prikaziAutomobileBezVozaca();
//        List<Vozilo> listaVozila = Vozilo.ucitajSveAutomobile();
//
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Izaberi broj taksi vozila: ");
//        String brTaksiVozila = sc.nextLine();
//        Vozilo voziloZaBrisanje = Vozilo.pronadjiPoBrojuTaksiVozila(Long.parseLong(brTaksiVozila), listaVozila);
//        List<Vozilo> automobili = ucitajSveAutomobile();
//        for (Vozilo a : automobili) {
//            if (voziloZaBrisanje != null && a.getVozacId() == null && !a.isObrisan() && a.getBrTaksiVozila() == (voziloZaBrisanje.getBrTaksiVozila())) {
//                System.out.println("Izbrisan");
//                a.setObrisan(true);
//                sacuvajListuAutomobilaUFajl(automobili);
//                break;
//            }
//        }
//
//    }

//    public static Vozilo izaberiAutomobil() {
//        prikaziAutomobile();
//        List<Vozilo> listaVozila = Vozilo.ucitajSveAutomobile();
//
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Izaberite broj taksi vozila: ");
//        Vozilo vozilo = pronadjiPoBrojuTaksiVozila(Long.parseLong(sc.nextLine()), listaVozila);
//        return vozilo;
//    }

    //    moguce menjanje samo registarske oznake
//    public static void izmeniAutomobil() throws IOException {
//        Vozilo vozilo = izaberiAutomobil();
//        izmeniBrojRegistarskeOznake(vozilo);
//        List<Vozilo> automobili = ucitajSveAutomobile();
//        for (Vozilo a : automobili) {
//            if (a.getBrTaksiVozila() == (vozilo.getBrTaksiVozila())) {
//                a.setBrRegistarskeOznake(vozilo.getBrRegistarskeOznake());
//            }
//        }
//        sacuvajListuAutomobilaUFajl(automobili);
//    }
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