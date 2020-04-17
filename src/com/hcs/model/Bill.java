package com.hcs.model;


import java.sql.Date;

public class Bill {
	
	private int BillID;
	private String BillType;
	private Date BillDate;
	private Double BillAmount;
	private String ReferenceID;
	private String ReferenceType;
	
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
	public String getReferenceID() {
		return ReferenceID;
	}
	public void setReferenceID(String referenceID) {
		ReferenceID = referenceID;
	}
	public String getReferenceType() {
		return ReferenceType;
	}
	public void setReferenceType(String referenceType) {
		ReferenceType = referenceType;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}