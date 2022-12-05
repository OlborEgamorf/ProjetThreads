//package src;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.*;

public class BarreVitesse {
    private JFrame Fenetre;
    private JLabel TexteDessus;
    private JLabel TexteDessous;
    private JPanel controlPanel;

    private int value = 1;

    public BarreVitesse(){
        prepareGUI();
    }

    private void prepareGUI(){
        Fenetre= new JFrame("Vitesse de la simulation");
        Fenetre.setSize(320,180);
        Fenetre.setLocation(200,200);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - Fenetre.getWidth()) );
        int y = (int) ((dimension.getHeight() - Fenetre.getHeight()) / 2);
        Fenetre.setLocation(x, y);
        Fenetre.getContentPane().setBackground(Color.decode("#DAF6F1"));

        Fenetre.setLayout(new GridLayout(3, 1));

        Fenetre.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        TexteDessus = new JLabel("", JLabel.CENTER);
        TexteDessous = new JLabel("",JLabel.CENTER);
        TexteDessous.setSize(300,100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        Fenetre.add(TexteDessus);
        Fenetre.add(controlPanel);
        Fenetre.add(TexteDessous);
        Fenetre.setVisible(true);
    }
    void showSliderDemo(){
        TexteDessus.setText("Changer la vitesse de la simulation :");
        Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
        fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        TexteDessus.setFont(new Font("Verdana", Font.BOLD, 14).deriveFont(fontAttributes));
        JSlider slider = new JSlider(JSlider.HORIZONTAL,1,100,value);
        TexteDessous.setText("Vitesse de la simulation : 30" );
        TexteDessous.setForeground(Color.decode("#666666"));
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);

        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                TexteDessous.setText("Vitesse de la simulation : " + ((JSlider)e.getSource()).getValue());
                Coeff.setCoeff(slider.getValue());
                System.out.println(value);
            }
        });
        controlPanel.add(slider);
        Fenetre.setVisible(true);


    }
    public int getValue() {
        return value;
    }
}

