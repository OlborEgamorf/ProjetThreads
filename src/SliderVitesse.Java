//package src;

/*
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class SliderVitesse extends JFrame {

    private JPanel contentPane;
    static SliderVitesse frame;
    static JLabel label;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new SliderVitesse();

                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SliderVitesse() {
        setTitle("Slider vitesse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Taille de la fenetre
        setBounds(350, 50, 320, 130);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JSlider slider = new JSlider();
        label = new JLabel();

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                slider.setValue(slider.getValue());
                label.setText("La vitesse de la simulation est de : " + slider.getValue());
            }
        });
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBounds(50, 10, 200, 52);
        label.setBounds(45,30,300,100);
        contentPane.add(slider);
        contentPane.add(label);
        label.setText("La vitesse de la simulation est de : " + slider.getValue());

    }
}

 */