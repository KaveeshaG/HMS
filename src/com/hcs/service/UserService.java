package com.hcs.service;

import java.text.ParseException;

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

import com.google.gson.Gson;
import com.hcs.controller.UserController;
import com.hcs.model.User;

@Path("/User")
public class UserService {

	UserController userController = new UserController();

	@GET
	@Path("/read")
	@Produces({ MediaType.TEXT_PLAIN })
	public String readUser() {
		return new Gson().toJson(userController.readUser());
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("username") String username, @FormParam("usertype") String usertype,
			@FormParam("usercontact") String usercontact, @FormParam("useremail") String useremail) throws ParseException {

		User user = new User();
		user.setUsername(username);
		user.setUsertype(usertype);
		user.setUsercontact(usercontact);
		user.setUseremail(useremail);

		return userController.Adduser(user);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(@PathParam("id")String uid) {

		return userController.deleteUser(uid);
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(@FormParam("uid") String uid,@FormParam("username") String username, @FormParam("usertype") String usertype,
			@FormParam("usercontact") String usercontact, @FormParam("useremail") String useremail) throws ParseException {

		User user = new User();
		user.setUid(Integer.parseInt(uid));
		user.setUsername(username);
		user.setUsertype(usertype);
		user.setUsercontact(usercontact);
		user.setUseremail(useremail);

		return userController.updateuser(user);
	}
	
	@GET
	@Path("/search/{id}")
	@Produces({ MediaType.TEXT_PLAIN })
	public String searchUser(@PathParam("id")String uid) {
		return new Gson().toJson(userController.searchUser(uid));
	}
	
	
}
