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

    public Interface(Plage plage){
        setVisible(true);
        JFrame frame = new JFrame("Plage");
        frame.setSize(plage.getLargeur(), plage.getLongueur());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocation(0,0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);

        mer = plage.getMer();
        longueur = plage.getLongueur();
        largeur = plage.getLargeur();
        threads = plage.getThreads();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.decode("#FFE333"));
        g.fillRect(0,0, largeur, longueur);
        g.setColor(Color.decode("#338AFF"));
        g.fillRect(0, mer, largeur, longueur-mer);

        for (Personne personne : threads) {
            if (personne.getEstSauveteur()){
                g.setColor(Color.red);
                g.fillOval(personne.getPosition()[1], personne.getPosition()[0], pixel, pixel);
            }
            else{
                if (personne.getEtat()== Etat.NOYADE){
                    g.setColor(Color.white);
                    g.fillOval(personne.getPosition()[1], personne.getPosition()[0], pixel, pixel);
                }
                else{
                    g.setColor(Color.gray);
                    g.fillOval(personne.getPosition()[1], personne.getPosition()[0], pixel, pixel);
                    
                }
                g.setColor(Color.black);
                int[] posPlage = personne.getPositionPlage();
                if (posPlage != null) {
                    g.fillRect(posPlage[0], posPlage[1], pixel, pixel);
                }
                
            }
        }
    }

    public void turn(){
        this.repaint();
    }
}
