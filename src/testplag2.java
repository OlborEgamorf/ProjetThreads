package plage2;

import java.util.Arrays;
import java.util.Scanner;

public class testplag2 {
    public static void main(String [] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Longueur");
        double longueur = s.nextDouble();
        System.out.println("Largeur");
        double largeur = s.nextDouble();

        double tab1 [] = new double [2];
        tab1[0] = 0;
        tab1[1] = 0;
        double tab2 [] = new double [2];
        tab2[0] = 0;
        tab2[1] = longueur/2;
        double tab3 [] = new double [2];
        tab3[0] = largeur;
        tab3[1] = longueur/2;
        double tab4 [] = new double [2];
        tab4[0] = largeur;
        tab4[1] = 0;
        System.out.println("Coordonnées de la plage :");
        System.out.println(Arrays.toString(tab1));
        System.out.println(Arrays.toString(tab2));
        System.out.println(Arrays.toString(tab3));
        System.out.println(Arrays.toString(tab4));

        double tab5 [] = new double [2];
        tab5[0] = 0;
        tab5[1] = longueur/2;
        double tab6 [] = new double [2];
        tab6[0] = 0;
        tab6[1] = longueur;
        double tab7 [] = new double [2];
        tab7[0] = largeur;
        tab7[1] = longueur;
        double tab8 [] = new double [2];
        tab8[0] = largeur;
        tab8[1] = longueur/2;
        System.out.println("Coordonnées de la mer :");
        System.out.println(Arrays.toString(tab5));
        System.out.println(Arrays.toString(tab6));
        System.out.println(Arrays.toString(tab7));
        System.out.println(Arrays.toString(tab8));


    }

    public static int[] coordToArray(int x, int y) {
        int[] liste = {x,y};
        return liste;
    }

}
