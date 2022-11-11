public abstract class Vector {
    protected double x, y, objX, objY;

    protected double vitesse;
    protected double coeff = 1;
    
    protected int sens;

    
    public Vector(double x, double y, double objX, double objY, double vitesse, double coeff) {
        this.x = x;
        this.y = y;
        this.objX = objX;
        this.objY = objY;
        this.vitesse = vitesse;
        this.coeff = coeff;
    }

    Vector(Vector vect) {
        this(vect.x,vect.y,vect.objX,vect.objY,vect.vitesse,vect.coeff);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double[] getCoords() {
        return new double[]{x,y};
    }

    public double getObjX() {
        return objX;
    }

    public double getObjY() {
        return objY;
    }

    public double[] getCoordsOnj() {
        return new double[]{objX,objY};
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setCoords(double[] coords) {
        this.x = coords[0];
        this.y = coords[1];
    }
    public void setCoords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setCoordsObj(double[] coords) {
        this.objX = coords[0];
        this.objY = coords[1];
    }
    public void setCoordsObj(double x, double y) {
        this.objX = x;
        this.objY = y;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public void setSens() {
        if (x-objX > 0) {
            sens = -1;
        } else {
            sens = 1;
        }
    }

    public boolean isArrived() {
        return x == objX && y == objY;
    }

    public static boolean isCoordsNull(double[] coords) {
        return coords[0] == -1 && coords[1] == -1;
    }

    public abstract void glissement();
    public abstract Vector copy();

    public double[] isCroisement(Vector vect){
        double incX = -1;
        double incY = -1;
        if (this instanceof VectOblique && vect instanceof VectOblique) {
            VectOblique vect1 = (VectOblique)this;
            VectOblique vect2 = (VectOblique)vect;

            if (vect2.getM() - vect1.getM() == 0) {
                return new double[]{-1,-1}; // Les deux droites sont parallèles
            }

            double testX = (vect1.getP() - vect2.getP())/(vect2.getM() - vect1.getM());
            double testY = vect1.getM()*testX + vect1.getP();

            if (((vect1.objX >= testX && testX >= vect1.x) || (vect1.objX <= testX && testX <= vect1.x)) && ((vect1.objY >= testY && testY >= vect1.y) || (vect1.objY <= testY && testY <= vect1.y))) {
                incX = testX;
                incY = testY;
            }

        } else if (this instanceof VectHorizontal && vect instanceof VectHorizontal) {
            if (this.y == vect.y) {
                incX = x;
                incY = y;
            }

        } else if (this instanceof VectVertical && vect instanceof VectVertical) {
            if (this.x == vect.x) {
                incX = x;
                incY = y;
            }

        } else if ((this instanceof VectOblique || vect instanceof VectOblique) && ((this instanceof VectHorizontal || vect instanceof VectHorizontal))) {
            VectOblique vectO;
            VectHorizontal vectH;
            if (this instanceof VectOblique) {
                vectO = (VectOblique)this;
                vectH = (VectHorizontal)vect;
            } else {
                vectH = (VectHorizontal)this;
                vectO = (VectOblique)vect;
            }

            double testX = vectH.y/vectO.getM() - vectO.getP();

            if ((vectO.objX >= testX && testX >= vectO.x) || (vectO.objX <= testX && testX <= vectO.x)) {
                incX = testX;
                incY = y;
            }

        } else if ((this instanceof VectOblique || vect instanceof VectOblique) && ((this instanceof VectVertical || vect instanceof VectVertical))) {
            VectOblique vectO;
            VectVertical vectV;

            if (this instanceof VectOblique) {
                vectO = (VectOblique)this;
                vectV = (VectVertical)vect;
            } else {
                vectV = (VectVertical)this;
                vectO = (VectOblique)vect;
            }

            double testY = vectV.x * vectO.getM() - vectO.getP();

            if ((vectO.objY >= testY && testY >= vectO.y) || (vectO.objY <= testY && testY <= vectO.y)) {
                incX = x;
                incY = testY;
            }

        } else {
            VectHorizontal vectH;
            VectVertical vectV;

            if (this instanceof VectHorizontal) {
                vectH = (VectHorizontal)this;
                vectV = (VectVertical)vect;
            } else {
                vectV = (VectVertical)this;
                vectH = (VectHorizontal)vect;
            }

            if (((vectH.objX >= vectV.x && vectV.x >= vectH.x) || (vectH.objX <= vectV.x && vectV.x <= vectH.x)) && ((vectV.objY >= vectH.y && vectH.y >= vectV.y) || (vectV.objY <= vectH.y && vectH.y <= vectV.y))) {
                incX = vectV.x;
                incY = vectH.y;
            }
        }
        return new double[]{incX,incY};
    }

    public static boolean isCollision(Vector vect1, Vector vect2) {
        boolean flag = false;
        while (!vect1.isArrived() && !vect2.isArrived() && !flag) {
            vect1.glissement();
            vect2.glissement();
            if (Math.sqrt(Math.pow(vect1.x - vect2.x, 2) + Math.pow(vect1.y - vect2.y,2)) < 1) {
                flag = true;
            }
        }
        return flag;
    }

    
}
