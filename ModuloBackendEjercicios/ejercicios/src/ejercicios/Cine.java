package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cine{
	 private List<Pelicula> peliculas;

	    public Cine() {
	        this.peliculas = new ArrayList<>();
	    }

	    public void agregarPelicula(Pelicula pelicula) {
	        peliculas.add(pelicula);
	    }

	    public void mostrarPeliculas() {
	        System.out.println("Películas disponibles:");
	        for (int i = 0; i < peliculas.size(); i++) {
	            System.out.println((i + 1) + ". " + peliculas.get(i));
	        }
	    }

	    public Pelicula seleccionarPelicula(int opcion) {
	        if (opcion > 0 && opcion <= peliculas.size()) {
	            return peliculas.get(opcion - 1);
	        } else {
	            System.out.println("Selección no válida.");
	            return null;
	        }
	    }

	    public void procesarPagoSimulado(double monto) {
	        System.out.println("Procesando pago de $" + monto + "...");
	        System.out.println("Pago realizado exitosamente.");
	    }
	}

	class SalaCine {
	    private int filas;
	    private int columnas;
	    private boolean[][] asientos;

	    public SalaCine(int filas, int columnas) {
	        this.filas = filas;
	        this.columnas = columnas;
	        this.asientos = new boolean[filas][columnas];
	    }

	    public void mostrarAsientos() {
	        System.out.println("Asientos: (X = ocupado, O = disponible)");
	        for (int i = 0; i < filas; i++) {
	            for (int j = 0; j < columnas; j++) {
	                System.out.print(asientos[i][j] ? "X " : "O ");
	            }
	            System.out.println();
	        }
	    }

	    public boolean reservarAsiento(int fila, int columna) {
	        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
	            System.out.println("Asiento no válido.");
	            return false;
	        }
	        if (asientos[fila][columna]) {
	            System.out.println("El asiento ya está ocupado.");
	            return false;
	        }
	        asientos[fila][columna] = true;
	        return true;
	    }

	    public void liberarAsiento(int fila, int columna) {
	        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
	            asientos[fila][columna] = false;
	        }
	    }

	    public boolean estaOcupado(int fila, int columna) {
	        return asientos[fila][columna];
	    }
	}

	class Pelicula {
	    private String titulo;
	    private SalaCine sala;

	    public Pelicula(String titulo ,SalaCine sala){
	        this.titulo = titulo;
	        this.sala = sala;
	    }

	    public String getTitulo() {
	        return titulo;
	    }

	    public SalaCine getSala() {
	        return sala;
	    }

	    @Override
	    public String toString() {
	        return "Película: " + titulo ;
	    }
	}
   class ReservaCine {
     public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        Cine cine = new Cine();

	        // salas cine
	        SalaCine sala1 = new SalaCine(5, 5); 
	        SalaCine sala2 = new SalaCine(5, 5);
	        SalaCine sala3 = new SalaCine(5, 5);
	        SalaCine sala4 = new SalaCine(5, 5);

	        // peliculas
	        Pelicula pelicula1 = new Pelicula("Sex And The City", sala1);
	        Pelicula pelicula2 = new Pelicula("Amor y prejuicio", sala2);
	        Pelicula pelicula3 = new Pelicula("Interestellar", sala3);
	        Pelicula pelicula4 = new Pelicula("Charlie y la fábrica de chocolate", sala4);


	        // Agregar peliculas
	        cine.agregarPelicula(pelicula1);
	        cine.agregarPelicula(pelicula2);
	        cine.agregarPelicula(pelicula3);
	        cine.agregarPelicula(pelicula4);

	        // Menu
	        while (true) {
	            System.out.println("\n--- Sistema de Reserva de Cine ---");
	            cine.mostrarPeliculas();
	            System.out.print("Seleccione una película (o 0 para salir): ");
	            int seleccionPelicula = scanner.nextInt();

	            if (seleccionPelicula == 0) {
	                System.out.println("Saliendo del sistema...");
	                break;
	            }

	            Pelicula peliculaSeleccionada = cine.seleccionarPelicula(seleccionPelicula);
	            if (peliculaSeleccionada != null) {
	                SalaCine sala = peliculaSeleccionada.getSala();
	                sala.mostrarAsientos();

	                // Selección de asiento
	                System.out.print("Seleccione fila: ");
	                int fila = scanner.nextInt();
	                System.out.print("Seleccione columna: ");
	                int columna = scanner.nextInt();

	                if (sala.reservarAsiento(fila - 1, columna - 1)) {
	                    // Simulación de pago
	                    System.out.print("Ingrese el monto a pagar: ");
	                    double monto = scanner.nextDouble();
	                    cine.procesarPagoSimulado(monto);

	                    System.out.println("Reserva realizada exitosamente para la película " + peliculaSeleccionada.getTitulo());
	                } else {
	                    System.out.println("No se pudo completar la reserva.");
	                }
	            }
	        }

	        scanner.close();
	    }
}