package ejercicios;

import java.util.Scanner;

public class CajeroAutomatico {
	

private String numeroCuenta;
private String pin;
private double saldo;


public CajeroAutomatico(String numeroCuenta, String pin, double saldoInicial) {
	this.numeroCuenta=numeroCuenta;
	this.pin=pin;
	this.saldo=saldoInicial;
}
public boolean iniciarSesion(String numeroCuenta, String pin){
	return this.numeroCuenta.equals(numeroCuenta)&&this.pin.equals(pin);
}

public double consultarSaldo() {
	return saldo;
}

//depositar dinero

public void depositarDinero(double monto) {
	if (monto > 0) {
        saldo += monto;
        System.out.println("Has depositado $" + monto);
    } else {
        System.out.println("El monto debe ser mayor a 0.");
    }
}



//retirar 
public void retirarDinero(double monto) {
    if (monto > 0 && monto <= saldo) {
        saldo -= monto;
        System.out.println("Has retirado $" + monto);
    } else if (monto > saldo) {
        System.out.println("Saldo insuficiente.");
    } else {
        System.out.println("El monto debe ser mayor a 0.");
    }
}

	public static void main(String[] args) {
		  Scanner scanner = new Scanner(System.in);

	        // Datos 
	        CajeroAutomatico cajero = new CajeroAutomatico("21568899", "2390", 1000.00);

	        // Iniciar sesión
	        System.out.print("Ingrese su número de cuenta: ");
	        String numeroCuenta = scanner.nextLine();

	        System.out.print("Ingrese su PIN: ");
	        String pin = scanner.nextLine();

	        if (cajero.iniciarSesion(numeroCuenta, pin)) {
	            int opcion;
	            do {
	                System.out.println("\n--- Menú Cajero Automático ---");
	                System.out.println("1. Consultar saldo");
	                System.out.println("2. Depositar dinero");
	                System.out.println("3. Retirar dinero");
	                System.out.println("4. Salir");
	                System.out.print("Elija una opción: ");
	                opcion = scanner.nextInt();

	                switch (opcion) {
	                    case 1:
	                        System.out.println("Su saldo actual es: $" + cajero.consultarSaldo());
	                        break;
	                    case 2:
	                        System.out.print("Ingrese el monto a depositar: ");
	                        double deposito = scanner.nextDouble();
	                        cajero.depositarDinero(deposito);
	                        break;
	                    case 3:
	                        System.out.print("Ingrese el monto a retirar: ");
	                        double retiro = scanner.nextDouble();
	                        cajero.retirarDinero(retiro);
	                        break;
	                    case 4:
	                        System.out.println("Gracias por usar el cajero automático.");
	                        break;
	                    default:
	                        System.out.println("Opción no válida.");
	                }
	            } while (opcion != 4);

	        } else {
	            System.out.println("Número de cuenta o PIN incorrectos.");
	        }

	        scanner.close();

	}

}
