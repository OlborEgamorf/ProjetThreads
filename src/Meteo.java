package src;

public enum Meteo {
    Soleil (1), Nuageux (2), Pluie (3);

    private int nombre;
    Meteo(int nombre) {
        this.nombre = nombre;
    }

    public int getNombre(){
        return this.nombre;
    }
}
