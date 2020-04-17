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
	public String readHospital() {
		return new Gson().toJson(hospitalController.readHospital());
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospital(@FormParam("hospitalname") String hospitalname, @FormParam("hospitaladdress") String hospitaladdress,
			@FormParam("contactnumber") String contactnumber, @FormParam("email") String email) throws ParseException {

		Hospital hospital = new Hospital();
		hospital.setHospitalname(hospitalname);
		hospital.setHospitaladdress(hospitaladdress);
		hospital.setContactnumber(contactnumber);
		hospital.setEmail(email);

		return hospitalController.Addhospital(hospital);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(@PathParam("id")String hid) {

		return hospitalController.deleteHospital(hid);
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospital(@FormParam("hid") String hid,@FormParam("hospitalname") String hospitalname, @FormParam("hospitaladdress") String hospitaladdress,
			@FormParam("contactnumber") String contactnumber, @FormParam("email") String email) throws ParseException {

		Hospital hospital = new Hospital();
		hospital.setHid(Integer.parseInt(hid));
		hospital.setHospitalname(hospitalname);
		hospital.setHospitaladdress(hospitaladdress);
		hospital.setContactnumber(contactnumber);
		hospital.setEmail(email);

		return hospitalController.updatehospital(hospital);
	}
	
	@GET
	@Path("/search/{id}")
	@Produces({ MediaType.TEXT_PLAIN })
	public String searchHospital(@PathParam("id")String hid) {
		return new Gson().toJson(hospitalController.searchHospital(hid));
	}
	
	

}
