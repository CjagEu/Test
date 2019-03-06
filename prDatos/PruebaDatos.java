import java.util.Arrays;

import prDatos.Datos;				//al crearlo en el paquete por defecto hay que importar el paquete
import prDatos.DatosException;

public class PruebaDatos {			//recuerda para crear las clases 'Pruebas' se crea una clase y en la direccion se borra lo que halla.

	public static void main(String[] args) {
		try {
		double min = Double.parseDouble(args[0]);			//primero nos pasan el minimo y el maximo(se intuye al ver el toSTring)
		double max = Double.parseDouble(args[1]);
		Datos datos = new Datos(Arrays.copyOfRange(args, 2, args.length),min, max);
		System.out.println();
		System.out.println(datos);
		System.out.println();
		try {
			datos.setRango("0 ; 4");
			System.out.println(datos);
		} catch (DatosException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println();
		try {
			datos.setRango("15  25");
			System.out.println(datos);
		} catch (DatosException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println();
		try {
			datos.setRango("15 ; hola");
			System.out.println(datos);
		} catch (DatosException e) {
			System.err.println(e.getMessage());
		}
		
	} catch (IndexOutOfBoundsException e) {
		System.err.println("Error, no hay valores suficientes");
	} catch (NumberFormatException e) {
		System.err.println("Error, al convertir un valor a número real ("+e.getMessage()+")");
	} catch (Exception e) {
		System.err.println(e);
		
		
		}
	}

}
