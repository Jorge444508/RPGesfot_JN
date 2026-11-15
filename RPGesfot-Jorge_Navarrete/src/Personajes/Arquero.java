package Personajes;

public class Arquero extends Personaje {

    private int flechas;

    public Arquero(String nombre, int nivel, int vida,
                   int vidaMaxima, int ataque, int defensa,
                   int flechas) {

        super(nombre, nivel, vida, vidaMaxima, ataque, defensa);
        setFlechas(flechas);
    }

    public int getFlechas() {
        return flechas;
    }

    public void setFlechas(int flechas) {
        if (flechas < 0) {
            throw new IllegalArgumentException("Las flechas no pueden ser negativas.");
        }
        this.flechas = flechas;
    }

    public void dispararFlecha(Personaje enemigo) {
        if (!estaVivo() || enemigo == null || !enemigo.estaVivo()) {
            System.out.println("No se puede disparar.");
            return;
        }

        if (flechas <= 0) {
            System.out.println("No tienes suficientes flechas.");
            return;
        }

        flechas--;

        System.out.println(getNombre() + " dispara una flecha.");
        enemigo.recibirDanio(getAtaque() + 5);

        System.out.println("Flechas restantes: " + flechas);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Flechas: " + flechas);
    }
}
