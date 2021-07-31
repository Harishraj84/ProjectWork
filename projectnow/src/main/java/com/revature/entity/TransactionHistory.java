package com.revature.entity;

public class TransactionHistory {
	
	private int id;
	private long fromActNum;
	private long toActNum;
	private String date ;
	private String time;
	
	
	public TransactionHistory(int id, long fromActNum, long toActNum, String date, String time) {
		super();
		this.id = id;
		this.fromActNum = fromActNum;
		this.toActNum = toActNum;
		this.date = date;
		this.time = time;
	}

	

	public TransactionHistory() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public long getFromActNum() {
		return fromActNum;
	}


	public void setFromActNum(long fromActNum) {
		this.fromActNum = fromActNum;
	}


	public long getToActNum() {
		return toActNum;
	}


	public void setToActNum(long toActNum) {
		this.toActNum = toActNum;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "TransactionHistory [id=" + id + ", fromActNum=" + fromActNum + ", toActNum=" + toActNum + ", date="
				+ date + ", time=" + time + "]";
	}
	

	
	
	
}


