//package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class InteractionMenu extends JFrame implements ActionListener {
    private JLabel espacement = new JLabel("");
    private JLabel intituleLongueur = new JLabel("                                                         Longueur de la plage :");
    private Saisie longueur = new Saisie("200");
    private JLabel intituleLargeur = new JLabel("                                                           Largeur de la plage :");
    private Saisie largeur = new Saisie("150");

    private JLabel intituleTraitDeCote = new JLabel("                                                               Trait de côte :");

    private Saisie mer = new Saisie("50");

    private JLabel intitulePersonne = new JLabel("                                                          Nombre d'individus :");
    private Saisie personne = new Saisie("150");

    private JLabel intituleTemperature = new JLabel("                                         Météo (1 = Soleil, 2 = Nuageux, 3 = Pluie ) :");
    private Saisie vent = new Saisie("20");
    private JLabel intituleVent = new JLabel("                                                     Vitesse du vent en km/h :");


    private JButton validation = new JButton("Confirmer les choix");


    private boolean done = false;
    private Saisie meteo = new Saisie("1");

    public InteractionMenu() {
        super("Saisie des paramètres");
        gestionDisposition();
        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        validation.addActionListener(this);
        setSize(550,800);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        getContentPane().setBackground(Color.decode("#DAF6F1"));


    }
    private class Saisie extends JTextField {
        public Saisie(String texte) {
            super(texte, 10);
            setFont(new Font("Verdana", Font.BOLD, 20));
            setMargin(new Insets(0, 3, 0, 15));
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            setForeground(Color.decode("#666666"));
            setBackground(Color.decode("#F7F5F8"));
        }
    }

    private void gestionDisposition() {
        GroupLayout groupe = new GroupLayout(getContentPane());
        getContentPane().setLayout(groupe);
        groupe.setAutoCreateContainerGaps(true);
        groupe.setAutoCreateGaps(true);

        JLabel texte2 = new JLabel("Choix des paramètres :");
        Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
        fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        texte2.setFont(new Font("Verdana", Font.BOLD, 15).deriveFont(fontAttributes));
        add(texte2);
        texte2.setBounds(165,-15,200,50);

        GroupLayout.ParallelGroup horzGroupe = groupe.createParallelGroup();
        GroupLayout.SequentialGroup vertGroupe = groupe.createSequentialGroup();
        horzGroupe.addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(espacement).addComponent(intituleLargeur).addComponent(intituleTraitDeCote).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleTemperature).addComponent(meteo);

        horzGroupe.addComponent(intituleLongueur).addComponent(longueur).addComponent(largeur).addComponent(espacement).addComponent(intituleLargeur).addComponent(intituleTraitDeCote).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleTemperature).addComponent(meteo);
        horzGroupe.addComponent(validation, GroupLayout.Alignment.CENTER);
        vertGroupe.addComponent(espacement).addComponent(intituleLargeur).addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleTraitDeCote).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleTemperature).addComponent(meteo);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intituleTraitDeCote).addComponent(intituleTraitDeCote).addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(espacement).addComponent(intituleLargeur).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleTemperature).addComponent(meteo);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intitulePersonne).addComponent(personne).addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(espacement).addComponent(intituleLargeur).addComponent(intituleTraitDeCote).addComponent(mer).addComponent(intituleTemperature).addComponent(meteo).addComponent(intituleVent).addComponent(vent);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intituleTemperature).addComponent(meteo).addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(espacement).addComponent(intituleLargeur).addComponent(intituleTraitDeCote).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intituleVent).addComponent(vent).addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(espacement).addComponent(intituleLargeur).addComponent(intituleTraitDeCote).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleTemperature).addComponent(meteo);
        vertGroupe.addComponent(validation);
        groupe.setHorizontalGroup(horzGroupe);
        groupe.setVerticalGroup(vertGroupe);

        Object[] elements = new Object[]{"Taille de base", "Petite plage", "Grande plage"};
        Object[] elements1 = new Object[]{"Délimitation de base", "Délimitation basse", "Délimitation haute"};
        Object[] elements2 = new Object[]{"Individus de base", "Peu d'individu", "Beaucoup d'individu"};
        Object[] elements3 = new Object[]{"Soleil", "Nuageux", "Pluie"};
        Object[] elements4 = new Object[]{"Vitesse de base", "Vitesse faible", "Vitesse forte"};

        JComboBox<String> liste = new JComboBox(elements);
        liste.setForeground(Color.decode("#666666"));
        liste.setBackground(Color.decode("#f4fefe"));
        JComboBox<String> liste2 = new JComboBox(elements1);
        liste2.setForeground(Color.decode("#666666"));
        liste2.setBackground(Color.decode("#f4fefe"));
        JComboBox<String> liste3 = new JComboBox(elements2);
        liste3.setForeground(Color.decode("#666666"));
        liste3.setBackground(Color.decode("#f4fefe"));
        JComboBox<String> liste4 = new JComboBox(elements3);
        liste4.setForeground(Color.decode("#666666"));
        liste4.setBackground(Color.decode("#f4fefe"));
        JComboBox<String> liste5 = new JComboBox(elements4);
        liste5.setForeground(Color.decode("#666666"));
        liste5.setBackground(Color.decode("#f4fefe"));

        liste.setBounds(30, 460, 150, 23);
        liste2.setBounds(200, 460, 150, 23);
        liste3.setBounds(370, 460, 150, 23);
        liste4.setBounds(115, 570, 150, 23);
        liste5.setBounds(285, 570, 150, 23);

        add(liste);
        add(liste2);
        add(liste3);
        add(liste4);
        add(liste5);


        JLabel texte = new JLabel("Choix de préréglages optionnels :");
        texte.setFont(new Font("Verdana", Font.BOLD, 15).deriveFont(fontAttributes));
        add(texte);
        texte.setBounds(130,400,300,50);

        JLabel label = new JLabel("Taille de la plage");
        add(label);
        label.setBounds(55, 420, 250, 50);
        JLabel label2 = new JLabel("Délimitation plage");
        add(label2);
        label2.setBounds(220, 420, 250, 50);
        JLabel label3 = new JLabel("Nombre d'individus");
        add(label3);
        label3.setBounds(390, 420, 250, 50);
        JLabel label4 = new JLabel("Météo");
        add(label4);
        label4.setBounds(170, 530, 250, 50);
        JLabel label5 = new JLabel("Vitesse du vent");
        add(label5);
        label5.setBounds(315, 530, 250, 50);

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
                System.out.println("Météo : " + liste4.getSelectedItem().toString());
                if (liste4.getSelectedItem().toString().equals("Soleil")){
                    meteo.setText("1");
                }
                if (liste4.getSelectedItem().toString().equals("Nuageux")){
                    meteo.setText("2");
                }
                if (liste4.getSelectedItem().toString().equals("Pluie")){
                    meteo.setText("3");
                }
            }
        });

        liste5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (liste5.getSelectedItem().toString().equals("Vitesse de base")){
                    vent.setText("50");
                }
                if (liste5.getSelectedItem().toString().equals("Vitesse faible")){
                    vent.setText("10");
                }
                if (liste5.getSelectedItem().toString().equals("Vitesse forte")){
                    vent.setText("200");
                }
            }
        });

    }
    public void actionPerformed(ActionEvent e) {

        done = isNumber(largeur.getText()) && isNumber(longueur.getText()) && isNumber(mer.getText()) && isNumber(personne.getText()) && isNumber(meteo.getText()) && isNumber(vent.getText());

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
    public int getMeteo() {
        return Integer.valueOf(meteo.getText());
    }
    public int getVent() {
        return Integer.valueOf(vent.getText());
    }
    public int getMer() {
        return Integer.valueOf(mer.getText());
    }




}
