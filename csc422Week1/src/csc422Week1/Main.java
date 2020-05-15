/* 
 * Kaitlyn Schlegel
 * CSC 422 Software Engineering
 * Week 1 Assignment 1 Part 2
 * May 14, 2020
 * 
 * This program will allow a user to enter pets into the database, update pets, search for pets, and display all pets
 */

package csc422Week1;

import java.util.Scanner;
import java.util.ArrayList;

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
		
		System.out.println("Pet Database Program.");
		
		// continue displaying the menu after each selection until the user chooses to exit
		while (cont == true) {
			System.out.println("\nWhat would you like to do?");
			System.out.println("1) View all pets");
			System.out.println("2) Add more pets");
			System.out.println("3) Update an existing pet");
			System.out.println("4) Remove an existing pet");
			System.out.println("5) Search for pets by name");
			System.out.println("6) Search for pets by age");
			System.out.println("7) Exit program\n");
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
					Pet pet = new Pet();
					pet.setName(name);
					pet.setAge(age);
					pets.add(pet);
				}
				break;
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
			case 4: // remove a pet
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
				break;

			case 5: // search pets by name
				counter = 0;
				System.out.print("Enter a name to search: ");
				name = stdin.next();
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
			case 7: // exit program
				System.out.println("Goodbye!");
				// close the scanner
				stdin.close();
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
	}
} // end Main class
