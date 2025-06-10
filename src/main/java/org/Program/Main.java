package org.Program;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // ==== SECCIÓN 1: ANIMALES ====
        System.out.println("=== CLASIFICACIÓN DE ANIMALES ===");

        List<Animal> listaAnimales = Arrays.asList(
                new Animal("Oso", "Terrestre", "Masculino"),
                new Animal("Perro", "Terrestre", "Masculino"),
                new Animal("Ballena", "Acuático", "Femenino"),
                new Animal("Tiburón", "Acuático", "Masculino"),
                new Animal("Paloma", "Aéreo", "Femenino"),
                new Animal("Buitre", "Aéreo", "Masculino")
        );

        Map<String, List<Animal>> clasificacion = new TreeMap<>();

        // Agrupar animales por tipo
        listaAnimales.forEach(animal -> {
            clasificacion.computeIfAbsent(animal.getTipo(), k -> new ArrayList<>()).add(animal);
        });

        // Mostrar clasificación
        clasificacion.forEach((tipo, animales) -> {
            System.out.println(tipo + ":");
            animales.forEach(a -> System.out.println("  - " + a.getNombre()));
        });

        // ==== SECCIÓN 2: PERSONAS ====
        System.out.println("\n=== OPERACIONES CON PERSONAS ===");

        List<Persona> listaPersonas = Arrays.asList(
                new Persona("Juan", "Morales", 40, "M", 5000000, "Director"),
                new Persona("Luis", "Pérez", 35, "M", 3000000, "Director"),
                new Persona("Ana", "Martínez", 28, "F", 2500000, "Desarrollador"),
                new Persona("Carla", "Mejía", 30, "F", 2700000, "Diseñadora"),
                new Persona("Luisa", "Ramírez", 25, "F", 4000000, "Desarrollador"),
                new Persona("Mario", "Suárez", 45, "M", 3500000, "Contador")
        );

        // a. Mostrar sueldo por 8 horas para directores hombres
        System.out.println("\n-- Sueldo por 8 horas (Directores Masculinos) --");
        listaPersonas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("Director") && p.getGenero().equalsIgnoreCase("M"))
                .peek(p -> System.out.print("Nombre: " + p.getNombre() + " " + p.getApellido() + " - "))
                .map(p -> p.getSueldoHora() * 8)
                .forEach(sueldo -> System.out.println("Sueldo por 8 horas: $" + sueldo));

        // b. Mostrar primera mujer desarrolladora
        System.out.println("\n-- Primera desarrolladora mujer --");
        listaPersonas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("Desarrollador") && p.getGenero().equalsIgnoreCase("F"))
                .findFirst()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("No hay desarrolladoras en la lista.")
                );

        // c. Mostrar desarrollador que más gana por hora
        System.out.println("\n-- Desarrollador con mayor sueldo por hora --");
        Optional<Persona> masGana = listaPersonas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("Desarrollador"))
                .max(Comparator.comparing(Persona::getSueldoHora));

        if (masGana.isPresent()) {
            System.out.println("Mayor sueldo por hora: " + masGana.get());
        } else {
            System.out.println("No hay desarrolladores.");
        }

        // d. Mostrar mujeres ordenadas por nombre
        System.out.println("\n-- Mujeres ordenadas por nombre --");
        listaPersonas.stream()
                .filter(p -> p.getGenero().equalsIgnoreCase("F"))
                .sorted(Comparator.comparing(Persona::getNombre))
                .forEach(System.out::println);
    }
}
