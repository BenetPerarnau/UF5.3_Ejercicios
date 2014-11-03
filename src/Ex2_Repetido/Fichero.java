package Ex2_Repetido;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Fichero {

	private final static  String ruta="Ficheros/stockTextFile.txt";
	private final static Path path=Paths.get(ruta);
	private final static  String ruta2="Ficheros/stockTextFileAUXILIAR.txt";
	private final static Path path2=Paths.get(ruta2);
	
	public static boolean borarr(String cod){
		
		try {
			//creación de un fichero auxiliar donde iremos colocando todos los productos excepto el que queramos borrar
			 BufferedWriter escribe=Files.newBufferedWriter(path2, 
					 java.nio.charset.StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.CREATE);
			//abrimos en modo lectura el fichero donde tenemos la bbdd de productos
			 BufferedReader stdin=Files.newBufferedReader(path,
					 		java.nio.charset.StandardCharsets.UTF_8);
			
			String linea="";	
			while((linea=stdin.readLine())!=null){
				String part[]=linea.split(";");
				if(part[0].equalsIgnoreCase(cod)){//si el cod , es el mismo al que queremos borrar no copia en el fichero auxiliar
					
				}else{//si el cod, es distinto copia en el fichero auxiliar.
					
						escribe.write(part[0]+";"+part[1]+";"+part[2]+";"+part[3]);			
						escribe.newLine();		
				
				}
			}
			//mueve el contenido del fichero auxiliar al fichero principal
			Files.move(path2, path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			//eliminamos el fichero auxiliar
			Files.deleteIfExists(path2);
			escribe.close();
			stdin.close();
		}catch(InvalidPathException e){
			System.out.println("Error en leer la ruta del fichero "+e);
			return false;
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}	
		return true;
	}
	
	public static boolean insertar_nuevo(Producto x){
		
		try{
			//abrimos el fichero en modo lectura con la opción append para insertar el nuevo producto en final del fichero
			BufferedWriter escribe=Files.newBufferedWriter(path, 
					java.nio.charset.StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.APPEND);			
			
			escribe.write(x.getCod()+";"+x.getDesc()+";"+x.getStock()+";"+x.getPrecio());			
			escribe.newLine();
			escribe.close();
			return true;
		}catch(InvalidPathException e){
			System.out.println("Error en leer la ruta del fichero "+e);
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}				
	}
	
	
	protected static boolean modificar(String cod, int stock){
		
		try{
			//Creación de un fichero auxiliar, para insertar todos los registros del fichero principal mas la lina modificada.
			BufferedWriter escribe=Files.newBufferedWriter(path2, 
					 java.nio.charset.StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.CREATE);
			//abrir el fichero principal para su lectura
			BufferedReader stdin=Files.newBufferedReader(path,
					java.nio.charset.StandardCharsets.UTF_8);
			
			String linea="";
			while((linea=stdin.readLine())!=null){
				String [] part=linea.split(";");	
				if(part[0].equalsIgnoreCase(cod)){//si el cod es el mismo que el cod a modificar, insertamos en esa linea el nuevo stock
					
					escribe.write(part[0]+";"+part[1]+";"+stock+";"+part[3]);			
					escribe.newLine();		
					
				}else{//si no, escribimos tal cual esta en el fichero principal en el auxiliar
					
					escribe.write(part[0]+";"+part[1]+";"+part[2]+";"+part[3]);			
					escribe.newLine();
				}		
			}
			//mueve el contenido del fichero auxiliar al fichero principal
			Files.move(path2, path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			//eliminar el fichero auxiliar
			Files.deleteIfExists(path2);
			escribe.close();
			stdin.close();
		}catch(IOException e){
			System.out.println("Error en leer el fichero "+e);
			return false;
		}
		return true;
	}
	
	protected static boolean buscarPalabraDescripcion(String buscar){
		boolean aux=false;
		//buca en cada linea del fichero si en la "casilla " descripcion hay la cadena que se esta buscando
		//si encuentra alguna coincidencia imprimira toda la linea
		try{
			
			BufferedReader stdin=Files.newBufferedReader(path,java.nio.charset.StandardCharsets.UTF_8);
			String linea="";
			
			while((linea=stdin.readLine())!=null){
				String [] part=linea.split(";");
				if(part[1].contains(buscar)){//encontar si hay coincidencia en el campo desc
					System.out.println(part[0]+" "+part[1]+" "+part[2]+" "+part[3]);
					aux=true;
				}else{
					//no fa res
				}
			}		
		}catch(IOException e){
			System.out.println("Error en leer el fichero "+e);
		}
		return aux;
	}
	protected static boolean buscarcod(String cod){
		try{
			//metodo usado en dar de baja y modificar stock, se utiliza para comprovar si el cod esixte en el fichero
			//si no esta, no se podra ni dar de baja ni modificar el stock.
			BufferedReader stdin=Files.newBufferedReader(path,
					java.nio.charset.StandardCharsets.UTF_8);
			String linea="";
			while((linea=stdin.readLine())!=null){
				String [] part=linea.split(";");
				if(part[0].equalsIgnoreCase(cod)){ //si el cod buscado coincide en alguna linea del fichero retorna true y sera posible dar de baja o modificar stock   
					return true;
				}else{
					//no fa res
				}
			}	
			
		}catch(IOException e){
			System.out.println("Error en leer el fichero "+e);
		}
		return false;	
	}
}