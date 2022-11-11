public class Rectangle {
    private double[] a;
    private double[] b;
    private double[] c;
    private double[] d;
    private double[] centre;
    

    public Rectangle(double[] a, double[] b, double[] c, double[] d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.centre = new double[]{(a[0]+c[0])/2,(a[1]+c[1])/2};
    }


    public boolean isIn(Rectange rect) {

    }
}
