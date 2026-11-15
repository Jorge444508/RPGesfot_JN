package Personajes;

public class Paladin extends Guerrero {

    private int energiaSagrada;
    private boolean escudoActivo;

    public Paladin(String nombre, int nivel, int vida,
                   int vidaMaxima, int ataque, int defensa,
                   int fuerza, int energiaSagrada) {

        super(nombre, nivel, vida, vidaMaxima,
                ataque, defensa, fuerza);

        setEnergiaSagrada(energiaSagrada);
        this.escudoActivo = false;
    }

    public int getEnergiaSagrada() {
        return energiaSagrada;
    }

    public void setEnergiaSagrada(int energiaSagrada) {
        if (energiaSagrada < 0) {
            throw new IllegalArgumentException("La energia sagrada no puede ser negativa.");
        }

        this.energiaSagrada = energiaSagrada;
    }

    public boolean isEscudoActivo() {
        return escudoActivo;
    }

    public void curar(Personaje objetivo) {
        if (!estaVivo() || objetivo == null || !objetivo.estaVivo()) {
            System.out.println("No se puede realizar la curacion.");
            return;
        }

        if (energiaSagrada < 10) {
            System.out.println("No tienes suficiente energia sagrada.");
            return;
        }

        if (objetivo.getVida() == objetivo.getVidaMaxima()) {
            System.out.println("El personaje ya tiene su vida maxima.");
            return;
        }

        energiaSagrada -= 10;

        int vidaAnterior = objetivo.getVida();
        int nuevaVida = Math.min(
                objetivo.getVidaMaxima(),
                vidaAnterior + 20
        );

        objetivo.setVida(nuevaVida);

        System.out.println(getNombre() + " ha curado a "
                + objetivo.getNombre());

        System.out.println("Vida recuperada: "
                + (nuevaVida - vidaAnterior));

        System.out.println("Energía sagrada restante: "
                + energiaSagrada);
    }

    public void activarEscudo() {
        if (!estaVivo()) {
            System.out.println("Un personaje derrotado no puede activar el escudo.");
            return;
        }

        escudoActivo = true;
        System.out.println(getNombre() + " activo su escudo sagrado.");
    }

    @Override
    public void recibirDanio(int cantidad) {
        if (!estaVivo() || cantidad <= 0) {
            return;
        }

        if (escudoActivo) {
            System.out.println("¡El escudo sagrado redujo 5 puntos de daño!");
            cantidad = Math.max(0, cantidad - 5);
            escudoActivo = false;
        }

        super.recibirDanio(cantidad);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Energiia sagrada: " + energiaSagrada);
        System.out.println("Escudo activo: " + escudoActivo);
    }
}
