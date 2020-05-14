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
		
		
		
		System.out.println("NEW CONTENTTTTT");
		
		
		
		// initialize variables
		ArrayList<Pet> pets = new ArrayList<Pet>();
		int selection;
		String name = "";
		int age = 0;
		boolean cont = true;
		
		System.out.println("Pet Database Program.");
		
		// continue displaying the menu after each selection until the user chooses to exit
		while (cont == true) {
			System.out.println("\nWhat would you like to do?");
			System.out.println("1) View all pets");
			System.out.println("2) Add more pets");
			System.out.println("3) Exit program");
			selection = stdin.nextInt();
			
			// do whatever the user selected
			switch(selection) {
			case 1:
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
				break;
			case 2:
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
			case 3:
				System.out.println("Goodbye!");
				System.exit(0);
			default:
				System.out.println("Please enter a number 1-3");
			}
		}
	}

}
