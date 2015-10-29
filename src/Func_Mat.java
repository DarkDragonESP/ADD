
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Func_Mat {

	/**
	 * @param matriz
	 *            datos de entrada
	 * @param column
	 *            atributo sobre el cual se va a realizar la ganancia
	 * @return ganancia obtenida
	 */
	public static double ganancia(Map<Integer, List<String>> matriz, int column) {

		double ent = entropia(matriz);

		int total = matriz.size();

		for (String value : valores(matriz, column).keySet()) {
			double ent1 = (valores(matriz, column).get(value) / total)
					* entropia_condicionada(matriz, column, value);

			ent -= ent1;
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
	private static double entropia_condicionada(Map<Integer, List<String>> matriz,
			int column, String value) {

		int total = 0;
		Map<String, Integer> salidas = new HashMap<>();

		for (int index : matriz.keySet()) {
			List<String> atrs = matriz.get(index);

			if (atrs.get(column).equals(value)) {
				total++;
				String salida = atrs.get(atrs.size() - 1);

				if (salidas.containsKey(salida))
					salidas.put(salida, salidas.get(salida) + 1);
				else
					salidas.put(salida, 1);
			}
		}

		double res = 0.0;
		for (String item : salidas.keySet()) {
			int num = salidas.get(item);
			double e;
			try {
				e = (num / total) * (Math.log10(num / total) / Math.log10(2));
			} catch (Exception ex) {
				e = 0;
			}
			res -= e;
		}

		return res;
	}

	/**
	 * @param matriz
	 *            datos de entrada
	 * @param column
	 *            atributo sobre el cual se obtendrán los valores
	 * @return distintos valores del atributo junto con su cardinalidad
	 */
	public static Map<String, Integer> valores(Map<Integer, List<String>> matriz,
			int column) {
		Map<String, Integer> salidas = new HashMap<>();

		for (int index : matriz.keySet()) {
			List<String> atrs = matriz.get(index);

			String element;
			if (column != -1) {
				element = atrs.get(column);
			} else {
				element = atrs.get(atrs.size() - 1);
			}

			if (salidas.containsKey(element))
				salidas.put(element, salidas.get(element) + 1);
			else
				salidas.put(element, 1);
		}

		return salidas;
	}

	/**
	 * @param matriz
	 *            datos de entrada
	 * @return entropía sin condicionar
	 */
	private static double entropia(Map<Integer, List<String>> matriz) {

		Map<String, Integer> salidas = valores(matriz, -1);
		int total = matriz.size();

		double res = 0.0;
		for (String item : salidas.keySet()) {
			int num = salidas.get(item);
			double e;
			try {
				e = (num / total) * (Math.log10(num / total) / Math.log10(2));
			} catch (Exception ex) {
				e = 0;
			}
			res -= e;
		}

		return res;
	}

}
