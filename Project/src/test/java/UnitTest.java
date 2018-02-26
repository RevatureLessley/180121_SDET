import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.project.services.EmployeeServices;
import com.project.services.TuitionServices;

public class UnitTest {

	@Test
	//Login credentials test
	public void validateLogin() {
		assertEquals(true,EmployeeServices.validateLogin("HARISH", "PASSWORD"));
	}
	
	@Test
	//User name taken test
	public void validateUsername() {
		assertEquals(true,EmployeeServices.validateUsername("HARISH"));
	}
	
	@Test
	//User name Title test
	public void getTitle() {
		assertEquals("EMPLOYEE",EmployeeServices.getTitle("HARISH"));
	}
	
	@Test
	//Tuition form status test
	public void getStatus() {
		assertEquals("PENDING", TuitionServices.getStatus(0));
	}
	
	@Test
	//Tuition form approve test
	public void approveTuition() {
		assertEquals(false, TuitionServices.approveTuition(2000));
	}
	
	@Test
	//Tuition form more info test
	public void moreInfo() {
		assertEquals(false, TuitionServices.moreInfo(2000, "Not much"));
	}
}
