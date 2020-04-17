package com.hcs.service;



	

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import com.hcs.controller.BillController;
import com.hcs.model.Bill;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Bill")
public class BillService {

	BillController billController = new BillController();

	@GET
	@Path("/read")
	@Produces({ MediaType.TEXT_PLAIN })
	public String readItems() {
		return new Gson().toJson(billController.readBill());
	}

	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(@PathParam("id")String BillId) {

		return billController.deleteBill(BillId);
	}
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBill(@FormParam("BillType") String BillType, @FormParam("BillDate") String BillDate,
			@FormParam("BillAmount") String BillAmount, @FormParam("ReferenceID") String ReferenceID, @FormParam("ReferenceType") String ReferenceType) throws ParseException {

		Bill bill = new Bill();
		bill.setBillType(BillType);
		bill.setBillDate(Date.valueOf(BillDate));
		bill.setBillAmount(Double.parseDouble(BillAmount));
		bill.setReferenceID(ReferenceID);
		bill.setReferenceType(ReferenceType);
		

		return billController.AddBill(bill);
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(@FormParam("BillID") String BillID,@FormParam("BillType") String BillType, @FormParam("BillDate") String BillDate,
			@FormParam("BillAmount") String BillAmount, @FormParam("ReferenceID") String ReferenceID, @FormParam("ReferenceType") String ReferenceType) throws ParseException {

		Bill bill = new Bill();
		bill.setBillID(Integer.parseInt(BillID));
	    bill.setBillType(BillType);
		bill.setBillDate(Date.valueOf(BillDate));
	    bill.setBillAmount(Double.parseDouble(BillAmount));
	    bill.setReferenceID(ReferenceID);
	    bill.setReferenceType(ReferenceType);
		

		return billController.updatebill(bill);
	}
	
	
	
	
	

}	
	
	
	
	


