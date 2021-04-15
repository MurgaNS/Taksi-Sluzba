package Model;

import java.util.*;


public class Dispecer extends Korisnik {


    protected double plata;
    protected String brojTelefonskeLinije;
    protected String odeljenjeRada;

    public Dispecer() {
    }

    public Dispecer(double plata, String brojTelefonskeLinije, String odeljenjeRada) {
        this.plata = plata;
        this.brojTelefonskeLinije = brojTelefonskeLinije;
        this.odeljenjeRada = odeljenjeRada;
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

    public String getOdeljenjeRada() {
        return odeljenjeRada;
    }

    public void setOdeljenjeRada(String odeljenjeRada) {
        this.odeljenjeRada = odeljenjeRada;
    }

    protected void prikazPodataka() {
    }

    protected void prikazPodatakaOTaksiSluzbi() {
    }

    protected boolean izmenaPodatakaTaksiSluzbe(TaksiSluzba taksiSluzba) {
        return false;
    }

    protected void prikaziVozace(List<Vozac> vozaci) {
    }

    protected boolean dodajVozaca(Vozac vozac) {
        return false;
    }

    protected boolean izmeniVozaca(Vozac vozac) {
        return false;
    }

    protected boolean izbrisiVozaca(Vozac vozac) {
        return false;
    }

    protected void prikaziAutomobile(List<Automobil> automobili) {
    }

    protected boolean dodajAutomobil(Automobil automobil) {
        return false;
    }

    protected boolean izmeniAutomobil(Automobil automobil) {
        return false;
    }

    protected boolean izbrisiAutomobil(Automobil automobil) {
        return false;
    }

    protected void prikazSvihVoznji(List<Voznja> listaVoznji) {
    }

    protected boolean dodeljivanjeVoznje(Vozac vozac, Voznja voznja) {
        return false;
    }

    protected List<Vozac> pretragaVozaca(String kriterijum) {
        return null;
    }

    protected List<Automobil> pretragaAutomobila(String kriterijum) {
        return null;
    }

    protected void dnevniIzvestaj() {
    }

    protected void nedeljniIzvestaj() {
    }

    protected void mesecniIzvestaj() {
    }

    protected void godisnjiIzvestaj() {
    }

    protected void dnevniIzvestajVozaca(Vozac vozac) {
    }

    protected void nedeljniIzvestajVozaca(Vozac vozac) {
    }

    protected void mesecniIzvestajVozaca(Vozac vozac) {
    }

    protected void godisnjiIzvestajVozaca(Vozac vozac) {
    }

}