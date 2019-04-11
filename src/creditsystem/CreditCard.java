package creditsystem;

public class CreditCard {
	
	private String creditCardHolder;
	private String creditCardNumber;
	private double APR;
	private double limit;
	private boolean limitExceeded = false;
	private int dateOpened;
	
	public CreditCard(String creditCardHolder, String creditCardNumber, double APR, double limit, int dateOpened) {
		this.creditCardHolder = creditCardHolder;
		this.creditCardNumber = creditCardNumber;
		this.APR = APR;
		this.limit = limit;
		this.dateOpened = dateOpened;
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
	
	public int getDateOpened() {
		return this.dateOpened;
	}
	
	public void printCreditCardInfo() {
		System.out.println("Credit Card Holder: ..." + this.creditCardHolder);
		System.out.println("Credit Card Number: ..." + this.creditCardNumber);
		System.out.println("APR: ... " + this.APR);
		System.out.println("Limit: ... $" + this.limit);
		System.out.println("Date Opened: ... " + this.dateOpened);
	}
}