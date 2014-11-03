package Ex2;

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
	private static Path path=Paths.get(ruta);
	
	public static Map<String,Producto> leerFichero(Map<String,Producto> array) throws NumberFormatException, Exception{
		
		try{
			array=new HashMap<String,Producto>();
			
			BufferedReader stdin=Files.newBufferedReader(path,
					java.nio.charset.StandardCharsets.UTF_8);
			String linea="";
			while((linea=stdin.readLine())!=null){
				String [] part=linea.split(";");
				array.put(part[0], new Producto(part[0],part[1],Integer.parseInt(part[2]),Double.parseDouble(part[3])));
			}
			
			
		}catch(IOException e){
			System.out.println("Error en leer el fichero "+e);
		}
		
		
		return array;
	}
	
	
	public static boolean insertar_nuevo(Producto x){
		
		try{
			
			BufferedWriter escribe=Files.newBufferedWriter(path, 
					java.nio.charset.StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.APPEND);			
			
			escribe.write(x.getCod()+";"+x.getDesc()+";"+x.getStock()+";"+x.getPrecio());			
			escribe.newLine();
			escribe.close();//si no no escribe pq?
			return true;
		}catch(InvalidPathException e){
			System.out.println("Error en leer la ruta del fichero "+e);
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public static boolean escribir(Map<String,Producto> array){
		
		try{
			
			BufferedWriter escribe=Files.newBufferedWriter(path, 
					java.nio.charset.StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.TRUNCATE_EXISTING);
			
			Iterator it=array.entrySet().iterator();
			
			while(it.hasNext()){	
				Map.Entry e=(Map.Entry)it.next();
				
				escribe.write(e.getKey()+";"+((Producto)e.getValue()).getDesc()+";"+((Producto)e.getValue()).getStock()+";"+((Producto)e.getValue()).getPrecio());			
				escribe.newLine();
				
			}
			escribe.close();//si no no escribe pq?
			return true;
		}catch(InvalidPathException e){
			System.out.println("Error en leer la ruta del fichero "+e);
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected static void modificar(){
		//leer hasta encontrar que fila quiero modificar
		try{
			
			
			BufferedReader stdin=Files.newBufferedReader(path,
					java.nio.charset.StandardCharsets.UTF_8);
			String linea="";
			while((linea=stdin.readLine())!=null){
				String [] part=linea.split(";");
				
			}
			
			
		}catch(IOException e){
			System.out.println("Error en leer el fichero "+e);
		}
	}
	
	protected static void buscarPalabraDescripcion(String buscar){
		try{
			
			
			BufferedReader stdin=Files.newBufferedReader(path,
					java.nio.charset.StandardCharsets.UTF_8);
			String linea="";
			while((linea=stdin.readLine())!=null){
				String [] part=linea.split(";");
				if(part[1].contains(buscar)){
					System.out.println(part[0]+" "+part[1]+" "+part[2]+" "+part[3]);
				}
			}
			
			
		}catch(IOException e){
			System.out.println("Error en leer el fichero "+e);
		}
	}
	
//System.out.println(e.getKey()+";"+((Producto)e.getValue()).getDesc()+";"+((Producto)e.getValue()).getStock()+";"+((Producto)e.getValue()).getPrecio());
	

}