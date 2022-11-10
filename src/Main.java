//package src;

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
        int temperature = menu.getTemperature();
        int vent = menu.getVent();
        int mer = menu.getMer();
        int vitesse = 20;
        int coefficient = 1;


        int nbMax = menu.getPersonne();

        Plage plage = new Plage(longueur, largeur, profondeur, temperature, vent, mer, nbMax);

        Interface interfaced = new Interface(plage);
        while(true){
            plage.turn();
            interfaced.turn();
            Thread.sleep(vitesse * coefficient);
        }

    }
}