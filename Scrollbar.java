package src;

import java.awt.*;
import java.awt.event.*;


public class Scrollbar {
    Scrollbar() {
        Frame f = new Frame("Vitesse");
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
        final Scrollbar vitesse = new Scrollbar(Adjustable.HORIZONTAL, 0, 0, 0, 2000);
        // Position Scrollbar
        vitesse.setBounds(50, 0, 200, 50);
        f.add(vitesse);
        f.add(label);
        // Taille de la fenetre
        f.setSize(300, 125);
        f.setLayout(null);
        f.setVisible(true);
        vitesse.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                label.setText("Vitesse de la simulation : "+ vitesse.getValue());
            }
        });
    }

    public static void main(String args[]){
        new test2();
    }
}