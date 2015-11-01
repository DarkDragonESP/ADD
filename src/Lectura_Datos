import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lectura_Datos{
	public static void main(String [] args){
		
		// Este string podria darse como argumento de entrada.
		String csvFile = "C:\\Users\\Trethtzer\\Desktop\\Prueba.csv";
		
		BufferedReader br = null;      			// Para leer el csv
		String line = "";						// La variable que almacena la linea actual en cada iteración.
		String [] cabecera;						// La variable que almacena la primera linea.
		String cvsSplitBy = ",";				// El token separador.
		Map<Integer,List<String>> mapa = new TreeMap<Integer,List<String>>();
		String [][] matriz;						// La matriz con los datos.
		int cont;								// El contador que nos indica la posición actual a rellenar.
		List<Atributo> datos_atributos = new ArrayList<Atributo>();
		
		// Vamos a leer una linea por cada iteración del bucle while. 
		try{
			br = new BufferedReader(new FileReader(csvFile));
			
			// Primero leemos la primera linea y la almacenamos en cabecera.
			cabecera = line.split(cvsSplitBy);
			// En este for relleno una lista con todos los objetos atributos (todavía no tienen los valores añadidos).
			for(int i = 0; i < cabecera.length; i++){
				Atributo aux = new Atributo(cabecera[i]);
				datos_atributos.add(aux);
			}
			
			matriz = new String [100][cabecera.length];
			cont = 0;						// Necesito este contador para saber si me he quedado sin espacio.
			
			// En este punto ya he terminado con el procesamiento de los nombres de los atributos. Empiezo con los datos.
			while ((line = br.readLine()) != null){
				// Primero relleno una matriz con los datos.
				if(cont >= matriz.length){					// Este if aumenta el tamaño de la matriz si se queda pequeña.
					Arrays.copyOf(matriz, matriz.length + 100);
				}
				matriz[cont] = line.split(cvsSplitBy); 		// Aquí añado a la matriz la fila que toca.
				
				// Ahora relleno el mapa y el set de atributos.
				List<String> lista = new ArrayList<String>();
				for(int i = 0; i < matriz[cont].length;i++){
					lista.add(matriz[cont][i]);
					Atributo aux = datos_atributos.get(i);
					aux.add(matriz[cont][i]);
					// Con estas dos últimas lineas deberia quedar añadido el valor al objeto atributo.
				}
				mapa.put(cont, lista);
				
				cont++;
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
