//package src;
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
        y += sensY*vitesse*Coeff.getCoeff();
        if ((sensY == 1 && y > objY) || (sensY == -1 && y < objY)) {
            y = objY;
        }
    }

    public Coordonnees croisementRectangle(Rectangle rect) {
        boolean flag = false;
        for (double i=rect.getA().getX();i<=rect.getB().getX() && !flag;i+=0.1) {
            if (y+i <= rect.getA().getY() && y+i >= rect.getD().getY()) {
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
            } while ((sensY == 1 && vectCop.x < rect.getD().getY()) || (sensY == -1 && vectCop.x > rect.getA().getY()));

            return new Coordonnees(colX, colY);
            
        }
        return new Coordonnees(-1,-1);
    }
    
}
