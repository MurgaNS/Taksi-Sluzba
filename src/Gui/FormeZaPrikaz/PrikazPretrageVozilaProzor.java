package Gui.FormeZaPrikaz;

import Model.Vozilo;

import javax.swing.*;
import java.util.List;

public class PrikazPretrageVozilaProzor extends JFrame {
    List<Vozilo> listaVozila;

    public PrikazPretrageVozilaProzor(List<Vozilo> listaPronadjenihVozila) {
        listaVozila = listaPronadjenihVozila;
    }
}
