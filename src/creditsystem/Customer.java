package creditsystem;

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
		this.creditCards = new HashSet<CreditCard>();
		this.payments = new ArrayList<Payment>();
		this.charges = new ArrayList<Charge>();
	}
	
	public void openCreditCard(CreditCard c) {
		this.creditCards.add(c);
	}
	
	public void chargeCreditCard(Charge c) {
		CreditCard card = c.getCreditCard();
		card.updateLimit(c.getAmount());
		if(!card.checkLimit()) {
			this.charges.add(c);
			if(c.getDate() == 30) {
				this.outstandingbalance += c.getAmount();
				this.calculateTotalOutstandingBalance(card, c.getDate());
			}
			else {
				this.outstandingbalance += c.getAmount();
			}
		}
	}
	
	public void calculateTotalOutstandingBalance(CreditCard c, float date) {
		double interest = 0;
		ArrayList<Float> dateDifferences = new ArrayList<Float>();
		for (int i = 1; i <= this.charges.size(); i++ ) {
			if(date == 30) {
				interest += (this.charges.get(i-1).getAmount() * c.getAPR() / 365 * 30);
			} else {
				dateDifferences.add((this.charges.get(i).getDate()-this.charges.get(i).getDate()));
			}
		}
		if(date != 30) {
			for (int i = 0; i < this.charges.size(); i++ ) {			
				interest += (this.charges.get(i).getAmount() * c.getAPR() / 365 * (dateDifferences.get(i)));
			}
		}
		this.outstandingbalance += interest;
	}

	public void payCreditCard(Payment p) {
		if(creditCards.contains(p.getCreditCard()) && this.outstandingbalance > 0) {
			this.payments.add(p);
			this.outstandingbalance -= p.getAmount();
		}
	}
	
	public void printCreditCards() {
		if((creditCards.size()) > 0) {
			for( CreditCard c : this.creditCards) {
				System.out.print("Credit Card Number: ...#" + c.getCreditCardNumber());
			}
		}
		else {
			System.out.println("No credit cards have been opened with this account");
		}
	}
	
	public void printCharges() {
		if(this.charges.size() > 0) {
			for(int i = 0; i < this.charges.size(); i++ ) {
				System.out.println("Credit Card Number: .... #"+ this.charges.get(i).getCreditCard().getCreditCardNumber() + "\tAmount: $" + this.charges.get(i).getAmount());
			}
		}
		else {
			System.out.println("There are no listed charges on this card");
		}
	}
	
	public void printPayments() {
		if(this.payments.size() > 0) {
			for(int i = 0; i < this.payments.size(); i++ ) {
				System.out.println("Credit Card Number: .... #"+ this.payments.get(i).getCreditCard().getCreditCardNumber() + "\tAmount: $" + this.payments.get(i).getAmount());
			}
		}
		else {
			System.out.println("There are no listed payments on this card");
		}
	}
	
	public void printOutstandingBalance() {
		System.out.println("You have an Outstanding Balance: ... $" + this.outstandingbalance);
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getOutstandingBalance() {
		return this.outstandingbalance;
	}
}
