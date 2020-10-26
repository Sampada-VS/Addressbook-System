package com.addressbook;

import java.io.*;
import java.util.*;

class AddressBookOperations {
	ArrayList<Person> personInfo;
	Scanner sc;
	
	public AddressBookOperations() {
		personInfo = new ArrayList<Person>();
		getFile();
	}

	public void getFile() {
		String tokens[] = null;
		String fname,lname,addr,city,state,zip,ph;

		try {
			FileReader filereader = new FileReader ("contacts.txt");
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

				Person p= new Person(fname,lname,addr,city,state,zip,ph);
				personInfo.add(p);
				line=bufferedreader.readLine();
			}
			bufferedreader.close();
			filereader.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}

	public void setFile() {
		try {
			Person p;
			String line;

			FileWriter filewriter = new FileWriter("contacts.txt");
			PrintWriter printwriter = new PrintWriter(filewriter);

			for (int i=0; i<personInfo.size(); i++) {
				p = (Person)personInfo.get(i);
				line = p.getFirstName() +","+ p.getLastName() +","+ p.getAddress() +","+ p.getCity() +","+ p.getState() +","+ p.getZip() +","+ p.getPhone();
				printwriter.println(line);
			}
			printwriter.flush();
			printwriter.close();
			filewriter.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}

	public void addPerson() {
		sc=new Scanner(System.in);
		System.out.print("\nEnter first name : ");
		String name=sc.nextLine();
		System.out.print("Enter last name : ");
		String surname=sc.nextLine();
		System.out.print("Enter address : ");
		String addr=sc.nextLine();
		System.out.print("Enter city : ");
		String cityName=sc.nextLine();
		System.out.print("Enter state : ");
		String stateName=sc.nextLine();
		System.out.print("Enter zip code : ");
		String zipCode=sc.nextLine();
		System.out.print("Enter mobile number : ");
		String phoneNo=sc.nextLine();
		System.out.println();
		Person p = new Person(name,surname,addr,cityName,stateName,zipCode,phoneNo);

		personInfo.add(p);
		System.out.println("Person added :\n"+p);
	}
	public void updatePerson (String n, String n1) {
		for (int i=0; i<personInfo.size(); i++) {
			Person p = (Person)personInfo.get(i);
			if ( n.equalsIgnoreCase(p.getFirstName()) && n1.equalsIgnoreCase(p.getLastName()) )
				personInfo.remove(i);
		}
		sc=new Scanner(System.in);
		System.out.print("Enter address : ");
		String addr=sc.nextLine();
		System.out.print("Enter city : ");
		String cityName=sc.nextLine();
		System.out.print("Enter state : ");
		String stateName=sc.nextLine();
		System.out.print("Enter zip code : ");
		String zipCode=sc.nextLine();
		System.out.print("Enter mobile number : ");
		String phoneNo=sc.nextLine();
		System.out.println();
		
		Person p=new Person(n,n1,addr,cityName,stateName,zipCode,phoneNo);
		personInfo.add(p);
		System.out.println("Person updated :\n"+p);
	}
	public void removePerson (String n, String n1) {
		for (int i=0; i<personInfo.size(); i++) {
			Person p = (Person)personInfo.get(i);
			if ( n.equalsIgnoreCase(p.getFirstName()) && n1.equalsIgnoreCase(p.getLastName()) )
				personInfo.remove(i);
		}
		System.out.println(personInfo);
	}
	public void printAll() {
		System.out.println("\nAll contacts in mailing label format :");
		for (int i=0; i<personInfo.size(); i++) {
			Person p = (Person)personInfo.get(i);
			System.out.println(p.getFirstName()+" "+p.getLastName());
			System.out.println(p.getAddress());
			System.out.println(p.getCity()+" "+p.getState()+"-"+p.getZip());
			System.out.println(p.getPhone()+"\n");
		}
	}
}
