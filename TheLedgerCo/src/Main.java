import java.util.Scanner;

import driver.InputParser;

public class Main {

	public static void main(String[] args) {

		InputParser inputParser = new InputParser();

		if (args.length == 1) {
			inputParser.parseFileInput(args[0]);
		} else {
			Scanner sc = new Scanner(System.in);

			System.out.println("Please Enter the full File Path. Example ->/home/akshanshohm/eclipse-workspace/TheLedgerCo/src/test.txt");

			inputParser.parseFileInput(sc.nextLine());
		}

	}

}
