package Ex2_Repetido;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Test_Ejercicio2 {
	
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
				System.out.println("ERROR: Entrada/Salida");
			}catch(NumberFormatException e){
				System.out.println("Se espera un valor numerico tipo entero.");
			}
		
			String cod="";
			boolean valido;
			switch(op){ 
			
			case 1://insertar nuevo producto
				/*
				 * Se solicita al usuario el COD del producto a insertar. 
				 * Se comprueba si el COD facilitado existe en el fichero.
							 Si no existe se solicitan el resto de datos (se verifica que se cumplen los requisitos no funcionales) 
							   y se introduce el nuevo producto informando al usuario.
							 Si existe se informa al usuario y se regresa al menú.
				 */
				try{
					
					BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
					
					do{
						
					System.out.print("COD del producto a insertar => ");
					cod=stdin.readLine();
					valido=validCod(cod);//metodo que retorna true,false si el cod introducido cumple con los requisitos del campo COD
					
					if(!valido){
						System.out.println("El codigo tiene que tener entre 5 y 20 caracteres el introducido tiene "+cod.length());
					}
					
					}while(valido==false);
				
				if(Fichero.buscarcod(cod)){//metodo que retorna true,false segun si el cod introducido existe en el fichero
					
					System.out.println("El COD introducido ya existe en el fichero");
				
				}else{//si no existe se piden el resto de datos
					
					System.out.print("Descripcion => ");
					String  desc=stdin.readLine();
					System.out.print("Precio => ");
					Double  precio=Double.parseDouble(stdin.readLine());
					System.out.print("Stock => ");
					int  stock=Integer.parseInt(stdin.readLine());
					
					Producto a=new Producto(cod,desc,stock,precio);//creamos un nuevo objeto Producto, si los campos no cumplen con los requisitos saltara al bloque catch informando de que campo es incorrecto.
					
					if(Fichero.insertar_nuevo(a)){//finalmente se añadira en el fichero el nuevo producto.
						
						System.out.println("El producto ha sido guardado correctamente en el fichero");
					
					}else{
						
						System.out.println("El producto no ha podido guardarse en el fichero");
					}					
				}	
				}catch(IOException e){
					System.out.println("Error en la entrada por teclado "+e);
				} catch (Exception e) {//catch que capturara si hay algun campo mal al hacer un nuevo producto
					System.out.println("ERROR: "+e);
				}
				
				break;
			case 2://dar de baja producto
				/*
				 * Se solicita al usuario el COD del producto a dar de baja.
				 * Se comprueba si el COD facilitado existe en el fichero.
					 Si existe, se da de baja el nuevo producto informando al usuario.
					 Si no existe se informa al usuario y se regresa al menú.
				 */
				try{
					BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
					do{
					System.out.print("COD del producto a dar de baja => ");
					cod=stdin.readLine();
					valido=validCod(cod);//se mira que el cod cumpla con los requisitos del campo, y asi si no lo cumple ahorrara tener que leer todo el fichero.
					if(valido==false){
						System.out.println("El codigo tiene que tener entre 5 y 20 caracteres el introducido tiene "+cod.length());
					}else{
						
						if(Fichero.buscarcod(cod)){	//mirar si el cod a borrar esta en nuestro fichero		
							
							if(Fichero.borarr(cod)){//explicación en el metodo
								
								System.out.println("El producto con codigo "+cod+" se ha borrado correctamente.");
								
							}else{
								
								System.out.println("Fallo al borrar el producto con cod "+cod);
							}
							
						}else{
							
							System.out.println("El cod "+cod+" no corresponde a ningun producto del fichero.");
						}
					}
					}while(valido==false);
					
				}catch(IOException e){
					System.out.println("Error en la entrada por teclado "+e);
				}
				break;
			case 3://modificar stock de producto
				/*
				 * Se solicita al usuario el COD del producto cuyo stock se desea modificar.
				 * Se comprueba si el COD facilitado existe en el fichero.
					 Si existe, se modifica el stock del producto informando al usuario, 
					   verificando que se cumplen los requisitos no funcionales.
					 Si no existe se informa al usuario y se regresa al menú.
				 */
				try{
					BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
					System.out.print("COD del producto a modificar stock => ");
					cod=stdin.readLine();
					
					if(validCod(cod)){//se mira que el cod cumpla con los requisitos del campo, y asi si no lo cumple ahorrara tener que leer todo el fichero.
						
						if(Fichero.buscarcod(cod)){//mirar si el cod a modificar esta en nuestro fichero	
							
							System.out.print("Nuevo stock => ");
							int stockn=Integer.parseInt(stdin.readLine());
							
							if(validStock(stockn)){//metodo que comprueva si el nuevo stock cumple con las restricciones del campo
								
								if(Fichero.modificar(cod, stockn)){//explicación en el metodo
									
									System.out.println("El producto con codigo "+cod+" se le a puesto un estock de "+stockn+" satisfactoriamente");
								
								}else{
									
									System.out.println("Erro en modificar el stock, en el fichero.");
								}
								
								
							}else{
								//no fa res, ya se encarga la Excepcion 
							}
						}else{
							
							System.out.println("El cod "+cod+" no corresponde a ningun producto en el fichero.");
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
				/*
				 * Se solicita al usuario alguna palabra de la descripción (DESC) del producto.
				 * Se comprueba si existe en el fichero algún producto cuya descripción coincida
				 * con alguna de las palabras de búsqueda.
					 Si existen registros que cumplan con el criterio de búsqueda se muestran
						todos los datos de los productos (COD, DESC, STOCK, PRICE)
					 Si no existen registros que cumplan con el criterio de búsqueda, 
						se informa al usuario y se regresa al menú.
				 */
				try{
					BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
					System.out.print("Buscar en las descripciones => ");
					String buscar=stdin.readLine();
					if(Fichero.buscarPalabraDescripcion(buscar)){//metodo que retorna true,false segun si ha encontrado coincidencias.
						
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
/*
 * no se que es mejor y e implementado dos metos distintos: 
 * 1. validCod :
 * 		Me he inventado este metodo para no tener que abrir el fichero y leer todas las lineas cuando el usuario no introduzca
 * 		un cod que cumpla los patrones para este campo.
 * 2.validStock:
 * 		Lo mismo que el anterior pero ahora para el campo stock, en vez de programar los parametos, me invento un objeto
 * 		producto y le intento meter el stock si salta es que no es correto.
 * 		Es mas o menos como el de arriva su funcion es identica, pero aqui uso las restircciones de la clase Producto.
 * No se que es mejor :( 
 */
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
