package com.hcs.dto;

import java.util.Date;

public class Doctor {
	
	private int DoctorID;
	private String DoctorName;
	private String Email;
	private int PhoneNumber;
	private String DoctorType;
	private String WorkHospital;
	
	
	public int getDoctorID() {
		return DoctorID;
	}
	public void setDoctorID(int doctorID) {
		DoctorID = doctorID;
	}
	public String getDoctorName() {
		return DoctorName;
	}
	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getDoctorType() {
		return DoctorType;
	}
	public void setDoctorType(String doctorType) {
		DoctorType = doctorType;
	}
	public String getWorkHospital() {
		return WorkHospital;
	}
	public void setWorkHospital(String workHospital) {
		WorkHospital = workHospital;
	}
	
	
	
	
}
