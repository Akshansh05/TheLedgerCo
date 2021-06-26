package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.Loan;
import model.LoanInstallments;

public class InstallmentService {

	private ArrayList<LoanInstallments> installmentList;

	public InstallmentService() {

		this.installmentList = new ArrayList<LoanInstallments>();
	}

	public ArrayList<LoanInstallments> getInstallmentList() {
		return installmentList;
	}

	public void createInstallments(Loan loan) {

		if (loan == null) {
			return;
		}

		int tenure = loan.getTenure() * 12;
		
		int emiAmount = (int)Math.ceil((double)loan.getAmount() / tenure);
		
		for (int i = 1; i <= tenure; i++) {
			LoanInstallments li = new LoanInstallments(loan, i, emiAmount);
			li.setInstallmentPaid(0);
			li.setTotalinstallmentPaidSoFar(0);
			li.setisInstallmentPaid(false);
			li.setInstallmentCountLeft(tenure - i);
			//System.out.println(li.getInstallmentAmount()+" "+li.getInstallmentCountLeft()+" "+li.getInstallmentPaid());
			installmentList.add(li);
		}
		loan.setInstallmentsCount(tenure);

	}

	public ArrayList<LoanInstallments> getLoanInstallmentsList(Loan loan) {

		ArrayList<LoanInstallments> l = new ArrayList<LoanInstallments>();

		if (loan == null) {
			return l;
		}

		for (LoanInstallments li : installmentList) {
			if (li.getLoan().getId().equals(loan.getId())) {
				l.add(li);
			}
		}
		Collections.sort(l, new SortByInstallmentNumber());

		return l;

	}

	class SortByInstallmentNumber implements Comparator<LoanInstallments> {
		public int compare(LoanInstallments a, LoanInstallments b) {
			return a.getInstallmentnumber() - b.getInstallmentnumber();
		}
	}

	public void removeLastInstallment(Loan loan) {

		if (loan == null) {
			return;
		}

		ArrayList<LoanInstallments> installmentListOfLoan = getLoanInstallmentsList(loan);

		installmentList.remove(installmentListOfLoan.get(installmentListOfLoan.size() - 1));

	}

	public void updateLastInstallmentAmount(Loan loan, int amount) {

		if (loan == null) {
			return;
		}

		ArrayList<LoanInstallments> installmentListOfLoan = getLoanInstallmentsList(loan);

		LoanInstallments li = installmentListOfLoan.get(installmentListOfLoan.size() - 1);

		li.setInstallmentAmount(amount);

	}

	public void updateLoanInstallments(Loan loan, int emiAmount, int emiNumber) {

		if (loan == null) {
			return;
		}

		int amountDue = loan.getAmountDue();

		int oldInstallmentCount = loan.getInstallmentsCount();

		int multiplier = amountDue / emiAmount;

		int installmentLeft = (int) Math.ceil((double)amountDue / emiAmount);

		int newInstallmentCount = emiNumber + installmentLeft;

		int diffInstallments = oldInstallmentCount - newInstallmentCount;

		while (diffInstallments-- > 0) {
			removeLastInstallment(loan);
		}

		int newLastEmiAmount = amountDue - (multiplier * emiAmount);

		updateLastInstallmentAmount(loan, newLastEmiAmount);
		loan.setInstallmentsCount(newInstallmentCount);

		updateLoanInstallmentsCountPostGivenEmiNumber(loan, emiNumber);

	}

	public void updateLoanInstallmentsCountPostGivenEmiNumber(Loan loan, int emiNumber) {

		if (loan == null) {
			return;
		}

		ArrayList<LoanInstallments> installmentListOfLoan = getLoanInstallmentsList(loan);

		if (emiNumber > installmentListOfLoan.size()) {
			return;
		}

		for (int i = emiNumber; i <= installmentListOfLoan.size(); i++) {
			
			LoanInstallments li;
			if(i == 0) {
				 li = installmentListOfLoan.get(i);

				
			}else {
				 li = installmentListOfLoan.get(i - 1);

			}
			li.setInstallmentCountLeft(installmentListOfLoan.size() - i);
		}

	}

}
