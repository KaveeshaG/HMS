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
import com.hcs.api.BillService;
import com.owlike.genson.ext.jaxrs.GensonJsonConverter;




//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



@Path("/Bill")
public class BillService {
	

	public static final String BILL_URI="http://localhost:8080/BillService/BillService/Bill";

	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_PLAIN)
	public String readBill() throws URISyntaxException {
		URI uri=new URI(BILL_URI+"/read/");
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
	public String insertBill(@FormParam("BillType") String BillType, @FormParam("BillDate") String BillDate,
			@FormParam("BillAmount") String BillAmount, @FormParam("ReferenceID") String ReferenceID, @FormParam("ReferenceType") String ReferenceType) throws ParseException {


		
		Form form =new Form();
		form.param("BillType",BillType);
		form.param("BillDate", BillDate);
		form.param("BillAmount", BillDate);
		form.param("ReferenceID", ReferenceID);
		form.param("ReferenceType", ReferenceType);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BILL_URI+"/insert/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .post(Entity.form(form), String.class);

		return response;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(@PathParam("id")String BillId) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BILL_URI);
		String response = target.path(BillId)
								.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .delete(String.class);

		return response;
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(@FormParam("BillID") String BillID,@FormParam("BillType") String BillType, @FormParam("BillDate") String BillDate,
			@FormParam("BillAmount") String BillAmount, @FormParam("ReferenceID") String ReferenceID, @FormParam("ReferenceType") String ReferenceType) throws ParseException {


		Form form =new Form();
		form.param("BillID", BillID);
		form.param("BillType", BillType);
		form.param("BillDate", BillDate);
		form.param("BillAmount", BillAmount);
		form.param("ReferenceID", ReferenceID);
		form.param("ReferenceType",ReferenceType);
		
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BILL_URI+"/update/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .put(Entity.form(form), String.class);

		return response;
	}
	
	
	

}
