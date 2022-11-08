import java.awt.*;
import java.awt.event.*;
/*

public class Scrollbar {
    Scrollbar() {
        Frame f = new Frame("Vitesse");
        final Label label = new Label();
        label.setAlignment(Label.CENTER);
        // Position texte
        label.setSize(300, 150);
        // Localisation fenetre
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - dimension.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - dimension.getHeight()) / 2);
        f.setLocation(500, 500);
        // Parametre Scrollbar
        final java.awt.Scrollbar vitesse = new java.awt.Scrollbar(Adjustable.HORIZONTAL, 0, 0, 0, 10);
        // Position Scrollbar ///////
        vitesse.setBounds(50, 0, 200, 50);
        f.add(vitesse);
        f.add(label);
        // Taille de la fenetre
        f.setSize(300, 100);
        f.setLayout(null);
        f.setVisible(true);
        vitesse.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                label.setText("Vitesse : "+ vitesse.getValue());
            }
        });
    }

    // main method
    public static void main(String args[]){
        new Scrollbar();
    }
