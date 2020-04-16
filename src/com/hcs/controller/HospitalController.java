package com.hcs.controller;
import com.hcs.model.Hospital;
import com.hcs.service.HospitalService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

@Path("/hospital")
public class HospitalController {
	private  HospitalService appRepo = new HospitalService();

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String readHospital() {
        return appRepo.readHospital();
    }

    @GET
    @Path("/hospital/{hid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Hospital readHospital(@PathParam("hid") String hid) {
        return appRepo.readHospital(hid);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertHospital(@FormParam("hid") int hid, @FormParam("name") String HospitalName, @FormParam("address") String HospitalAddress, @FormParam("contact") int HospitalContact,
                               @FormParam("email") String HospitalEmail)
                                {

        Hospital hospital = new Hospital();
        hospital.setHid(hid);
        hospital.setHospitalName(HospitalName);
        hospital.setHospitalAddress(HospitalAddress);
        hospital.setHospitalContact(HospitalContact);
        hospital.setHospitalEmail(HospitalEmail);

        return appRepo.insertHospital(hospital);
        
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateHospital(String appData) {
    	Hospital hospital = new Hospital();
        JsonObject appObject;

        appObject = new JsonParser().parse(appData).getAsJsonObject();
        hospital.setHid(Integer.parseInt(appObject.get("hid").getAsString()));
        hospital.setHospitalName(appObject.get("HospitalName").getAsString());
        hospital.setHospitalAddress(appObject.get("HospitalAddress").getAsString());
        hospital.setHospitalContact(Integer.parseInt(appObject.get("HospitalContact").getAsString()));
        hospital.setHospitalEmail(appObject.get("hospitalEmail").getAsString());
        

        return appRepo.updateHospital(hospital);
    }

    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteHospital(String hospitalData) {

        //Convert the input string to an XML document
        Document doc = Jsoup.parse(hospitalData, "", Parser.xmlParser());
        String hospital_id = doc.select("hid").text();
        return appRepo.deleteItem(hospital_id);
    }
	
	
	
}