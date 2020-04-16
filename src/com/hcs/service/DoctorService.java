package com.hcs.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import com.hcs.controller.DoctorController;
import com.hcs.model.Doctor;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doctor")
public class DoctorService {

	DoctorController doctorController = new DoctorController();

	@GET
	@Path("/read")
	@Produces({ MediaType.TEXT_PLAIN })
	public String readItems() {
		return new Gson().toJson(doctorController.readDoctors());
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("DoctorName") String DoctorName, @FormParam("Email") String Email,
			@FormParam("PhoneNumber") String PhoneNumber, @FormParam("DoctorType") String DoctorType,
			@FormParam("WorkHospital") String WorkHospital) throws ParseException {

		Doctor doctor = new Doctor();
		doctor.setDoctorName(DoctorName);
		doctor.setEmail(Email);
		doctor.setPhoneNumber(Integer.parseInt(PhoneNumber));
		doctor.setDoctorType(DoctorType);
		doctor.setWorkHospital(WorkHospital);

		return doctorController.AddDoctor(doctor);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(@PathParam("id")String DoctorId) {

		return doctorController.deleteDoctor(DoctorId);
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(@FormParam("DoctorId") String DoctorID,@FormParam("DoctorName") String DoctorName, @FormParam("Email") String Email,
			@FormParam("PhoneNumber") String PhoneNumber, @FormParam("DoctorType") String DoctorType,
			@FormParam("WorkHospital") String WorkHospital) throws ParseException {

		Doctor doctor = new Doctor();
		doctor.setDoctorID(Integer.parseInt(DoctorID));
		doctor.setDoctorName(DoctorName);
		doctor.setEmail(Email);
		doctor.setPhoneNumber(Integer.parseInt(PhoneNumber));
		doctor.setDoctorType(DoctorType);
		doctor.setWorkHospital(WorkHospital);

		return doctorController.updatedoctor(doctor);
	}
	
	

}
