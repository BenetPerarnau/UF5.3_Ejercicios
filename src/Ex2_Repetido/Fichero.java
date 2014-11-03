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
			 BufferedWriter escribe=Files.newBufferedWriter(path2, 
					 java.nio.charset.StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.CREATE);
			
			 BufferedReader stdin=Files.newBufferedReader(path,
					 		java.nio.charset.StandardCharsets.UTF_8);
			String linea="";
			
			while((linea=stdin.readLine())!=null){
				String part[]=linea.split(";");
				if(part[0].equalsIgnoreCase(cod)){
					//no fa res
				}else{
					
						escribe.write(part[0]+";"+part[1]+";"+part[2]+";"+part[3]);			
						escribe.newLine();
						
				}
			}
			
			Files.move(path2, path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			Files.deleteIfExists(path2);
			escribe.close();
			stdin.close();
		}catch(InvalidPathException e){
			System.out.println("Error en leer la ruta del fichero "+e);
			return false;
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally{
			//escribe.close();
			//stdin.close();
		}
		
		
		return true;
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
	
	
	protected static boolean modificar(String cod, int stock){
		
		try{
			
			BufferedWriter escribe=Files.newBufferedWriter(path2, 
					 java.nio.charset.StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.CREATE);
			
			BufferedReader stdin=Files.newBufferedReader(path,
					java.nio.charset.StandardCharsets.UTF_8);
			String linea="";
			
			while((linea=stdin.readLine())!=null){
				String [] part=linea.split(";");
				
				if(part[0].equalsIgnoreCase(cod)){
					
					escribe.write(part[0]+";"+part[1]+";"+stock+";"+part[3]);			
					escribe.newLine();		
					
				}else{
					escribe.write(part[0]+";"+part[1]+";"+part[2]+";"+part[3]);			
					escribe.newLine();
				}		
			}
			Files.move(path2, path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
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
				if(part[1].contains(buscar)){
					System.out.println(part[0]+" "+part[1]+" "+part[2]+" "+part[3]);
					aux=true;
				}
			}		
		}catch(IOException e){
			System.out.println("Error en leer el fichero "+e);
		}
		return aux;
	}
	protected static boolean buscarcod(String cod){
		try{

			BufferedReader stdin=Files.newBufferedReader(path,
					java.nio.charset.StandardCharsets.UTF_8);
			String linea="";
			while((linea=stdin.readLine())!=null){
				String [] part=linea.split(";");
				if(part[0].equalsIgnoreCase(cod)){    
					return true;
				}
			}	
			
		}catch(IOException e){
			System.out.println("Error en leer el fichero "+e);
		}
		return false;
		
	}
//System.out.println(e.getKey()+";"+((Producto)e.getValue()).getDesc()+";"+((Producto)e.getValue()).getStock()+";"+((Producto)e.getValue()).getPrecio());
	

}