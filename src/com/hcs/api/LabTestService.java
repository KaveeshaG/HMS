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
import com.hcs.api.LabTestService;
import com.owlike.genson.ext.jaxrs.GensonJsonConverter;




//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



@Path("/LabTest")
public class LabTestService {
	
	

	public static final String LABTEST_URI="http://localhost:8089/LabTestService/LabTestService/LabTest";

	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_PLAIN)
	public String readLabTest() throws URISyntaxException {
		URI uri=new URI(LABTEST_URI+"/read/");
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
	public String insertLabTest(
			@FormParam("PatientID") String PatientID,
			@FormParam("TestName") String TestName,
			@FormParam("TestType") String TestType, 
			@FormParam("TestDescription") String TestDescription,
			@FormParam("LabDate") String LabDate) throws ParseException {
		
		Form form =new Form();
		form.param("PatientID", PatientID);
		form.param("TestName", TestName);
		form.param("TestType", TestType);
		form.param("TestDescription", TestDescription);
		form.param("LabDate", LabDate);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(LABTEST_URI+"/insert/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .post(Entity.form(form), String.class);

		return response;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteLabTest(@PathParam("id")String LabTestId) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(LABTEST_URI);
		String response = target.path(LabTestId)
								.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .delete(String.class);

		return response;
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateLabTest(
			@FormParam("LabTestID") String LabTestID,
			@FormParam("PatientID") String PatientID,
			@FormParam("TestName") String TestName, 
			@FormParam("TestType") String TestType,
			@FormParam("TestDescription") String TestDescription,
			@FormParam("LabDate") String LabDate) throws ParseException {
		
		Form form =new Form();
		form.param("LabTestID", LabTestID);
		form.param("PatientID", PatientID);
		form.param("TestName", TestName);
		form.param("TestType", TestType);
		form.param("TestDescription", TestDescription);
		form.param("LabDate", LabDate);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(LABTEST_URI+"/update/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .put(Entity.form(form), String.class);

		return response;
	}
	

	

}
