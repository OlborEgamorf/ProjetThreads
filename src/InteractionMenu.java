package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InteractionMenu extends JFrame implements ActionListener {
    private JLabel intituléLongueur = new JLabel("Longueur plage :");
    private Saisie Longueur = new Saisie("");
    private JLabel intituléLargeur = new JLabel("Largeur plage :");
    private Saisie largeur = new Saisie("");
    private JLabel intituléPersonne = new JLabel("Nombre de personne max :");
    private Saisie personne = new Saisie("");

    private JLabel intituléTemperature = new JLabel("Température :");
    private Saisie temperature = new Saisie("");
    private JLabel intituléVent = new JLabel("Vent :");
    private Saisie vent = new Saisie("");
    private JButton validation = new JButton("Valider");
    private Saisie résultat = new Saisie("Résultat");

    public InteractionMenu() {
        super("Saisie des paramètres");
        résultat.setEditable(false);
        gestionDisposition();
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        validation.addActionListener(this);
    }

    private class Saisie extends JTextField {
        public Saisie(String texte) {
            super(texte, 20);
            setFont(new Font("Verdana", Font.BOLD, 20));
            setMargin(new Insets(0, 3, 0, 0));
        }
    }

    private void gestionDisposition() {
        GroupLayout groupe = new GroupLayout(getContentPane());
        getContentPane().setLayout(groupe);
        groupe.setAutoCreateContainerGaps(true);
        groupe.setAutoCreateGaps(true);
        GroupLayout.ParallelGroup horzGroupe = groupe.createParallelGroup();
        GroupLayout.SequentialGroup vertGroupe = groupe.createSequentialGroup();
        horzGroupe.addComponent(intituléLongueur).addComponent(Longueur).addComponent(intituléLargeur).addComponent(largeur).addComponent(intituléPersonne).addComponent(personne).addComponent(intituléVent).addComponent(vent).addComponent(intituléTemperature).addComponent(temperature);
        horzGroupe.addComponent(validation);
        vertGroupe.addComponent(intituléLargeur).addComponent(largeur).addComponent(intituléLongueur).addComponent(Longueur).addComponent(intituléPersonne).addComponent(personne).addComponent(intituléVent).addComponent(vent).addComponent(intituléTemperature).addComponent(temperature);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intituléPersonne).addComponent(personne).addComponent(intituléLongueur).addComponent(Longueur).addComponent(intituléLargeur).addComponent(largeur).addComponent(intituléTemperature).addComponent(temperature).addComponent(intituléVent).addComponent(vent);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intituléTemperature).addComponent(temperature).addComponent(intituléLongueur).addComponent(Longueur).addComponent(intituléLargeur).addComponent(largeur).addComponent(intituléPersonne).addComponent(personne).addComponent(intituléVent).addComponent(vent);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intituléVent).addComponent(vent).addComponent(intituléLongueur).addComponent(Longueur).addComponent(intituléLargeur).addComponent(largeur).addComponent(intituléPersonne).addComponent(personne).addComponent(intituléTemperature).addComponent(temperature);
        vertGroupe.addComponent(validation);
        groupe.setHorizontalGroup(horzGroupe);
        groupe.setVerticalGroup(vertGroupe);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(largeur.getText());
        System.out.println(Longueur.getText());
        System.out.println(personne.getText());
        System.out.println(temperature.getText());
        System.out.println(vent.getText());


    }

    public static void main(String[] args) {
        new InteractionMenu();
    }

}
