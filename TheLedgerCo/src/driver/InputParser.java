package driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import manager.LoanManager;

public class InputParser {

	private LoanManager loanManager;

	public InputParser() {
		this.loanManager = new LoanManager();
	}

	// Take Input From File
	public void parseFileInput(String filePath) {
		File inputFile = new File(filePath);
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line;
			try {
				while ((line = br.readLine()) != null) {
					parseTextInput(line.trim());
				}
			} catch (IOException ex) {
				System.out.println("Error in reading the input file.");
				ex.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found in the path specified.");
			e.printStackTrace();
		}
	}

	public void parseTextInput(String inputString){

        String inputs[]=inputString.split(" ");
        switch (inputs[0]) {
            case "LOAN":
                try {
                    loanManager.createLoan(inputs[1], inputs[2], Integer.parseInt(inputs[3]),Integer.parseInt(inputs[4]), Float.parseFloat(inputs[5]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
                case "PAYMENT":
                try {
                	loanManager.createPayment(inputs[1], inputs[2], Integer.parseInt(inputs[3]), Integer.parseInt(inputs[4]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
                case "BALANCE":
                try {
                	loanManager.showBalance(inputs[1], inputs[2], Integer.parseInt(inputs[3]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;      
        
            default:
            System.out.println("Invalid input.");
                break;
        }
    }

}
