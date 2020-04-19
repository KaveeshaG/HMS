package com.hcs.api;

import java.net.URI;
import java.net.URISyntaxException;
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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.hcs.api.PatientService;

@Path("/patients")
public class PatientService {

	public static final String PATIENT_URI="http://localhost:8080/PatientService/PatientService/patients";

	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_PLAIN)
	public String readPatient() throws URISyntaxException {
		URI uri=new URI(PATIENT_URI+"/read/");
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
	public String insertPatient(@FormParam("PatientName") String PatientName,@FormParam("Age") String Age,@FormParam("PhoneNo") String PhoneNo,
			@FormParam("Email") String Email, @FormParam("Address") String Address) throws ParseException {
		
		Form form =new Form();
		form.param("PatientName", PatientName);
		form.param("Age", Age);
		form.param("PhoneNo", PhoneNo);
		form.param("Email", Email);
		form.param("Address", Address);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PATIENT_URI+"/insert/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .post(Entity.form(form), String.class);

		return response;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(@PathParam("id")String PatientId) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PATIENT_URI);
		String response = target.path(PatientId)
								.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .delete(String.class);

		return response;
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(@FormParam("PatientId") String PatientId,@FormParam("PatientName") String PatientName,@FormParam("Age") String Age,@FormParam("PhoneNo") String PhoneNo,
			@FormParam("Email") String Email, @FormParam("Address") String Address) throws ParseException {

		Form form =new Form();
		form.param("DoctorId", PatientId);
		form.param("DoctorName", PatientName);
		form.param("Age", Age);
		form.param("PhoneNo", PhoneNo);
		form.param("Email", Email);
		form.param("Address", Address);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PATIENT_URI+"/update/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .put(Entity.form(form), String.class);

		return response;
	}
	
	@GET
	@Path("/search/{id}")
	@Produces({ MediaType.TEXT_PLAIN })
	public String searchDoctor(@PathParam("id")String PatientId) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PATIENT_URI+"/search");
		String response = target.path(PatientId)
								.request(MediaType.APPLICATION_JSON)
		                        .get(String.class);

		return response;
	}
	
	

}
