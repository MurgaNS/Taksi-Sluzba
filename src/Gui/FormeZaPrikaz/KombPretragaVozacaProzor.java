package Gui.FormeZaPrikaz;

import Model.Vozac;
import Model.Vozilo;

import javax.swing.*;
import java.util.List;

public class KombPretragaVozacaProzor extends JFrame {
    List<Vozac> listaVozaca;

    public KombPretragaVozacaProzor(List<Vozac> listaPronadjenihVozaca) {
        listaVozaca = listaPronadjenihVozaca;
    }
}