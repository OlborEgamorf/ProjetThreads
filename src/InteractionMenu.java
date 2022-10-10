package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InteractionMenu extends JFrame implements ActionListener {
    private JLabel intituléNom = new JLabel("Longueur plage :");
    private Saisie nom = new Saisie("");
    private JLabel intituléPrénom = new JLabel("Largeur plage :");
    private Saisie prénom = new Saisie("");
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
            super(texte, 10);
            setFont(new Font("Verdana", Font.BOLD, 16));
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
        horzGroupe.addComponent(intituléNom).addComponent(nom).addComponent(intituléPrénom).addComponent(prénom).addComponent(intituléPersonne).addComponent(personne).addComponent(intituléVent).addComponent(vent).addComponent(intituléTemperature).addComponent(temperature);
        horzGroupe.addComponent(validation).addComponent(résultat);
        vertGroupe.addComponent(intituléNom).addComponent(nom).addComponent(intituléPrénom).addComponent(prénom).addComponent(intituléPersonne).addComponent(personne).addComponent(intituléVent).addComponent(vent).addComponent(intituléTemperature).addComponent(temperature);
        vertGroupe.addComponent(validation).addComponent(résultat);
        vertGroupe.addComponent(intituléPersonne).addComponent(nom).addComponent(intituléPrénom).addComponent(prénom).addComponent(intituléPersonne).addComponent(personne).addComponent(intituléVent).addComponent(vent).addComponent(intituléTemperature).addComponent(temperature);
        vertGroupe.addComponent(validation).addComponent(résultat);
        vertGroupe.addComponent(intituléTemperature).addComponent(temperature).addComponent(intituléPrénom).addComponent(prénom).addComponent(intituléPersonne).addComponent(personne).addComponent(intituléVent).addComponent(vent).addComponent(intituléTemperature).addComponent(temperature);
        vertGroupe.addComponent(validation).addComponent(résultat);
        vertGroupe.addComponent(intituléVent).addComponent(vent).addComponent(intituléPrénom).addComponent(prénom).addComponent(intituléPersonne).addComponent(personne).addComponent(intituléVent).addComponent(vent).addComponent(intituléTemperature).addComponent(temperature);
        vertGroupe.addComponent(validation).addComponent(résultat);
        groupe.setHorizontalGroup(horzGroupe);
        groupe.setVerticalGroup(vertGroupe);
    }

    public void actionPerformed(ActionEvent e) {
        résultat.setText(prénom.getText() + ' ' + nom.getText() + ' ' + personne.getText() + ' ' + vent.getText() + ' ' + temperature.getText());
    }

    public static void main(String[] args) {
        new Champ();
    }
}