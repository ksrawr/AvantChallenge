package creditsystem;

public class Charge extends CreditCardActivity {
	
	private CreditCard creditCard;
	private double amount;
	private int date;
	
	public Charge(CreditCard creditCard, double amount, int date) {
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
	
	public int getDate() {
		return this.date;
	}
	
	public void printCharge() {
		System.out.println("Credit Card: ..." + this.creditCard.getCreditCardNumber());
		System.out.println("Has been Charged: $" + this.amount);
		System.out.println(this.date + " days since card was opened");
	}
	
}
