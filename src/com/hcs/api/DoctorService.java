package com.hcs.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.hcs.dto.Doctor;
import com.owlike.genson.ext.jaxrs.GensonJsonConverter;




//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



@Path("/Doctor")
public class DoctorService {

	public static final String HOSPITAL_URI="http://localhost:8081/DoctorService/DoctorService/Doctor";

	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_PLAIN)
	public String readDoctor() throws URISyntaxException {
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
	public String insertDoctor(@FormParam("DoctorName") String DoctorName, @FormParam("Email") String Email,
			@FormParam("PhoneNumber") String PhoneNumber, @FormParam("DoctorType") String DoctorType,
			@FormParam("WorkHospital") String WorkHospital) throws ParseException {
		
		Form form =new Form();
		form.param("DoctorName", DoctorName);
		form.param("Email", Email);
		form.param("PhoneNumber", PhoneNumber);
		form.param("WorkHospital", WorkHospital);
		
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
	public String deleteDoctor(@PathParam("id")String DoctorId) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HOSPITAL_URI);
		String response = target.path(DoctorId)
								.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .delete(String.class);

		return response;
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(@FormParam("DoctorId") String DoctorID,@FormParam("DoctorName") String DoctorName, @FormParam("Email") String Email,
			@FormParam("PhoneNumber") String PhoneNumber, @FormParam("DoctorType") String DoctorType,
			@FormParam("WorkHospital") String WorkHospital) throws ParseException {

		Form form =new Form();
		form.param("DoctorId", DoctorID);
		form.param("DoctorName", DoctorName);
		form.param("Email", Email);
		form.param("PhoneNumber", PhoneNumber);
		form.param("WorkHospital", WorkHospital);
		
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
	public String searchDoctor(@PathParam("id")String DoctorId) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HOSPITAL_URI+"/search");
		String response = target.path(DoctorId)
								.request(MediaType.APPLICATION_JSON)
		                        .get(String.class);

		return response;
	}
	
	

}
