package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import View.ShowProductsView;


public class Model  {
	
	private User user_;
	
	public Model(String type) throws IOException {		
		
		 UserFactory userFactory = new UserFactory();
		 user_ = userFactory.getUser(type);
	}
	
	public User getUser() {
		return this.user_;
	}
	
	public ArrayList<Product> getAllProducts() throws IOException {

		return user_.readDataFromDataBase();
	}
	
	public void updateProduct(Product product) throws IOException  {
		
		user_.updateProduct(product);
	}
	
	public ArrayList<Product> readDataFromDataBase() throws IOException {		
		
		return user_.readDataFromDataBase();
	}


	public Product getProductById(int id) throws  NumberFormatException, IOException {
		
		return user_.getProductById(id);
	}
	
	public  HashMap<String, Integer> getAllCatagoriesNames() throws IOException{
		
		return user_.getAllCatagoriesNames();
		
	}
	
	public ArrayList<Product> getAllOfCatagory(String catagory) throws IOException{
		
		return user_. getAllOfCatagory(catagory);
	}
	
	public boolean addProduct(Product p) throws IOException {		
		
		if( user_.getType() == "Manager") {
			boolean result =  ((Manager)user_).addProduct(p);
			return result;
		}
		
		return false;
	}
	
	public void removeProductById(int id) throws  IOException {
		
		if( user_.getType() == "Manager") {
			((Manager)user_).removeProductById(id);
		}
		
	}
	
		
}
