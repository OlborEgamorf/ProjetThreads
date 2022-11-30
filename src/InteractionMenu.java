//package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InteractionMenu extends JFrame implements ActionListener {
    private JLabel espacement = new JLabel("");
    private JLabel intituleLongueur = new JLabel("             Longueur de la plage (en m)");
    private Saisie longueur = new Saisie("100");
    private JLabel intituleLargeur = new JLabel("               Largeur de la plage (en m)");
    private Saisie largeur = new Saisie("200");
    private JLabel intituleMer = new JLabel("                   Trait de côte (en m)");
    private Saisie mer = new Saisie("50");
    private JLabel intitulePersonne = new JLabel("           Nombre d'individus maximal");
    private Saisie personne = new Saisie("500");
    private JLabel intituleMeteo = new JLabel("Météo (1 = Soleil, 2 = Nuageux, 3 = Pluie )");
    private Saisie meteo = new Saisie("1");
    private Saisie vent = new Saisie("20");
    private JLabel intituleVent = new JLabel("               Vitesse du vent (en km/h)");
    private JButton validation = new JButton("Lancement !");
    private boolean done = false;

    public InteractionMenu() {
        super("Saisie des paramètres");
        pack();
        gestionDisposition();
        validation.addActionListener(this);
        setSize(10,800);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        setSize(565,800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon("plage.png"));
        add(background);
        getContentPane().setBackground(Color.decode("#dcf9fa"));
        background.setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    private class Saisie extends JTextField {
        public Saisie(String texte) {
            super(texte, 15);
            setFont(new Font("Verdana", Font.BOLD, 20));
            setMargin(new Insets(0, 0, 0, 0));
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            setForeground(Color.decode("#7B7878"));
            setBackground(Color.decode("#F7F5F8"));
        }
    }

    private void gestionDisposition() {
        GroupLayout groupe = new GroupLayout(getContentPane());
        getContentPane().setLayout(groupe);
        groupe.setAutoCreateContainerGaps(true);
        groupe.setAutoCreateGaps(true);

        JLabel texte2 = new JLabel("Choix des paramètres");
        Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
        fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        texte2.setFont(new Font("Verdana", Font.BOLD, 15).deriveFont(fontAttributes));
        add(texte2);
        texte2.setBounds(40, -15, 200, 50);

        GroupLayout.ParallelGroup horzGroupe = groupe.createParallelGroup();
        GroupLayout.SequentialGroup vertGroupe = groupe.createSequentialGroup();
        horzGroupe.addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(espacement).addComponent(intituleLargeur).addComponent(intituleMer).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleMeteo).addComponent(meteo);
        horzGroupe.addComponent(intituleLongueur).addComponent(longueur).addComponent(largeur).addComponent(espacement).addComponent(intituleLargeur).addComponent(intituleMer).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleMeteo).addComponent(meteo);
        horzGroupe.addComponent(validation,GroupLayout.Alignment.CENTER);
        vertGroupe.addComponent(espacement).addComponent(intituleLargeur).addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(intituleMer).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleMeteo).addComponent(meteo);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intituleMer).addComponent(intituleMer).addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(espacement).addComponent(intituleLargeur).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent).addComponent(intituleMeteo).addComponent(meteo);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intitulePersonne).addComponent(personne).addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(espacement).addComponent(intituleLargeur).addComponent(intituleMer).addComponent(mer).addComponent(intituleMeteo).addComponent(meteo).addComponent(intituleVent).addComponent(vent);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intituleMeteo).addComponent(meteo).addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(espacement).addComponent(intituleLargeur).addComponent(intituleMer).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleVent).addComponent(vent);
        vertGroupe.addComponent(validation);
        vertGroupe.addComponent(intituleVent).addComponent(vent).addComponent(largeur).addComponent(intituleLongueur).addComponent(longueur).addComponent(espacement).addComponent(intituleLargeur).addComponent(intituleMer).addComponent(mer).addComponent(intitulePersonne).addComponent(personne).addComponent(intituleMeteo).addComponent(meteo);
        vertGroupe.addComponent(validation);
        groupe.setHorizontalGroup(horzGroupe);
        groupe.setVerticalGroup(vertGroupe);

        JLabel prereglagesTexte = new JLabel("Préréglages");
        prereglagesTexte.setFont(new Font("Verdana", Font.BOLD, 15).deriveFont(fontAttributes));
        add(prereglagesTexte);
        prereglagesTexte.setBounds(220, 400, 300, 50);

        Object[] taille = new Object[]{"Taille de base", "Petite plage", "Grande plage"};
        Object[] delimitation = new Object[]{"Délimitation de base", "Délimitation basse", "Délimitation haute"};
        Object[] individus = new Object[]{"Individus de base", "Peu d'individu", "Beaucoup d'individu"};
        Object[] temps = new Object[]{"Soleil", "Nuageux", "Pluie"};
        Object[] vitesse = new Object[]{"Vitesse de base", "Vitesse faible", "Vitesse forte"};


        JComboBox<String> comboTaille = new JComboBox(taille);
        comboTaille.setForeground(Color.decode("#7B7878"));
        comboTaille.setBackground(Color.decode("#F7F5F8"));
        JComboBox<String> comboDelimitation = new JComboBox(delimitation);
        comboDelimitation.setForeground(Color.decode("#7B7878"));
        comboDelimitation.setBackground(Color.decode("#F7F5F8"));
        JComboBox<String> comboIndividus = new JComboBox(individus);
        comboIndividus.setForeground(Color.decode("#7B7878"));
        comboIndividus.setBackground(Color.decode("#F7F5F8"));
        JComboBox<String> comboTemps = new JComboBox(temps);
        comboTemps.setForeground(Color.decode("#7B7878"));
        comboTemps.setBackground(Color.decode("#F7F5F8"));
        JComboBox<String> comboVitesse = new JComboBox(vitesse);
        comboVitesse.setForeground(Color.decode("#7B7878"));
        comboVitesse.setBackground(Color.decode("#F7F5F8"));

        comboTaille.setBounds(30, 460, 150, 23);
        comboDelimitation.setBounds(200, 460, 150, 23);
        comboIndividus.setBounds(370, 460, 150, 23);
        comboTemps.setBounds(115, 570, 150, 23);
        comboVitesse.setBounds(285, 570, 150, 23);

        add(comboTaille);
        add(comboDelimitation);
        add(comboIndividus);
        add(comboTemps);
        add(comboVitesse);

        JLabel labelTaille = new JLabel("Taille de la plage");
        add(labelTaille);
        labelTaille.setBounds(55, 420, 250, 50);
        JLabel labelDelimitation = new JLabel("Délimitation plage");
        add(labelDelimitation);
        labelDelimitation.setBounds(220, 420, 250, 50);
        JLabel labelIndividus = new JLabel("Nombre d'individus");
        add(labelIndividus);
        labelIndividus.setBounds(390, 420, 250, 50);
        JLabel labelMeteo = new JLabel("Météo");
        add(labelMeteo);
        labelMeteo.setBounds(170, 530, 250, 50);
        JLabel labelVitesse = new JLabel("Vitesse du vent");
        add(labelVitesse);
        labelVitesse.setBounds(315, 530, 250, 50);
        comboTaille.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboTaille.getSelectedItem().toString().equals("Taille de base")) {
                    longueur.setText("100");
                    largeur.setText("200");
                }
                if (comboTaille.getSelectedItem().toString().equals("Petite plage")) {
                    longueur.setText("75");
                    largeur.setText("100");
                }
                if (comboTaille.getSelectedItem().toString().equals("Grande plage")) {
                    longueur.setText("150");
                    largeur.setText("350");
                }

            }
        });

        comboDelimitation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboDelimitation.getSelectedItem().toString().equals("Délimitation de base")) {
                    mer.setText("50");
                }
                if (comboDelimitation.getSelectedItem().toString().equals("Délimitation basse")) {
                    mer.setText("100");
                }
                if (comboDelimitation.getSelectedItem().toString().equals("Délimitation haute")) {
                    mer.setText("30");
                }
            }
        });

        comboIndividus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Nombre d'individus : " + comboIndividus.getSelectedItem().toString());
                if (comboIndividus.getSelectedItem().toString().equals("Individus de base")) {
                    personne.setText("500");
                }
                if (comboIndividus.getSelectedItem().toString().equals("Peu d'individus")) {
                    personne.setText("200");
                }
                if (comboIndividus.getSelectedItem().toString().equals("Beaucoup d'individus")) {
                    personne.setText("1500");
                }
            }
        });

        comboTemps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Météo : " + comboTemps.getSelectedItem().toString());
                if (comboTemps.getSelectedItem().toString().equals("Soleil")) {
                    meteo.setText("1");
                }
                if (comboTemps.getSelectedItem().toString().equals("Nuageux")) {
                    meteo.setText("2");
                }
                if (comboTemps.getSelectedItem().toString().equals("Pluie")) {
                    meteo.setText("3");
                }
            }
        });

        comboVitesse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboVitesse.getSelectedItem().toString().equals("Vitesse de base")) {
                    vent.setText("20");
                }
                if (comboVitesse.getSelectedItem().toString().equals("Vitesse faible")) {
                    vent.setText("10");
                }
                if (comboVitesse.getSelectedItem().toString().equals("Vitesse forte")) {
                    vent.setText("80");
                }
            }

        });

        JLabel labelChoixFichier = new JLabel("Choisir un fichier .txt à importer");
        add(labelChoixFichier);
        labelChoixFichier.setBounds(325, 0, 250, 50);


        File dir = new File("Sauvegardes");
        File[] liste = dir.listFiles();

        JComboBox<String> combo = new JComboBox();
        for (File s : liste) {
            combo.addItem(String.valueOf(s));
        }
        combo.setForeground(Color.decode("#7B7878"));
        combo.setBackground(Color.decode("#F7F5F8"));
        combo.setBounds(340, 40, 150, 23);
        add(combo);
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuffer ajout = new StringBuffer (String.valueOf(combo.getSelectedItem()));
                ajout.insert(8,"\\");
                try
                {
                    File file = new File(String.valueOf(ajout));
                    ArrayList fichiers = new ArrayList();
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    StringBuffer sb = new StringBuffer();
                    ArrayList<String> liste = new ArrayList<String>();
                    String line;
                    while((line = br.readLine()) != null)
                    {
                        liste.add(String.valueOf(line));
                    }
                    largeur.setText(liste.get(0));
                    longueur.setText(liste.get(1));
                    mer.setText(liste.get(2));
                    personne.setText(liste.get(3));
                    vent.setText(liste.get(4));
                    meteo.setText(liste.get(5));
                    fr.close();
                }
                catch(IOException p)
                {
                    p.printStackTrace();
                }
            }
        });

        JLabel labelExporterFichier = new JLabel("Exporter mes données en .txt");
        add(labelExporterFichier);
        labelExporterFichier.setBounds(330,120,250,50);

        JButton boutonSauvegarde = new JButton("Sauvegarder");
        boutonSauvegarde.setBounds(355,160,120,26);
        add(boutonSauvegarde);
        boutonSauvegarde.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                Frame frameFichier = new JFrame("TextField Example");

                JLabel saisieNomFichier = new JLabel("Saisir le nom du fichier :");
                saisieNomFichier.setBounds(200,82,300,15);
                frameFichier.add(saisieNomFichier);

                JButton boutonValider = new JButton("Valider");
                boutonValider.setBounds(215,125,100,20);
                frameFichier.add(boutonValider);

                JTextField nomFichier;
                nomFichier = new JTextField("");
                nomFichier.setBounds(170, 100, 200, 25);
                frameFichier.add(nomFichier);
                boutonValider.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(nomFichier.getText().length() >= 1){
                            try {
                                File dossier = new File("Sauvegardes");
                                boolean res = dossier.mkdir();

                                if (res) {
                                    System.out.println("Le dossier sauvegardes a été créé.");
                                    System.out.println("Ajout de la sauvegarde");
                                    PrintWriter writer = new PrintWriter("Sauvegardes\\"+nomFichier.getText()+".txt", "UTF-8");
                                    writer.println(largeur.getText());
                                    writer.println(longueur.getText());
                                    writer.println(mer.getText());
                                    writer.println(personne.getText());
                                    writer.println(vent.getText());
                                    writer.println(meteo.getText());
                                    writer.close();
                                } else {
                                    System.out.println("Le dossier sauvegardes existe déja.");
                                    System.out.println("Ajout de la sauvegarde");
                                    PrintWriter writer = new PrintWriter("Sauvegardes\\"+nomFichier.getText()+".txt", "UTF-8");
                                    System.out.println(frameFichier.getName());
                                    writer.println(largeur.getText());
                                    writer.println(longueur.getText());
                                    writer.println(mer.getText());
                                    writer.println(personne.getText());
                                    writer.println(vent.getText());
                                    writer.println(meteo.getText());
                                    writer.close();
                                }
                            } catch (FileNotFoundException ex) {
                                throw new RuntimeException(ex);
                            } catch (UnsupportedEncodingException ex) {
                                throw new RuntimeException(ex);
                            }
                            frameFichier.dispose();
                        }
                        else{
                            System.out.println("Veuillez saisir un nom de fichier valide");
                        }
                    }
                });



                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - getHeight()) / 2);
                frameFichier.setLocation(x, y);
                frameFichier.setSize(565, 250);
                ((JFrame) frameFichier).getContentPane().setBackground(Color.decode("#dcf9fa"));
                frameFichier.setLayout(null);
                frameFichier.setVisible(true);
            }});
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
            } else if (Integer.parseInt(number) < 0 || Integer.parseInt(number) > 50000) {
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
    public Meteo getMeteo() {
        if (Integer.valueOf(meteo.getText()) == 1){
            return Meteo.Soleil;
        }
        else if (Integer.valueOf(meteo.getText()) == 2){
            return Meteo.Nuageux;
        }
        else if (Integer.valueOf(meteo.getText()) == 3){
            return Meteo.Pluie;
        }
        return null;
    }
    public int getVent() {
        return Integer.valueOf(vent.getText());
    }
    public int getMer() {
        return Integer.valueOf(mer.getText());
    }
}
