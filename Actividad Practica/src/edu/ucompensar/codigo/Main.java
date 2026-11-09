import java.util.*;
import java.util.stream.Collectors;
public class Main {
   static class Estudiante {
       private String nombre;
       private String programa;
       private double nota;
       public Estudiante(String nombre, String programa, double nota) {
           this.nombre = nombre;
           this.programa = programa;
           this.nota = nota;
       }
       public String getNombre() {
           return nombre;
       }
       public String getPrograma() {
           return programa;
       }
       public double getNota() {
           return nota;
       }
       @Override
       public String toString() {
           return nombre + " | " + programa + " | Nota: " + nota;
       }
   }
   public static void main(String[] args) {
       List<Estudiante> estudiantes = new ArrayList<>();
       estudiantes.add(new Estudiante("Juan", "Ingenieria", 4.5));
       estudiantes.add(new Estudiante("Maria", "Software", 4.8));
       estudiantes.add(new Estudiante("Carlos", "Ingenieria", 2.8));
       estudiantes.add(new Estudiante("Laura", "Software", 4.2));
       estudiantes.add(new Estudiante("Pedro", "Administracion", 3.5));
       estudiantes.add(new Estudiante("Andres", "Ingenieria", 2.5));
       estudiantes.add(new Estudiante("Sofia", "Software", 4.7));
       estudiantes.add(new Estudiante("Daniel", "Administracion", 3.8));
       estudiantes.add(new Estudiante("Camila", "Administracion", 2.9));
       estudiantes.add(new Estudiante("David", "Ingenieria", 3.9));
       System.out.println(" LISTA DE ESTUDIANTES ");
       estudiantes.forEach(System.out::println);
       List<Estudiante> aprobados = estudiantes.stream()
               .filter(e -> e.getNota() >= 3.0)
               .collect(Collectors.toList());
       System.out.println("\n ESTUDIANTES APROBADOS ");
       aprobados.forEach(System.out::println);
       List<Estudiante> ordenados = estudiantes.stream()
               .sorted(
                   Comparator.comparingDouble(Estudiante::getNota)
                   .reversed()
                   .thenComparing(Estudiante::getNombre)
               )
               .collect(Collectors.toList());
       System.out.println("\n ORDENADOS POR NOTA ");
       ordenados.forEach(System.out::println);
       List<Estudiante> mejores = ordenados.stream()
               .limit(3)
               .collect(Collectors.toList());
       System.out.println("\n LOS TRES MEJORES ");
       mejores.forEach(System.out::println);
       OptionalDouble promedio = estudiantes.stream()
               .mapToDouble(Estudiante::getNota)
               .average();
       System.out.println("\n PROMEDIO GENERAL ");
       if (promedio.isPresent()) {
           System.out.println("Promedio: " + promedio.getAsDouble());
       } else {
           System.out.println("No hay estudiantes registrados.");
       }
       Map<String, Double> promedioPrograma = estudiantes.stream()
               .collect(Collectors.groupingBy(
                   Estudiante::getPrograma,
                   Collectors.averagingDouble(Estudiante::getNota)
               ));
       System.out.println("\n PROMEDIO POR PROGRAMA ");
       promedioPrograma.forEach((programa, nota) ->
               System.out.println(programa + ": " + nota)
       );
       Map<Boolean, List<Estudiante>> particion = estudiantes.stream()
               .collect(Collectors.partitioningBy(
                   e -> e.getNota() >= 3.0
               ));
       System.out.println("\n PARTICION DE ESTUDIANTES ");
       System.out.println("Aprobados:");
       particion.get(true).forEach(System.out::println);
       System.out.println("Reprobados:");
       particion.get(false).forEach(System.out::println);
       Map<String, Double> mapaNotas = estudiantes.stream()
               .collect(Collectors.toMap(
                   Estudiante::getNombre,
                   Estudiante::getNota
               ));
       System.out.println("\n MAPA NOMBRE Y NOTA ");
       mapaNotas.forEach((nombre, nota) ->
               System.out.println(nombre + " -> " + nota)
       );
   }
}