package ejercicios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HabitosDiarios {
	 private String nombre;
	    private int progreso; //en porcentaje

	    public HabitosDiarios(String nombre) {
	        this.nombre = nombre;
	        this.progreso = 0;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public int getProgreso() {
	        return progreso;
	    }

	    public void registrarProgreso(int porcentaje) {
	        if (porcentaje >= 0 && porcentaje <= 100) {
	            this.progreso = porcentaje;
	        } else {
	            System.out.println("Porcentaje inválido. Debe estar entre 0 y 100.");
	        }
	    }

	    @Override
	    public String toString() {
	        return nombre + ": " + progreso + "% completado";
	    }
	}

	class SeguimientoHabitos {
	    private static Map<String, List<HabitosDiarios>> progresoSemanal = new HashMap<>();
	    private static Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        while (true) {
	            System.out.println("\n--- Seguimiento de Hábitos Diarios ---");
	            System.out.println("1. Añadir hábito");
	            System.out.println("2. Registrar progreso diario");
	            System.out.println("3. Ver resumen diario");
	            System.out.println("4. Ver resumen semanal");
	            System.out.println("5. Salir");
	            System.out.print("Seleccione una opción: ");
	            int opcion = scanner.nextInt();
	            scanner.nextLine(); //limpiar 

	            switch (opcion) {
	                case 1:
	                    agregarHabito();
	                    break;
	                case 2:
	                    registrarProgreso();
	                    break;
	                case 3:
	                    mostrarResumenDiario();
	                    break;
	                case 4:
	                    mostrarResumenSemanal();
	                    break;
	                case 5:
	                    System.out.println("Saliendo...");
	                    return;
	                default:
	                    System.out.println("Opción no válida. Intente de nuevo.");
	            }
	        }
	    }

	    // agregar hábito
	    private static void agregarHabito() {
	        System.out.print("Ingrese el nombre del hábito: ");
	        String nombreHabito = scanner.nextLine();
	        HabitosDiarios nuevoHabito = new HabitosDiarios(nombreHabito);

	        // Guardar el hábito
	        String diaActual = obtenerDia();
	        progresoSemanal.putIfAbsent(diaActual, new ArrayList<>());
	        progresoSemanal.get(diaActual).add(nuevoHabito);

	        System.out.println("Hábito añadido exitosamente.");
	    }

	    //registrar progreso diario 
	    private static void registrarProgreso() {
	        String diaActual = obtenerDia();
	        List<HabitosDiarios> habitosHoy = progresoSemanal.get(diaActual);

	        if (habitosHoy == null || habitosHoy.isEmpty()) {
	            System.out.println("No hay hábitos para hoy.");
	            return;
	        }

	        System.out.println("Seleccione el hábito para registrar el progreso:");
	        for (int i = 0; i < habitosHoy.size(); i++) {
	            System.out.println((i + 1) + ". " + habitosHoy.get(i).getNombre());
	        }

	        int seleccion = scanner.nextInt();
	        if (seleccion < 1 || seleccion > habitosHoy.size()) {
	            System.out.println("Selección no válida.");
	            return;
	        }

	        HabitosDiarios habitoSeleccionado = habitosHoy.get(seleccion - 1);
	        System.out.print("Ingrese el progreso del hábito (0-100%): ");
	        int progreso = scanner.nextInt();
	        habitoSeleccionado.registrarProgreso(progreso);
	        System.out.println("Progreso registrado.");
	    }

	    // mostrar resumen diario
	    private static void mostrarResumenDiario() {
	        String diaActual = obtenerDia();
	        List<HabitosDiarios> habitosHoy = progresoSemanal.get(diaActual);

	        if (habitosHoy == null || habitosHoy.isEmpty()) {
	            System.out.println("No hay hábitos registrados para hoy.");
	        } else {
	            System.out.println("Resumen diario:");
	            for (HabitosDiarios habito : habitosHoy) {
	                System.out.println(habito);
	            }
	        }
	    }

	    // resumen semanal
	    private static void mostrarResumenSemanal() {
	        System.out.println("Resumen semanal:");
	        for (String dia : progresoSemanal.keySet()) {
	            System.out.println("\nDía: " + dia);
	            List<HabitosDiarios> habitosDia = progresoSemanal.get(dia);
	            for (HabitosDiarios habito : habitosDia) {
	                System.out.println(habito);
	            }
	        }
	    }

	    //dia 
	    private static String obtenerDia() {
	        System.out.print("Ingrese el día (Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo): ");
	        return scanner.nextLine().trim();
	    }
}
