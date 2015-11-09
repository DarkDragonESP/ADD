import java.util.*;

public class test {

	public static void main(String[] args) {
		Inicializador a1;
		
		a1 = new Inicializador("ejemplo1.csv",true);
		System.out.println(a1.procesar());
		
		a1 = new Inicializador("ejemplo2.csv",true);
		System.out.println(a1.procesar());
		
		a1 = new Inicializador("ejemplo3false.csv",false);
		System.out.println(a1.procesar());
		
		a1 = new Inicializador("ejemplo4false.csv",false);
		System.out.println(a1.procesar());
		
		a1 = new Inicializador("ejemplo5false.csv",false);
		System.out.println(a1.procesar());
		
		
		a1 = new Inicializador("ejemplo6false.csv",false);
		System.out.println(a1.procesar());
		
		
		
		
		//datos de entrada tras procesar .csv
		/*List<Boolean> filas=new LinkedList<Boolean>();;
		List<Boolean> columnas=new LinkedList<Boolean>();;

		
		/*Map<Integer,List<String>> ejemplo = new HashMap<Integer,List<String>>();
		List<Atributo> atributos = new LinkedList<Atributo>();
		Set<String> posibles_valores= new TreeSet<String>();
		posibles_valores.add("Si");posibles_valores.add("No");
		atributos.add(new Atributo("Administrar farmaco",posibles_valores));
		atributos.add(new Atributo("alergia",posibles_valores));
		atributos.add(new Atributo("enfermedad",posibles_valores));
		
		List<String> valores=new LinkedList<String>();
		valores.add("Si");valores.add("Si");valores.add("Si");
		ejemplo.put(0,valores);
		
		valores=new LinkedList<String>();
		valores.add("Si");valores.add("No");valores.add("Si");
		ejemplo.put(1,valores);
		
		valores=new LinkedList<String>();
		valores.add("No");valores.add("Si");valores.add("No");
		ejemplo.put(2,valores);
		
		valores=new LinkedList<String>();
		valores.add("No");valores.add("No");valores.add("No");
		ejemplo.put(3,valores);
		
		for(Integer i=0;i<ejemplo.size();i++){
			filas.add(new Boolean(true));
		}
		for(Integer i=0;i<atributos.size();i++){
			columnas.add(new Boolean(true));
		}
		
		
		System.out.println("Ganancia 1(0) = "+Func_Mat.ganancia(filas,ejemplo,1));
		System.out.println("Ganancia 2(1) = "+Func_Mat.ganancia(filas,ejemplo,2));
		System.out.println("------------------------");
		
		analizadorDeDatos datos= new analizadorDeDatos(ejemplo,atributos);
			System.out.println(Nodo.toStringTree(datos.procesarDatos(filas, columnas, " ")));
		
		
		analizadorDeDatos datos2=Lectura_Datos.crea_analizador("ejemplo1.csv");
	
	
		
		System.out.println("------------------------");
		System.out.println(datos2);
		List<Boolean> filas2=new LinkedList<Boolean>();;
		List<Boolean> columnas2=new LinkedList<Boolean>();;
		for(Integer i=0;i<datos2.getDatos().size();i++){
			filas2.add(new Boolean(true));
		}
		for(Integer i=0;i<datos2.getAtributos().size();i++){
			columnas2.add(new Boolean(true));
		}
		columnas2.set(0, false);
		
		
		System.out.println(Nodo.toStringTree(datos2.procesarDatos(filas2, columnas2, " ")));
		
		System.out.println("------------------------");
		
		
analizadorDeDatos datos3=Lectura_Datos.crea_analizador("ejemplo2.csv");
	
	
		
		System.out.println(datos3);
		List<Boolean> filas3=new LinkedList<Boolean>();;
		List<Boolean> columnas3=new LinkedList<Boolean>();;
		for(Integer i=0;i<datos3.getDatos().size();i++){
			filas3.add(new Boolean(true));
		}
		for(Integer i=0;i<datos3.getAtributos().size();i++){
			columnas3.add(new Boolean(true));
		}
		columnas3.set(0, false);
		
		
		System.out.println(Nodo.toStringTree(datos3.procesarDatos(filas3, columnas3, " ")));
		
		System.out.println("------------------------");
		
		/*List<Nodo> hijo= new LinkedList<Nodo>();
		
		Nodo nodo= new Nodo("raiz");
		nodo.anadirHijo(new Nodo("pepe","asd"));
		nodo.anadirHijo(new Nodo("pepe2","asd2"));
		nodo.anadirHijo(new Nodo("pepe3","asd3"));
		System.out.println(Nodo.toStringTree(nodo));
		System.out.println("------------------------");
		System.out.println(atributos);
		*/
	}

}
