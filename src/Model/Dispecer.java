package Model;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class Dispecer extends Korisnik {

    private double plata;
    private String brojTelefonskeLinije;
    private OdeljenjeRada odeljenjeRada;

    public enum OdeljenjeRada {
        PRIJEM_VOZNJE,
        REKLAMACIJE
    }

    public Dispecer(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, double plata, String brojTelefonskeLinije, OdeljenjeRada odeljenjeRada) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona);
        this.plata = plata;
        this.brojTelefonskeLinije = brojTelefonskeLinije;
        this.odeljenjeRada = odeljenjeRada;
    }

    public String korisnikUString() {
        return "dispecer," + super.korisnikUString() + "," + plata + "," + brojTelefonskeLinije + "," + odeljenjeRada;
    }


    public static void prikaziMeni() {
        System.out.println("1. Prikaz podataka taksi službe");
        System.out.println("2. Izmena podataka o taksi službi");
        System.out.println("3. Prikaz vozača");
        System.out.println("4. Dodavanje vozača");
        System.out.println("5. Izmena vozača");
        System.out.println("6. Brisanje vozača");
        System.out.println("7. Dodavanje automobila");
        System.out.println("8. Prikaz automobila");
        System.out.println("9. Izmena automobila");
        System.out.println("10. Brisanje automobila");
        System.out.println("11. Prikaz voznji");
        System.out.println("12. Prikaz voznji dodeljenih putem telefona");
        System.out.println("13. Kombinovana pretraga vozaca");
        System.out.println("14. Izveštaj");


    }


    public static void izvestaj() {
        System.out.println("1. Dnevni izveštaj");
        System.out.println("2. Nedeljni izveštaj");
        System.out.println("3. Mesečni izveštaj");
        System.out.println("4. Godišnji izveštaj");
        Scanner scanner = new Scanner(System.in);
        int izbor = scanner.nextInt();
        switch (izbor) {
            case 1:
                dnevniIzvestaj();
                break;
            case 2:
                nedeljniIzvestaj();
                break;
            case 3:
                mesecniIzvestaj();
                break;
            case 4:
                godisnjiIzvestaj();
                break;
            default:
                System.out.println("Nepostojeća komanda");
                break;
        }


    }

    public static void godisnjiIzvestaj() {
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        List<Voznja> godisnjeVoznje = new ArrayList<>();

        Date date = new Date();
        for (Voznja voznja : voznje) {
            if ((voznja.getDatumPorudzbine().getYear() == date.getYear())) {
                godisnjeVoznje.add(voznja);
            }
        }

        ispisiIzvestaj(godisnjeVoznje);
    }


    public static void mesecniIzvestaj() {
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        List<Voznja> mesecneVoznje = new ArrayList<>();

        Date date = new Date();
        for (Voznja voznja : voznje) {
            if ((voznja.getDatumPorudzbine().getYear() == date.getYear()) && (voznja.getDatumPorudzbine().getMonth() == date.getMonth())) {
                mesecneVoznje.add(voznja);
            }
        }

        ispisiIzvestaj(mesecneVoznje);


    }

    public static void nedeljniIzvestaj() {
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        List<Voznja> nedeljneVoznje = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Date monday = c.getTime();

        Date nextMonday = new Date(monday.getTime() + 7 * 24 * 60 * 60 * 1000);
        for (Voznja voznja : voznje) {
            if (voznja.getDatumPorudzbine().after(monday) && voznja.getDatumPorudzbine().before(nextMonday)) {
                nedeljneVoznje.add(voznja);
            }
        }

        ispisiIzvestaj(nedeljneVoznje);


    }

    public static void dnevniIzvestaj() {
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        Date danasnjiDatum = new Date();
        Instant instant2 = danasnjiDatum.toInstant().truncatedTo(ChronoUnit.DAYS);
        List<Voznja> danasnjeVoznje = new ArrayList<>();
        for (Voznja voznja : voznje) {
            Instant instant1 = voznja.getDatumPorudzbine().toInstant().truncatedTo(ChronoUnit.DAYS);
            if (instant1.equals(instant2)) {
                danasnjeVoznje.add(voznja);
            }
        }
        ispisiIzvestaj(danasnjeVoznje);
    }


    public static void ispisiIzvestaj(List<Voznja> voznje) {
        System.out.println("Ukupan broj vožnji: " + voznje.size());
        int brojVoznjiPorucenihAplikacijom = 0;
        int brojVoznjiPorucenihTelefonom = 0;
        int brojAktivnihVozaca = 0;
        double ukupnoTrajanjeVoznji = 0;
        double ukupanBrojPredjenihKilometara = 0;
        double ukupnaZarada = 0;
        TaksiSluzba taksiSluzba = TaksiSluzba.preuzmiPodatkeOTaksiSluzbi();

        for (Voznja voznja : voznje) {
            if (voznja.getNacinPorudzbine().equals("APLIKACIJOM")) {
                brojVoznjiPorucenihAplikacijom++;
            }
            if (voznja.getNacinPorudzbine().equals("TELEFONOM")) {
                brojVoznjiPorucenihTelefonom++;
            }
            if (voznja.getVozacId() != null) {
                brojAktivnihVozaca++;
            }
            ukupnoTrajanjeVoznji += voznja.getTrajanjeVoznjeUMinutama();
            ukupanBrojPredjenihKilometara += voznja.getBrojPredjenihKilometara();
            ukupnaZarada += taksiSluzba.getCenaStarta() + voznja.getBrojPredjenihKilometara() * taksiSluzba.getCenaPoKilometru();

        }
        System.out.println("Broj vožnji poručenih aplikacijom: " + brojVoznjiPorucenihAplikacijom);
        System.out.println("Broj vožnji poručenih telefonom: " + brojVoznjiPorucenihTelefonom);
        System.out.println("Broj aktivnih vozača: " + brojAktivnihVozaca);

        double prosecnoTrajanjeVoznji = ukupnoTrajanjeVoznji / voznje.size();
        double prosecanBrojPredjenihKilometara = ukupanBrojPredjenihKilometara / voznje.size();
        System.out.println("Prosečno trajanje vožnji: " + prosecnoTrajanjeVoznji);
        System.out.println("Prosečan broj pređenih kilometara: " + prosecanBrojPredjenihKilometara);

        System.out.println("Ukupna zarada za sve vožnje " + ukupnaZarada);

        double prosecnaZarada = ukupnaZarada / voznje.size();

        System.out.println("Prosečna zarada po vožnji: " + prosecnaZarada);
    }


    // 2.2.5
    public static void dodeljivanjeVoznjiKreiranihTelefonom() {
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        for (Voznja voznja : voznje) {
            if (voznja.getNacinPorudzbine().equals("TELEFONOM")) {
                System.out.println(voznja);
            }
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Unesi id vožnje");
        long idVoznje = scanner.nextLong();

        Voznja voznja = null;
        for (Voznja voznja2 : voznje) {
            if (voznja2.getId() == idVoznje) {
                voznja = voznja2;
            }
        }

        System.out.println("Unesi jmbg vozača: ");
        long jmbg = scanner.nextLong();
        List<Korisnik> korisnici = Korisnik.ucitajSveKorisnike();

        Vozac vozac = null;
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getJMBG() == jmbg) {
                vozac = (Vozac) korisnik;
            }
        }
        System.out.println(vozac);
//        @murga
//        bilo je setvozacId(vozac), dodao sam .getJMBG jer se sad prosledjuje Long
        voznja.setVozacId(vozac.getJMBG());
        voznja.setStatusVoznje(Voznja.StatusVoznje.DODELJENA);
        Voznja.upisiVoznje(voznje);

        System.out.println("Uspešno dodeljena vožnja");

    }


    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public String getBrojTelefonskeLinije() {
        return brojTelefonskeLinije;
    }

    public void setBrojTelefonskeLinije(String brojTelefonskeLinije) {
        this.brojTelefonskeLinije = brojTelefonskeLinije;
    }

    public OdeljenjeRada getOdeljenjeRada() {
        return odeljenjeRada;
    }

    public void setOdeljenjeRada(OdeljenjeRada odeljenjeRada) {
        this.odeljenjeRada = odeljenjeRada;
    }

}