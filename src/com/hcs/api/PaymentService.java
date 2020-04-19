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
import com.hcs.dto.Payment;
import com.owlike.genson.ext.jaxrs.GensonJsonConverter;




//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



@Path("/payment")
public class PaymentService {
	

	public static final String PAYMENT_URI="http://localhost:8080/PaymentService/PaymentService/payment";

	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_PLAIN)
	public String readPayment() throws URISyntaxException {
		URI uri=new URI(PAYMENT_URI+"/read/");
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
	public String insertPayment(@FormParam("BillID") String BillID, @FormParam("PaymentDate") String PaymentDate,
			@FormParam("PaymentAmount") String PaymentAmount, @FormParam("PaymentType") String PaymentType, @FormParam("PaymentDescription") String PaymentDescription) throws ParseException {

		
		Form form =new Form();
		form.param("BillID", BillID);
		form.param("PaymentDate", PaymentDate);
		form.param("PaymentAmount", PaymentAmount);
		form.param("PaymentType", PaymentType);
		form.param("PaymentDescription", PaymentDescription);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PAYMENT_URI+"/insert/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .post(Entity.form(form), String.class);

		return response;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(@PathParam("id")String PaymentId) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PAYMENT_URI);
		String response = target.path(PaymentId)
								.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .delete(String.class);

		return response;
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(@FormParam("PaymentID") String PaymentID,@FormParam("BillID") String BillID, @FormParam("PaymentDate") String PaymentDate,
			@FormParam("PaymentAmount") String PaymentAmount, @FormParam("PaymentType") String PaymentType, @FormParam("PaymentDescription") String PaymentDescription) throws ParseException {


		Form form =new Form();
		form.param("PaymentID", PaymentID);
		form.param("BillID", BillID);
		form.param("PaymentDate", PaymentDate);
		form.param("PaymentAmount", PaymentAmount);
		form.param("PaymentType",PaymentType);
		form.param("PaymentDescription",PaymentDescription);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PAYMENT_URI+"/update/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .put(Entity.form(form), String.class);

		return response;
	}
	
	
	

}
