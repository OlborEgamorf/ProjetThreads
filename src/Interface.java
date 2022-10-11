import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JPanel {
    private final int pixel = 2;
    private Plage plage;

    public Interface(Plage plage){
        setVisible(true);
        JFrame frame= new JFrame("Plage");
        frame.setSize(plage.getLargeur(),plage.getLongueur());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocation(0,0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.yellow);
        g.fillRect(0,0, plage.getLargeur(), plage.getLongueur()-plage.getMer());
        g.setColor(Color.blue);
        g.fillRect(0,(plage.getLargeur()-plage.getMer()+1), plage.getLargeur(),plage.getLongueur()-plage.getMer());

        for (Personne personne : plage.getThreads()) {
            
            if (personne.getEstSauveteur()){
                g.setColor(Color.red);
                g.fillOval(personne.getPosition()[0], personne.getPosition()[1], pixel, pixel);
            }
            else{
                if (personne.getEtat()== Etat.NOYADE){
                    g.setColor(Color.white);
                    g.fillOval(personne.getPosition()[0], personne.getPosition()[1], pixel, pixel);
                }
                else{
                    g.setColor(Color.gray);
                    g.fillOval(personne.getPosition()[0], personne.getPosition()[1], pixel, pixel);
                    g.setColor(Color.black);
                    g.fillRect(personne.getPositionPlage()[0], personne.getPositionPlage()[1], pixel, pixel);
                }
            }
        }
    }

    public void turn(){
        this.repaint();
    }
}
