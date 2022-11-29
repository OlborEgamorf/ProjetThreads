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

        Meteo meteo = menu.getMeteo();
        int vent = menu.getVent();
        int mer = menu.getMer();
        int nbMax = menu.getPersonne();
        
        Plage plage = new Plage(longueur, largeur, vent, mer, nbMax, meteo);

        Interface interfaced = new Interface(plage);
        BarreVitesse swingControlDemo = new BarreVitesse();
        swingControlDemo.showSliderDemo();

        plage.startAll();

        while(true){
            plage.turn();
            interfaced.turn();
            Thread.sleep(10);
        }
    }}
