import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lectura_Datos{
	public static analizadorDeDatos crea_analizador(String ruta){
		
		// Este string podria darse como argumento de entrada.
		String csvFile = ruta;
		
		BufferedReader br = null;      			// Para leer el csv
		String line = "";						// La variable que almacena la linea actual en cada iteracion.
		String [] cabecera;						// La variable que almacena la primera linea.
		String cvsSplitBy = ",";				// El token separador.
		Map<Integer,List<String>> mapa = new TreeMap<Integer,List<String>>();
		String [] linea;						// Una linea de datos.
		int cont;								// El contador que nos indica la posicion actual a rellenar.
		List<Atributo> datos_atributos = new ArrayList<Atributo>();
		
		try{
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
			
		// Primero leemos la primera linea y la almacenamos en cabecera.
		cabecera = line.split(cvsSplitBy);
		// En este for relleno una lista con todos los objetos atributos (todav�a no tienen los valores a�adidos).
		for(int i = 0; i < cabecera.length; i++){
			datos_atributos.add(new Atributo(cabecera[i]));
		}
		
		linea = new String [cabecera.length];
		cont = 0;
		
		// En este punto ya he terminado con el procesamiento de los nombres de los atributos. Empiezo con los datos.		
		try{
			while ((line = br.readLine()) != null){
				
				linea = line.split(cvsSplitBy); 		// Aqui a�ado a la matriz la fila que toca.
				
				// Ahora relleno el mapa y el set de atributos.
				List<String> lista = new ArrayList<String>();
				for(int i = 0; i < linea.length;i++){
					lista.add(linea[i]);
					Atributo aux = datos_atributos.get(i);
					aux.add(linea[i]);
					// Con estas dos ultimas lineas deberia quedar a�adido el valor al objeto atributo.
				}
				mapa.put(cont, lista);
				
				cont++;
			}
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		analizadorDeDatos date = new analizadorDeDatos(mapa,datos_atributos);
		return date;
		
		// Aqu� unos prints para comprobar si los datos se han leido bien.
		/*
		for(int i = 0; i < datos_atributos.size(); i++){
			System.out.println(datos_atributos.get(i).toString());
		}
		System.out.println("\nPacientes.");
		for(int i = 0; i < mapa.size(); i++){
			System.out.println("Paciente " + i + ": " + mapa.get(i).toString());
		}
		*/
	}
}
