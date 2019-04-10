package CreditSystem;

import java.util.ArrayList;

public class Bank {
	
	private String name;
	private ArrayList<CreditCard> creditCards;
	private String fakenum = "1000100010001000";
	
	public Bank(String name) {
		this.name = name;
	}
	
	private void generateCreditCard(String creditCardHolder, int APR, int limit) {
		
		if(creditCards.size() - 1 > 0) {
			float temp = Float.parseFloat(this.fakenum);
			temp++;
			fakenum = Float.toString(temp);
		}
		
		creditCards.add(new CreditCard(creditCardHolder, this.fakenum, APR, limit));
	}
	
	private String getName() {
		return this.name;
	}
}
