package com.revature.entity;

public class MoneyTransfer {
	private int customer_id;
	private String customer_name;
	private long customer_ActNum;
	private int Act_balance;
	
	public MoneyTransfer(int customer_id, String customer_name, long customer_ActNum, int act_balance) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_ActNum = customer_ActNum;
		Act_balance = act_balance;
	}

	public MoneyTransfer() {
		
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public long getCustomer_ActNum() {
		return customer_ActNum;
	}

	public void setCustomer_ActNum(long customer_ActNum) {
		this.customer_ActNum = customer_ActNum;
	}

	public int getAct_balance() {
		return Act_balance;
	}

	public void setAct_balance(int act_balance) {
		Act_balance = act_balance;
	}

	@Override
	public String toString() {
		return "MoneyTransfer [customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_ActNum="
				+ customer_ActNum + ", Act_balance=" + Act_balance + "]";
	}

	
	
}


 
