package com.hcs.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import com.hcs.controller.LabTestController;
import com.hcs.model.LabTest;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/LabTest")

public class LabTestService {

	LabTestController labController = new LabTestController();

	@GET
	@Path("/read")
	@Produces({ MediaType.TEXT_PLAIN })
	public String readItems() {
		return new Gson().toJson(labController.readLabTest());
	}

	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertLabTest(@FormParam("TestName") String TestName,
			@FormParam("TestType") String TestType, @FormParam("TestDescription") String TestDescription,@FormParam("LabDate") String LabDate) throws ParseException {

		LabTest lab = new LabTest();
		lab.setTestName(TestName);
		lab.setTestType(TestType);
		lab.setTestDescription(TestDescription);
		lab.setLabDate(Date.valueOf(LabDate));
		
		return labController.AddLabTest(lab);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteLabTest(@PathParam("id")String LabTestID) {

		return labController.deleteLabTest(LabTestID);
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateLabTest(@FormParam("LabTestID") String LabTestID,@FormParam("TestName") String TestName, @FormParam("TestType") String TestType,
			@FormParam("TestDescription") String TestDescription,@FormParam("LabDate") String LabDate) throws ParseException {

		LabTest lab = new LabTest();
		lab.setLabTestID(Integer.parseInt(LabTestID));
		lab.setTestName(TestName);
		lab.setTestType(TestType);
		lab.setTestDescription(TestDescription);
		lab.setLabDate(Date.valueOf(LabDate));

		return labController.updateLabTest(lab);
	}
	
	

}
