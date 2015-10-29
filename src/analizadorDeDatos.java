
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
	public Nodo procesarDatos(analizadorDeDatos datos,List<Boolean> filas,List<Boolean> columnas,String rama){
		Nodo nodo;

		if(rama == " "){ //crear raiz y calcular ganancias
			double gananciaMax =0;
			int indice=0;
			for(int i=1;i<datos.getAtributos().size();i++){
				if( gananciaMax < Func_Mat.ganancia(datos.getDatos(),i)){
					indice=i;
				}	
			}
			nodo= new Nodo(datos.getAtributos().get(indice).getNombre());
			for(int i=0;i<datos.getAtributos().get(indice).getValores().size();i++){
				//crear dos nuevas listas filas2 y columnas2 segun la rama y atributo actual
				List<Boolean> filas2=filas,columnas2=columnas;//no terminado clonar y modificar
				nodo.anadirHijo(procesarDatos(datos,filas2,columnas2,datos.getAtributos().get(indice).getValores().get(i)));
			}

		}else{ // calcular hijos
			if(Func_Mat.valores(datos.getDatos(),0).size()==1){ // si solo hay un tipo de atributo de salida, este sera el hijo
				nodo= new Nodo("si"/*necesito el tipo del atributo para el que filas es True*/,rama);
			}else{ //procesar datos para cada uno de los tipos de nodo
				double gananciaMax =0;
				int indice=0;
				for(int i=1;i<datos.getAtributos().size();i++){
					if(columnas.get(i) && gananciaMax < Func_Mat.ganancia(datos.getDatos(),i)){
						indice=i;
					}	
				}
				nodo= new Nodo(datos.getAtributos().get(indice).getNombre());
				for(int i=0;i<datos.getAtributos().get(indice).getValores().size();i++){
					//crear dos nuevas listas filas2 y columnas2 segun la rama y atributo actual
					List<Boolean> filas2=filas,columnas2=columnas;//no terminado clonar y modificar
					nodo.anadirHijo(procesarDatos(datos,filas2,columnas2,datos.getAtributos().get(indice).getValores().get(i)));
				}
			}
		}
		return nodo;
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
