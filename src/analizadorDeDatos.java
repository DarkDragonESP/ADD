
import java.util.*;

public class analizadorDeDatos {
	Map<Integer,List<String>> datos;
	List<Atributo> atributos;

	public analizadorDeDatos(Map<Integer,List<String>> datos,List<Atributo> atributos){
		this.datos=datos;
		this.atributos=atributos;
	}

	/**
	 * @param datos
	 *            datos de entrada + atributos (True/False)
	 * @param nodo
	 *            nodo del arbol en el que estamos trabajando
	 * @param hijo
	 * 			  nºdel hijo si nodo no es vacio
	 */
	public Nodo procesarDatos(List<Boolean> filas,List<Boolean> columnas,String rama){
		Nodo nodo;
		//si entropia vale cero es un nodo hoja su atributo(todo si o no)
		if(rama == " "){ //crear raiz y calcular ganancias
			double gananciaMax =0;
			int indice=0;
			for(int i=1;i<this.getAtributos().size();i++){
				if( gananciaMax < Func_Mat.ganancia(filas,this.getDatos(),i)){
					indice=i;
				}	
			}
			nodo= new Nodo(this.getAtributos().get(indice).getNombre());
			for (String tipo : this.getAtributos().get(indice).getValores()){
				//crear dos nuevas listas filas2 y columnas2 segun la rama y atributo actual
				List<Boolean> filas2=filas,columnas2=columnas;//no terminado clonar y modificar
				nodo.anadirHijo(procesarDatos(filas2,columnas2,tipo));
			}
			
			

		}else{ // calcular hijos
			if(Func_Mat.valores(filas,this.getDatos(),0).size()==1){ // si solo hay un tipo de atributo de salida, este sera el hijo
				nodo= new Nodo("si"/*necesito el tipo del atributo para el que filas es True*/,rama);
			}else{ //procesar datos para cada uno de los tipos de nodo
				double gananciaMax =0;
				int indice=0;
				for(int i=1;i<this.getAtributos().size();i++){
					if(columnas.get(i) && gananciaMax < Func_Mat.ganancia(filas,this.getDatos(),i)){
						indice=i;
					}	
				}
				nodo= new Nodo(this.getAtributos().get(indice).getNombre());
				for (String tipo : this.getAtributos().get(indice).getValores()){
					//crear dos nuevas listas filas2 y columnas2 segun la rama y atributo actual
					List<Boolean> filas2=filas,columnas2=columnas;//no terminado clonar y modificar
					nodo.anadirHijo(procesarDatos(filas2,columnas2,tipo));
				}
			}
		}
		return nodo;
	}



	public Map<Integer, List<String>> getDatos() {
		return datos;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}


}