package com.hcs.dto;

import java.sql.Date;

public class LabTest {
	private int LabTestID;
	private int PatientID;
	private String TestName;
	private String TestType;
	private String TestDescription;
	private Date LabDate;

	

	public int getLabTestID() {
		return LabTestID;
	}
	public void setLabTestID(int labTestID) {
		LabTestID = labTestID;
	}
	public int getPatientID() {
		return PatientID;
	}
	public void setPatientID(int patientID) {
		PatientID = patientID;
	}
	public String getTestName() {
		return TestName;
	}
	public void setTestName(String testName) {
		TestName = testName;
	}
	public String getTestType() {
		return TestType;
	}
	public void setTestType(String testType) {
		TestType = testType;
	}
	public String getTestDescription() {
		return TestDescription;
	}
	public void setTestDescription(String testDescription) {
		TestDescription = testDescription;
	}
	public Date getLabDate() {
		return LabDate;
	}
	public void setLabDate(Date labDate) {
		LabDate = labDate;
	}
	
	
	}
	
	
	

