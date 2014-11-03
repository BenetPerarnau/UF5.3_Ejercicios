package Ex4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import filesgeneration.Product;

public class Test_Ejercicio4 {

	private static Map<String,Product> array=null;
	
	public static void main(String[]args){
		byte op=0;
		do{
			try{
				
			BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("1 Insertar nuevo producto");
			System.out.println("2 Dar de baja producto");
			System.out.println("3 Modificar stock de producto");
			System.out.println("4 Consultar producto");
			System.out.println("5 Salir");
			System.out.print("OP => ");
			op=Byte.parseByte(stdin.readLine());
			
			}catch(IOException e){
				System.out.println("er");
			}catch(NumberFormatException e){
				System.out.println("Se espera un valor numerico tipo entero");
			}
			
			String cod="";
			boolean valido;
			switch(op){ 
			
			case 1://insertar nuevo producto
				
				try {
					BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
					
					System.out.println("Insertar nuevo producto:");
					System.out.print("COD => ");
					cod=stdin.readLine();			
					if(Fichero4.buscarCod(cod)==null){
						Product p=new Product(cod,"Hola",12,100.50);
						if(Fichero4.insertarP(p)){
							System.out.println("si "+p.toString());
						}
					}else{
						//no
					}
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			case 2://dar de baja producto
				
				break;
			case 3://modificar stock de producto
				
				break;
			case 4://consultar producto por una palabra de la descripción
				
				Fichero4.leertodo();
				break;
			case 5://salir
				System.out.println("Bye");
				break;
				default:
					System.out.println("Valores numericos del 1 al 5");
					break;
			
			}
			
		}while(op!=5);
	}
	
}
