import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner scan = new Scanner(System.in);
        /*int largeur = scan.nextInt();
        int longueur = scan.nextInt();
        int profondeur = scan.nextInt();
        double temperature = scan.nextDouble();
        int vent = scan.nextInt();
        int mer = scan.nextInt();*/

        int largeur = 600;
        int longueur = 600; 
        int profondeur = 23;
        double temperature = 98;
        int vent = 123;
        int mer = 450;

        scan.close();

        int nbMax = 150;

        Plage plage = new Plage(longueur, largeur, profondeur, temperature, vent, mer, nbMax);


        // Une Plage => 1 Thread.
        // Plusieurs personnes => 1 thread pour chaques personnes.

        // Il va nous nous falloir une boite de communications entre les threads

        System.out.println("J'ARRIVE ICI HIHIKIKK");
        Interface interfaced = new Interface(plage);
        while(true){
            plage.turn();
            interfaced.turn();
            Thread.sleep(10);
        }

    }
}