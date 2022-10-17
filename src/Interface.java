import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JPanel {
    private final int pixel = 5;
    private int longueur;
    private int largeur;
    private int mer;
    private Personne[] threads;
    private Case[][] matrice;

    public Interface(Plage plage){
        setVisible(true);
        JFrame frame = new JFrame("Plage");
        frame.setSize(plage.getLargeur(), plage.getLongueur()+plage.getMer());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocation(0,0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);

        mer = plage.getMer();
        longueur = plage.getLongueur();
        largeur = plage.getLargeur();
        threads = plage.getThreads();
        matrice = plage.getMatrice();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.decode("#FFE333"));
        g.fillRect(0, 0, largeur, longueur);
        g.setColor(Color.decode("#338AFF"));
        g.fillRect(0, longueur, largeur, mer);

        for (Personne personne : threads) {
            if (personne.getAlive()) {
                if (personne.getEstSauveteur()){
                    g.setColor(Color.red);
                    g.fillOval(personne.getPosition()[1], personne.getPosition()[0], pixel, pixel);
                }
                else{
                    if (personne.getEtat() == Etat.NOYADE){
                        g.setColor(Color.white);
                        g.fillOval(personne.getPosition()[1], personne.getPosition()[0], pixel, pixel);
                    }
                    else{
                        g.setColor(Color.gray);
                        g.fillOval(personne.getPosition()[1], personne.getPosition()[0], pixel, pixel);
                        
                    }
                    g.setColor(Color.black);
                    /*if (personne.getPositionPlage() != null) {
                        g.fillRect(personne.getPositionPlage()[1], personne.getPositionPlage()[0], pixel, pixel);
                    }*/
                }
            }
        }
        /*for (int i = 0; i < longueur; i++){
            for (int j = 0; j < largeur; j++){
                if (matrice [i][j].type == Type.TEMPORAIRE){
                    g.setColor(Color.red);
                    g.fillRect(j, i, pixel, pixel);
                }
            }
        }*/
        for (int i = 0; i < longueur; i++){
            for (int j = 0; j < largeur; j++){
                if (matrice [i][j].type == Type.AFFAIRES){
                    g.setColor(Color.black);
                    g.fillRect(j, i, pixel, pixel);
                }
            }
        }
    }

    public void turn(){
        this.repaint();
    }
}
