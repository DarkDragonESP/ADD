
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Leer_datos{
	public static void main(String [] args){
		
		// Este string podria darse como argumento de entrada.
		String csvFile = "C:\\Users\\Trethtzer\\Desktop\\Prueba.csv";
		
		BufferedReader br = null;      			// Para leer el csv
		String line = "";						// La variable que almacena la linea actual en cada iteración.
		String [] cabecera;						// La variable que almacena la primera linea.
		String cvsSplitBy = ",";				// El token separador.
		String [][] matriz;						// La matriz con los datos.
		int cont;								// El contador que nos indica la posición actual a rellenar.
		
		// Vamos a leer una linea por cada iteración del bucle while. 
		try{
			br = new BufferedReader(new FileReader(csvFile));
			
			// Primero leemos la primera linea y la almacenamos en cabecera.
			cabecera = line.split(cvsSplitBy);
			
			matriz = new String [100][cabecera.length];
			cont = 0;						// Necesito este contador para saber si me he quedado sin espacio.
			
			while ((line = br.readLine()) != null){
				
				if(cont >= matriz.length){		// Ampliación de matriz.
					Arrays.copyOf(matriz, matriz.length + 100);
				}
				// Aquí hacer lo que me pide Fran. Necesito saber que estructura quiere usar el y rellenársela.
				matriz[cont] = line.split(cvsSplitBy);
				cont++;
				//System.out.print(matriz[cont-1][0] + "\n");
			}
			br.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		/* La variable cont nos dice el número de paciente que tenemos. ¿Podría usar Arrays.copyof para descartar 
	 	las lineas sobrantes? */
	}
}
