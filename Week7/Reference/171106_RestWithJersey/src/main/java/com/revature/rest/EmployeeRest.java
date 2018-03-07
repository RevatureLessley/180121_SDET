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

<<<<<<< HEAD
// Path annotation denotes what urlk will be used to access THIS class' REST METHODs
//So typcially path includes the ip & port for the sever, the name of the project 
//(Which is either the ArtifactId which is defined in the POM of the project or the finalName (trumps artifactID))
//followed by whatever URL pattern provided in the web.xml under the dispatchter servlet for Jersery. (In this case we wrote /rest/*)
//Convention suggest you have a least SOMETHING to separate
=======
//Path annotation denotes what url will be used to access THIS class's REST methods.
//So typical path includes the ip & port for the server,
//the name of the project (Which is either the ArtifactID as indicated in the pom.xml,
//OR
/*
 * 	<build>
		<finalName>PROJECTNAME</finalName>
	</build>
 */
/*
 * Followed by whatever URL pattern provided in the web.xml under the
 * dispatcher servlet for Jersey. (In this case we wrote /rest/*)
 * Convention suggests you have at least SOMETHING to separate rest endpoints
 * from actual server url mappings.
 */
>>>>>>> c379323b8095d7f29594268f8d7c3a0e8adce5d5
@Path("/emp") //http://localhost:8085/ArtifactId/rest/emp
public class EmployeeRest {
	public static List<Employee> emps = new ArrayList<>();
	@GET //The method below is GET allowed (As offered by Jax_rs)
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
<<<<<<< HEAD
	@Path("/get/{empId}") // Note the curly notation in this endpoint, this marks
						// a variable in the url, making it dynamic
	@Produces(MediaType.APPLICATION_JSON)
	//
	public Employee getEmployeeIndex(@PathParam("empId") int id){
=======
	@Path("/get/{arrayListIndex}") //Note the curly notation in this endpoint, this marks
						  //A variable in the url, making it dynamic
	@Produces(MediaType.APPLICATION_JSON)
	//Use PathParm in the parameter to pass the url content as the variable
	public Employee getEmployeeIndex(@PathParam("arrayListIndex") int index){
>>>>>>> c379323b8095d7f29594268f8d7c3a0e8adce5d5
		
		if(emps.size()<index){
			return null;
		}
		
		return emps.get(index);
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
