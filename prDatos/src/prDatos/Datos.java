package prDatos;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Datos {
	private double [] datos;
	private String [] errores;
	private double min;
	private double max;
	
	public Datos (String[] d,double min,double max ) {
		this.min = min;
		this.max = max;
		procesarDatos(d);
		/*
		datos = new double[d.length];		//creo un array para datos 'validos'.
		int nDatos = 0;						//aux para meter en datos
		errores = new String[d.length];		//creo un array para errores
		int nErrores = 0;					//aux para meter en erroes
		for(int i=0; i<d.length; i++) {		//recorro el array de datos que me dan
			try {							//este codigo es el subceptible de que se produzcan errores
				datos[nDatos] = Double.parseDouble(d[i]);		//Con parseDouble saco el numero de cada elemento del array [3 2 24 53 ] saca cada numero por separado
				nDatos++;						//meto el numero en el array Datos y aumento el contador nDatos
			} catch(NumberFormatException e) {	//si hay errores se ejecuta este codigo
				errores[nErrores] = d[i];		//meto el elemento en el array de errores
				nErrores++;						//aumento el iterador de errores.
			}
		}
		datos = Arrays.copyOf(datos,  nDatos);			//con esto ajustamos el array de datos y errores al tamaño que nos ha dado
		errores = Arrays.copyOf(errores, nErrores);		// en funcion de cuales datos del array inicial se han ido a 'datos' o a 'errores'
		*/
	}
	
	public double calcMedia() {		
		double suma = 0;
		int n = 0;
		for(int i = 0; i < datos.length; i++){
			if(min <= datos[i] && datos[i] <= max) {
				suma += datos[i];
				n++;
			}
		}
		if(n == 0) {
			throw new DatosException("Datos vacíos");
		}
		return suma / n;
	}
	
	public double calcDesvTipica(){
		double media = this.calcMedia();
		double suma = 0;
		int n = 0;
		for(int i = 0; i < datos.length; i++) {
			if(min <= datos[i] && datos[i] <= max) {
				suma += Math.pow(datos[i] - media, 2);
				n++;
			}
		}
		return Math.sqrt(suma / n);
	}
	
	public void setRango(String minmax) {		//creo que no esta bien, (la solucion es sin scanner)
		try(Scanner sc = new Scanner(minmax)){
			sc.useDelimiter("[;]");
			sc.useLocale(Locale.ENGLISH);
			String minr = sc.next();
			String maxr = sc.next();
			min = Double.parseDouble(minr);
			max = Double.parseDouble(maxr);
		} catch (IndexOutOfBoundsException e) {
			throw new DatosException("ERROR, no hay valores suficientes (" + minmax +")");
		} catch (NumberFormatException e) {
			throw new DatosException("ERROR al convertir los datos pasados por argumentos ("+e.getMessage()+")");
		} catch (Exception e) {
			throw new DatosException(e.getMessage());
		}
	}
	
	public double[] getDatos() {
		return datos;
	}
	
	public String[] getErrores() {
		return errores;
	}
	
	@Override
	public String toString() {		//mal
		String res = "Min: " + min + ", " + "Max: " + max + ", "+"\n[";
		for(int i = 0; i < datos.length-1; i++) {
			res += datos[i] + ", "; 
		}
		res += datos[datos.length-1] + "], " + "\n[";
		for( int j = 0; j < errores.length-1;j++) {
			res += errores[j] + "; ";
		}
		res += errores[errores.length-1] + "], ";
		res += "\nMedia: " + calcMedia() + ", " + "DesvTipica: " + calcDesvTipica();
		return res;
	}
	
	private void procesarDatos(String[] d) {
		int nDatos = 0;
		int nErrores = 0;
		datos = new double[d.length];
		errores = new String[d.length];
		for(int i = 0;i < d.length; i++) {
			try {
				datos[nDatos] = Double.parseDouble(d[i]);
				nDatos++;
			}catch (NumberFormatException e){
				errores[nErrores] = d[i];
				nErrores++;
			} 
		}
		datos = Arrays.copyOf(datos, nDatos);
		errores = Arrays.copyOf(errores, nErrores);
	}
}
