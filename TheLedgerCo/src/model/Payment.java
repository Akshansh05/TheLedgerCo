package model;

import java.util.ArrayList;
import java.util.UUID;

public class Payment {

	private String id;
	private Loan loan;
	private int amountPaid;
	private ArrayList<LoanInstallments> installment;

	public Payment(Loan loan, int amountPaid, ArrayList<LoanInstallments> installment) {
		this.id = UUID.randomUUID().toString();
		this.loan = loan;
		this.amountPaid = amountPaid;
		this.installment = installment;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public ArrayList<LoanInstallments> getInstallment() {
		return installment;
	}

	public void setInstallment(ArrayList<LoanInstallments> installment) {
		this.installment = installment;
	}

	public String getId() {
		return id;
	}

}
