package Juego;

import Personajes.Arquero;
import Personajes.Mago;
import Personajes.Paladin;
import Personajes.Personaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorJuego {

    private List<Personaje> personajes;
    private Map<String, Personaje> registro;

    public GestorJuego() {
        personajes = new ArrayList<>();
        registro = new HashMap<>();
    }

    public boolean agregarPersonaje(Personaje personaje) {
        if (personaje == null) {
            System.out.println("Personaje invalido.");
            return false;
        }

        String nombre = personaje.getNombre().toLowerCase();

        if (registro.containsKey(nombre)) {
            System.out.println("Ya existe un personaje con ese nombre.");
            return false;
        }

        personajes.add(personaje);
        registro.put(nombre, personaje);

        System.out.println("Personaje registrado correctamente.");
        return true;
    }

    public Personaje buscarPersonaje(String nombre) {
        if (nombre == null) {
            return null;
        }

        return registro.get(nombre.trim().toLowerCase());
    }

    public boolean existePersonaje(String nombre) {
        return buscarPersonaje(nombre) != null;
    }

    public List<Personaje> getPersonajes() {
        return new ArrayList<>(personajes);
    }

    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No existen personajes registrados.");
            return;
        }

        System.out.println("===== LISTA DE PERSONAJES =====");
        for (Personaje personaje : personajes) {
            personaje.mostrarInformacion();
        }
    }

    public void mostrarPersonajesVivos() {
        boolean encontrado = false;

        System.out.println("===== PERSONAJES VIVOS =====");

        for (Personaje personaje : personajes) {
            if (personaje.estaVivo()) {
                personaje.mostrarInformacion();
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay personajes vivos.");
        }
    }

    public void atacarPersonaje(String nombreAtacante,
                                String nombreObjetivo) {

        Personaje atacante = buscarPersonaje(nombreAtacante);
        Personaje objetivo = buscarPersonaje(nombreObjetivo);

        if (atacante == null || objetivo == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }

        if (atacante == objetivo) {
            System.out.println("No puedes atacarte a ti mismo.");
            return;
        }

        if (!atacante.estaVivo() || !objetivo.estaVivo()) {
            System.out.println("Uno de los personajes esta derrotado.");
            return;
        }

        atacante.atacar(objetivo);
    }

    public void utilizarHabilidad(String nombrePersonaje,
                                  String nombreObjetivo) {

        Personaje personaje = buscarPersonaje(nombrePersonaje);

        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }

        if (!personaje.estaVivo()) {
            System.out.println("El personaje esta derrotado.");
            return;
        }

        if (nombreObjetivo != null && !nombreObjetivo.trim().isEmpty()) {
            Personaje objetivo = buscarPersonaje(nombreObjetivo);

            if (objetivo == null) {
                System.out.println("Objetivo no encontrado.");
                return;
            }

            if (!objetivo.estaVivo()) {
                System.out.println("El objetivo esta derrotado.");
                return;
            }

            if (objetivo == personaje) {
                System.out.println("No puedes seleccionar el mismo personaje.");
                return;
            }

            if (personaje instanceof Mago) {
                ((Mago) personaje).lanzarHechizo(objetivo);

            } else if (personaje instanceof Arquero) {
                ((Arquero) personaje).dispararFlecha(objetivo);

            } else if (personaje instanceof Paladin) {
                ((Paladin) personaje).curar(objetivo);

            } else {
                System.out.println("Este personaje no tiene esa habilidad.");
            }

        } else if (personaje instanceof Paladin) {
            ((Paladin) personaje).activarEscudo();

        } else {
            System.out.println("Esta habilidad necesita un objetivo.");
        }
    }

    public void simularCombate(Personaje primero, Personaje segundo) {

        if (primero == null || segundo == null) {
            System.out.println("Los personajes no existen.");
            return;
        }

        if (primero == segundo) {
            System.out.println("Debes seleccionar dos personajes diferentes.");
            return;
        }

        if (!primero.estaVivo() || !segundo.estaVivo()) {
            System.out.println("No se puede iniciar el combate con personajes derrotados.");
            return;
        }

        System.out.println("========== COMBATE RPG ==========");
        System.out.println(primero.getNombre() + " VS " + segundo.getNombre());

        int turno = 1;

        while (primero.estaVivo() && segundo.estaVivo()) {

            Personaje atacante;
            Personaje objetivo;

            if (turno % 2 != 0) {
                atacante = primero;
                objetivo = segundo;
            } else {
                atacante = segundo;
                objetivo = primero;
            }

            System.out.println("Turno " + turno + ": "
                    + atacante.getNombre() + " ataca a "
                    + objetivo.getNombre());

            atacante.atacar(objetivo);

            System.out.println("Vida de " + primero.getNombre()
                    + ": " + primero.getVida());

            System.out.println("Vida de " + segundo.getNombre()
                    + ": " + segundo.getVida());

            if (!objetivo.estaVivo()) {
                break;
            }

            turno++;
        }

        Personaje ganador = primero.estaVivo() ? primero : segundo;

        System.out.println("========== FIN DEL COMBATE ==========");
        System.out.println("GANADOR: " + ganador.getNombre());
    }

    public void simularCombatePorNombres(String nombre1, String nombre2) {

        Personaje primero = buscarPersonaje(nombre1);
        Personaje segundo = buscarPersonaje(nombre2);

        if (primero == null || segundo == null) {
            System.out.println("No se encontraron los personajes.");
            return;
        }

        simularCombate(primero, segundo);
    }
}
