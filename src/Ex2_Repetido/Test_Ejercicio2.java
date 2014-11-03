package Ex2_Repetido;

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
				
				if(Fichero.buscarcod(cod)){
					
					System.out.println("El COD introducido ya existe en el fichero");
				
				}else{
					
					System.out.print("Descripcion => ");
					String  desc=stdin.readLine();
					System.out.print("Precio => ");
					Double  precio=Double.parseDouble(stdin.readLine());
					System.out.print("Stock => ");
					int  stock=Integer.parseInt(stdin.readLine());
					
					Producto a=new Producto(cod,desc,stock,precio);
					
					if(Fichero.insertar_nuevo(a)){
						System.out.println("El producto ha sido guardado correctamente en el fichero");
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
					do{
					System.out.print("COD del producto a dar de baja => ");
					cod=stdin.readLine();
					valido=validCod(cod);
					if(valido==false){
						System.out.println("El codigo tiene que tener entre 5 y 20 caracteres el introducido tiene "+cod.length());
					}else{
						//continua programa
					}
					}while(valido==false);
					
				}catch(IOException e){
					System.out.println("Error en la entrada por teclado "+e);
				}
				//mirar si el cod a borrar esta en nuestro fichero
				if(Fichero.buscarcod(cod)){			
					
					if(Fichero.borarr(cod)){
						System.out.println("El producto con codigo "+cod+" se ha borrado correctamente.");
					}else{
						System.out.println("Fallo al borrar el producto con cod "+cod);
					}
					
				}else{
					System.out.println("El cod "+cod+" no corresponde a ningun producto del fichero.");
				}
				
				
				break;
			case 3://modificar stock de producto
				try{
					BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
					System.out.print("COD del producto a modificar stock => ");
					cod=stdin.readLine();
					
					if(validCod(cod)){
						System.out.print("Nuevo stock => ");
						int stockn=Integer.parseInt(stdin.readLine());
						
						if(validStock(stockn)){
							
							if(Fichero.modificar(cod, stockn)){
								System.out.println("El producto con codigo "+cod+" se le a puesto un estock de "+stockn+" satisfactoriamente");
							}else{
								System.out.println("Erro en modificar el stock");
							}
							
							
						}else{
							//no fa res, ya se encarga la Excepcion 
						}
	
					}else{
						System.out.println("El COD introducido no cumple con los requisitos que se esperan para un COD.");
					
					}
				}catch(IOException e){
					System.out.println("Error en la entrada por teclado "+e);
				}catch(NumberFormatException x){
					System.out.println("Error al introducir un nuevo stock, se espera valores numericos tipo entero "+x);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4://consultar producto por una palabra de la descripción
				try{
					BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
					System.out.print("Buscar en las descripciones => ");
					String buscar=stdin.readLine();
					if(Fichero.buscarPalabraDescripcion(buscar)){
						
					}else{
						System.out.println("No se ha encontrado ninguna descripcion que contenga => "+buscar);
					}
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

	public static boolean validCod(String cod){
	
		if(cod.length()<5 || cod.length()>20){
			return false;
		}else{	
		return true;
		}
	}
	public static boolean validStock(int stock) throws Exception{
		
		Producto a =new Producto();
		a.setStock(stock);
		
		return true;//solo llegara a esta linia si el stok es valido, si no se lanza la excepcion
	}
}
