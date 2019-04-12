package creditsystem;

import java.util.HashSet;

public class Bank {

	private String name;
	private CreditCard cc;
	private HashSet<CreditCard> creditCards;
	private HashSet<Customer> customers;
	private String fakenum = "1000";

	public Bank(String name) {
		this.name = name;
		this.creditCards = new HashSet<CreditCard>();
		this.customers = new HashSet<Customer>();
	}

	public void addCustomer(Customer c) {
		customers.add(c);
	}

	public CreditCard generateCreditCard(String creditCardHolder, double APR, double limit, int dateOpened) {
		if(!creditCards.isEmpty()) {
			float temp = Float.parseFloat(this.fakenum);
			temp++;
			this.fakenum = Float.toString(temp);
		}
		this.cc = new CreditCard(creditCardHolder, this.fakenum, APR, limit, dateOpened);
		this.creditCards.add(this.cc);
		return this.cc;
	}

	public String getName() {
		return this.name;
	}
}
