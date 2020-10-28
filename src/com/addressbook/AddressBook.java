package com.addressbook;

import java.util.Scanner;

public class AddressBook {

	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		int option;
		String fileOperated=null;
		do {
			System.out.println("== Address Book == \n 1.Create new addressbook 2.Open existing addressbook 3.Exit  :");
			option= sc.nextInt();
			switch (option) {
				case 1:
					fileOperated=AddressBookOperations.createAddressBook();
					performOperations(fileOperated);
					break;
				case 2:
					fileOperated=AddressBookOperations.displayAddressBook();
					if (fileOperated == null)
						System.out.println("No such address book present.");
					else
						performOperations(fileOperated);
					break;
				case 3:
					System.out.println("You exit the program.");
					System.exit(0);
				default:
					System.out.println("Wrong choice");
			}
		}while(option != 3);
		
	}
	
	public static void performOperations(String workOnAddressBook) {
		AddressBookOperations addressbookoperations = new AddressBookOperations(workOnAddressBook);
		String str, str1;
		int choice;

		do {
			System.out.print(
					" 1.Add a person \n 2.Update person info \n 3.Delete person \n 4.Print all contacts \n 5.Close the address book. \n Enter your choice : ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter first and last name to add details : ");
				str = sc.next();
				str1 = sc.next();
				addressbookoperations.addPerson(str,str1);
				break;
			case 2:
				System.out.println("Enter first and last name to update details : ");
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
				System.out.println("You closed the '"+workOnAddressBook.replaceFirst("[.][^.]+$", "")+" ' address book.");
				addressbookoperations.setFile(workOnAddressBook);
				break;
			default:
				System.out.println("Wrong choice.");
			}
		} while (choice != 5);
	}

}
