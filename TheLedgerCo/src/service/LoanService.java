package service;

import java.util.ArrayList;

import model.Bank;
import model.Loan;
import model.LoanInstallments;
import model.User;

public class LoanService {

	private ArrayList<Loan> loanList;

	public LoanService() {
		this.loanList = new ArrayList<Loan>();
	}

	public ArrayList<Loan> getLoanList() {
		return loanList;
	}

	public Loan createLoan(UserService us, BankService bs, InstallmentService is, String userName, String bankName,
			int principalAmount, int tenure, float roi) {

		User u = us.getUserByName(userName);
		if (u == null) {
			 u= us.addUser(userName);
		}
		
		Bank b = bs.getBankByName(bankName);
		if (b == null) {
			b= bs.addBank(bankName);
		}

		Loan loan = new Loan(b, u, principalAmount, tenure, roi);

		loan.setAmountDue(loan.getAmount());
		loan.setAmountPaid(0);

		is.createInstallments(loan);

		loanList.add(loan);
		//System.out.println( " "+loan.getUser().getName()+ " "+loan.getBank().getName()+" " +loan.getAmount());

		return loan;
	}

	public Loan getLoanByUserNameBankName(UserService us, BankService bs, String userName, String bankName) {
		User u = us.getUserByName(userName);
		if (u == null) {
			return null;
		}

		Bank b = bs.getBankByName(bankName);
		if (b == null) {
			return null;
		}
		for (Loan loan : loanList) {

			if (loan.getUser().getName().equalsIgnoreCase(userName)
					&& loan.getBank().getName().equalsIgnoreCase(bankName)) {
				return loan;
			}
		}

		return null;
	}

	public void showBalance(UserService us, BankService bs, InstallmentService is, PaymentService ps, String userName,
			String bankName, int emiNumber) {

		Loan loan = this.getLoanByUserNameBankName(us, bs, userName, bankName);
		if(emiNumber == 0) {
			ArrayList<LoanInstallments> loanInstallments = is.getLoanInstallmentsList(loan);
			LoanInstallments li = loanInstallments.get(0);
				int val  = loan.getAmountPaid();
				if(val != li.getTotalinstallmentPaidSoFar()) {
					val =0;
				}
			System.out.println(bankName + " " + userName + " " +val +" "+loan.getInstallmentsCount());
			return;
		}

		ps.createPayment(us, bs, is, this, userName, bankName, 0, emiNumber);

		ArrayList<LoanInstallments> loanInstallments = is.getLoanInstallmentsList(loan);
		
		


		LoanInstallments li = loanInstallments.get(emiNumber - 1);

		System.out.println(bankName + " " + userName + " " + li.getTotalinstallmentPaidSoFar() + " " + li.getInstallmentCountLeft());

	}
}
