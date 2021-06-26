package service;

import java.util.ArrayList;

import model.Loan;
import model.LoanInstallments;
import model.Payment;

public class PaymentService {

	private ArrayList<Payment> paymentList;

	public PaymentService() {
		this.paymentList = new ArrayList<Payment>();

	}

	public ArrayList<Payment> getPaymentList() {
		return paymentList;
	}

	public Payment createPayment(UserService us, BankService bs, InstallmentService is, LoanService ls, String userName,
			String bankName, int lumpSumAmount, int emiNumber) {

		Loan loan = ls.getLoanByUserNameBankName(us, bs, userName, bankName);

		if (loan == null) {
			return null;
		}
		

		ArrayList<LoanInstallments> loanInstallments = is.getLoanInstallmentsList(loan);

		int emiAmount = loanInstallments.get(0).getInstallmentAmount();

		int totalPaymentMade = 0;
		ArrayList<LoanInstallments> loanInstallmentsPaid = new ArrayList<LoanInstallments>();

		for (int i = 1; i <= emiNumber && i <= loanInstallments.size(); i++) {
			LoanInstallments li = loanInstallments.get(i - 1);

			if (!li.isInstallmentPaid()) {

				li.setInstallmentPaid(li.getInstallmentPaid() + li.getInstallmentAmount());

				loan.setAmountPaid(loan.getAmountPaid() + li.getInstallmentAmount());

				loan.setAmountDue(loan.getAmountDue() - li.getInstallmentAmount());

				li.setTotalinstallmentPaidSoFar(loan.getAmountPaid());

				totalPaymentMade += li.getInstallmentAmount();

				li.setisInstallmentPaid(true);
			}

		}
		
		if(lumpSumAmount >0) {
			// calc for lumpsumAmount
			
			LoanInstallments li;
			if(emiNumber == 0) {
				 li = loanInstallments.get(0);
			}else {
				 li = loanInstallments.get(emiNumber - 1);

			}

			li.setInstallmentPaid(li.getInstallmentPaid() + lumpSumAmount);

			loan.setAmountPaid(loan.getAmountPaid() + lumpSumAmount);
			loan.setAmountDue(loan.getAmountDue() - lumpSumAmount);
			li.setTotalinstallmentPaidSoFar(loan.getAmountPaid());
			totalPaymentMade += lumpSumAmount;

			// update installments post lumpsum
			is.updateLoanInstallments(loan, emiAmount, emiNumber);
			
		}
		

		for (int i = 1; i <= emiNumber && i <= loanInstallments.size(); i++) {
			LoanInstallments lis = loanInstallments.get(i - 1);
			loanInstallmentsPaid.add(lis);

		}
		
			Payment payment = new Payment(loan, totalPaymentMade, loanInstallmentsPaid);
			paymentList.add(payment);
			return payment;

	}
	 

}
