package com.hcs.model;

public class Hospital {
	private int hid;
	private String HospitalName;
	private String HospitalAddress;
	private int HospitalContact;
	private String HospitalEmail;
	

	public Hospital(int hid, String hospitalName, String hospitalAddress, int hospitalContact, String hospitalEmail) {
		super();
		this.hid = hid;
		HospitalName = hospitalName;
		HospitalAddress = hospitalAddress;
		HospitalContact = hospitalContact;
		HospitalEmail = hospitalEmail;
	}
	public Hospital() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getHospitalName() {
		return HospitalName;
	}
	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}
	public String getHospitalAddress() {
		return HospitalAddress;
	}
	public void setHospitalAddress(String hospitalAddress) {
		HospitalAddress = hospitalAddress;
	}
	public int getHospitalContact() {
		return HospitalContact;
	}
	public void setHospitalContact(int hospitalContact) {
		HospitalContact = hospitalContact;
	}
	public String getHospitalEmail() {
		return HospitalEmail;
	}
	public void setHospitalEmail(String hospitalEmail) {
		HospitalEmail = hospitalEmail;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospital other = (Hospital) obj;
		if (hid != other.hid)
			return false;
		return true;
	}
	
	

	
}
