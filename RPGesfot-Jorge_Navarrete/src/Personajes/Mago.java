package Personajes;

public class Mago extends Personaje {

    private int mana;

    public Mago(String nombre, int nivel, int vida,
                int vidaMaxima, int ataque, int defensa,
                int mana) {

        super(nombre, nivel, vida, vidaMaxima, ataque, defensa);
        setMana(mana);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("El mana no puede ser negativo.");
        }
        this.mana = mana;
    }

    public void lanzarHechizo(Personaje enemigo) {
        if (!estaVivo() || enemigo == null || !enemigo.estaVivo()) {
            System.out.println("No se puede lanzar el hechizo.");
            return;
        }

        if (mana < 10) {
            System.out.println("No tienes suficiente mana.");
            return;
        }

        mana -= 10;

        System.out.println(getNombre() + " lanza un hechizo magico.");
        enemigo.recibirDanio(20);

        System.out.println("Mana restante: " + mana);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Mana: " + mana);
    }
}