//package src;

public class Case {
    private int x;
    private int y;
    private int id;
    private Type type;

    Case (int x, int y) {
        this.x = x;
        this.y = y;
        type = Type.VIDE;
    }

    public void setCase(int id, Type type){
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public int[] getCoords() {
        int[] coords = {x,y};
        return coords;
    }

    public String toString() {
        if (type == Type.VIDE) {
            return "0";
        } else if (type == Type.AFFAIRES) {
            return "2";
        } else if (type == Type.PERSONNE) {
            return "1";
        } else if (type == Type.TEMPORAIRE) {
            return "3";
        } else {
            return "?";
        }
    }
}