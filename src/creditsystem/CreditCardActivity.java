package creditsystem;

public abstract class CreditCardActivity {
	
	private CreditCard creditCard;
	private double amount;
	private int date;
	
	public CreditCard getCreditCard() {
		return this.creditCard;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public int getDate() {
		return this.date;
	}
	
	public int getInterestDate() {
		this.date = 30 - this.date;
		return this.date;
	}
}
