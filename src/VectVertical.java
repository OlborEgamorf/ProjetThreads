public class VectVertical extends Vector {

    public void glissement() {
        y += sens*vitesse;
        if ((sens == 1 && y > objY) || (sens == -1 && y < objY)) {
            y = objY;
        }
    }
    
}
