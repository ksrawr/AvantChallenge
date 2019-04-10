package CreditSystem;

public class CreditCard {
	
	private String creditCardHolder;
	private String creditCardNumber;
	private double APR;
	private double limit;
	private boolean limitExceeded = false;
	
	public CreditCard(String creditCardHolder, String creditCardNumber, double APR, double limit) {
		this.creditCardHolder = creditCardHolder;
		this.creditCardNumber = creditCardNumber;
		this.APR = APR;
		this.limit = limit;
	}
	
	public void updateLimit(double amount) {
		double temp = this.limit;
		temp -= amount;
		if(temp < 0) {
			limitExceeded = true;
		}
		else {
			this.limit = temp;
		}
	}
	
	public boolean checkLimit() {
		return this.limitExceeded;
	}
	
	public String getCreditCardHolder() { 
		return this.creditCardHolder;
	}
	
	public String getCreditCardNumber() {
		return this.creditCardNumber;
	}
	
	public double getAPR() {
		return this.APR;
	}
	
	public double getLimit() {
		return this.limit;
	}
}