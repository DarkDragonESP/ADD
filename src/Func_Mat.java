import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Func_Mat {

	/**
	 * @param columnas
	 *            columnas válidas para realizar la ganancia
	 * @param filas
	 *            filas válidas para realizar la ganancia
	 * @param matriz
	 *            datos de entrada
	 * @param column
	 *            atributo sobre el cual se va a realizar la ganancia
	 * @return ganancia obtenida
	 */
	public static double ganancia(List<Boolean> filas,
			Map<Integer, List<String>> matriz, int column) {

		double ent = entropia(filas, matriz);

		double total = 0;
		for (boolean val:filas)
			if (val)
				total++;
		for (String value : valores(filas, matriz, column).keySet()) {
			double ent1 = (valores(filas, matriz, column).get(value) / total)
					* entropia_condicionada(filas, matriz, column, value);
			ent -= ent1;
			//System.out.println(ent1);
		}

		return ent;
	}

	/**
	 * @param matriz
	 *            datos de entrada
	 * @param column
	 *            atributo sobre el cual se realiza la entropia
	 * @param value
	 *            valor condicionado del atributo
	 * @return entropia condicionada al atributo y valor
	 */
	private static double entropia_condicionada(List<Boolean> filas, Map<Integer, List<String>> matriz,
			int column, String value) {

		double total = 0;
		Map<String, Integer> salidas = new HashMap<>();

		for (int index : matriz.keySet()) {
			if (filas.get(index)){
				List<String> atrs = matriz.get(index);

				if (atrs.get(column).equals(value)) {
					total++;
					String salida = atrs.get(0);

					if (salidas.containsKey(salida))
						salidas.put(salida, salidas.get(salida) + 1);
					else
						salidas.put(salida, 1);
				}
			}
		}

		double res = 0.0;
		for (String item : salidas.keySet()) {
			double num = salidas.get(item);
			double e;
			try {
				if(num/total ==0){ //log10 0 == NaN ,NaN no es una exepcion (?)
					e=0;
				}else{
					e = (num / total) * (Math.log10(num / total) / Math.log10(2));
				}
			} catch (Exception ex) {
				e = 0;
			}
			res -= e;
			//System.out.println( " div es "+num +"/"+total);
		}
//System.out.println("Entropia condicionada de "+value+" es "+res);
		return res;
	}
	
	/**
	 * @param matriz
	 *            datos de entrada
	 * @return nombre del atributo que más se repite
	 */
	public static String mayoritario(Map<Integer, List<String>> matriz) {
		Map<String, Integer> salidas = new HashMap<>();
		for (int index : matriz.keySet()) {
			List<String> atrs = matriz.get(index);

			String element = atrs.get(0);

			if (salidas.containsKey(element))
				salidas.put(element, salidas.get(element) + 1);
			else
				salidas.put(element, 1);
		}

		int cantidad = 0;
		String result = null;
		for (String nodo : salidas.keySet()) {
			int this_cantidad = salidas.get(nodo);
			if (this_cantidad > cantidad) {
				cantidad = this_cantidad;
				result = nodo;
			}
		}

		return result;
	}

	/**
	 * @param filas
	 *            filas válidas
	 * @param matriz
	 *            datos de entrada
	 * @param column
	 *            atributo sobre el cual se obtendrÃ¡n los valores
	 * @return distintos valores del atributo junto con su cardinalidad
	 */
	public static Map<String, Integer> valores(List<Boolean> filas,
			Map<Integer, List<String>> matriz, int column) {
		Map<String, Integer> salidas = new HashMap<>();

		for (int index : matriz.keySet()) {
			if (filas.get(index)){
				List<String> atrs = matriz.get(index);

				String element;
				if (column != -1) {
					element = atrs.get(column);
				} else {
					//Suponemos que las salidas están en la primera columna
					element = atrs.get(0);
				}

				if (salidas.containsKey(element))
					salidas.put(element, salidas.get(element) + 1);
				else
					salidas.put(element, 1);
			}
		}

		return salidas;
	}

	/**
	 * @param columnas
	 *            columnas válidas
	 * @param matriz
	 *            datos de entrada
	 * @return entropÃ­a sin condicionar
	 */
	private static double entropia(List<Boolean> filas, Map<Integer, List<String>> matriz) {

		//Suponemos que las salidas son la primera columna
		Map<String, Integer> salidas = valores(filas, matriz, -1);
		double total = 0;
		for (boolean val:filas)
			if (val)
				total++;
		double res = 0.0;
		for (String item : salidas.keySet()) {
			int num = salidas.get(item);
			double e;
			try {
				if(num/total ==0){ //log10 0 == NaN ,NaN no es una exepcion (?)
					e=0;
				}else{
					e = (num / total) * (Math.log10(num / total) / Math.log10(2));
				}

			} catch (Exception ex) {
				e = 0;
			}
			res -= e;
		}
		return res;
	}

}
