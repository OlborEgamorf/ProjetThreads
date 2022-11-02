//package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InteractionMenu extends JFrame implements ActionListener {
    private JLabel intituleLongueur = new JLabel("Longueur plage :");
    private Saisie longueur = new Saisie("200");
    private JLabel intituleLargeur = new JLabel("Largeur plage :");
    private Saisie largeur = new Saisie("200");
    private JLabel intituleTraitDeCote = new JLabel("Trait de côte :");
    private Saisie traitDeCote = new Saisie("50");
    private JLabel intitulePersonne = new JLabel("Nombre de personne max :");
    private Saisie personne = new Saisie("1000");
    private JLabel intituleTemperature = new JLabel("Vent :");
    private Saisie temperature = new Saisie("20");
    private JLabel intituleVent = new JLabel("Température :");
    private Saisie vent = new Saisie("20");
    private JButton validation = new JButton("Valider");
    private Saisie résultat = new Saisie("Résultat");

    private boolean done = false;

    public InteractionMenu() {
        super("Saisie des paramètres");
        résultat.setEditable(false);
        gestionDisposition();
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        validation.addActionListener(this);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

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
        horzGroupe.addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleLargeur).addComponent(largeur).addComponent(intituleTraitDeCote).addComponent(traitDeCote).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleTemperature).addComponent(temperature);
        horzGroupe.addComponent(validation);
        vertGroupe.addComponent(intituleLargeur).addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleTraitDeCote).addComponent(traitDeCote).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleTemperature).addComponent(temperature);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intituleTraitDeCote).addComponent(intituleTraitDeCote).addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleLargeur).addComponent(largeur).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleTemperature).addComponent(temperature);
        vertGroupe.addComponent(validation);

        vertGroupe.addComponent(intitulePersonne).addComponent(personne).addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleLargeur).addComponent(largeur).addComponent(intituleTraitDeCote).addComponent(traitDeCote).addComponent(intituleTemperature).addComponent(temperature).addComponent(intituleVent).addComponent(vent);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intituleTemperature).addComponent(temperature).addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleLargeur).addComponent(largeur).addComponent(intituleTraitDeCote).addComponent(traitDeCote).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intituleVent).addComponent(vent).addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleLargeur).addComponent(largeur).addComponent(intituleTraitDeCote).addComponent(traitDeCote).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleTemperature).addComponent(temperature);
        vertGroupe.addComponent(validation);
        groupe.setHorizontalGroup(horzGroupe);
        groupe.setVerticalGroup(vertGroupe);
/*
        Object[] elements = new Object[]{"Rien","Grande plage", "Moyenne plage", "Grande plage"};
        Object[] elements1 = new Object[]{"Rien","Délimitation basse", "Délimitation au milieu", "Délimitation haute"};
        Object[] elements2 = new Object[]{"Rien","Peu d'individus", "Individus moyen", "Beaucoup d'individu"};
        Object[] elements3 = new Object[]{"Rien","Température haute", "Température moyenne", "Température haute"};
        Object[] elements4 = new Object[]{"Rien","Vent fort", "Vent moyen", "Vent faible"};

        JComboBox<String> liste = new JComboBox(elements);
        JComboBox<String> liste2 = new JComboBox(elements1);
        JComboBox<String> liste3 = new JComboBox(elements2);
        JComboBox<String> liste4 = new JComboBox(elements3);
        JComboBox<String> liste5 = new JComboBox(elements4);

        liste.setBounds(10, 440, 120, 23);
        liste2.setBounds(150, 440, 120, 23);
        liste3.setBounds(290, 440, 120, 23);
        liste4.setBounds(10, 550, 120, 23);
        liste5.setBounds(150, 550, 120, 23);

        add(liste);
        add(liste2);
        add(liste3);
        add(liste4);
        add(liste5);


        JLabel label = new JLabel("Taille de la plage");
        add(label);
        label.setBounds(10, 400, 250, 50);
        JLabel label2 = new JLabel("Délimitation plage");
        add(label2);
        label2.setBounds(150, 400, 250, 50);
        JLabel label3 = new JLabel("Nombre d'individus");
        add(label3);
        label3.setBounds(290, 400, 250, 50);
        JLabel label4 = new JLabel("Température");
        add(label4);
        label4.setBounds(10, 510, 250, 50);
        JLabel label5 = new JLabel("Vent");
        add(label5);
        label5.setBounds(150, 510, 250, 50);


 */

    }

    public void actionPerformed(ActionEvent e) {

        done = isNumber(largeur.getText()) && isNumber(longueur.getText()) && isNumber(traitDeCote.getText()) && isNumber(personne.getText()) && isNumber(temperature.getText()) && isNumber(vent.getText());
    
    }

    public boolean isDone() {
        return done;
    }

    public static boolean isNumber(String number) {
        // Cette fonction vérifie si un String donné est bien un nombre
        if (number.length() == 0) {
            return false;
        }
        int i = 0;
        boolean flag = true;

        while (flag && i<number.length()) {
            if (!Character.isDigit(number.charAt(i))) {
                flag = false;
            }
            i ++;
        }

        return flag;
    }

    public int getLongueur() {
        return Integer.valueOf(longueur.getText());
    }

    public int getLargeur() {
        return Integer.valueOf(largeur.getText());
    }

    public int getPersonne() {
        return Integer.valueOf(personne.getText());
    }

    public int getTemperature() {
        return Integer.valueOf(temperature.getText());
    }

    public int getVent() {
        return Integer.valueOf(vent.getText());
    }

    public int getTraitDeCote() {
        return Integer.valueOf(traitDeCote.getText());
    }



    
}
