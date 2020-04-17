package com.hcs.service;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.hcs.controller.AppointmentController;
import com.hcs.model.Appointment;

@Path("/Appointment")
public class AppointmentService {
	
	AppointmentController appointmentController = new AppointmentController();

	@GET
	@Path("/read")
	@Produces({ MediaType.TEXT_PLAIN })
	public String readItems() {
		return new Gson().toJson(appointmentController.readAppointments());
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("PatientName") String PatientName, @FormParam("PatientEmail") String PatientEmail,
			@FormParam("DoctorName") String DoctorName, @FormParam("Reason") String Reason)
			 throws ParseException {

		Appointment appointment = new Appointment();
		appointment.setPatientName(PatientName);
		appointment.setPatientEmail(PatientEmail);
		appointment.setDoctorName(DoctorName);
		appointment.setReason(Reason);

		return appointmentController.AddAppointment(appointment);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(@PathParam("id")String AppointmentID) {

		return appointmentController.deleteAppointment(AppointmentID);
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(@FormParam("AppointmentID") String AppointmentID,@FormParam("PatientName") String PatientName, @FormParam("PatientEmail") String PatientEmail,
			@FormParam("DoctorName") String DoctorName, @FormParam("Reason") String Reason)
			 throws ParseException {

		Appointment appointment = new Appointment();
		appointment.setAppointmentID(Integer.parseInt(AppointmentID));
		appointment.setPatientName(PatientName);
		appointment.setPatientEmail(PatientEmail);
		appointment.setDoctorName(DoctorName);
		appointment.setReason(Reason);

		return appointmentController.updateAppointment(appointment);
	}
	
}
