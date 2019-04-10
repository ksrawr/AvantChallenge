package CreditSystem;

import java.util.ArrayList;

public class Customer {
	
	private String name;
	private ArrayList<CreditCard> creditCards;
	private ArrayList<Payment> payments;
	private ArrayList<Charge> charges;
	
	public Customer(String name) {
		this.name = name;
	}
	
	public void openCreditCard(CreditCard c) {
		creditCards.add(c);
	}
	
	public void printCreditCards() {
		if((creditCards.size() - 1) > 0) {
			for( int i = 0; i < (creditCards.size() - 1); i++) {
				System.out.print("Credit Card Number: ............" + creditCards.get(i).getCreditCardNumber());
			}
		}
		else {
			System.out.println("No credit cards have been opened with this account");
		}
	}
}
