
import java.util.*;

public class Nodo {
	private String nombre;
	private String rama;
	private List<Nodo> hijos; //guarda la rama y el nodo hijo
	
	public Nodo(String nombre){
		this.nombre=nombre;
		this.rama= " ";
		hijos= new LinkedList<Nodo>();
	}
	public Nodo(String nombre,String rama){
		this.nombre=nombre;
		this.rama= rama;
		hijos= new LinkedList<Nodo>();
	}
	
	public void anadirHijo(Nodo n){
		hijos.add(n);
	}
}
