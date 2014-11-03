package Ex2;

public class Producto {
	
	private String cod; //min 5 max 20
	private String desc;//no puede quedar vacio y como maximo 20
	private double precio;//>0
	private int stock;//>0
	
	public Producto(String cod, String desc, int stock ,double precio) throws Exception{
		this.setCod(cod);
		this.setDesc(desc);
		this.setPrecio(precio);
		this.setStock(stock);
	}

	protected String getCod() {
		return cod;
	}
	protected String getDesc() {
		return desc;
	}
	protected double getPrecio() {
		return precio;
	}
	protected int getStock() {
		return stock;
	}
	protected void setCod(String cod) throws Exception{
		if(cod.length()<5 || cod.length()>20){
			throw new Exception("El codigo tiene que tener minimo 5 caracteres y menos de 20 caracteres.");
		}else{
		this.cod = cod;
		}
	}

	protected void setDesc(String desc) throws Exception{
		if(desc.length()<1 || desc.length()>20){
			System.out.println("La que peta => "+desc);
			throw new Exception("La descripcion no puede estar vacio o contener mas de 20 caracteres.");
		}else{
		this.desc = desc;
		}
	}

	protected void setPrecio(double precio)throws Exception {
		if(precio<1){
			throw new Exception("El precio tiene que ser superior a 0.");
		}else{
		this.precio = precio;
		}
	}


	protected void setStock(int stock) throws Exception{
		if(stock<0){
			throw new Exception("El stock tiene que ser superior a 0.");
		}else{
		this.stock = stock;
		}
	}
	
	

}
