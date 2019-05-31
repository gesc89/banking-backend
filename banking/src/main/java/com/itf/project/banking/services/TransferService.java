package com.itf.project.banking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itf.project.banking.models.Account;
import com.itf.project.banking.models.Revenue;
import com.itf.project.banking.repositories.AccountRepository;

@Service
public class TransferService implements TransferServiceImpl {

	@Autowired
	AccountRepository accountRepo;

	@Override
	public void transferMoney(List<Revenue> revenues) {

		for (Revenue revenue : revenues) {

			if (revenue.getAccountingStatus().equals("S")) {

				revenue.setValue(revenue.getValue() * -1);

				Account transfererAccount = accountRepo.findById(revenue.getTransfererAccountId()).get();

				transfererAccount.setBalance(transfererAccount.getBalance() + revenue.getValue());

				transfererAccount.getRevenues().add(revenue);

				accountRepo.save(transfererAccount);

			} else if (revenue.getAccountingStatus().equals("H")) {

				Account receiverAccount = accountRepo.findById(revenue.getReceiverAccountId()).get();

				receiverAccount.setBalance(receiverAccount.getBalance() + revenue.getValue());

				receiverAccount.getRevenues().add(revenue);

				accountRepo.save(receiverAccount);

			}

		}

	}

	@Override
	public void transferMoneytoDifferentBank(List<Revenue> revenues) {

		for (Revenue revenue : revenues) {

			if (revenue.getAccountingStatus().equals("S")) {

				revenue.setValue(revenue.getValue() * -1);

				Account transfererAccount = accountRepo.findById(revenue.getTransfererAccountId()).get();

				transfererAccount.setBalance(transfererAccount.getBalance() + revenue.getValue());

				transfererAccount.getRevenues().add(revenue);

				accountRepo.save(transfererAccount);

			}
		}

	}

}
