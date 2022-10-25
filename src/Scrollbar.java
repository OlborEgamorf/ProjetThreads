import java.awt.*;
import java.awt.event.*;


public class Scrollbar {
    Scrollbar() {
        Frame f = new Frame("Nombre de population");
        final Label label = new Label();
        label.setAlignment(Label.CENTER);
        // Position texte
        label.setSize(300, 150);
        // Localisation fenetre
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
        f.setLocation(x, y);
        // Parametre Scrollbar
        final java.awt.Scrollbar population = new java.awt.Scrollbar(Adjustable.HORIZONTAL, 0, 0, 0, 2000);
        // Position Scrollbar ///////
        population.setBounds(50, 0, 200, 50);
        f.add(population);
        f.add(label);
        // Taille de la fenetre
        f.setSize(300, 125);
        f.setLayout(null);
        f.setVisible(true);
        population.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                label.setText("Nombre de personne : "+ population.getValue());
            }
        });
    }

    // main method
    public static void main(String args[]){
        new Scrollbar();
    }
}    