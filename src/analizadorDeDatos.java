
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
				System.out.println("Ganancia de "+this.getAtributos().get(i).nombre+" "+Func_Mat.ganancia(filas,this.getDatos(),i));
				if( gananciaMax < Func_Mat.ganancia(filas,this.getDatos(),i)){
					gananciaMax = Func_Mat.ganancia(filas,this.getDatos(),i);
					indice=i;
				}	
			}
			System.out.println("Nuevo nodo "+this.getAtributos().get(indice).toString());
			nodo= new Nodo(this.getAtributos().get(indice).getNombre());
			columnas.set(indice, false);
			//System.out.println(this.getAtributos().get(indice).getValores());
			for (String tipo : this.getAtributos().get(indice).getValores()){
				//crear dos nuevas listas filas2 y columnas2 segun la rama y atributo actual
				List<Boolean> filas2= procesarFilas(filas,tipo,indice);
				//System.out.println("filas2"+filas2);
				//System.out.println("Caso 1 por cada rama hacer "+tipo);
				//System.out.println(Nodo.toStringTree(nodo));
				nodo.anadirHijo(procesarDatos(filas2,columnas,tipo));
			}
			
			

		}else{ // calcular hijos
			System.out.println("Caso 2  "+Func_Mat.valores(filas,this.getDatos(),0).size());
			
			if(Func_Mat.valores(filas,this.getDatos(),0).size()==1){ // si solo hay un tipo de atributo de salida, este sera el hijo
				System.out.println("Caso 2.1");
				//System.out.println(Func_Mat.valores(filas,this.getDatos(),0).keySet().toString());
				System.out.println("Nuevo nodo "+Func_Mat.valores(filas,this.getDatos(),0).keySet().toString());
				nodo= new Nodo(Func_Mat.valores(filas,this.getDatos(),0).keySet().toString(),rama);
			}else{ //procesar datos para cada uno de los tipos de nodo
				System.out.println("Caso 2.2");
				double gananciaMax =0;
				int indice=0;
				for(int i=1;i<this.getAtributos().size();i++){
					if(columnas.get(i)){
						System.out.println("Ganancia de "+this.getAtributos().get(i).nombre+" "+Func_Mat.ganancia(filas,this.getDatos(),i));
						if(columnas.get(i) && gananciaMax < Func_Mat.ganancia(filas,this.getDatos(),i)){
							gananciaMax = Func_Mat.ganancia(filas,this.getDatos(),i);
							indice=i;
						}	
					}
					
				}
				System.out.println("Nuevo nodo "+this.getAtributos().get(indice).toString());
				nodo= new Nodo(this.getAtributos().get(indice).getNombre(),rama);
				columnas.set(indice, false);
				for (String tipo : this.getAtributos().get(indice).getValores()){
					//crear dos nuevas listas filas2 y columnas2 segun la rama y atributo actual
					//List<Boolean> filas2=filas,columnas2=columnas;//no terminado clonar y modificar
					List<Boolean> filas2= procesarFilas(filas,tipo,indice);
					nodo.anadirHijo(procesarDatos(filas2,columnas,tipo));
				}
			}
		}
		return nodo;
	}


//dado la columna y el tipo, debemos tachar los que no sean igual al tipo
	private List<Boolean> procesarFilas(List<Boolean> filas, String tipo, int columna) {
		List<Boolean> filas_new = new ArrayList<Boolean>();
		filas_new.addAll(filas);
		for(Map.Entry<Integer,List<String>> e: this.getDatos().entrySet()){
			//System.out.println("for "+e.getKey()+" "+e.getValue().get(columna));
			if(!e.getValue().get(columna).equalsIgnoreCase(tipo)){
				filas_new.set(e.getKey(), false);
			}
		}
		
		return filas_new;
	}

	public Map<Integer, List<String>> getDatos() {
		return datos;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}
	
	public String toString(){
		return new String("Atributos: "+this.atributos.toString()+"\n Datos: \n"+this.getDatos().toString());
	}

}