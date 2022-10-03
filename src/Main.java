import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int largeur = scan.nextInt();
        int longueur = scan.nextInt();
        int profondeur = scan.nextInt();
        double temperature = scan.nextDouble();
        int vent = scan.nextInt();
        int mer = scan.nextInt();

        int nbMax = 1000;

        Plage plage = new Plage(longueur, largeur, profondeur, temperature, vent, mer);
        Personne[] threads = new Personne[nbMax];

        // Une Plage => 1 Thread.
        // Plusieurs personnes => 1 thread pour chaques personnes.

        // Il va nous nous falloir une boite de communications entre les threads

        BoiteCommu outil = new BoiteCommu();

        
        Plage.start();
        int[] posTest = {0,0};

        for (int i = 0; i < nbMax; i++) {

            threads[i] = new Personne(posTest,vent,outil);
            threads[i].start();

        }
    }
}