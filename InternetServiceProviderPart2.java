import java.util.Scanner;
import java.text.DecimalFormat;

public class InternetServiceProviderPart2 {
	public static void main(String[] args)

	{

	String userInput;

	char packageChosen;

	double packageA,packageB,totalHours;

	Scanner keyboard = new Scanner(System.in);

	System.out.println("Enter the letter of the package purchased:");

	userInput = keyboard.nextLine();

	packageChosen = userInput.charAt(0);

	switch (packageChosen)

	{

	case 'A':

	System.out.println("Enter the number of hours that were used: ");

	totalHours = keyboard.nextDouble();

	if (totalHours <= 10)

	{

	packageA = 9.95;

	System.out.print("Your total charges are $" + packageA);

	System.out.print("\nYou would have saved $" + (13.95-9.95) + "if you had gotten package B\n"

	+ "and You would have saved $" + (19.95-9.95) + "if you had gotten package C");

	}

	else

	packageA = 9.95 + ((totalHours-10) * 2.00);

	System.out.print("Your total charges are $" + packageA);

	packageB = totalHours<20?13.95:13.95 + ((totalHours-20) * 1.00);

	if(packageA>packageB) {

	System.out.print("\nYou would have saved $" + (packageA-packageB) + "if you had gotten package B");

	}

	if(packageA>19.95) {

	System.out.print("\nYou would have saved $" + (packageA-19.95) + "if you had gotten package C");

	}

	break;

	case 'B':

	System.out.println("Enter the number of hours that were used: ");

	totalHours = keyboard.nextDouble();

	if (totalHours <= 20)

	{

	packageB = 13.95 ;
	

	System.out.print("Your total charges are $" + packageB);

	System.out.print("\nYou would have saved $" + (19.95-13.95) + "if you had gotten package C");

	}

	else

	{

	packageB = 13.95 + ((totalHours-20) * 1.00);

	System.out.print("Your total charges are $" + packageB);

	if(packageB>19.95) {

	System.out.print("\nYou would have saved $" + (packageB-19.95) + "if you had gotten package C");

	}

	}

	break;

	case 'C':
	System.out.println("Enter the number of hours that were used: ");

	totalHours = keyboard.nextDouble();

	System.out.print("Your total charges are $19.95");


	break;

	default:
	System.out.println("Enter the number of hours that were used: ");

	totalHours = keyboard.nextDouble();

	System.out.print("That package input was not an option.");

	}

	}

}
