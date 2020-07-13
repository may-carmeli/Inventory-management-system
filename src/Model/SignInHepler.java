package Model;

public class SignInHepler {
	
		
	private String savedManagerUserName = "MANAGER";
	private String savedManagerPassword = "MANAGER";
	
	private String savedSellerUserName = "SELLER";
	private String savedSellerPassword = "SELLER";

	public SignInHepler() {};
	
	public String signIn(String userName, String password) {
		
		if(userName.equals(savedManagerUserName) == true && password.equals(savedManagerPassword) ) {
			
			return	"Manager"; 
		} else 	if(userName.equals(savedSellerUserName) == true && password.equals(savedSellerPassword) ) {
			
			return "Seller";
		}
		
		return "";
	}
}
