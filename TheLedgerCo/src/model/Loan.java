package model;

import java.util.UUID;

public class Loan {

	private String id;
	private User user;
	private Bank bank;
	private int principalAmount;
	private int tenure;
	private float roi;

	private int Amount;
	private int amountDue;
	private int amountPaid;
	private int installmentsCount;

	public Loan(Bank bank, User user, int principalAmount, int tenure, float roi) {
		this.id = UUID.randomUUID().toString();
		this.bank = bank;
		this.user = user;
		this.principalAmount = principalAmount;
		this.tenure = tenure;
		this.roi = roi;

		this.Amount = (int) Math.ceil(principalAmount + (double) (principalAmount * roi * tenure) / 100);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public float getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(int principalAmount) {
		this.principalAmount = principalAmount;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public float getRoi() {
		return roi;
	}

	public void setRoi(int roi) {
		this.roi = roi;
	}

	public String getId() {
		return id;
	}

	public int getAmount() {
		return Amount;
	}

	public int getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(int amountDue) {
		this.amountDue = amountDue;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getInstallmentsCount() {
		return installmentsCount;
	}

	public void setInstallmentsCount(int installmentsCount) {
		this.installmentsCount = installmentsCount;
	}

}
