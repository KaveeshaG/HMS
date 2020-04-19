package com.hcs.api;



import java.net.URI;
	import java.net.URISyntaxException;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.List;


	//For REST Service
	import javax.ws.rs.*;
	import javax.ws.rs.client.Client;
	import javax.ws.rs.client.ClientBuilder;
	import javax.ws.rs.client.Entity;
	import javax.ws.rs.client.WebTarget;
	import javax.ws.rs.core.Form;
	import javax.ws.rs.core.MediaType;
	//For JSON
	import com.google.gson.*;
	import com.hcs.dto.Appointment;
	
	//For XML
	import org.jsoup.*;
	import org.jsoup.parser.*;
	import org.jsoup.nodes.Document;




@Path("/Appointment")
public class AppointmentService {

		public static final String APPOINMENT_URI="http://localhost:8089/HelthCareSystem/AppointmentsService/Appointment";

		@GET
		@Path("/read")
		@Produces(MediaType.TEXT_PLAIN)
		public String readAppointment() throws URISyntaxException {
			URI uri=new URI(APPOINMENT_URI+"/read/");
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(uri);
			// you can map it to a pojo, no need to have a string or map
			String jsonString = target.request(MediaType.TEXT_PLAIN).get(String.class);
			return jsonString;
		}

		@POST
		@Path("/insert")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertAppointment(@FormParam("PatientName") String PatientName, @FormParam("PatientEmail") String PatientEmail,
				@FormParam("DoctorName") String DoctorName, @FormParam("Reason") String Reason)
						throws ParseException {
			
			Form form =new Form();
			form.param("PatientName", PatientName);
			form.param("PatientEmail", PatientEmail);
			form.param("DoctorName", DoctorName);
			form.param("Reason", Reason);
			
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(APPOINMENT_URI+"/insert/");
			String response = target.request(MediaType.APPLICATION_JSON)
			                        .accept(MediaType.TEXT_PLAIN_TYPE)
			                        .post(Entity.form(form), String.class);

			return response;
		}

		@DELETE
		@Path("/{id}")
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteAppointment(@PathParam("id")String AppointmentId) {

			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(APPOINMENT_URI);
			String response = target.path(AppointmentId)
									.request(MediaType.APPLICATION_JSON)
			                        .accept(MediaType.TEXT_PLAIN_TYPE)
			                        .delete(String.class);

			return response;
		}
		
		
		@PUT
		@Path("/update")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateAppointment(@FormParam("AppointmentId") String AppointmentID,@FormParam("PatientName") String PatientName, @FormParam("PatientEmail") String PatientEmail,
				@FormParam("DoctorName") String DoctorName, @FormParam("Reason") String Reason)
						throws ParseException {

			Form form =new Form();
			form.param("AppointmentID", AppointmentID);
			form.param("PatientName", PatientName);
			form.param("PatientEmail", PatientEmail);
			form.param("DoctorName", DoctorName);
			form.param("Reason", Reason);
			
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(APPOINMENT_URI+"/update/");
			String response = target.request(MediaType.APPLICATION_JSON)
			                        .accept(MediaType.TEXT_PLAIN_TYPE)
			                        .put(Entity.form(form), String.class);

			return response;
		}
		
		
		
		

	}

