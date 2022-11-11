//package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InteractionMenu extends JFrame implements ActionListener {
    private JLabel intituleLongueur = new JLabel("Longueur plage :");
    private Saisie longueur = new Saisie("200");
    private JLabel intituleLargeur = new JLabel("Largeur plage :");
    private Saisie largeur = new Saisie("150");
    private JLabel intituleMer = new JLabel("Trait de côte :");
    private Saisie mer = new Saisie("50");
    private JLabel intitulePersonne = new JLabel("Nombre de personne max :");
    private Saisie personne = new Saisie("150");
    private JLabel intituleTemperature = new JLabel("Vent :");
    private Saisie temperature = new Saisie("20");
    private JLabel intituleVent = new JLabel("Température :");
    private Saisie vent = new Saisie("20");
    private boolean done = false;

    public InteractionMenu() {
        super("Saisie des paramètres");
        gestionDisposition();
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
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
        horzGroupe.addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleLargeur).addComponent(largeur).addComponent(intituleMer).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleTemperature).addComponent(temperature);
        vertGroupe.addComponent(intituleLargeur).addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleMer).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleTemperature).addComponent(temperature);
        vertGroupe.addComponent(intituleMer).addComponent(intituleMer).addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleLargeur).addComponent(largeur).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleTemperature).addComponent(temperature);
        vertGroupe.addComponent(intitulePersonne).addComponent(personne).addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleLargeur).addComponent(largeur).addComponent(intituleMer).addComponent(mer).addComponent(intituleTemperature).addComponent(temperature).addComponent(intituleVent).addComponent(vent);
        vertGroupe.addComponent(intituleTemperature).addComponent(temperature).addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleLargeur).addComponent(largeur).addComponent(intituleMer).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent);
        vertGroupe.addComponent(intituleVent).addComponent(vent).addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleLargeur).addComponent(largeur).addComponent(intituleMer).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleTemperature).addComponent(temperature);
        groupe.setHorizontalGroup(horzGroupe);
        groupe.setVerticalGroup(vertGroupe);

        Object[] elements = new Object[]{"Taille de base", "Petite plage", "Grande plage"};
        Object[] elements1 = new Object[]{"Délimitation de base", "Délimitation basse", "Délimitation haute"};
        Object[] elements2 = new Object[]{"Individus de base", "Peu d'individu", "Beaucoup d'individu"};
        Object[] elements3 = new Object[]{"Température de base", "Température basse", "Température haute"};
        Object[] elements4 = new Object[]{"Vent de base", "Vent faible", "Vent fort"};

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

        liste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (liste.getSelectedItem().toString().equals("Taille de base")){
                    longueur.setText("200");
                    largeur.setText("150");
                }
                if (liste.getSelectedItem().toString().equals("Petite plage")){
                    longueur.setText("100");
                    largeur.setText("50");
                }
                if (liste.getSelectedItem().toString().equals("Grande plage")){
                    longueur.setText("300");
                    largeur.setText("225");
                }

            }
        });

        liste2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (liste2.getSelectedItem().toString().equals("Délimitation de base")){
                    mer.setText("50");
                }
                if (liste2.getSelectedItem().toString().equals("Délimitation basse")){
                    mer.setText("100");
                }
                if (liste2.getSelectedItem().toString().equals("Délimitation haute")){
                    mer.setText("30");
                }
            }
        });

        liste3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Nombre d'individus : " + liste3.getSelectedItem().toString());
                if (liste3.getSelectedItem().toString().equals("Individus de base")){
                    personne.setText("150");
                }
                if (liste3.getSelectedItem().toString().equals("Peu d'individu")){
                    personne.setText("50");
                }
                if (liste3.getSelectedItem().toString().equals("Beaucoup d'individu")){
                    personne.setText("300");
                }
            }
        });

        liste4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Température : " + liste4.getSelectedItem().toString());
                if (liste4.getSelectedItem().toString().equals("Température de base")){
                    vent.setText("20");
                }
                if (liste4.getSelectedItem().toString().equals("Température basse")){
                    vent.setText("10");
                }
                if (liste4.getSelectedItem().toString().equals("Température haute")){
                    vent.setText("35");
                }
            }
        });

        liste5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (liste5.getSelectedItem().toString().equals("Vent de base")){
                    temperature.setText("50");
                }
                if (liste5.getSelectedItem().toString().equals("Vent faible")){
                    temperature.setText("10");
                }
                if (liste5.getSelectedItem().toString().equals("Vent fort")){
                    temperature.setText("200");
                }
            }
        });

    }
    public void actionPerformed(ActionEvent e) {

        done = isNumber(largeur.getText()) && isNumber(longueur.getText()) && isNumber(mer.getText()) && isNumber(personne.getText()) && isNumber(temperature.getText()) && isNumber(vent.getText());

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
            } else if (Integer.parseInt(number) < 0 || Integer.parseInt(number) > 7500) {
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
    public int getMer() {
        return Integer.valueOf(mer.getText());
    }



    
}
