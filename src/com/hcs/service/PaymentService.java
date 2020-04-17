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
import com.hcs.controller.PaymentController;
import com.hcs.model.Bill;
import com.hcs.model.Payment;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/payment")
public class PaymentService {

	PaymentController paymentController = new PaymentController();

	@GET
	@Path("/read")
	@Produces({ MediaType.TEXT_PLAIN })
	public String readItems() {
		return new Gson().toJson(paymentController.readPayment());
	}
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(@PathParam("id")String PaymentId) {

		return paymentController.deletePayment(PaymentId);
	}
	
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("BillID") String BillID, @FormParam("PaymentDate") String PaymentDate,
			@FormParam("PaymentAmount") String PaymentAmount, @FormParam("PaymentType") String PaymentType, @FormParam("PaymentDescription") String PaymentDescription) throws ParseException {

		Payment payment = new Payment();
		payment.setBillID(BillID);
		payment.setPaymentDate(Date.valueOf(PaymentDate));
		payment.setPaymentAmount(Double.parseDouble(PaymentAmount));
		payment.setPaymentType(PaymentType);
		payment.setPaymentDescription(PaymentDescription);
		
		

		return paymentController.AddPayment(payment);
	}

	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(@FormParam("PaymentID") String PaymentID,@FormParam("BillID") String BillID, @FormParam("PaymentDate") String PaymentDate,
			@FormParam("PaymentAmount") String PaymentAmount, @FormParam("PaymentType") String PaymentType, @FormParam("PaymentDescription") String PaymentDescription) throws ParseException {

		Payment payment = new Payment();
		payment.setPaymentID(Integer.parseInt(PaymentID));
		payment.setBillID(BillID);
		payment.setPaymentDate(Date.valueOf(PaymentDate));
		payment.setPaymentAmount(Double.parseDouble(PaymentAmount));
		payment.setPaymentType(PaymentType);
		payment.setPaymentDescription(PaymentDescription);
		

		return paymentController.updatepayment(payment);
	
	
}
	
	
}