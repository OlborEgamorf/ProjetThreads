package Specialisation.projet;

public class BoiteCommu {
    boolean entree;
    double val;
    // Constructeur:
    BoiteCommu(){
        entree = false; // Elle est initialisé de base a False
    }
    synchronized public boolean estsortie () {
        return ! entree;
    }
    synchronized public boolean estentree (){
        return entree;
    }
    synchronized public void EntreeValeur (double valeur) throws Exception{
        if (entree){
            throw new Exception("Donnee deja entree");
        }
        val = valeur;
        entree = true;
    }
    synchronized public int SortieValeur () throws Exception{
        if (! entree){
            throw new Exception("Donnee deja sortie");
        }
        entree = false;
        return (int) val;

    }
}
