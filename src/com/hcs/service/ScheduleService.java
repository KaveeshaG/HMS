package com.hcs.service;


import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import com.hcs.controller.ScheduleController;
import com.hcs.model.Doctor;
import com.hcs.model.Schedule;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Schedule")
public class ScheduleService {
	
	
ScheduleController schedulecontroller = new ScheduleController();

	@GET
	@Path("/read")
	@Produces({ MediaType.TEXT_PLAIN })
	public String readSchedule() {
		return new Gson().toJson(schedulecontroller.readSchedule());
	}
	
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertSchedule(@FormParam("hid") String hid, @FormParam("DoctorID") String DoctorID,
			@FormParam("ScheduleDate") String ScheduleDate, @FormParam("ScheduleTime") String ScheduleTime
			) throws ParseException {

		Schedule schedule = new Schedule();
		schedule.setHid(Integer.parseInt(hid));
		schedule.setDoctorID(Integer.parseInt(DoctorID));
		schedule.setScheduleDate(Date.valueOf(ScheduleDate));
		schedule.setScheduleTime(Time.valueOf(ScheduleTime));
		

		return schedulecontroller.AddSchedule(schedule);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteschedule(@PathParam("id")String ScheduleId) {

		return schedulecontroller.deleteSchedule(ScheduleId);
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateSchedule(@FormParam("SchduleID") String SchduleID,@FormParam("hid") String hid, @FormParam("DoctorID") String DoctorID,
			@FormParam("ScheduleDate") String ScheduleDate, @FormParam("ScheduleTime") String ScheduleTime) throws ParseException {

		Schedule schedule = new Schedule();
		schedule.setSchduleID(Integer.parseInt(SchduleID));
		schedule.setHid(Integer.parseInt(hid));
		schedule.setDoctorID(Integer.parseInt(DoctorID));
		schedule.setScheduleDate(Date.valueOf(ScheduleDate));
		schedule.setScheduleTime(Time.valueOf(ScheduleTime));

		return schedulecontroller.updateSchedule(schedule);
	}
	
	
	

	
	
}
