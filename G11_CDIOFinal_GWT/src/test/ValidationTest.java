package test;

import static org.junit.Assert.*;

import org.junit.Test;

import dk.dtu.cdiofinal.shared.FieldVerifier;

public class ValidationTest {

	
	@Test
	public void cprValidationTest() {
		// Testing cpr validation
		// the validation is simple. its testing every digit by itself
		
		// valid date
		String cpr1 = "260184-1234";
		
		// invalid date
		String cpr2 = "430372-4321";
		
		// containing letters
		String cpr3 = "1b0865-3125";
		
		// Expecting valid
		assertTrue(FieldVerifier.cprValid(cpr1));
		
		// invalid
		assertTrue(!FieldVerifier.cprValid(cpr2));
		
		// invalid
		assertTrue(!FieldVerifier.cprValid(cpr3));
	}
	
	
	@Test
	public void roleValidationTest() {
	
		
		// Testing role 0 // should not exist
		assertTrue(!FieldVerifier.roleValid(0));
		
		// valid role, should be correct
		assertTrue(FieldVerifier.roleValid(1));
		
		// valid role, should be correct
		assertTrue(FieldVerifier.roleValid(2));
		
		// valid role, should be correct
		assertTrue(FieldVerifier.roleValid(3));
		
		// valid role, should be correct
		assertTrue(FieldVerifier.roleValid(4));
		
		// invalid role
		assertTrue(!FieldVerifier.roleValid(5));
		
		// testing big numbers
		assertTrue(!FieldVerifier.roleValid(Integer.MAX_VALUE));
		
		// testing negative numbers
		assertTrue(!FieldVerifier.roleValid(-1));
		
		// testing very low numbers
		assertTrue(!FieldVerifier.roleValid(Integer.MIN_VALUE));
	}
	
	
	@Test
	public void passwordValidationTest() {
		// short
		String pass = "1aB!";

		// one rule
		String pass1 = "ABCDEF";
		String pass2 = "abcdef";
		String pass3 = "123456";

		// two rules
		String pass4 = "abcdeF";
		String pass5 = "abcde1";
		String pass6 = "ABCDE1";
		String pass7 = "12345.";

		// illegal characters
		String pass8 = "123ten$";

		// three rules
		String pass9 = "Abcde1";

		
		// These should not be valid, hence the Exclamationmark
		assertTrue(!FieldVerifier.passwordValid(pass));
		assertTrue(!FieldVerifier.passwordValid(pass1));
		assertTrue(!FieldVerifier.passwordValid(pass2));
		assertTrue(!FieldVerifier.passwordValid(pass3));
		assertTrue(!FieldVerifier.passwordValid(pass4));
		assertTrue(!FieldVerifier.passwordValid(pass5));
		assertTrue(!FieldVerifier.passwordValid(pass6));
		assertTrue(!FieldVerifier.passwordValid(pass7));
		assertTrue(!FieldVerifier.passwordValid(pass8));

		// These should be valid
		assertTrue(FieldVerifier.passwordValid(pass9));
	}

}
