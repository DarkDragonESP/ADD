
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
		this.hijos= new LinkedList<Nodo>();
	}
	
	public void anadirHijo(Nodo n){
		this.hijos.add(n);
	}
	public boolean tieneHijos(){
		return !hijos.isEmpty();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRama() {
		return rama;
	}
	public void setRama(String rama) {
		this.rama = rama;
	}
	public List<Nodo> getHijos() {
		return hijos;
	}
	public void setHijos(List<Nodo> hijos) {
		this.hijos = hijos;
	}
	
	public String toString(){
		return new String(this.getRama()+"->"+this.getNombre());
	}
	//---Dibujar arbol
		/**
		 * Creates a tree representation of the Nodo
		 * @param Nodo The Nodo, which may not be null
		 * @return A string containing the formatted tree
		 */
		public static String toStringTree(Nodo Nodo) {
			final StringBuilder buffer = new StringBuilder();
			return toStringTreeHelper(Nodo, buffer, new LinkedList<Iterator<Nodo>>()).toString();
		}

		private static String toStringTreeDrawLines(List<Iterator<Nodo>> parentIterators, boolean amLast) {
			StringBuilder result = new StringBuilder();
			Iterator<Iterator<Nodo>> it = parentIterators.iterator();
			while (it.hasNext()) {
				Iterator<Nodo> anIt = it.next();
				if (anIt.hasNext() || (!it.hasNext() && amLast)) {
					result.append("        |");
				}
				else {
					result.append("           ");
				}
			}
			return result.toString();
		}

		private static StringBuilder toStringTreeHelper(Nodo Nodo, StringBuilder buffer, List<Iterator<Nodo>>
		parentIterators) {
			if (!parentIterators.isEmpty()) {
				boolean amLast = !parentIterators.get(parentIterators.size() - 1).hasNext();
				buffer.append("\n");
				String lines = toStringTreeDrawLines(parentIterators, amLast);
				buffer.append(lines);
				buffer.append("\n");
				buffer.append(lines);
				buffer.append("- ");
			}
			buffer.append(Nodo.toString());
			if (Nodo.tieneHijos()) {
				Iterator<Nodo> it = Nodo.getHijos().iterator();
				parentIterators.add(it);
				while (it.hasNext()) {
					Nodo child = it.next();
					toStringTreeHelper(child, buffer, parentIterators);
				}
				parentIterators.remove(it);
			}
			return buffer;
		}
}
