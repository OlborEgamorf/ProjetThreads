package src;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
class MenuDeroulant extends JFrame {
    static JFrame f;
    static JLabel l, l3, l5, l7, l9;
    static JComboBox c1, c2, c3, c4, c5;
    public static void main(String[] args)
    {
        f = new JFrame("Paramètre");
        MenuDeroulant s = new MenuDeroulant();
        String s1[] = { "Petite", "Moyenne", "Grande" };
        String s2[] = { "+ Plage / - Mer", "Plage = Mer", "- Plage / + Mer" };
        String s3[] = { "Peu", "Moyen", "Beaucoup" };
        String s4[] = { "Basse", "Moyenne", "Haute" };
        String s5[] = { "Peu", "Moyen", "Beaucoup" };

        c1 = new JComboBox(s1);
        c2 = new JComboBox(s2);
        c3 = new JComboBox(s3);
        c4 = new JComboBox(s4);
        c5 = new JComboBox(s5);

        c1.setSelectedIndex(1);
        c2.setSelectedIndex(1);
        c3.setSelectedIndex(1);
        c4.setSelectedIndex(1);
        c5.setSelectedIndex(1);

        c1.setEditable(true);

        l = new JLabel("Taille de la plage ");
        l3 = new JLabel("Délimitation de la plage ");
        l5 = new JLabel("Nombre d'individus ");
        l7 = new JLabel("Température ");
        l9 = new JLabel("Vitesse du vent ");

        JPanel p = new JPanel();
        p.add(l);
        p.add(c1);
        p.add(l3);
        p.add(c2);
        p.add(l5);
        p.add(c3);
        p.add(l7);
        p.add(c4);
        p.add(l9);
        p.add(c5);
        p.setLayout(new FlowLayout());
        f.add(p);
        f.setSize(280, 280);
        f.show();
    }

}