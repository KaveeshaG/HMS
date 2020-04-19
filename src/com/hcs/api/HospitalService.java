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

import com.hcs.api.HospitalService;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospital")
public class HospitalService {

	public static final String HOSPITAL_URI="http://localhost:8080/HospitalService/HospitalService/Hospital";

	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_PLAIN)
	public String readHospital() throws URISyntaxException {
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
	public String insertHospital(@FormParam("hospitalname") String hospitalname, @FormParam("hospitaladdress") String hospitaladdress,
			@FormParam("contactnumber") String contactnumber, @FormParam("email") String email) throws ParseException {

		
		Form form =new Form();
		form.param("hospitalname", hospitalname);
		form.param("hospitaladdress", hospitaladdress);
		form.param("contactnumber", contactnumber);
		form.param("email", email);
		
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
	public String deleteHospital(@PathParam("id")String hid) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HOSPITAL_URI);
		String response = target.path(hid)
								.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .delete(String.class);

		return response;
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospital(@FormParam("hid") String hid,@FormParam("hospitalname") String hospitalname, @FormParam("hospitaladdress") String hospitaladdress,
			@FormParam("contactnumber") String contactnumber, @FormParam("email") String email) throws ParseException {


		Form form =new Form();
		form.param("hid", hid);
		form.param("hospitalname", hospitalname);
		form.param("hospitaladdress", hospitaladdress);
		form.param("contactnumber", contactnumber);
		form.param("email", email);
		
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
	public String searchDoctor(@PathParam("id")String hid) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HOSPITAL_URI+"/search");
		String response = target.path(hid)
								.request(MediaType.APPLICATION_JSON)
		                        .get(String.class);

		return response;
	}
	
	
}
