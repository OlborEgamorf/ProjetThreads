public class VectVertical extends Vector {

    public VectVertical(double x, double y, double objX, double objY, double vitesse, int timing) {
        super(x, y, objX, objY, vitesse, timing);
    }

    public VectVertical(Vector vect) {
        super(vect);
    }

    public VectVertical copy() {
        return new VectVertical(this);
    } 

    public void glissement() {
        y += sensY*vitesse;
        if ((sensY == 1 && y > objY) || (sensY == -1 && y < objY)) {
            y = objY;
        }
    }

    public Coordonnees croisementRectangle(Rectangle rect) {
        boolean flag = false;
        for (double i=rect.getA()[0];i<=rect.getB()[0] && !flag;i+=0.1) {
            if (y+i <= rect.getA()[1] && y+i >= rect.getD()[1]) {
                flag = true;
            }
        }

        if (flag) {
            Vector vectCop = copy();
            double colX, colY;
            do {   
                colX = vectCop.x;
                colY = vectCop.y;
                vectCop.glissement();
            } while ((sensY == 1 && vectCop.x < rect.getD()[1]) || (sensY == -1 && vectCop.x > rect.getA()[1]));

            return new Coordonnees(colX, colY);
            
        }
        return new Coordonnees(-1,-1);
    }
    
}
