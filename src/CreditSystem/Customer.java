package CreditSystem;

import java.util.ArrayList;
import java.util.HashSet;

public class Customer {
	
	private String name;
	private HashSet<CreditCard> creditCards;
	private ArrayList<Payment> payments;
	private ArrayList<Charge> charges;
	private double outstandingbalance = 0; 
	
	public Customer(String name) {
		this.name = name;
	}
	
	public void openCreditCard(CreditCard c) {
		this.creditCards.add(c);
	}
	
	public void chargeCreditCard(double amount, CreditCard c, float date) {
		c.updateLimit(amount);
		if(!c.checkLimit()) {
			this.charges.add(new Charge(c, amount, date));
			if(date == 30) {
				this.calculateTotalOutstandingBalance(c, date);
			}
			else {
				this.outstandingbalance += amount;
			}
		}
	}
	
	public void calculateTotalOutstandingBalance(CreditCard c, float date) {
		double interest = 0;
		float remainingDate;
		for (int i = 0; i < this.charges.size() - 1; i++ ) {
			Charge charge = this.charges.get(i);
			if(charge.equals(c)) {
				remainingDate = date - charge.getDate(); 
				interest += (charge.getAmount() * c.getAPR() / 365 * (remainingDate));
			}
		}
		this.outstandingbalance += interest;
	}
	
	public void payCreditCard(double amount, CreditCard c, int date) {
		if(creditCards.contains(c) && this.outstandingbalance > 0) {
			this.payments.add(new Payment(c, amount, date));
			this.outstandingbalance -= amount;
		}
	}
	
	public void printCreditCards() {
		if((creditCards.size()) > 0) {
			for( CreditCard c : this.creditCards) {
				System.out.print("Credit Card Number: ..." + c.getCreditCardNumber());
			}
		}
		else {
			System.out.println("No credit cards have been opened with this account");
		}
	}
	
	public void printPayments() {
		for(int i = 0; i < this.payments.size() - 1; i++ ) {
			System.out.println("Credit Card Number: .... "+ this.payments.get(i).getCreditCard() + "\tAmount: $" + this.payments.get(i).getAmount());
		}
	}
}
