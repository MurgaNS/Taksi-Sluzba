import Model.Dispecer;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class App {
    public static void main(String[] args) {
//        login
//        dispecer
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("1.Prikazi podatke o taksi sluzbi");
                System.out.println("2.Izmeni podatke o taksi sluzbi");
                System.out.print("Unesi opciju: ");
                String line = sc.nextLine();
                switch (Integer.parseInt(line)) {
                    case 1 -> Dispecer.prikazPodatakaOTaksiSluzbi();
                    case 2 -> Dispecer.izmenaPodatakaTaksiSluzbe();
                }
            }
        } catch (IllegalStateException | NoSuchElementException | IOException e) {
            System.out.println("Doslo je do greske! System.in je zatvoren.");
        }
    }
}
