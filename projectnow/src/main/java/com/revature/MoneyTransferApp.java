package com.revature;

import java.time.LocalDate;
//import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

import com.google.protobuf.Value.KindCase;
import com.revature.repository.JdbcMoneyTransferRepository;
import com.revature.repository.JdbcMoneyTransferRepository;
import com.revature.repository.MoneyTransferRepository;

public class MoneyTransferApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		MoneyTransferRepository moneyTransferRepository = new JdbcMoneyTransferRepository();

		System.out.println("-------------------------------------Money Transfer App---------------------------------");

		System.out.println("------Menu---------");
		System.out.println("1.Transfer & Update");
		System.out.println("2.Transaction by account");
		System.out.println("3.Transaction by dates");
		System.out.println("4.Top 10 Transactions");
		System.out.println("5.Transactions by months");
		System.out.println("6.Last 3 months Transactions");
		
		
		System.out.println("");
		System.out.println("Enter the option:");
		int value=sc.nextInt();
		switch(value) {
		
		
		case 1:
		System.out.println("From Account Number");

		long FNum = sc.nextLong();

		System.out.println("To Account Number");

		long TNum = sc.nextLong();

		System.out.println("Amount To Transfer:");

		int amount = sc.nextInt();

		moneyTransferRepository.getFdetails(FNum);

		moneyTransferRepository.getTdetails(TNum);

		moneyTransferRepository.send(amount);

		moneyTransferRepository.Updatedetails();

		moneyTransferRepository.saveTranhistory(amount);

		break;
		case 2:
		System.out.println("Enter Acc no to get transaction details");
		long GETACCNO = sc.nextLong();
		moneyTransferRepository.getAccounts(GETACCNO).forEach(gact -> System.out.println(gact));
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------");
		break;
		
		case 3:
		System.out.println("Enter between dates in YYYY-MM-DD");
//		String fdate =  "2021-07-11";  
		System.out.println("Enter Starting Date");
		String fdate = sc.next();
		System.out.println("Enter Ending Date");
		String tdate = sc.next();
//		String tdate  = "2021-07-13";  
		System.out.println("Enter Acc no to get transaction details by date wise");
		long ACCNO = sc.nextLong();
		moneyTransferRepository.getBetDatesAccounts(ACCNO, fdate, tdate).forEach(bdt -> System.out.println(bdt));
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------");
		break;
		
		case 4:
		System.out.println("Enter Acc no to get top 10 transactions");
		long TOPACC = sc.nextLong();
		moneyTransferRepository.getTop10Accounts(TOPACC).forEach(t10 -> System.out.println(t10));
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------");
		break;
		
		case 5:
		System.out.println("Enter month number");
		int month = sc.nextInt();
		System.out.println("Enter Acc number");
		long ACC = sc.nextLong();
		moneyTransferRepository.getMonthAccounts(ACC, month).forEach(bm -> System.out.println(bm));
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------");
		break;
		
		case 6:
		System.out.println("Enter Acc number to get 3 months info");
		long ACCO = sc.nextLong();
//		System.out.println("Enter month1 number");
//		int month1 = sc.nextInt();
//		System.out.println("Enter month2 number");
//		int month2 = sc.nextInt();
//		System.out.println("Enter month3 number");
//		int month3 = sc.nextInt();
		moneyTransferRepository.getThreeMonthAccounts(ACCO /*, month1, month2, month3*/)
				.forEach(tma -> System.out.println(tma));

		break;
		default: System.out.println("Error!!! You have choosen wrong option");
		}
		sc.close();
		
	}

}
