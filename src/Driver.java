import java.util.Scanner;

import creditsystem.Bank;
import creditsystem.Charge;
import creditsystem.CreditCard;
import creditsystem.Customer;
import creditsystem.Payment;
import creditsystem.CreditCardActivity;

public class Driver {

	public static boolean run = true;
	public static boolean loginStatus = false;

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		Bank bank;
		Charge charge;
		CreditCard card;
		Payment payment;
		Customer customer;
		double APR = 0.35;
		double amount;
		int date = 0;
		int prevDate = 0;

		while(run) {
			System.out.println("Welcome to the CreditCard Manager");
			System.out.println("------------------------------");
			System.out.println("Please select the following option");
			System.out.println("1. Open an account");
			System.out.println("2. Log In");
			System.out.println("3. Exit");
			int option = keyboard.nextInt();

			if(option == 1) {
				System.out.println("Create Account");
				System.out.println("------------------------------");
				System.out.println("Enter the account name");
				keyboard.nextLine();
				String username = keyboard.nextLine();
				loginStatus = true;
				System.out.println("Enter the desired credit card limit");
				double limit = keyboard.nextDouble();
				//				System.out.println("username: " + username);
				//				System.out.println("limit: " + limit);
				bank = new Bank("X");
				card = bank.generateCreditCard(username, APR, limit, date);
				System.out.println("***Credit Card created***");
				card.printCreditCardInfo();
				customer = new Customer(username);
				customer.openCreditCard(card);

				while(loginStatus) {
					System.out.println("------------------------------");
					System.out.println("Day: " + date + " since Credit Card: #" + card.getCreditCardNumber() + " Opened");
					System.out.println("Welcome to the Main Menu " + customer.getName() + "!");
					System.out.println("------------------------------");
					System.out.println("1. Charge Credit Card");
					System.out.println("2. Make a Payment");
					System.out.println("3. List of Charges");
					System.out.println("4. List of Payments");
					System.out.println("5. Calculate Outstanding Balance for X day");
					System.out.println("6. Exit");
					System.out.println("------------------------------");
					option = keyboard.nextInt();

					if(option == 1) {
						System.out.println("How much would you like to charge to your card?");
						amount = keyboard.nextDouble();
						System.out.println("When would you like to submit this charge?");
						date = keyboard.nextInt();
						charge = new Charge(card, amount, date);
						customer.chargeCreditCard(charge);
						charge.printCharge();
//						customer.printOutstandingBalance();
						date = charge.getDate();
					}
					else if(option == 2) {
						customer.printOutstandingBalance(date);
						if(customer.getOutstandingBalance() == 0) {
							System.out.println("No payment necessary. Returning to Main Menu");
						}
						else { 
							System.out.println("How much would you like to pay off your card?");
							amount = keyboard.nextDouble();
							System.out.println("When would you like to submit this charge?");
							date = keyboard.nextInt();
							payment = new Payment(card, amount, date);
							customer.payCreditCard(payment);
//							customer.printOutstandingBalance();
							date = payment.getDate();
						}
					}
					else if(option == 3) {
						System.out.println("Here are your charges: ");
						customer.printCharges();
					}
					else if(option == 4) {
						System.out.println("Here are your payments: ");
						customer.printPayments();
					}
					else if(option == 5) {
						customer.printOutstandingBalanceDates();
						System.out.println("***Max input: 30 and will result in accured interest***");
						System.out.println("What day would you like to calculate for?");
						int dateOption = keyboard.nextInt();
						customer.calculateTotalOutstandingBalance(card, dateOption);
					}
				}
			}
			else if (option == 2) {

			}
			else if (option == 3) {

			}
		}
		keyboard.close();
	}

}
