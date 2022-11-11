public class VectHorizontal extends Vector {
    
    public void glissement() {
        x += sens*vitesse;
        if ((sens == 1 && x > objX) || (sens == -1 && x < objX)) {
            x = objX;
        }
    }
}