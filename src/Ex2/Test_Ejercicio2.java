package Ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

public class Test_Ejercicio2 {

	private static Map<String,Producto> array=null;
	
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
			if(op!=5 && array==null){
				try {
					array=Fichero.leerFichero(array);
				} catch (NumberFormatException e) {
					System.out.println("Error en los tipos de datos del fichero se esperva valores numericos");
				} catch (Exception e) {
					System.out.println(e.getMessage()+" "+e);
				}
			}
			String cod="";
			boolean valido;
			switch(op){ 
			
			case 1://insertar nuevo producto
				try{
					BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
					do{
					System.out.print("COD del producto a insertar => ");
					cod=stdin.readLine();
					valido=validCod(cod);
					if(!valido){
						System.out.println("El codigo tiene que tener entre 5 y 20 caracteres el introducido tiene "+cod.length());
					}
					}while(valido==false);
				if(array.get(cod)!=null){
					System.out.println("El COD introducido ya existe en el fichero");
				}else{
					System.out.print("Descripcion => ");
					String  desc=stdin.readLine();
					System.out.print("Precio => ");
					Double  precio=Double.parseDouble(stdin.readLine());
					System.out.print("Stock => ");
					int  stock=Integer.parseInt(stdin.readLine());
					
					Producto a=new Producto(cod,desc,stock,precio);
					array.put(cod, a);
					boolean insertado=Fichero.insertar_nuevo(a);
					if(insertado){
						System.out.println("El producto ha sido guardado correctamente");
					}else{
						System.out.println("El producto no ha podido guardarse en el fichero");
					}
					
				}
					
				}catch(IOException e){
					System.out.println("Error en la entrada por teclado "+e);
				} catch (Exception e) {
					System.out.println(""+e);
				}
				
				break;
			case 2://dar de baja producto
				try{
					BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
					System.out.print("COD del producto a dar de baja => ");
					cod=stdin.readLine();
					
				}catch(IOException e){
					System.out.println("Error en la entrada por teclado "+e);
				}
				if(array.get(cod)!=null){
					array.remove(cod);					
					//imprimirarray();
					boolean borrado=Fichero.escribir(array);
					if(borrado){
						System.out.println("El producto se ha borrado correctamente");
					}else{
						System.out.println("No se ha podido borrar el producto");
					}
				}else{
					System.out.println("El COD introducido no existe.");
				}
				break;
			case 3://modificar stock de producto
				try{
					BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
					System.out.print("COD del producto a modificar stock => ");
					cod=stdin.readLine();
					
					if(array.get(cod)!=null){
						System.out.print("Nuevo stock => ");
						int stockn=Integer.parseInt(stdin.readLine());
						array.get(cod).setStock(stockn);
						
						boolean borrado=Fichero.escribir(array);
						
						if(borrado){
							System.out.println("El stock se ha actualizado correctamente");
						}else{
							System.out.println("No se ha podido actualizar el stock, error en escribir en el fichero");
						}
					}else{
						System.out.println("El COD introducido no existe.");
					
					}
				}catch(IOException e){
					System.out.println("Error en la entrada por teclado "+e);
				}catch(NumberFormatException x){
					System.out.println("Error al introducir un nuevo stock"+x);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4://consultar producto por una palabra de la descripción
				try{
					BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
					System.out.print("Buscar en las descripciones => ");
					String buscar=stdin.readLine();
					Fichero.buscarPalabraDescripcion(buscar);
				}catch(IOException e){
					System.out.println("Error en la entrada por teclado");
				}
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
	public static void imprimirarray(){
		Iterator it=array.entrySet().iterator();
		
		while(it.hasNext()){	
			Map.Entry e=(Map.Entry)it.next();	
			System.out.println(e.getKey()+";"+((Producto)e.getValue()).getDesc()+";"+((Producto)e.getValue()).getStock()+";"+((Producto)e.getValue()).getPrecio());			
			
			
		}
	}

	public static boolean validCod(String cod){
	
		if(cod.length()<5 || cod.length()>20){
			return false;
		}else{	
		return true;
		}
	}
}
