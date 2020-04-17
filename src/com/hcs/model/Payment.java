package com.hcs.model;


import java.sql.Date;

public class Payment {
	
	private int PaymentID;
	private String BillID;
	private Date PaymentDate;
	private Double PaymentAmount;
	private String PaymentType;
	private String paymentDescription;
	
	
	public int getPaymentID() {
		return PaymentID;
	}
	
	public void setPaymentID(int paymentID) {
		PaymentID = paymentID;
	}
	public String getBillID() {
		return BillID;
	}
	public void setBillID(String billID) {
		BillID = billID;
	}
	public Date getPaymentDate() {
		return PaymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		PaymentDate = paymentDate;
	}
	public Double getPaymentAmount() {
		return PaymentAmount;
	}
	public void setPaymentAmount(Double paymentAmount) {
		PaymentAmount = paymentAmount;
	}
	public String getPaymentType() {
		return PaymentType;
	}
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	public String getPaymentDescription() {
		return paymentDescription;
	}
	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}
	
	
	
	
	
	
	
}
