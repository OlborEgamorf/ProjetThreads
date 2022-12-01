public class Rectangle implements Comparable<Rectangle> {
    private Coordonnees a;
    private Coordonnees b;
    private Coordonnees c;
    private Coordonnees d;
    private Coordonnees centre;    
    private int zone;
    private int id = -1;

    public Rectangle(double ax, double ay, double bx, double dy, int zone, int id) {
        this.a = new Coordonnees(ax, ay);
        this.b = new Coordonnees(bx, ay);;
        this.c = new Coordonnees(bx, dy);;
        this.d = new Coordonnees(ax, dy);;
        this.zone = zone;
        this.centre = new Coordonnees((ax+bx)/2,(ay+dy)/2);
        this.id = id;
    }


    public boolean isIn(Rectangle rect) {
        return (rect.a.getX() >= a.getX() && rect.a.getX() <= b.getX() && rect.a.getY() >= d.getY() && rect.a.getY() <= a.getY()) || (rect.b.getX() >= a.getX() && rect.b.getX() <= b.getX() && rect.b.getY() >= d.getY() && rect.b.getY() <= a.getY()) || (rect.c.getX() >= a.getX() && rect.c.getX() <= b.getX() && rect.c.getY() >= d.getY() && rect.c.getY() <= a.getY()) || (rect.d.getX() >= a.getX() && rect.d.getX() <= b.getX() && rect.d.getY() >= d.getY() && rect.d.getY() <= a.getY());
    }


    public Coordonnees getCentre() {
        return centre;
    }


    public int getZone() {
        return zone;
    }


    public Coordonnees getD() {
        return d;
    }


    public Coordonnees getA() {
        return a;
    }


    public Coordonnees getB() {
        return b;
    }


    public Coordonnees getC() {
        return c;
    }

    public int compareTo(Rectangle other) {
        if (d.getY() > other.d.getY()) {
            return 1;
        } else if (d.getY() < other.d.getY()) {
            return -1;
        } else {
            return 0;
        }
    }

    public int getIdRect() {
        return id;
    }
    

}
