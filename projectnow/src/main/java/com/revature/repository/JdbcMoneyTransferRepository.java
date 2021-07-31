package com.revature.repository;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.db.ConnectionFactory;
import com.revature.entity.MoneyTransfer;
import com.revature.entity.TransactionHistory;

public class JdbcMoneyTransferRepository implements MoneyTransferRepository {
	public static Logger  logger = Logger.getLogger("MoneyTransferApp");
	MoneyTransfer mt1;
	MoneyTransfer mt2;
	@Override
	public void getFdetails(long Act_Num) {
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from Accounts where customer_ActNum = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, Act_Num);
			ResultSet rs =  ps.executeQuery();
			rs.next();
			 mt1 = new MoneyTransfer(rs.getInt(1) , rs.getString(2), rs.getLong(3) , rs.getInt(4));
			System.out.println(mt1.toString());	
			 logger.info("From account details...");
			 System.out.println();
	  } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void getTdetails(long Act_Num) {
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from Accounts where customer_ActNum = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, Act_Num);
			ResultSet rs =  ps.executeQuery();
			rs.next();
			
		    mt2 = new MoneyTransfer(rs.getInt(1) , rs.getString(2), rs.getLong(3) , rs.getInt(4));
			System.out.println(mt2.toString());	
		    logger.info("To account details...");
		    System.out.println();
	  }catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} }
	@Override
	public void send(int amount)  {
		if ( (amount >= 1) && (amount <= mt1.getAct_balance() ) )
		{
			int n = mt1.getAct_balance();
			int m = mt2.getAct_balance();
			mt1.setAct_balance(mt1.getAct_balance() - amount);
			logger.info("transaction intiated...");
			System.out.println(amount + " transfered");
			System.out.println( "Before withdraw"+" -- "+ n + " -> " + "After withdraw" + " -- " + mt1.getAct_balance() + " Debited" );
			
			mt2.setAct_balance(mt2.getAct_balance() + amount);
			System.out.println( "Before depoit"+" -- " + m + " -> " + "After deposit" + " -- " + mt2.getAct_balance() + " Credited");
			
			logger.info("tansferred success...");
			System.out.println();
		} else {
			logger.info("tansferred unsuccessful.....");
			logger.error("Amount range error");
			throw new AmountRangeException(amount);
			
		} }
	@Override
	public void Updatedetails() {
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "update Accounts set Act_balance =? where customer_ActNum  = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, mt1.getAct_balance());
			ps.setDouble(2, mt1.getCustomer_ActNum());
			ps.executeUpdate();	
			logger.info("Updated Withdraw...");
			 System.out.println();
			 
			 String sql1 = "update Accounts set Act_balance =? where customer_ActNum = ?";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setInt(1, mt2.getAct_balance());
				ps1.setDouble(2, mt2.getCustomer_ActNum());
				ps1.executeUpdate();	
				logger.info("Updated diposit...");
				System.out.println();
	  } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} }	
	@Override
	public void saveTranhistory(int amount) {
		try (Connection con = ConnectionFactory.getConnection()) {
			String sql = "insert into TransactionHistory (fromactnum,toActnum,date,time,Amt_transfered) values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, mt1.getCustomer_ActNum());
			ps.setLong(2, mt2.getCustomer_ActNum());
			ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
			ps.setTimestamp(4, java.sql.Timestamp.from(java.time.Instant.now()));
			ps.setDouble(5, amount);

			int Count = ps.executeUpdate();
			if (Count == 1) {
				logger.info("transaction history saved");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<TransactionHistory>getAccounts(long GETACCNO) {
		Connection con = null;
		List<TransactionHistory> acts = new ArrayList<TransactionHistory>();
		try {
			con = ConnectionFactory.getConnection();
	
			String sql = "select * from TransactionHistory where fromactnum = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, GETACCNO);
	
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TransactionHistory th = new TransactionHistory();
				th.setId(rs.getInt(1));
				th.setFromActNum(rs.getLong(2));
				th.setToActNum(rs.getLong(3));
				th.setDate(rs.getString(4));
				th.setTime(rs.getString(5));
				acts.add(th);
			}
			logger.info("Getting trans history based on acc no");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return acts;
	}
	public List<TransactionHistory>getBetDatesAccounts(long ACCNO, String fdate , String tdate) {
		Connection con = null;
		List<TransactionHistory> dates = new ArrayList<TransactionHistory>();
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from TransactionHistory where fromactnum = ? and date between '"+fdate+"' and '"+tdate+"' order by date";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1,ACCNO);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TransactionHistory th = new TransactionHistory();
				th.setId(rs.getInt(1));
				th.setFromActNum(rs.getLong(2));
				th.setToActNum(rs.getLong(3));
				th.setDate(rs.getString(4));
				th.setTime(rs.getString(5));
				dates.add(th);
			}
			logger.info("Got Accounts Between Dates...");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} return dates;
	}
public List<TransactionHistory>getTop10Accounts(long TOPACC) {
		Connection con = null;
		List<TransactionHistory> dates = new ArrayList<TransactionHistory>();
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from TransactionHistory where fromactnum = ? order by id asc limit 10 ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1,TOPACC);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TransactionHistory th = new TransactionHistory();
				th.setId(rs.getInt(1));
				th.setFromActNum(rs.getLong(2));
				th.setToActNum(rs.getLong(3));
				th.setDate(rs.getString(4));
				th.setTime(rs.getString(5));
				dates.add(th);
			}
			logger.info(" top 10 queries retrived");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dates;
	}
public List<TransactionHistory>getMonthAccounts(long ACC ,  int val) {
	Connection con = null;
	List<TransactionHistory> months = new ArrayList<TransactionHistory>();
	try {
		con = ConnectionFactory.getConnection();
		String sql = "select * from TransactionHistory where fromactnum = ? and month(date) ='"+val+"' ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setLong(1,ACC);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			TransactionHistory th = new TransactionHistory();
			th.setId(rs.getInt(1));
			th.setFromActNum(rs.getLong(2));
			th.setToActNum(rs.getLong(3));
			th.setDate(rs.getString(4));
			th.setTime(rs.getString(5));
			months.add(th);
		}
		logger.info(" Got month account ");
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return months;
}
@Override
public int getABalance(long act_Num) {
	Connection con = null;
	try {
		con = ConnectionFactory.getConnection();
	
		String sql = "select Act_balance from Accounts where customer_ActNum = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setLong(1, act_Num);
		ResultSet rs =  ps.executeQuery();
		rs.next();	 
		int value = rs.getInt(1);	
		return value;
  }
	catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} return 0;
}	

public List<TransactionHistory> getThreeMonthAccounts(long aCCO /*, int month1, int month2, int month3*/){
	Connection con = null;
	List<TransactionHistory> months = new ArrayList<TransactionHistory>();
	try {
		con = ConnectionFactory.getConnection();
//		String sql = "select * from TransactionHistory where fromactnum = ? and (month(date) ='"+month1+"' or month(date)='"+month2+"' or month(date)='"+month3+"'); ";
		String sql = "select * from TransactionHistory where fromactnum = ? and month(date)<=now()-3;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setLong(1,aCCO);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			TransactionHistory th = new TransactionHistory();
			th.setId(rs.getInt(1));
			th.setFromActNum(rs.getLong(2));
			th.setToActNum(rs.getLong(3));
			th.setDate(rs.getString(4));
			th.setTime(rs.getString(5));
			months.add(th);
		}
		logger.info(" Got 3 month account ");
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return months;
}

}

