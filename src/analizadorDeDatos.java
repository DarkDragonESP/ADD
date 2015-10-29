
import java.util.*;

public class analizadorDeDatos {
	Map<Integer,List<String>> datos; //= new HashMap<Integer,List<String>>();
	List<Atributo> atributos;
	List<Boolean> filas;
	List<Boolean> columnas;

	public analizadorDeDatos(Map<Integer,List<String>> datos,List<Atributo> atributos){
		this.datos=datos;
		this.atributos=atributos;
		filas=new LinkedList<Boolean>();
		columnas=new LinkedList<Boolean>();
		for(Integer i=0;i<datos.size();i++){
			filas.add(new Boolean(true));
		}
		for(Integer i=0;i<datos.get(0).size();i++){
			columnas.add(new Boolean(true));
		}
	}

	/**
	 * @param datos
	 *            datos de entrada + atributos (True/False)
	 * @param nodo
	 *            nodo del arbol en el que estamos trabajando
	 * @param hijo
	 * 			  nºdel hijo si nodo no es vacio
	 */
	public void procesarDatos(analizadorDeDatos datos,Nodo nodo,int hijo){
		Map<Integer,Double> temp = new HashMap<Integer,Double>(); //columna,ganancia

		if(nodo == null){ //crear raiz y cancular ganancias
			double gananciaMax =0;
			int indice=0;
			for(int i=1;i<datos.getAtributos().size();i++){
				if( gananciaMax < Func_Mat.ganancia(datos.getDatos(),i)){
					indice=i;
				}	
			}
			nodo= new Nodo(datos.getAtributos().get(indice).getNombre());
			for(int i=0;i<datos.getAtributos().get(indice).getValores().size();i++){
				nodo.anadirHijo(datos.getAtributos().get(indice).getValores().get(i));
				procesarDatos(datos,)
			}

		}else{ // calcular hijos
			if(Func_Mat.valores(datos.getDatos(),0).size()==1){ // si solo hay un tipo de atributo de salida, este sera el hijo

			}else{ //procesar datos para cada uno de los tipos de nodo

			}
		}
	}

	public List<Boolean> getColumnas() {
		return columnas;
	}

	public void setColumnas(List<Boolean> columnas) {
		this.columnas = columnas;
	}

	public Map<Integer, List<String>> getDatos() {
		return datos;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public List<Boolean> getFilas() {
		return filas;
	}




}
