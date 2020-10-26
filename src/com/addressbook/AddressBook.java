package com.addressbook;

import java.util.Scanner;

public class AddressBook {

	static Scanner sc;

	public static void main(String[] args) {
		AddressBookOperations addressbookoperations = new AddressBookOperations();

		String str, str1;
		int choice;
		sc = new Scanner(System.in);

		do {
			System.out.print(
					" 1.Add a person \n 2.Update person info \n 3.Delete person \n 4.Print all contacts \n 5.Exit \n Enter your choice : ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				addressbookoperations.addPerson();
				break;
			case 2:
				System.out.println("Enter first and last name to update info : ");
				str = sc.next();
				str1 = sc.next();
				addressbookoperations.updatePerson(str, str1);
				break;
			case 3:
				System.out.print("Enter first and last name to delete a person :");
				str = sc.next();
				str1 = sc.next();
				addressbookoperations.removePerson(str, str1);
				System.out.println("Person named " + str + " " + str1 + " deleted from address book.");
				break;
			case 4:
				addressbookoperations.printAll();
				break;
			case 5:
				System.out.println("You exited the program.");
				addressbookoperations.setFile();
				System.exit(0);
				break;
			default:
				System.out.println("Wrong choice.");
			}
		} while (choice != 5);
	}

}
