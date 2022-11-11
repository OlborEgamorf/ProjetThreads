public class VectOblique extends Vector {
    private double m, p;

    

    public VectOblique(double x, double y, double objX, double objY, double vitesse, double coeff) {
        super(x, y, objX, objY, vitesse, coeff);
        setM();
        setP();

        if (objX > x) {
            sens = 1;
        } else {
            sens = -1;
        }

        System.out.println(x+" "+y+" "+objX+" "+objY+" "+vitesse+" "+m+" "+p);
    }

    VectOblique(VectOblique vect) {
        this(vect.x,vect.y,vect.objX,vect.objY,vect.vitesse,vect.coeff);
    }

    public VectOblique copy() {
        return new VectOblique(this);
    } 

    public double getM() {
        return m;
    }

    public double getP() {
        return p;
    }

    public void setM() {
        this.m = (objY-y)/(objX-x);
    }

    public void setP() {
        this.p = y-m*x;
    }

    public void glissement() {
        x = x + sens*vitesse / m;
        y = x * m + p;  
        if ((sens == 1 && x >= objX && y >= objY) || (sens == -1 && x <= objX && y <= objY)) {
            x = objX;
            y = objY;
        }
    }
}

//(1,2) -> (4,4)
