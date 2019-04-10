package CreditSystem;

public class Charge {
	
	private CreditCard creditCard;
	private double amount;
	private float date;
	
	public Charge(CreditCard creditCard, double amount, float date) {
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
	
	public float getDate() {
		return this.date;
	}
	
}
