package manager;

import service.BankService;
import service.InstallmentService;
import service.LoanService;
import service.PaymentService;
import service.UserService;

public class LoanManager {

	BankService bs;
	UserService us;
	LoanService ls;
	InstallmentService is;
	PaymentService ps;

	public LoanManager() {

		this.bs = new BankService();
		this.us = new UserService();
		this.ls = new LoanService();
		this.is = new InstallmentService();
		this.ps = new PaymentService();
	}

	public void createLoan(String bankName, String userName, int amount, int tenure, float roi) {

		ls.createLoan(us, bs, is, userName, bankName, amount, tenure, roi);
	}

	public void createPayment(String bankName, String userName, int lumpSumAmount, int emiNumber) {

		ps.createPayment(us, bs, is, ls, userName, bankName, lumpSumAmount, emiNumber);
	}

	public void showBalance(String bankName, String userName, int emiNumber) {

		ls.showBalance(us, bs, is, ps, userName, bankName, emiNumber);

	}

}
