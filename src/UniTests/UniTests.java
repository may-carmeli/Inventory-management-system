package UniTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

import Model.Manager;
import Model.Seller;
import Model.Product;
import Model.SignInHepler;
import Model.User;
import Model.TxtDb;

import Model.UserFactory;

class UniTests {

	@Test
	void managerSignInTest() {
		
		SignInHepler signInHepler = new SignInHepler();
		
		String userName = "MANAGER";
		String password = "MANAGER"; 
		
		String result = signInHepler.signIn(userName, password);
		assertEquals("Manager", result);		
	}
	
	@Test
	void sellerSignInTest() {
		
		SignInHepler signInHepler = new SignInHepler();
		
		String userName = "SELLER";
		String password = "SELLER"; 
		
		String result = signInHepler.signIn(userName, password);
		assertEquals("Seller", result);		
	}
	
	@Test
	void wrongUserAndPasswordSignInTest() {
		
		SignInHepler signInHepler = new SignInHepler();
		
		String userName = "adsdsad";
		String password = "agfhgh"; 
		
		String result = signInHepler.signIn(userName, password);
		assertEquals("", result);		
	}
	
	@Test
	void goodUserFactoryTest() throws IOException {
		 
		String wantedType = "Seller";
		UserFactory userFactory = new UserFactory();
		User user = userFactory.getUser(wantedType);
		
		if ( user.getType().equals("Seller")) {
			assertTrue(true);
		}
		
		else {
			fail(" user factory failed");
		}
	}
	
	@Test
	void badUserFactoryTest() throws IOException {
		 
		String wantedType = "dummy";
		UserFactory userFactory = new UserFactory();
		/// userFactory can't create user dummy
		assertNull(userFactory.getUser(wantedType));		
	}
	
	@Test
	void sizeOfDataBaseTest() throws IOException {
		 
		TxtDb txtDb  = new TxtDb();	
		txtDb.setDbPath("test1_database.csv");
		ArrayList<Product> products = txtDb.getAllProductsFromDb();
		///check that the size of all the product is 12 (all the products was loaded properly
		assertEquals(12, products.size());	
		
	}
	
	@Test
	void loadedProductTest() throws IOException {
		 
		TxtDb txtDb  = new TxtDb();	
		txtDb.setDbPath("test1_database.csv");
		ArrayList<Product> products = txtDb.getAllProductsFromDb();

		Product product = new Product(6666, "ניילון נצמד", "בית",10,20,50);
		
		/// check that specific product form  database was loaded properly 
		assertTrue(product.equals(products.get(4))); 
	}
	
	@Test
	void getProductByIdTest() throws IOException {
		 
		TxtDb txtDb  = new TxtDb();	
		txtDb.setDbPath("test1_database.csv");

		int wantedId = 10111;
		
		Product productFromDb = txtDb.getProductByProductId(wantedId);
		assertEquals(productFromDb.getProductID(), wantedId); 
	}
	
	@Test
	void removeProductFromDbAndAddItBackByManagerTest() throws IOException {
		
		TxtDb txtDb  = new TxtDb();	
		txtDb.setDbPath("test1_database.csv");
		
		Manager m =  new Manager();
		m.setDb(txtDb);
		
		/*
		 *  9999	סכיני גילוח	טיפוח	40	90	80
		 */

		int wantedProductId = 9999;
		Product savedProduct = m.getProductById(wantedProductId);
		
		/// remove product by manager
		m.removeProductById(wantedProductId);
		////check that the product has been removed		
		assertNull(m.getProductById(wantedProductId)); 	
		
		/// manager add the product back 
		m.addProduct(savedProduct);
		////check that the product has been returned to the db
		
		assertNotNull(m.getProductById(wantedProductId));
		
	}
	
	@Test
	void updateAmountOfSpesificProductTest() throws IOException {
		
		TxtDb txtDb  = new TxtDb();	
		txtDb.setDbPath("test1_database.csv");
		
		Seller s =  new Seller();
		s.setDb(txtDb);		
		
		int wantedProductId = 10333;
		Product product = s.getProductById(wantedProductId);
		
		Random rand =  new Random();;
		int max = 300;
		int min = 200;
		  
		int amount = rand.nextInt(max - min + 1);
		
		product.setAmount(amount);
		///change amount of this product 
		s.updateProduct(product);
		

		Product productFromDb = s.getProductById(wantedProductId);
		
		assertEquals(productFromDb.getAmount(), amount); 
		
	}

	


}
