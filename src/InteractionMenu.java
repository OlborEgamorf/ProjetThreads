//package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InteractionMenu extends JFrame implements ActionListener {

    private JLabel espacement = new JLabel("");
    private JLabel intituleLongueur = new JLabel("                                                     Longueur de la plage (en m)");
    private Saisie longueur = new Saisie("100");
    private JLabel intituleLargeur = new JLabel("                                                       Largeur de la plage (en m)");
    private Saisie largeur = new Saisie("200");

    private JLabel intituleTraitDeCote = new JLabel("                                                           Trait de côte (en m)");

    private Saisie mer = new Saisie("50");

    private JLabel intitulePersonne = new JLabel("                                                     Nombre d'individus maximal");
    private Saisie personne = new Saisie("500");

    private JLabel intituleTemperature = new JLabel("                                           Météo (1 = Soleil, 2 = Nuageux, 3 = Pluie )");
    private Saisie vent = new Saisie("20");
    private JLabel intituleVent = new JLabel("                                                       Vitesse du vent (en km/h)");


    private JButton validation = new JButton("Lancement !");


    private boolean done = false;
    private Saisie meteo = new Saisie("1");

    public InteractionMenu() {
        super("Saisie des paramètres");
        pack();
        gestionDisposition();

        validation.addActionListener(this);
        setSize(550,800);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

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
        texte2.setBounds(178, -15, 200, 50);

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
        Object[] elements5 = new Object[]{"Texte1", "Texte2", "Texte3"};


        JComboBox<String> liste = new JComboBox(elements);
        liste.setForeground(Color.decode("#7B7878"));
        liste.setBackground(Color.decode("#f4fefe"));
        JComboBox<String> liste2 = new JComboBox(elements1);
        liste2.setForeground(Color.decode("#7B7878"));
        liste2.setBackground(Color.decode("#f4fefe"));
        JComboBox<String> liste3 = new JComboBox(elements2);
        liste3.setForeground(Color.decode("#7B7878"));
        liste3.setBackground(Color.decode("#f4fefe"));
        JComboBox<String> liste4 = new JComboBox(elements3);
        liste4.setForeground(Color.decode("#7B7878"));
        liste4.setBackground(Color.decode("#f4fefe"));
        JComboBox<String> liste5 = new JComboBox(elements4);
        liste5.setForeground(Color.decode("#7B7878"));
        liste5.setBackground(Color.decode("#f4fefe"));
        JComboBox<String> liste6 = new JComboBox(elements5);
        liste6.setForeground(Color.decode("#7B7878"));
        liste6.setBackground(Color.decode("#f4fefe"));

        liste.setBounds(30, 460, 150, 23);
        liste2.setBounds(200, 460, 150, 23);
        liste3.setBounds(370, 460, 150, 23);
        liste4.setBounds(115, 570, 150, 23);
        liste5.setBounds(285, 570, 150, 23);
        liste6.setBounds(370, 385, 150, 23);

        add(liste);
        add(liste2);
        add(liste3);
        add(liste4);
        add(liste5);
        add(liste6);

        JLabel texte = new JLabel("Préréglages");
        texte.setFont(new Font("Verdana", Font.BOLD, 15).deriveFont(fontAttributes));
        add(texte);
        texte.setBounds(220, 400, 300, 50);

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
        JLabel label6 = new JLabel("Choisir .txt");
        add(label6);
        label6.setBounds(410, 350, 250, 50);
        liste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (liste.getSelectedItem().toString().equals("Taille de base")) {
                    longueur.setText("100");
                    largeur.setText("200");
                }
                if (liste.getSelectedItem().toString().equals("Petite plage")) {
                    longueur.setText("75");
                    largeur.setText("100");
                }
                if (liste.getSelectedItem().toString().equals("Grande plage")) {
                    longueur.setText("150");
                    largeur.setText("350");
                }

            }
        });

        liste2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (liste2.getSelectedItem().toString().equals("Délimitation de base")) {
                    mer.setText("50");
                }
                if (liste2.getSelectedItem().toString().equals("Délimitation basse")) {
                    mer.setText("100");
                }
                if (liste2.getSelectedItem().toString().equals("Délimitation haute")) {
                    mer.setText("30");
                }
            }
        });

        liste3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Nombre d'individus : " + liste3.getSelectedItem().toString());
                if (liste3.getSelectedItem().toString().equals("Individus de base")) {
                    personne.setText("500");
                }
                if (liste3.getSelectedItem().toString().equals("Peu d'individus")) {
                    personne.setText("200");
                }
                if (liste3.getSelectedItem().toString().equals("Beaucoup d'individus")) {
                    personne.setText("1500");
                }
            }
        });

        liste4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Météo : " + liste4.getSelectedItem().toString());
                if (liste4.getSelectedItem().toString().equals("Soleil")) {
                    meteo.setText("1");
                }
                if (liste4.getSelectedItem().toString().equals("Nuageux")) {
                    meteo.setText("2");
                }
                if (liste4.getSelectedItem().toString().equals("Pluie")) {
                    meteo.setText("3");
                }
            }
        });

        liste5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (liste5.getSelectedItem().toString().equals("Vitesse de base")) {
                    vent.setText("20");
                }
                if (liste5.getSelectedItem().toString().equals("Vitesse faible")) {
                    vent.setText("10");
                }
                if (liste5.getSelectedItem().toString().equals("Vitesse forte")) {
                    vent.setText("80");
                }
            }

        });




        JLabel tex = new JLabel("Choisir .txt :");
        tex.setFont(new Font("Verdana", Font.BOLD, 12));
        add(tex);
        tex.setBounds(415,-5,300,50);

        Image image1 = new ImageIcon("image.png").getImage().getScaledInstance(25, 25  , Image.SCALE_DEFAULT);
        JCheckBox check1 = new JCheckBox(new ImageIcon(image1));
        add(check1);
        check1.setBackground(Color.decode("#dcf9fa"));
        check1.setSize(25,25);
        check1.setBounds(500,2,200,38);
        check1.setFont(new Font("Verdana", Font.BOLD, 11));
        check1.setToolTipText("<html>Dans le fichier.txt doit être écrit : <br/>1er ligne : Largeur <br/> 2e ligne : Longueur <br/> 3e ligne : Trait de côte <br/> 4e ligne : Nombre d'individus <br/> 5e ligne : Vitesse du vent <br/> 6e ligne : Météo (1 = Soleil, <br/> 2 = Nuageux, 3 = Pluie)</html>");

        JButton bouton = new JButton("Sauvegarder");
        bouton.setBounds(30,366,120,26);
        add(bouton);
        bouton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {

                Frame f = new JFrame("TextField Example");



                JTextField t1;
                t1 = new JTextField("");
                t1.setBounds(170, 100, 200, 25);
                f.add(t1);

                JLabel titre = new JLabel("Saisir le nom du fichier :");
                titre.setBounds(200,82,300,15);
                f.add(titre);

                JButton bouton = new JButton("Valider");
                bouton.setBounds(215,125,100,20);
                f.add(bouton);

                bouton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(t1.getText().length() >= 1){
                            try {
                                File dossier = new File("Sauvegardes");
                                boolean res = dossier.mkdir();

                                if (res) {
                                    System.out.println("Le dossier sauvegardes a été créé.");
                                    System.out.println("Ajout de la sauvegarde");


                                    PrintWriter writer = new PrintWriter("Sauvegardes\\"+t1.getText()+".txt", "UTF-8");
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
                                    PrintWriter writer = new PrintWriter("Sauvegardes\\"+t1.getText()+".txt", "UTF-8");
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
                            System.out.println("oui");
                            f.dispose();
                        }
                        else{
                            System.out.println("Veuillez saisir un nom de fichier");
                        }


                                             }
                                         });



                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - getHeight()) / 2);
                f.setLocation(x, y);
                f.setSize(550, 250);
                ((JFrame) f).getContentPane().setBackground(Color.decode("#dcf9fa"));
                f.setLayout(null);
                f.setVisible(true);
            }});
