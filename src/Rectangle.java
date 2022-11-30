//package src;

public class Rectangle {
    private double[] a;
    private double[] b;
    private double[] c;
    private double[] d;
    private double[] centre;    
    private int zone;

    public Rectangle(double[] a, double[] b, double[] c, double[] d, int zone) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.zone = zone;
        this.centre = new double[]{(a[0]+c[0])/2,(a[1]+c[1])/2};
    }


    public boolean isIn(Rectangle rect) {
        return (rect.a[0] >= a[0] && rect.a[0] <= b[0] && rect.a[1] >= d[1] && rect.a[1] <= a[1]) || (rect.b[0] >= a[0] && rect.b[0] <= b[0] && rect.b[1] >= d[1] && rect.b[1] <= a[1]) || (rect.c[0] >= a[0] && rect.c[0] <= b[0] && rect.c[1] >= d[1] && rect.c[1] <= a[1]) || (rect.d[0] >= a[0] && rect.d[0] <= b[0] && rect.d[1] >= d[1] && rect.d[1] <= a[1]);
    }


    public double[] getCentre() {
        return centre;
    }


    public int getZone() {
        return zone;
    }


    public double[] getD() {
        return d;
    }

    

}
