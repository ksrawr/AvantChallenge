package creditsystem;

public class CreditCard {
	
	private String creditCardHolder;
	private String creditCardNumber;
	private double APR;
	private double limit;
	private boolean limitExceeded = false;
	private int date;
	
	public CreditCard(String creditCardHolder, String creditCardNumber, double APR, double limit, int date) {
		this.creditCardHolder = creditCardHolder;
		this.creditCardNumber = creditCardNumber;
		this.APR = APR;
		this.limit = limit;
		this.date = date;
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
	
//	public void resetDate() {
//		this.date = 0;
//	}
	
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
	
	public int getDate() {
		return this.date;
	}
	
	public void printCreditCardInfo() {
		System.out.println("Credit Card Holder: ..." + this.creditCardHolder);
		System.out.println("Credit Card Number: ..." + this.creditCardNumber);
		System.out.println("APR: ... " + this.APR);
		System.out.println("Limit: ... $" + this.limit);
		System.out.println("Date Opened: ... " + this.date);
	}
}