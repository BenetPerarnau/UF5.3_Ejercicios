package Ex4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import filesgeneration.Product;


public class Fichero4{

	
	private final static  String ruta="stockObjectFile.dat";
	private static Path path=Paths.get(ruta);
	
	
	
	
	public static Product buscarCod(String cod) throws IOException{
		
		try {
			ObjectInputStream dataIn=new ObjectInputStream(Files.newInputStream(path));
			
		while(true ){
				
				Product product=(Product) dataIn.readObject();
				
				if(product.getCod().equalsIgnoreCase(cod)){
					
					System.out.println("Este cod ya existe");
					return product;
				}
		}
		
		
		} catch (ClassNotFoundException e) {
			System.out.println("Error al leer el fichero binario "+e.getMessage());
		}catch(EOFException e){
			System.out.println("Final de fechero");
		}catch(IOException e){
			System.out.println("Fin de lectura");
		}
		return null;
		
	}
	
	protected static boolean insertarP(Product p){
		
			try {
				
				AppendObject dataOut=new AppendObject (Files.newOutputStream(path,java.nio.file.StandardOpenOption.APPEND ));
				dataOut.writeObject(p);
				dataOut.reset();
				dataOut.flush();
				dataOut.close();
				return true;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
	}
	
	protected static void leertodo(){
		
		
		try {
			ObjectInputStream dataIn = new ObjectInputStream(Files.newInputStream(path));
			while(true ){
				
				Product product=(Product) dataIn.readObject();
				System.out.println(product.toString());
					
		}
		}catch(EOFException e){
			System.out.println("Fin de fichero");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}