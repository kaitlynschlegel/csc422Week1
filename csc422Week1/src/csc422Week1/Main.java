/* 
 * Kaitlyn Schlegel
 * CSC 422 Software Engineering
 * Week 2 Assignment 2 Part 2
 * May 14, 2020
 * Revisions:
 * 		May 22, 2020: added save/load and error handling
 * 
 * This program will allow a user to enter pets into the database, update pets, search for pets, and display all pets
 */

package csc422Week1;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner stdin = new Scanner(System.in);
		
		// initialize variables
		ArrayList<Pet> pets = new ArrayList<Pet>();
		int selection;
		String name = "";
		int age = 0;
		boolean cont = true;
		int id;
		int counter;
		String oldName;
		int oldAge;
		boolean ageCont = true;
		
		
		/*
		 * Saving and loading the file was written with the help of:
		 * https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
		*/
		
		// load the pets from the petList.ser file
		try {
			FileInputStream fis = new FileInputStream("petList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			pets = (ArrayList<Pet>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			// do nothing.
			// the program will create the file at the end
		}
		
		System.out.println("Pet Database Program.");
		
		// continue displaying the menu after each selection until the user chooses to exit
		while (cont == true) {
			System.out.println("\nWhat would you like to do?");
			System.out.println("1) View all pets");
			System.out.println("2) Add more pets");
			System.out.println("3) Remove an existing pet");
			System.out.println("4) Exit program\n");
		/* remove these options because they are not needed for week 2
			System.out.println("3) Update an existing pet");
			System.out.println("4) Remove an existing pet");
			System.out.println("5) Search for pets by name");
			System.out.println("6) Search for pets by age");
		*/
			System.out.print("Your selection: ");
			selection = stdin.nextInt();
			
			
			// do whatever the user selected
			switch(selection) {
			case 1: // display all pets
				displayTable(pets);
				break;
			case 2: // add pets
				while (true) {
					System.out.print("Add pet (name, age): ");
					name = stdin.next();
					if (name.equals("done")) {
						break;
					}
					age = stdin.nextInt();
					// make sure the age is between 1 and 20
					while (ageCont == true) {
						if (age < 1 || age > 20) {
							System.out.println("Age must be between 1 and 20");
							System.out.print("Please enter " + name + "'s age: ");
							age = stdin.nextInt();
						} else {
							ageCont = false;
						}
					}
					// add the new pet to the array
					Pet pet = new Pet();
					pet.setName(name);
					pet.setAge(age);
					pets.add(pet);
					// don't allow more than 5 pets to be in the array
					if (pets.size() >= 5) {
						System.out.print("Database is full. You may not add more pets.\n");
						break;
					}
				}
				break;
				
			/* remove these options as they are not needed for week 2
			case 3: // update a pet
				// display all pets
				displayTable(pets);
				// get the pet that the user wants to update
				System.out.print("Enter the pet ID you want to update: ");
				id = stdin.nextInt();
				// save the old information to use in the print statement later
				oldName = pets.get(id).getName();
				oldAge = pets.get(id).getAge();
				// get the new name and age from the user
				System.out.print("Enter new name and new age: ");
				name = stdin.next();
				age = stdin.nextInt();
				// update the pet
				pets.get(id).setName(name);
				pets.get(id).setAge(age);
				System.out.print(oldName + " " + oldAge + " changed to " + pets.get(id).getName() + " " + pets.get(id).getAge() + ".\n");
				break;
			*/
				
			case 3: // remove a pet
				try {
					displayTable(pets);
					System.out.print("Enter the pet ID to remove: ");
					// get the pet to remove from the user
					id = stdin.nextInt();
					// save the old information to use in the print statement later
					oldName = pets.get(id).getName();
					oldAge = pets.get(id).getAge();
					// remove the pet
					pets.remove(id);
					System.out.print(oldName + " " + oldAge + " is removed.\n");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("You must enter a valid pet ID.");
					System.out.println("Valid pet IDs: 0-" + (pets.size()-1));
				}
				break;
				
			/* remove these options as they are not needed for week 2
			case 5: // search pets by name
				counter = 0;
				System.out.print("Enter a name to search: ");
				name = (stdin.next()).toLowerCase();
				// table header
				System.out.println("+----------------------+");
				System.out.println("| ID | NAME      | AGE |");
				System.out.println("+----------------------+");
				for (Pet pet : pets) {					
					if (pet.getName().equals(name)) {
						// increase the counter to know how many rows will be in this table
						counter++;
						// table body with data
						System.out.print("| ");
						System.out.printf("%2s", pets.indexOf(pet));
						System.out.print(" | ");
						System.out.printf("%-9s", pet.getName());
						System.out.print(" | ");
						System.out.printf("%3s", pet.getAge());
						System.out.print(" |\n");
					}
				}
				// table footer
				System.out.println("+----------------------+");
				System.out.println(counter + " rows in set.");
				break;
			case 6: // search pets by age
				counter = 0;
				System.out.print("Enter an age to search: ");
				age = stdin.nextInt();
				// table header
				System.out.println("+----------------------+");
				System.out.println("| ID | NAME      | AGE |");
				System.out.println("+----------------------+");
				for (Pet pet : pets) {					
					if (pet.getAge() == age) {
						// increase the counter to know how many rows will be in this table
						counter++;
						// table body with data
						System.out.print("| ");
						System.out.printf("%2s", pets.indexOf(pet));
						System.out.print(" | ");
						System.out.printf("%-9s", pet.getName());
						System.out.print(" | ");
						System.out.printf("%3s", pet.getAge());
						System.out.print(" |\n");
					}
				}
				// table footer
				System.out.println("+----------------------+");
				System.out.println(counter + " rows in set.");
				break;
			*/
			case 4: // exit program
				System.out.println("Goodbye!");
				// close the scanner
				stdin.close();
				// save the pets to the petList.ser file
				save(pets);
				// close the program;
				System.exit(0);
			default:
				System.out.println("Please enter a number 1-7");
			} // end switch for menu
		} // end while that keeps the program running
	} // end main method

	
	public static void displayTable(ArrayList<Pet> pets) {
		// table header
		System.out.println("+----------------------+");
		System.out.println("| ID | NAME      | AGE |");
		System.out.println("+----------------------+");
		// table body with data
		for (int i = 0; i < pets.size(); i++) {
			System.out.print("| ");
			System.out.printf("%2s", i);
			System.out.print(" | ");
			System.out.printf("%-9s", pets.get(i).getName());
			System.out.print(" | ");
			System.out.printf("%3s", pets.get(i).getAge());
			System.out.print(" |\n");
		}
		// table footer
		System.out.println("+----------------------+");
		System.out.println(pets.size() + " rows in set.\n");
	} // end displayTable()
	
	/*
	 * Saving and loading the file was written with the help of:
	 * https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
	*/
	public static void save(ArrayList<Pet> pets) {
		try {
			FileOutputStream fos = new FileOutputStream("petList.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(pets);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
} // end Main class
