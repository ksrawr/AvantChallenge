package creditsystem;

import java.util.ArrayList;
import java.util.HashSet;

public class Customer {

	private String name;
	private HashSet<CreditCard> creditCards;
	private ArrayList<Payment> payments;
	private ArrayList<Charge> charges;
	private double outstandingbalance = 0;
	private ArrayList<Double> outstandingbalances;
	private ArrayList<CreditCardActivity> ccActivities;
	private ArrayList<Integer> dates = new ArrayList<Integer>();

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
			if(!this.dateExists(c.getDate())) {
				this.dates.add(c.getDate());
			}
			this.outstandingbalance += c.getAmount();
			this.outstandingbalances.add(this.outstandingbalance);
		}
	}

	public void calculateTotalOutstandingBalance(CreditCard c, int date) {
		double interest = 0;
		ArrayList<Integer> dateDifferences = new ArrayList<Integer>();
		if(!this.dateExists(date)) {
			this.dates.add(date);
		}

		if(this.dates.size() > 1) {
			for(int i = 0; i < this.dates.size()-1; i++) {
				dateDifferences.add(this.dates.get(i+1) - this.dates.get(i));
			}
		}

		for(int i = 0; i < this.ccActivities.size(); i++) {
			if(this.ccActivities.size() > 1 && this.ccActivities.get(i).getDate() != 30) {
				interest += this.outstandingbalances.get(i) * c.getAPR() / 365 * (dateDifferences.get(i));
			}
			else {
				interest += this.outstandingbalances.get(i) * c.getAPR() / 365 * (dateDifferences.get(i)); 
			}
		}
		this.outstandingbalance += interest;
		this.printOutstandingBalance(date);
	}

	public void payCreditCard(Payment p) {
		if(creditCards.contains(p.getCreditCard()) && this.outstandingbalance > 0) {
			this.ccActivities.add(p);
			this.payments.add(p);
			if(!this.dateExists(p.getDate())) {
				this.dates.add(p.getDate());
			}
			this.outstandingbalance -= p.getAmount();
			this.outstandingbalances.add(this.outstandingbalance);
		}
	}

	public boolean dateExists(int date) {
		boolean dateDuplicate = false;
		for(int i = 0; i < this.dates.size(); i++) {
			if(this.dates.get(i) == date) {
				dateDuplicate = true;
				return dateDuplicate;
			}
		}
		return dateDuplicate;
	}
	
	public void reset() {
		this.ccActivities.clear();
		this.dates.clear();
		this.payments.clear();
		this.creditCards.clear();
		this.charges.clear();
		this.outstandingbalance = 0;
		this.outstandingbalances.clear();
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
		for(int date : this.dates) {
			System.out.print("[" + date + "]");
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
