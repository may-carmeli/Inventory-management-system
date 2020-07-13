package Model;

import java.io.IOException;
import java.util.ArrayList;

public class Manager extends  User {

	public Manager( )  throws IOException {	
	
	}
	
	public String getType() {
		return "Manager";
	}
	
	public void removeProductById(int id) throws IOException {
		
		txtDb_.removeProductById(id);
	}
	
	public boolean addProduct(Product p) throws IOException {		
		
		return txtDb_.addProductToDb(p);
	}

}
