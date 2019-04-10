package CreditSystem;

public class Payment {
	
	private CreditCard creditCard;
	private double amount;
	private int date;
	
	public Payment(CreditCard creditCard, double amount, int date) {
		this.creditCard = creditCard;
		this.amount = amount;
		this.date = date;
	}
	
	public CreditCard getCreditCard() {
		return this.creditCard;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public int date() {
		return this.date;
	}
}
