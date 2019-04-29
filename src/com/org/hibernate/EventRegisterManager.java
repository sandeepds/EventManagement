package com.org.hibernate;

/**
 * @author SDudi
 *
 */
import java.util.Scanner;

import com.org.hibernate.service.EventRegisterService;

public class EventRegisterManager {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		Scanner userInput = new Scanner(System.in);
		int choice;
		String mId;
		int eventId;
		boolean success = false;
		EventRegisterService eventRegisterService = new EventRegisterService();
		MENULOOP : do{

	        System.out.println("Choose from these choices");
	        System.out.println("-------------------------\n");
			System.out.print("1. Register Employee For Events \n");
			System.out.print("2. Display All employees \n");
			System.out.print("3. Exit the menu \n");		 
	        System.out.println("-------------------------\n");

			choice = userInput.nextInt();
		
			CHOICELOOP: switch (choice) {
			case 1:
				System.out.println("Register Employee For events \n");
				System.out.println("Enter MID of Employee \n");
				mId = userInput.next();
				if(mId != null){
					System.out.println("Enter any event id from the below list to which the employee wishes to enroll...\n");
					eventRegisterService.displayAllEvents();
					eventId = userInput.nextInt();
					success = eventRegisterService.registerEvent(mId, eventId);
					if (success) {
						System.out.println("Successfully registered the Employee to the event.");
					} else {	System.out.println("Do you wish to continue? Y/N");
						char userContinueInd = userInput.next().charAt(0);
	
						if (userContinueInd == 'Y') {
							break CHOICELOOP;
						} else if (userContinueInd == 'N') {
							System.out.println("Thank you for using the Event Registration system.");
							break MENULOOP;
						} else {
							System.out.println("Invalid input.");
							break CHOICELOOP;
						}
					}
				}
				break;
			case 2:			
				eventRegisterService.displayAllEmployees();
				System.out.println("Do you wish to continue? Y/N");
				char userContinueInd = userInput.next().charAt(0);

				if (userContinueInd == 'Y') {
					break CHOICELOOP;
				} else if (userContinueInd == 'N') {
					System.out.println("Thank you for using the Event Registration system.");
					break MENULOOP;
				} else {
					System.out.println("Invalid input. /n");
					break CHOICELOOP;
				}
				
			case 3:
				userInput.close();
				System.out.println("Thank you for using the Event Registration system.");
				break MENULOOP;
				
			default:
				System.out.println("Invalid Choice. /n");
				break CHOICELOOP;
			}
		}	while(choice != 3);
	}
	
}
