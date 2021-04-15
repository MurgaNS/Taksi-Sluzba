package Model;

import java.util.*;

public class Vozac extends Korisnik {

    protected double plata;
    protected int brojClanskeKarte;
    protected Set<Voznja> listaVoznji;
    protected Automobil automobil;

    public Vozac() {
    }

    public Vozac(double plata, int brojClanskeKarte, Set<Voznja> listaVoznji, Automobil automobil) {
        this.plata = plata;
        this.brojClanskeKarte = brojClanskeKarte;
        this.listaVoznji = listaVoznji;
        this.automobil = automobil;
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public int getBrojClanskeKarte() {
        return brojClanskeKarte;
    }

    public void setBrojClanskeKarte(int brojClanskeKarte) {
        this.brojClanskeKarte = brojClanskeKarte;
    }

    public Set<Voznja> getListaVoznji() {
        return listaVoznji;
    }

    public void setListaVoznji(Set<Voznja> listaVoznji) {
        this.listaVoznji = listaVoznji;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    protected void prikazIstorijeSpostvenihVoznji() {
    }

    protected void prikazVoznjiPutemAplikacije() {
    }

    protected void prikazVoznjiPutemTelefona() {
    }

    protected boolean krajVoznje(double predjeniKilometri, double trajanjeVoznje) {
        return false;
    }

    protected void dnevniIzvestaj() {
    }

    protected void nedeljniIzvestaj() {
    }

    protected void mesecniIzvestaj() {
    }

    protected void godisnjiIzvestaj() {
    }

    protected void aukcija(int minutaDoDolaska) {
    }

}