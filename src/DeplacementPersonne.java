package src;

public class DeplacementPersonne {
    private final int x = Math.floor(Math.random()) * Plage.longueur;
    private final int y = Math.floor(Math.random()) * Plage.largeur;
    private Personne perso;

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Personne getPerso() {
        return this.perso;
    }

    //Pour cette fonction s'aider des 4 fonctions plus bas.
    public void deplacer(){

        
        // je voulais utiliser switch mais sa marche pas du coup a supprimer si sa ne vaut rien.
        /*
        switch (){
            case 1:
                if (Plage[x][y]==1 && Plage[x][y-1]==0)
                    deplacerversgauche(perso);
            case 2:
                if (Plage[x][y]==1 && Plage[x][y+1]==0)
                    deplacerversdroite(perso);
            case 3:
                if (Plage[x][y]==1 && Plage[x+1][y]==0)
                    deplacerbas(perso);
            case 4:
                if (Plage[x][y]==1 && Plage[x-1][y]==0)
                    deplacerhaut(perso);
        }
        */
        if (Plage[x][y]==1 && Plage[x][y-1]==0)
            deplacerversgauche(perso);
        if (Plage[x][y]==1 && Plage[x][y+1]==0)
            deplacerversdroite(perso);
        if (Plage[x][y]==1 && Plage[x+1][y]==0)
            deplacerbas(perso);
        if (Plage[x][y]==1 && Plage[x-1][y]==0)
            deplacerhaut(perso);

    }

    // Pour deplacer faut verifie si la case voulu soit egal a 0 (case libre)

    //Fonction pour deplacer un personnage vers le haut.
    public void deplacerhaut(Personne per1){
        if (x-1 >= 0 && Plage[x-1][y] == 0){
            per1 = Plage[x][y];
            Plage[x][y] = 0;
            Plage[x-1][y] = per1;
        }
    }

    //Fonction pour deplacer un personnage vers le bas.
    public void deplacerbas(Personne per1){
        if (x+1 <= Plage.largeur && Plage[x+1][y] == 0){
            per1 = Plage[x][y];
            Plage[x][y] = 0;
            Plage[x+1][y] = per1;
        }
    }

    //Fonction pour deplacer un personnage vers la gauche.
    public void deplacerversgauche(Personne per1){
        if (y-1 >= 0 && Plage[x][y-1] == 0){
            per1 = Plage[x][y];
            Plage[x][y] = 0;
            Plage[x][y-1] = per1;
        }
    }

    //Fonction pour deplacer un personnage vers la gauche.
    public void deplacerversdroite(Personne per1){
        if (y+1 <= Plage.longueur && Plage[x][y+1] == 0){
            per1 = Plage[x][y];
            Plage[x][y] = 0;
            Plage[x][y+1] = per1;
        }
    }
}
