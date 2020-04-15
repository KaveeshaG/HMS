package com.hcs.service;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import com.hcs.controller.HospitalController;
import com.hcs.model.Hospital;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospital")
public class HospitalService {

	HospitalController hospitalController = new HospitalController();

	@GET
	@Path("/read")
	@Produces({ MediaType.TEXT_PLAIN })
	public String readItems() {
		return new Gson().toJson(hospitalController.readHospital());
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospital(@FormParam("HospitalName") String HospitalName, @FormParam("HospitalAddress") String HospitalAddress,
			@FormParam("HospitalContact") String HospitalContact, @FormParam("HospitalEmail") String HospitalEmail) throws ParseException {

		Hospital hospital = new Hospital();
		hospital.setHospitalName(HospitalName);
		hospital.setHospitalAddress(HospitalAddress);
		hospital.setHospitalContact(HospitalContact);
		hospital.setHospitalEmail(HospitalEmail);

		return hospitalController.AddHospital(hospital);
	}

	@DELETE
	@Path("/{hid}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(@PathParam("hid")String hid) {

		return hospitalController.deleteHospital(hid);
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospital(@FormParam("HospitalName") String HospitalName, @FormParam("HospitalAddress") String HospitalAddress,
			@FormParam("HospitalContact") String HospitalContact, @FormParam("HospitalEmail") String HospitalEmail) throws ParseException {

		Hospital hospital = new Hospital();
		hospital.setHospitalName(HospitalName);
		hospital.setHospitalAddress(HospitalAddress);
		hospital.setHospitalContact(HospitalContact);
		hospital.setHospitalEmail(HospitalEmail);

		return hospitalController.updateHospital(hospital);
	}
	
	

}
