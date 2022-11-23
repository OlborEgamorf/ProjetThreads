//package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws InterruptedException {





        InteractionMenu menu = new InteractionMenu();
        while (!menu.isDone()) {
            Thread.sleep(500);
        }
        menu.dispose();

        int largeur = menu.getLargeur();
        int longueur = menu.getLongueur();
        int profondeur = 23;

        Meteo meteo = menu.getMeteo();
        int vent = menu.getVent();
        int mer = menu.getMer();
        int nbMax = menu.getPersonne();
        
        Coeff coefficient = new Coeff();
        Plage plage = new Plage(longueur, largeur, profondeur, 1, vent, mer, nbMax, meteo, coefficient);

        Interface interfaced = new Interface(plage);
        BarreVitesse swingControlDemo = new BarreVitesse(coefficient);
        swingControlDemo.showSliderDemo();

        plage.startAll();

        while(true){
            plage.turn();
            interfaced.turn();
            Thread.sleep(10);
        }
    }}
