import static org.junit.Assert.*;
import org.junit.Test;
/*
 * This will test the createPassword method in Main.
 */
public class CreatePasswordTest {

	@Test
	public void test() {
		Main test = new Main();
		String password = test.createPassword();
		assertEquals("password", password);
	}
}
