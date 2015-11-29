package id3;
import java.util.Set;
import java.util.TreeSet;

public class Atributo {
	String nombre;
	
	Set<String> valores;
	public Atributo(String nombre){
		this.nombre = nombre;
		valores = new TreeSet<String>();
	}
	public Atributo(String nombre,Set<String> valores){
		this.nombre=nombre;
		this.valores= valores;
	}
	public void add(String valor){
		valores.add(valor);
	}
	public boolean esta(String valor){
		return valores.contains(valor);
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<String> getValores() {
		return valores;
	}
	public void setValores(Set<String> valores) {
		this.valores = valores;
	}
	public String toString(){
		return new String(this.getNombre()+" :"+this.getValores().toString()); 
	}
	
}
