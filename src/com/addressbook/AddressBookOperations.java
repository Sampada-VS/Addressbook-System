package com.addressbook;

import java.io.*;
import java.util.*;

class AddressBookOperations {
	ArrayList<Person> personInfo;
	static Scanner sc;
	Dictionary<String, String> statePerson = new Hashtable<String, String>();
	Dictionary<String, String> cityPerson = new Hashtable<String, String>();

	
	public AddressBookOperations(String addressBookName) {
		personInfo = new ArrayList<Person>();
		getFile(addressBookName);
	}
	public AddressBookOperations(String addressBookName,String city) {
		personInfo = new ArrayList<Person>();
		getFile(addressBookName);
	}

	public void searchCity(String addressBookName,String city) {
		personInfo = new ArrayList<Person>();
		getFile(addressBookName);
		searchUsingCity(city);
	}
	public void searchState(String addressBookName,String state) {
		personInfo = new ArrayList<Person>();
		getFile(addressBookName);
		searchUsingState(state);
	}

	public static String createAddressBook() {
		sc = new Scanner(System.in);
		System.out.println("Enter name of address book :");
		String fileName = sc.next();
		String addressBookCreated = null;
		try {
			File myObj = new File(fileName + ".txt");
			addressBookCreated = myObj.getName();
			if (myObj.createNewFile()) {
				System.out
						.println("Address book created: ' " + addressBookCreated.replaceFirst("[.][^.]+$", "") + " '");
			} else {
				System.out.println("Address book already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return addressBookCreated;
	}

	public static String displayAddressBook() {
		sc= new Scanner(System.in);
		File directoryPath = new File(".");
		String addressBookEntered;
		String addressBookExisted = null;
		int flag = 0;
		File[] files = directoryPath.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});

		for (File filename : files) {
			System.out.println(filename.getName().replaceFirst("[.][^.]+$", ""));
		}
		System.out.println("Enter address book :");
		addressBookEntered = sc.next();
		for (File filename : files) {
			if (addressBookEntered.equals(filename.getName().replaceFirst("[.][^.]+$", ""))) {
				addressBookExisted = filename.getName();
				flag = 1;
			}
		}
		if (flag == 1) {
			System.out.println("You opened ' " + addressBookEntered + " ' address book.");
			return addressBookExisted;
		} else
			return null;

	}

	public void getFile(String addressBookName) {
		String tokens[] = null;
		String fname, lname, addr, city, state, zip, ph;

		try {
			FileReader filereader = new FileReader(addressBookName);
			BufferedReader bufferedreader = new BufferedReader(filereader);

			String line = bufferedreader.readLine();
			while (line != null) {
				tokens = line.split(",");
				fname = tokens[0];
				lname = tokens[1];
				addr = tokens[2];
				city = tokens[3];
				state = tokens[4];
				zip = tokens[5];
				ph = tokens[6];

				Person p = new Person(fname, lname, addr, city, state, zip, ph);
				personInfo.add(p);
				line = bufferedreader.readLine();
			}
			bufferedreader.close();
			filereader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void setFile(String addressBookName) {
		try {
			Person p;
			String line;

			FileWriter filewriter = new FileWriter(addressBookName);
			PrintWriter printwriter = new PrintWriter(filewriter);

			for (int i = 0; i < personInfo.size(); i++) {
				p = (Person) personInfo.get(i);
				line = p.getFirstName() + "," + p.getLastName() + "," + p.getAddress() + "," + p.getCity() + ","
						+ p.getState() + "," + p.getZip() + "," + p.getPhone();
				printwriter.println(line);
			}
			printwriter.flush();
			printwriter.close();
			filewriter.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void addPerson(String name,String surname) {
/*		int flag = 0;
		for (int i = 0; i < personInfo.size(); i++) {
			Person p = (Person) personInfo.get(i);
			if (name.equalsIgnoreCase(p.getFirstName()) && surname.equalsIgnoreCase(p.getLastName()))
				flag = 1;
		}
		if (flag == 1)
	*/
		Person found = personInfo.stream().filter((p) -> name.equalsIgnoreCase(p.getFirstName()) && surname.equalsIgnoreCase(p.getLastName())).findAny().orElse(null);
		if (found != null)
			System.out.println("Can't add person entry because it already exists.");
		else {
			sc = new Scanner(System.in);
			System.out.print("Enter address : ");
			String addr = sc.nextLine();
			System.out.print("Enter city : ");
			String cityName = sc.nextLine();
			System.out.print("Enter state : ");
			String stateName = sc.nextLine();
			System.out.print("Enter zip code : ");
			String zipCode = sc.nextLine();
			System.out.print("Enter mobile number : ");
			String phoneNo = sc.nextLine();
			System.out.println();
			Person p = new Person(name, surname, addr, cityName, stateName, zipCode, phoneNo);
			personInfo.add(p);
			System.out.println("Person added :\n" + p);
		}
	}

	public void updatePerson(String n, String n1) {
		for (int i = 0; i < personInfo.size(); i++) {
			Person p = (Person) personInfo.get(i);
			if (n.equalsIgnoreCase(p.getFirstName()) && n1.equalsIgnoreCase(p.getLastName()))
				personInfo.remove(i);
		}
		sc = new Scanner(System.in);
		System.out.print("Enter address : ");
		String addr = sc.nextLine();
		System.out.print("Enter city : ");
		String cityName = sc.nextLine();
		System.out.print("Enter state : ");
		String stateName = sc.nextLine();
		System.out.print("Enter zip code : ");
		String zipCode = sc.nextLine();
		System.out.print("Enter mobile number : ");
		String phoneNo = sc.nextLine();
		System.out.println();

		Person p = new Person(n, n1, addr, cityName, stateName, zipCode, phoneNo);
		personInfo.add(p);
		System.out.println("Person updated :\n" + p);
	}

	public void removePerson(String n, String n1) {
		for (int i = 0; i < personInfo.size(); i++) {
			Person p = (Person) personInfo.get(i);
			if (n.equalsIgnoreCase(p.getFirstName()) && n1.equalsIgnoreCase(p.getLastName()))
				personInfo.remove(i);
		}
		System.out.println(personInfo);
	}

	public void printAll() {
		System.out.println("\nAll contacts in mailing label format :");
		for (int i = 0; i < personInfo.size(); i++) {
			Person p = (Person) personInfo.get(i);
			System.out.println(p.getFirstName() + " " + p.getLastName());
			System.out.println(p.getAddress());
			System.out.println(p.getCity() + " " + p.getState() + "-" + p.getZip());
			System.out.println(p.getPhone() + "\n");
		}
	}

	public void searchUsingCity(String city) {
		for (int i = 0; i < personInfo.size(); i++) {
				Person p = (Person) personInfo.get(i);
				if (city.equalsIgnoreCase(p.getCity()) ) 
					cityPerson.put(p.getFirstName(), city);										
		}
		Enumeration<String> personsKeys= cityPerson.keys();
		while(personsKeys.hasMoreElements()) {
			System.out.println(personsKeys.nextElement());
		}	
	}
	public void searchUsingState(String state) {
		for (int i = 0; i < personInfo.size(); i++) {
			Person p = (Person) personInfo.get(i);
			if (state.equalsIgnoreCase(p.getState()) ) 
				statePerson.put(p.getFirstName(), state);						
		}
		Enumeration<String> personKeys= statePerson.keys();
		while(personKeys.hasMoreElements()) {
			System.out.println(personKeys.nextElement());
		}
	}
}