/*

            //frame.dispose();
                String nomFichier;
                System.out.print("Saisir le nom du fichier : ");
                Scanner sc = new Scanner (System.in);
                nomFichier = sc.nextLine();



                try {
                    File dossier = new File("Sauvegardes");
                    boolean res = dossier.mkdir();

                    if (res) {
                        System.out.println("Le dossier sauvegardes a été créé.");
                        System.out.println("Ajout de la sauvegarde");


                        PrintWriter writer = new PrintWriter("Sauvegardes\\"+nomFichier+".txt", "UTF-8");
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
                        PrintWriter writer = new PrintWriter("Sauvegardes\\"+nomFichier+".txt", "UTF-8");
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
            }});



        check1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check1.isSelected() && Main.getDonnee().size() == 6) {
                    String lo;
                    String la;
                    String me;
                    String ind;
                    String v;
                    String met;
                    lo = Main.getDonnee().get(0);
                    la = Main.getDonnee().get(1);
                    me = Main.getDonnee().get(2);
                    ind = Main.getDonnee().get(3);
                    v = Main.getDonnee().get(4);
                    met = Main.getDonnee().get(5);
                    longueur.setText(lo);
                    largeur.setText(la);
                    mer.setText(me);
                    personne.setText(ind);
                    vent.setText(v);
                    meteo.setText(met);
                } else {
                    System.out.println("Les valeurs saisies ne sont pas bonnes");
                }
            }
        });

 */









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
