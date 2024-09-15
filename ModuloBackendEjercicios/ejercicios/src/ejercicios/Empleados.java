package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Empleados {
	 private String nombre;
	    private double salario;
	    private List<Integer> evaluaciones;

	    public Empleados(String nombre, double salario) {
	        this.nombre = nombre;
	        this.salario = salario;
	        this.evaluaciones = new ArrayList<>();
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public double getSalario() {
	        return salario;
	    }

	    public void agregarEvaluacion(int evaluacion) {
	        evaluaciones.add(evaluacion);
	    }

	    public List<Integer> getEvaluaciones() {
	        return evaluaciones;
	    }

	    // Calcula bonificación 
	    public double calcularBonificacion() {
	        if (evaluaciones.isEmpty()) {
	            return 0;
	        }
	        double promedioEvaluaciones = evaluaciones.stream().mapToInt(Integer::intValue).average().orElse(0);
	        return salario * (promedioEvaluaciones / 100); 
	    }

	    @Override
	    public String toString() {
	        return "Empleado: " + nombre + ", Salario: " + salario + ", Evaluaciones: " + evaluaciones.toString();
	    }
	}

	class GestorEmpleados {
	    private List<Empleados> empleados;

	    public GestorEmpleados() {
	        empleados = new ArrayList<>();
	    }

	    public void agregarEmpleado(Empleados empleado) {
	        empleados.add(empleado);
	    }

	    public Empleados buscarEmpleado(String nombre) {
	        for (Empleados empleado : empleados) {
	            if (empleado.getNombre().equalsIgnoreCase(nombre)) {
	                return empleado;
	            }
	        }
	        return null;
	    }

	    public void mostrarEmpleados() {
	        if (empleados.isEmpty()) {
	            System.out.println("No hay empleados registrados.");
	        } else {
	            for (Empleados empleado : empleados) {
	                System.out.println(empleado);
	            }
	        }
	    }
	}

	 class SistemaGestionEmpleados {

	    public static void main(String[] args) {
	        GestorEmpleados gestor = new GestorEmpleados();
	        Scanner scanner = new Scanner(System.in);
	        int opcion;

	        do {
	            System.out.println("\n--- Sistema de Gestión de Empleados ---");
	            System.out.println("1. Agregar empleado");
	            System.out.println("2. Registrar evaluación");
	            System.out.println("3. Mostrar empleados");
	            System.out.println("4. Calcular bonificación");
	            System.out.println("0. Salir");
	            System.out.print("Seleccione una opción: ");
	            opcion = scanner.nextInt();
	            scanner.nextLine();  // Limpiar

	            switch (opcion) {
	                case 1:
	                    // Agregar empleado
	                    System.out.print("Ingrese el nombre del empleado: ");
	                    String nombre = scanner.nextLine();
	                    System.out.print("Ingrese el salario del empleado: ");
	                    double salario = scanner.nextDouble();
	                    gestor.agregarEmpleado(new Empleados(nombre, salario));
	                    break;

	                case 2:
	                    // Registrar evaluación
	                    System.out.print("Ingrese el nombre del empleado: ");
	                    String nombreEval = scanner.nextLine();
	                    Empleados empleado = gestor.buscarEmpleado(nombreEval);
	                    if (empleado != null) {
	                        System.out.print("Ingrese la evaluación (0-100): ");
	                        int evaluacion = scanner.nextInt();
	                        empleado.agregarEvaluacion(evaluacion);
	                    } else {
	                        System.out.println("Empleado no encontrado.");
	                    }
	                    break;

	                case 3:
	                    // Mostrar empleados
	                    gestor.mostrarEmpleados();
	                    break;

	                case 4:
	                    //  bonificación
	                    System.out.print("Ingrese el nombre del empleado: ");
	                    String nombreBon = scanner.nextLine();
	                    Empleados empleadoBon = gestor.buscarEmpleado(nombreBon);
	                    if (empleadoBon != null) {
	                        double bonificacion = empleadoBon.calcularBonificacion();
	                        System.out.println("Bonificación para " + empleadoBon.getNombre() + ": $" + bonificacion);
	                    } else {
	                        System.out.println("Empleado no encontrado.");
	                    }
	                    break;

	                case 0:
	                    System.out.println("Saliendo del sistema...");
	                    break;

	                default:
	                    System.out.println("Opción no válida.");
	            }
	        } while (opcion != 0);

	        scanner.close();
	    }
}
