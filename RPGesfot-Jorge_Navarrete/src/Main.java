import Juego.GestorJuego;
import Personajes.*;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static GestorJuego gestor = new GestorJuego();

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {

                case 1:
                    crearGuerrero();
                    break;

                case 2:
                    crearMago();
                    break;

                case 3:
                    crearArquero();
                    break;

                case 4:
                    crearPaladin();
                    break;

                case 5:
                    gestor.mostrarPersonajes();
                    break;

                case 6:
                    buscarPersonaje();
                    break;

                case 7:
                    atacarPersonaje();
                    break;

                case 8:
                    utilizarHabilidad();
                    break;

                case 9:
                    combatir();
                    break;

                case 10:
                    gestor.mostrarPersonajesVivos();
                    break;

                case 11:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción invalida.");
            }

        } while (opcion != 11);

        scanner.close();
    }

    private static void mostrarMenu() {

        System.out.println("=============================");
        System.out.println("   ESFOT RPG   ");
        System.out.println("=============================");

        System.out.println("1. Crear Guerrero");
        System.out.println("2. Crear Mago");
        System.out.println("3. Crear Arquero");
        System.out.println("4. Crear Paladín");
        System.out.println("5. Mostrar personajes");
        System.out.println("6. Buscar personaje por nombre");
        System.out.println("7. Atacar a otro personaje");
        System.out.println("8. Utilizar habilidad especial");
        System.out.println("9. Simular combate por turnos");
        System.out.println("10. Mostrar personajes vivos");
        System.out.println("11. Salir");

        System.out.println("==================================");
    }

    private static void crearGuerrero() {

        System.out.println("===== CREAR GUERRERO =====");

        try {
            String nombre = leerTexto("Nombre: ");
            int nivel = leerEntero("Nivel: ");
            int vida = leerEntero("Vida: ");
            int vidaMaxima = leerEntero("Vida maxima: ");
            int ataque = leerEntero("Ataque: ");
            int defensa = leerEntero("Defensa: ");
            int fuerza = leerEntero("Fuerza: ");

            Guerrero guerrero = new Guerrero(
                    nombre, nivel, vida, vidaMaxima,
                    ataque, defensa, fuerza
            );

            gestor.agregarPersonaje(guerrero);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void crearMago() {

        System.out.println("===== CREAR MAGO =====");

        try {
            String nombre = leerTexto("Nombre: ");
            int nivel = leerEntero("Nivel: ");
            int vida = leerEntero("Vida: ");
            int vidaMaxima = leerEntero("Vida maxima: ");
            int ataque = leerEntero("Ataque: ");
            int defensa = leerEntero("Defensa: ");
            int mana = leerEntero("Mana: ");

            Mago mago = new Mago(
                    nombre, nivel, vida, vidaMaxima,
                    ataque, defensa, mana
            );

            gestor.agregarPersonaje(mago);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void crearArquero() {

        System.out.println("===== CREAR ARQUERO =====");

        try {
            String nombre = leerTexto("Nombre: ");
            int nivel = leerEntero("Nivel: ");
            int vida = leerEntero("Vida: ");
            int vidaMaxima = leerEntero("Vida maxima: ");
            int ataque = leerEntero("Ataque: ");
            int defensa = leerEntero("Defensa: ");
            int flechas = leerEntero("Cantidad de flechas: ");

            Arquero arquero = new Arquero(
                    nombre, nivel, vida, vidaMaxima,
                    ataque, defensa, flechas
            );

            gestor.agregarPersonaje(arquero);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void crearPaladin() {

        System.out.println("===== CREAR PALADIN =====");

        try {
            String nombre = leerTexto("Nombre: ");
            int nivel = leerEntero("Nivel: ");
            int vida = leerEntero("Vida: ");
            int vidaMaxima = leerEntero("Vida maxima: ");
            int ataque = leerEntero("Ataque: ");
            int defensa = leerEntero("Defensa: ");
            int fuerza = leerEntero("Fuerza: ");
            int energia = leerEntero("Energia sagrada: ");

            Paladin paladin = new Paladin(
                    nombre, nivel, vida, vidaMaxima,
                    ataque, defensa, fuerza, energia
            );

            gestor.agregarPersonaje(paladin);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void buscarPersonaje() {

        System.out.println("\n===== BUSCAR PERSONAJE =====");

        String nombre = leerTexto("Ingrese el nombre: ");

        Personaje personaje = gestor.buscarPersonaje(nombre);

        if (personaje != null) {
            personaje.mostrarInformacion();
        } else {
            System.out.println("Personaje no encontrado.");
        }
    }

    private static void atacarPersonaje() {

        System.out.println("\n===== ATACAR PERSONAJE =====");

        String atacante = leerTexto("Nombre del atacante: ");
        String objetivo = leerTexto("Nombre del objetivo: ");

        gestor.atacarPersonaje(atacante, objetivo);
    }

    private static void utilizarHabilidad() {

        System.out.println("\n===== HABILIDAD ESPECIAL =====");

        String nombre = leerTexto("Nombre del personaje: ");

        Personaje personaje = gestor.buscarPersonaje(nombre);

        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }

        if (personaje instanceof Paladin) {

            System.out.println("1. Curar a otro personaje");
            System.out.println("2. Activar escudo");

            int opcion = leerEntero("Seleccione habilidad: ");

            if (opcion == 1) {
                String objetivo = leerTexto("Nombre del objetivo: ");

                gestor.utilizarHabilidad(nombre, objetivo);

            } else if (opcion == 2) {
                gestor.utilizarHabilidad(nombre, "");

            } else {
                System.out.println("Opción inválida.");
            }

        } else if (personaje instanceof Mago
                || personaje instanceof Arquero) {

            String objetivo = leerTexto("Nombre del objetivo: ");

            gestor.utilizarHabilidad(nombre, objetivo);

        } else {
            System.out.println("El Guerrero no tiene una habilidad especial adicional.");
        }
    }

    private static void combatir() {

        System.out.println("\n===== COMBATE POR TURNOS =====");

        String nombre1 = leerTexto("Primer personaje: ");
        String nombre2 = leerTexto("Segundo personaje: ");

        gestor.simularCombatePorNombres(nombre1, nombre2);
    }

    private static String leerTexto(String mensaje) {

        while (true) {
            System.out.print(mensaje);

            String texto = scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("El texto no puede estar vacio.");
        }
    }

    private static int leerEntero(String mensaje) {

        while (true) {
            System.out.print(mensaje);

            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }
}