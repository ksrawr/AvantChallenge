package CreditSystem;

public class CreditCard {
	
	private String creditCardHolder;
	private String creditCardNumber;
	private int APR;
	private int limit;
	
	public CreditCard(String creditCardHolder, String creditCardNumber, int APR, int limit) {
		this.creditCardHolder = creditCardHolder;
		this.creditCardNumber = creditCardNumber;
		this.APR = APR;
		this.limit = limit;
	}
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
}