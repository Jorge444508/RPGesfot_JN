package Personajes;

public class Guerrero extends Personaje {

    private int fuerza;

    public Guerrero(String nombre, int nivel, int vida,
                    int vidaMaxima, int ataque, int defensa,
                    int fuerza) {

        super(nombre, nivel, vida, vidaMaxima, ataque, defensa);
        setFuerza(fuerza);
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        if (fuerza < 0) {
            throw new IllegalArgumentException("La fuerza no puede ser negativa.");
        }
        this.fuerza = fuerza;
    }

    @Override
    public void atacar(Personaje enemigo) {
        if (!estaVivo() || enemigo == null || !enemigo.estaVivo()) {
            System.out.println("No se puede atacar.");
            return;
        }

        System.out.println(getNombre() + " realiza un ataque de guerrero.");
        enemigo.recibirDanio(getAtaque() + fuerza);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Fuerza: " + fuerza);
    }
}