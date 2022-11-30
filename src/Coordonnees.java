//package src;

public class Coordonnees implements Comparable<Coordonnees> {
    private double x;
    private double y;

    Coordonnees(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double[] getCoords() {
        return new double[]{x,y};
    }
    
    public int compareTo(Coordonnees coord) {
        if (x > coord.x) {
            return 1;
        } else if (x < coord.x) {
            return -1;
        } else {
            return 0;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return x+" "+y;
    }
    

}
