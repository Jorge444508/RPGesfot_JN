package Personajes;

public class Personaje {

    private String nombre;
    private int nivel;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defensa;

    public Personaje(String nombre, int nivel, int vida,
                     int vidaMaxima, int ataque, int defensa) {

        setNombre(nombre);
        setNivel(nivel);
        setVidaMaxima(vidaMaxima);
        setVida(vida);
        setAtaque(ataque);
        setDefensa(defensa);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if (nivel <= 0) {
            throw new IllegalArgumentException("El nivel debe ser positivo.");
        }
        this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 0 || vida > vidaMaxima) {
            throw new IllegalArgumentException("Vida invalida.");
        }
        this.vida = vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        if (vidaMaxima <= 0) {
            throw new IllegalArgumentException("La vida maxima debe ser positiva.");
        }
        this.vidaMaxima = vidaMaxima;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        if (ataque < 0) {
            throw new IllegalArgumentException("El ataque no puede ser negativo.");
        }
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        if (defensa < 0) {
            throw new IllegalArgumentException("La defensa no puede ser negativa.");
        }
        this.defensa = defensa;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void recibirDanio(int cantidad) {
        if (!estaVivo() || cantidad <= 0) {
            return;
        }

        int danioReal = Math.max(1, cantidad - defensa);
        vida = Math.max(0, vida - danioReal);

        System.out.println(nombre + " recibio " + danioReal + " puntos de daño.");
        System.out.println("Vida restante: " + vida);
    }

    public void atacar(Personaje enemigo) {
        if (enemigo == null || !enemigo.estaVivo() || !estaVivo()) {
            System.out.println("No se puede realizar el ataque.");
            return;
        }

        System.out.println(nombre + " ataca a " + enemigo.getNombre());
        enemigo.recibirDanio(ataque);
    }

    public void atacar(Personaje enemigo, int bonificacion) {
        if (enemigo == null || !enemigo.estaVivo() || !estaVivo()) {
            System.out.println("No se puede realizar el ataque.");
            return;
        }

        if (bonificacion < 0) {
            System.out.println("La bonificacion no puede ser negativa.");
            return;
        }

        System.out.println(nombre + " realiza un ataque con bonificacion.");
        enemigo.recibirDanio(ataque + bonificacion);
    }

    public void mostrarInformacion() {
        System.out.println("--------------------------------");
        System.out.println("Nombre: " + nombre);
        System.out.println("Nivel: " + nivel);
        System.out.println("Vida: " + vida + "/" + vidaMaxima);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defensa: " + defensa);
        System.out.println("Estado: " + (estaVivo() ? "Vivo" : "Derrotado"));
        System.out.println("--------------------------------");
    }
}