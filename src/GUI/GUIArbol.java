package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import id3.Inicializador;
 
public class GUIArbol extends JFrame {
 
    private JPanel northPane;
    private JPanel centerPane;  
    private JPanel southPane;
    private JTextField textField;
    private JTextArea textArea;
    private JLabel textLabel;
    private JTextField colTextField;
    private File archivoElegido;
    private JLabel pathLabel;
 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUIArbol frame = new GUIArbol();
                    frame.pack();
                    frame.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    /**
     * Create the frame.
     */
    public GUIArbol(){
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        setLayout(new BorderLayout());
        setTitle("ID3");
      
        /* NORTH Panel*/
        
        northPane = new JPanel();
        northPane.setLayout(new GridLayout(3, 1));
 
        textLabel = new JLabel("Seleccione el fichero de entrada de datos (CSV)");
        northPane.add(new JPanel().add(textLabel));
        
        JPanel centerNorth = new JPanel();
        centerNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        textField = new JTextField();
        textField.setToolTipText("Selecciona un fichero de datos");   
        textField.setPreferredSize(new Dimension(250, 25));
        
        JButton btnSeleccionar = new JButton("Seleccionar");
        
        centerNorth.add(textField).setLocation(1, 0);
        centerNorth.add(btnSeleccionar);
        
        northPane.add(centerNorth);
        northPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel southNorth = new JPanel();
        
        JLabel textLabelCol = new JLabel("Numero de Columna del Atributo de Salida"); 
        southNorth.add(textLabelCol);
        southNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        colTextField = new JTextField();
        colTextField.setToolTipText("Indicar numero de columnas. Por defecto 0.");
        colTextField.setPreferredSize(new Dimension(50, 25));
        southNorth.add(colTextField);
        
        JButton run = new JButton("Iniciar");
        southNorth.add(run);
                    
        northPane.add(southNorth).setLocation(2,0);
        
        add(northPane, BorderLayout.NORTH);
        /*END North Panel*/
        
        /*Center Panel*/

      textArea = new JTextArea();
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
   
      Border border = BorderFactory.createLineBorder(Color.BLACK);
      textArea.setBorder(BorderFactory.createCompoundBorder(border, 
                  BorderFactory.createEmptyBorder(5, 20, 5, 20)));
     ;
      JScrollPane scroll=new JScrollPane(textArea);
     
      centerPane = new JPanel();
      centerPane.setLayout(new BorderLayout());
      centerPane.add(scroll);
     
      add(centerPane, BorderLayout.CENTER);
      /*END Center Panel*/
      
      /*South Panel*/      
      southPane = new JPanel();
      
      pathLabel = new JLabel();
      southPane.add(pathLabel);
      add(southPane, BorderLayout.SOUTH);
  
        btnSeleccionar.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
            	openSelector();
            }
        });
        
        
        run.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
            	initID3();
            pathLabel.setText(archivoElegido.getAbsolutePath());
            }
        });
 

    }
    
    public void openSelector(){
    	 JFileChooser fc = new JFileChooser();
    
    	  FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
          fc.setFileFilter(filter);
         int respuesta = fc.showOpenDialog(this);
         if (respuesta == JFileChooser.APPROVE_OPTION) {
             archivoElegido = fc.getSelectedFile();
             textField.setText(archivoElegido.getName());
         
         }

    }
    
    public void initID3(){
   	
    	String ncolumnsString = colTextField.getText();
    	int ncolumns = isNumeric(ncolumnsString) &&  Integer.parseInt(ncolumnsString)>=0 ?Integer.parseInt(ncolumnsString):0;
   	
    	Inicializador a1;
    	a1 = new Inicializador(archivoElegido.getAbsolutePath(),ncolumns);
    	textArea.setText(a1.procesar());

    }
    
    public static boolean isNumeric(String str)
    {
      return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    
}

