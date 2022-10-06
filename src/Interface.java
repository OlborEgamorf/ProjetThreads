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

        for (int i=0;i<=nbPersonnes; i++){
            Personne tmp= ;//trouver un truc pour parcourir les ID actifs
            if (tmp.getEstSauveteur()){
                g.setColor(Color.red);
                g.fillOval(tmp.getPosition()[0], tmp.getPosition()[1], pixel, pixel);
            }
            else{
                if (tmp.getEtat()== Etat.NOYADE){
                    g.setColor(Color.white);
                    g.fillOval(tmp.getPosition()[0], tmp.getPosition()[1], pixel, pixel);
                }
                else{
                    g.setColor(Color.gray);
                    g.fillOval(tmp.getPosition()[0], tmp.getPosition()[1], pixel, pixel);
                    g.setColor(Color.black);
                    g.fillRect(tmp.getPositionPlage()[0], tmp.getPositionPlage()[1], pixel, pixel);
                }
            }
        }
    }

    public void turn(){
        this.repaint();
    }
}
