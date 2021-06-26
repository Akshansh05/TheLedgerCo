package service;

import java.util.ArrayList;

import model.Bank;

public class BankService {
	private ArrayList<Bank> bankList;

	public BankService() {
		this.bankList = new ArrayList<Bank>();
	}

	public ArrayList<Bank> getBankList() {
		return bankList;
	}

	public Bank addBank(String bankName) {
		Bank b = new Bank(bankName);
		bankList.add(b);
		return b;
	}

	public Bank getBankByName(String bankName) {
		for (Bank b : bankList) {
			if (b.getName().equalsIgnoreCase(bankName)) {
				return b;
			}
		}
		return null;
	}

}
