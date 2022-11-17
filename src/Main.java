//package src;

<<<<<<< Updated upstream
=======
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;


>>>>>>> Stashed changes
public class Main {
    static ArrayList<String> Donnee = new ArrayList<>();

    public static ArrayList<String> getDonnee() {
        return Donnee;
    }

    public static void main(String[] args) throws InterruptedException {
        {
            try
            {
                // Le fichier d'entrée
                File file = new File("texte.txt");
                // Créer l'objet File Reader
                FileReader fr = new FileReader(file);
                // Créer l'objet BufferedReader
                BufferedReader br = new BufferedReader(fr);
                StringBuffer sb = new StringBuffer();
                String line;
                while((line = br.readLine()) != null)
                {
                    // ajoute la ligne au buffer
                    sb.append(line);
                    Donnee.add(String.valueOf((line)));
                    sb.append("\n");
                }
                fr.close();
                System.out.println("Contenu du fichier: ");
                System.out.println(sb.toString());
                System.out.println(Donnee);




            }
            catch(IOException e)
            {
                e.printStackTrace();
            }


        InteractionMenu menu = new InteractionMenu();
        while (!menu.isDone()) {
            Thread.sleep(500);
        }
        menu.dispose();

        int largeur = menu.getLargeur();
        int longueur = menu.getLongueur();
        int profondeur = 23;
        int meteo = menu.getMeteo();
        int vent = menu.getVent();
        int mer = menu.getMer();
        int vitesse = 20;
        int coefficient = 1;




        int nbMax = menu.getPersonne();

        Plage plage = new Plage(longueur, largeur, profondeur, meteo, vent, mer, nbMax);

        Interface interfaced = new Interface(plage);
        BarreVitesse swingControlDemo = new BarreVitesse();
        swingControlDemo.showSliderDemo();


        while(true){
            plage.turn();
            interfaced.turn();
            Thread.sleep(vitesse * coefficient);


        }

    }
}}