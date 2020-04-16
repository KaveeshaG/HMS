package com.hcs.model;


import java.sql.Date;

public class Bill {
	
	private int BillID;
	private String BillType;
	private Date BillDate;
	private Double BillAmount;
	
	public int getBillID() {
		return BillID;
	}
	public void setBillID(int billID) {
		BillID = billID;
	}
	public String getBillType() {
		return BillType;
	}
	public void setBillType(String billType) {
		BillType = billType;
	}
	public Date getBillDate() {
		return BillDate;
	}
	public void setBillDate(Date billDate) {
		BillDate = billDate;
	}
	public double getBillAmount() {
		return BillAmount;
	}
	public void setBillAmount(double billAmount) {
		BillAmount = billAmount;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}