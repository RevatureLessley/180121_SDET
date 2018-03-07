package com.revature.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.beans.Employee;

// Path annotation denotes what urlk will be used to access THIS class' REST METHODs
//So typcially path includes the ip & port for the sever, the name of the project 
//(Which is either the ArtifactId which is defined in the POM of the project or the finalName (trumps artifactID))
//followed by whatever URL pattern provided in the web.xml under the dispatchter servlet for Jersery. (In this case we wrote /rest/*)
//Convention suggest you have a least SOMETHING to separate
@Path("/emp") //http://localhost:8085/ArtifactId/rest/emp
public class EmployeeRest {
	public static List<Employee> emps = new ArrayList<>();
	@GET
	@Path("/get/all")
	//http://localhost:8085/ArtifactId/rest/emp/get/all
	@Produces(MediaType.APPLICATION_JSON)
	/*
	 * Path tells us how to hit the endpoint.
	 * GET simply handles get requests.
	 * Produces tells the requestor what datatype they
	 * will be getting
	 */
	public List<Employee> getAllEmps(){
		
		emps.add(new Employee("Bobbert", "Bobson", 123));
		emps.add(new Employee("Ryan", "Lessley", 77));
		emps.add(new Employee("Goodbye", "Hello", 10));
		
		return emps;
	}
	
	//The method below is GET Allowed  (as offered by Jax_rs)
	@GET
	@Path("/get/{empId}") // Note the curly notation in this endpoint, this marks
						// a variable in the url, making it dynamic
	@Produces(MediaType.APPLICATION_JSON)
	//
	public Employee getEmployeeIndex(@PathParam("empId") int id){
		
		if(emps.size()<id){
			return null;
		}
		
		return emps.get(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/post")
	public String insertEmployee(Employee emp){
		
		System.out.println(emp);
		emps.add(emp);
		return "Success";
	}
	
	

}
