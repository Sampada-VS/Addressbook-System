package com.addressbook;

public class AddressBook {
	public static void main(String[] args) {
		AddressBookOperations addressbookoperations = new AddressBookOperations();

		System.out.print(" Add a person : ");
		addressbookoperations.addPerson();
		addressbookoperations.setFile();

	}
}
