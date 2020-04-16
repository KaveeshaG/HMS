package com.hcs.model;

import java.sql.Date;
import java.sql.Time;


public class Schedule {
	

	private int SchduleID;
	private int hid;
	private int DoctorID;
	private Date ScheduleDate;
	private Time ScheduleTime;
	private Doctor doctor;
	
	
	public int getSchduleID() {
		return SchduleID;
	}
	public void setSchduleID(int schduleID) {
		SchduleID = schduleID;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getDoctorID() {
		return DoctorID;
	}
	public void setDoctorID(int doctorID) {
		DoctorID = doctorID;
	}
	public Date getScheduleDate() {
		return ScheduleDate;
	}
	public void setScheduleDate(Date scheduleDate) {
		ScheduleDate = scheduleDate;
	}
	public Time getScheduleTime() {
		return ScheduleTime;
	}
	public void setScheduleTime(Time scheduleTime) {
		ScheduleTime = scheduleTime;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
	

}
