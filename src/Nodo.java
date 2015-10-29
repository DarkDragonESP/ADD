
import java.util.*;

public class Nodo {
	private String nombre;
	private Map<String,Nodo> hijos; //guarda la rama y el nodo hijo
	
	public Nodo(String nombre){
		this.nombre=nombre;
		hijos= new HashMap<String,Nodo>();
	}
	
	public void anadirHijo(String nombre){
		hijos.put(nombre, null);
	}
}
