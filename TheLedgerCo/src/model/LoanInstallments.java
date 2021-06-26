package model;

import java.util.UUID;

public class LoanInstallments {

	private String id;
	private Loan loan;
	private int installmentnumber;
	private int installmentAmount;
	private int installmentPaid;
	private int totalinstallmentPaidSoFar;
	private int installmentCountLeft;
	private boolean isInstallmentPaid;

	public LoanInstallments(Loan loan, int installmentnumber, int installmentAmount) {
		this.id = UUID.randomUUID().toString();
		this.loan = loan;
		this.installmentnumber = installmentnumber;
		this.installmentAmount = installmentAmount;
	}

	public String getId() {
		return id;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public int getInstallmentnumber() {
		return installmentnumber;
	}

	public void setInstallmentnumber(int installmentnumber) {
		this.installmentnumber = installmentnumber;
	}

	public int getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(int installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public int getInstallmentPaid() {
		return installmentPaid;
	}

	public void setInstallmentPaid(int installmentPaid) {
		this.installmentPaid = installmentPaid;
	}

	public int getTotalinstallmentPaidSoFar() {
		return totalinstallmentPaidSoFar;
	}

	public void setTotalinstallmentPaidSoFar(int totalinstallmentPaidSoFar) {
		this.totalinstallmentPaidSoFar = totalinstallmentPaidSoFar;
	}

	public boolean isInstallmentPaid() {
		return isInstallmentPaid;
	}

	public void setisInstallmentPaid(boolean isInstallmentPaid) {
		this.isInstallmentPaid = isInstallmentPaid;
	}

	public int getInstallmentCountLeft() {
		return installmentCountLeft;
	}

	public void setInstallmentCountLeft(int installmentCountLeft) {
		this.installmentCountLeft = installmentCountLeft;
	}

}
