package com.revature.repository;

import java.util.List;

import com.revature.entity.MoneyTransfer;
import com.revature.entity.TransactionHistory;

public interface MoneyTransferRepository {

	int getABalance( long act_Num);
	
	void send(int amount);
	
	void getFdetails(long FNum);
	
	void getTdetails(long TNum);
	
	void Updatedetails();
	
	void saveTranhistory(int amount);

	List<TransactionHistory> getAccounts(long GETACCNO);

	List<TransactionHistory>getBetDatesAccounts(long ACCNO, String fdate , String tdate);
	
	List<TransactionHistory> getTop10Accounts(long TOPACC);
	
	List<TransactionHistory>getMonthAccounts(long ACC , int val);

	List<TransactionHistory> getThreeMonthAccounts(long aCCO /*, int month1, int month2, int month3*/);
	

}
