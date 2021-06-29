import Model.*;

import java.io.*;

public class Login {
    public Korisnik login(String kIme, String kLozinka) {
        Korisnik korisnik = null;
        File file = new File("src\\Data\\korisnici.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineParts = line.split(",");
                if (kIme.equals(lineParts[2]) && kLozinka.equals(lineParts[3])) {
                    String uloga = lineParts[0];
                    long id = Long.parseLong(lineParts[1]);
                    long jmbg = Long.parseLong(lineParts[2]);
                    String korisnickoIme = lineParts[3];
                    String lozinka = lineParts[4];
                    String ime = lineParts[5];
                    String prezime = lineParts[6];
                    String adresa = lineParts[7];
                    Korisnik.Pol pol = Korisnik.ucitajPol(lineParts[8]);
                    String brojTelefona = lineParts[9];
                    boolean obrisan = Boolean.parseBoolean(lineParts[10]);
                    switch (uloga) {
                        case "musterija" -> korisnik = new Musterija(id, jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan);
                        case "vozac" -> {
                            double plata = Double.parseDouble(lineParts[11]);
                            int brClanskeKarte = Integer.parseInt(lineParts[12]);
                            double prosecnaOcena = Double.parseDouble(lineParts[13]);
                            korisnik = new Vozac(id, jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan, plata, brClanskeKarte, prosecnaOcena);
                        }
                        case "dispecer" -> {
                            double plata = Double.parseDouble(lineParts[11]);
                            String brTelLinije = lineParts[12];
                            Dispecer.OdeljenjeRada odeljenjeRada = Dispecer.ucitajOdeljenjeRada(lineParts[12]);
                            korisnik = new Dispecer(id, jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan, plata, brTelLinije, odeljenjeRada);
                        }
                    }
                }
            }
            bufferedReader.close();

        } catch (FileNotFoundException exception) {
            System.out.println("Fajl nije pronadjen");
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Greska pri citanju datoteke");
        }
        return korisnik;
    }
}
