package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class User implements Person {

	
	protected TxtDb txtDb_;

	public User() throws IOException {

		txtDb_ = TxtDb.getInstance();
	}

	public void setDb(TxtDb txtDb) {
		txtDb_ = txtDb;
	}

	public String getType() {
		return "User";
	}



	public void updateProduct(Product product) throws IOException {

		txtDb_.updateProductInDb(product);

	}

	public ArrayList<Product> readDataFromDataBase() throws IOException {

		return txtDb_.getAllProductsFromDb();
	}

	public Product getProductById(int id) throws NumberFormatException, IOException {
		return txtDb_.getProductByProductId(id);

	}

	public HashMap<String, Integer> getAllCatagoriesNames() throws IOException {

		HashMap<String, Integer> catagoriesMap = new HashMap<String, Integer>();

		ArrayList<Product> products = readDataFromDataBase();

		for (int i = 0; i < products.size(); i++) {
			Product p = products.get(i);
			catagoriesMap.put(p.getCategory(), 1);
		}

		return catagoriesMap;

	}

	public ArrayList<Product> getAllOfCatagory(String catagory) throws IOException {

		ArrayList<Product> productsOfCatagory = new ArrayList<Product>();

		ArrayList<Product> products = null;
		try {
			products = readDataFromDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < products.size(); i++) {

			if (catagory.equals(products.get(i).getCategory())) {

				productsOfCatagory.add(products.get(i));
			}
		}

		return productsOfCatagory;
	}

}