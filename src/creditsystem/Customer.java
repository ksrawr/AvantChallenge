package creditsystem;

import java.util.ArrayList;
import java.util.HashSet;

public class Customer {

	private String name;
	private HashSet<CreditCard> creditCards;
	private ArrayList<Payment> payments;
	private ArrayList<Charge> charges;
//	private ArrayList<Integer> dates;
	private double outstandingbalance = 0;
	private ArrayList<Double> outstandingbalances;
	private ArrayList<CreditCardActivity> ccActivities;

	public Customer(String name) {
		this.name = name;
		this.creditCards = new HashSet<CreditCard>();
		this.payments = new ArrayList<Payment>();
		this.charges = new ArrayList<Charge>();
		this.ccActivities = new ArrayList<CreditCardActivity>();
		this.outstandingbalances = new ArrayList<Double>();
	}

	public void openCreditCard(CreditCard c) {
		this.creditCards.add(c);
	}

	public void chargeCreditCard(Charge c) {
		CreditCard card = c.getCreditCard();
		card.updateLimit(c.getAmount());
		if(!card.checkLimit()) {
			this.charges.add(c);
			this.ccActivities.add(c);
//			this.dates.add(c.getDate());
			//			if(c.getDate() == 30) {
			//				this.outstandingbalance += c.getAmount();
			////				this.calculateTotalOutstandingBalance(card, c.getDate());
			//				this.dates
			//			}
			//			else {
			//				this.outstandingbalance += c.getAmount();
			//			}
			this.outstandingbalance += c.getAmount();
			this.outstandingbalances.add(c.getAmount());
		}
	}

	public void calculateTotalOutstandingBalance(CreditCard c, int date) {
		double interest = 0;
//		CreditCard card;
//		for(int i = 1; i <= this.ccActivities.size(); i++) {
//			card = this.ccActivities.get(i-1).getCreditCard();
//			card.updateDateInterest(card.getDateInterest() - this.ccActivities.get(i-1).getDate());
//			if(card.getDateInterest() == 0) {
//				interest += this.ccActivities.get(i-1).getAmount() * c.getAPR() / 365 * (this.ccActivities.get(i-1).getDate());
//			} 
//			else {
//				if(this.ccActivities.get(i-1) instanceof Charge) {
//					this.outstandingbalance += this.ccActivities.get(i-1).getAmount();
//				}
//				else if(this.ccActivities.get(i-1) instanceof Payment) {
//					this.outstandingbalance -= this.ccActivities.get(i-1).getAmount();
//				}
//			}
//		}

		for(int i = 1; i <= this.ccActivities.size(); i++) {
			if(this.ccActivities.size() > 1 && this.ccActivities.get(i).getDate() != 30) {
				if(i == 1 && this.ccActivities.get(i).getDate() == 0) {
					continue;
				}
//				else if(i == 1 && this.ccActivities.get(i).getDate() != 0) {
//					interest += this.ccActivities.get(i-1).getAmount() * c.getAPR() / 365 * (this.ccActivities.get(i-1).getDate());
//				}
				else {
					interest += this.outstandingbalances.get(i) * c.getAPR() / 365 * (this.ccActivities.get(i-1).getInterestDate());
				} 
			}
			else {
				interest += this.outstandingbalances.get(i) * c.getAPR() / 365 * (this.ccActivities.get(i-1).getInterestDate()); 
			}
		}
		this.outstandingbalance += interest;
		this.printOutstandingBalance(date);
	}

	public void payCreditCard(Payment p) {
		if(creditCards.contains(p.getCreditCard()) && this.outstandingbalance > 0) {
			this.ccActivities.add(p);
			this.payments.add(p);
//			this.dates.add(p.getDate());
			this.outstandingbalance -= p.getAmount();
			this.outstandingbalances.add(p.getAmount());
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
	
	public void printOutstandingBalanceDates() {
		System.out.println("Suggested dates: ... ");
		for(int i = 0; i < this.ccActivities.size(); i++) {
			System.out.print("[" + this.ccActivities.get(i).getDate() + "]");
		}
		System.out.println(".");
	}

	public void printOutstandingBalance(int date) {
		System.out.println("After " + date + " days,... \nYou have an Outstanding Balance: ... $" + this.outstandingbalance);
	}

	public String getName() {
		return this.name;
	}

	public double getOutstandingBalance() {
		return this.outstandingbalance;
	}
}
