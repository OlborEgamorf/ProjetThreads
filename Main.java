package Specialisation.projet;

public class Main {
    public static void main(String[] args) {

        // Creation Plage et personne
        public class Plage{
            private int flux;
            private String meteo;
            private int taillePlage;
            private String danger;
            private int surveillance;

        }

        Plage(int flux, String meteo, int taillePlage, String danger, int surveillance){
            this.flux = flux;
            this.meteo = meteo;
            this.taillePlage = taillePlage;
            this.danger = danger;
            this.surveillance = surveillance;
        }

        Personne threads[] = new Personne[];

        // Une Plage => 1 Thread.
        // Plusieurs personnes => 1 thread pour chaques personnes.

        // Il va nous nous falloir une boite de communications entre les threads

        BoiteCommu outil = new BoiteCommu();

        Thread Pla = new Thread(new Plage (outil) );
        Thread Nbpers[] = new Thread [Personne.cstMaxPersonne]; //Nbpersonne = nombre de personne donc nombre thread a prevoir.
        Pla.start();

        int length = Nbpers.length;
        for (int i = 0; i < Nbpers.length; i++) {
            Nbpers[i] = new Thread(new Personne(i+1, outil));
            Nbpers[i].start();

        }


    }
}