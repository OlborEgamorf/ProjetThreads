public class Fourmi extends Thread {
    private int x;
    private int y;
    private int id;
    private double taille;
    private boolean larve;
    private String sexe;

    public void run() {
        if (larve) {
            try {
                Thread.sleep(999); // Temps avant fin larve
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

        larve = false;
        while(true) {
            
        }
    }
}
