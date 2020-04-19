package com.hcs.dto;

public class Appointment {
	private int AppointmentID;
	private String PatientName;
	private String PatientEmail;
	private String DoctorName;
	private String Reason;
	public int getAppointmentID() {
		return AppointmentID;
	}
	public void setAppointmentID(int appointmentID) {
		AppointmentID = appointmentID;
	}
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	public String getPatientEmail() {
		return PatientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		PatientEmail = patientEmail;
	}
	public String getDoctorName() {
		return DoctorName;
	}
	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	
	
	
	
}
