import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Inicializador {
	private analizadorDeDatos datos;
	private List<Boolean> filas,columnas;
	//columna True si salida es columna 0, false si columna salida es la ultima (checkbox)
	public Inicializador(String fichero, Boolean columna){
		datos=Lectura_Datos.crea_analizador(fichero);
		//mover la columna final a la primera si columna es falso
		if(!columna){
			this.modificarUltimaCol();
		}
		//inicializar filas y columnas
		filas=new LinkedList<Boolean>();;
		columnas=new LinkedList<Boolean>();;
		for(Integer i=0;i<datos.getDatos().size();i++){
			filas.add(new Boolean(true));
		}
		for(Integer i=0;i<datos.getAtributos().size();i++){
			columnas.add(new Boolean(true));
		}
		columnas.set(0, false);
	}
	public String procesar(){
		return Nodo.toStringTree(datos.procesarDatos(filas, columnas, " "));
	}
	private void modificarUltimaCol(){
		for(Map.Entry<Integer,List<String>> e: this.datos.getDatos().entrySet()){
			String aux = e.getValue().get(0);
			int ultimaPos = e.getValue().size()-1;
			e.getValue().set(0, e.getValue().get(ultimaPos));
			e.getValue().set(ultimaPos, aux);
		}
	}
}


