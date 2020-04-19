package com.hcs.api;


import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//For JSON
import com.google.gson.*;


import com.hcs.dto.Schedule;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



@Path("/Schedule")
public class ScheduleService {

	public static final String HOSPITAL_URI="http://localhost:8081/ScheduleService/ScheduleService/Schedule";

	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_PLAIN)
	public String readSchedule() throws URISyntaxException {
		URI uri=new URI(HOSPITAL_URI+"/read/");
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
	public String insertSchedule(@FormParam("hid") String hid, @FormParam("DoctorID") String DoctorID,
			@FormParam("ScheduleDate") String ScheduleDate, @FormParam("ScheduleTime") String ScheduleTime
			) throws ParseException {
		
		Form form =new Form();
		form.param("hid", hid);
		form.param("DoctorID", DoctorID);
		form.param("ScheduleDate", ScheduleDate);
		form.param("ScheduleTime", ScheduleTime);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HOSPITAL_URI+"/insert/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .post(Entity.form(form), String.class);

		return response;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(@PathParam("id")String ScheduleId) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HOSPITAL_URI);
		String response = target.path(ScheduleId)
								.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .delete(String.class);

		return response;
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateSchedule(@FormParam("SchduleID") String SchduleID,@FormParam("hid") String hid, @FormParam("DoctorID") String DoctorID,
			@FormParam("ScheduleDate") String ScheduleDate, @FormParam("ScheduleTime") String ScheduleTime) throws ParseException {

		Form form =new Form();
		form.param("SchduleID", SchduleID);
		form.param("hid", hid);
		form.param("DoctorID", DoctorID);
		form.param("ScheduleDate", ScheduleDate);
		form.param("ScheduleTime", ScheduleTime);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HOSPITAL_URI+"/update/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .put(Entity.form(form), String.class);

		return response;
	}
	
	@GET
	@Path("/search/{id}")
	@Produces({ MediaType.TEXT_PLAIN })
	public String searchSchedule(@PathParam("id")String DoctorId) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HOSPITAL_URI+"/search");
		String response = target.path(DoctorId)
								.request(MediaType.APPLICATION_JSON)
		                        .get(String.class);

		return response;
	}
	
	

}
