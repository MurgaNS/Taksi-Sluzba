import Model.*;

import java.io.*;

public class Login {
    public Korisnik login(String korisnickoIme, String lozinka) {
        Korisnik korisnik = null;
        File file = new File("src\\Data\\korisnici.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineParts = line.split(",");
                if (korisnickoIme.equals(lineParts[2]) && lozinka.equals(lineParts[3])) {
                    Korisnik.Pol pol;
                    if (lineParts[7].trim().equals("MUSKI")) {
                        pol = Korisnik.Pol.MUSKI;
                    } else {
                        pol = Korisnik.Pol.ZENSKI;
                    }
                    switch (lineParts[0]) {
                        case "musterija" -> korisnik = new Musterija(Long.parseLong(lineParts[1]), lineParts[2], lineParts[3], lineParts[4], lineParts[5], lineParts[6], pol, lineParts[8], false);
                        case "vozac" -> korisnik = new Vozac(Long.parseLong(lineParts[1]), lineParts[2], lineParts[3], lineParts[4], lineParts[5], lineParts[6], pol, lineParts[8], false, Double.parseDouble(lineParts[10]), Integer.parseInt(lineParts[11]));
                        case "dispecer" -> {
                            Dispecer.OdeljenjeRada odeljenjeRada;
                            if (lineParts[11].trim().equals("PRIJEM_VOZNJE")) {
                                odeljenjeRada = Dispecer.OdeljenjeRada.PRIJEM_VOZNJE;
                            } else {
                                odeljenjeRada = Dispecer.OdeljenjeRada.REKLAMACIJE;
                            }
                            korisnik = new Dispecer(Long.parseLong(lineParts[1]), lineParts[2], lineParts[3], lineParts[4], lineParts[5], lineParts[6], pol, lineParts[8], false, Double.parseDouble(lineParts[10]), lineParts[11], odeljenjeRada);
                        }
                    }
                    System.out.println("Uspesno ste se ulogovali");
                }
            }
            bufferedReader.close();

        } catch (FileNotFoundException exception) {
            System.out.println("Fajl nije pronadjen");
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Greska pri citanju datoteke");
        }
        Vozac.prijavljeniKorisnik = korisnik;
        return korisnik;
    }
}
