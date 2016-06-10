package dk.dtu.cdiofinal.shared;

public class FieldVerifier {


	public static String cprFormat(String cpr){
		// Add "-" to cpr
		String str = cpr;
		str = cpr.substring(0, 6);
		str+= "-";
		str+= cpr.substring(6);

		return str;
	}
	public static boolean passwordValid(String password){
		if(!password.matches("(.*)[^.-_+!?=a-zA-Z0-9](.*)")){

			// 4 rules, 3 should be fulfilled
			// 1: Contain upper characters (A-Z)
			// 2: Contain lower characters (a-z)
			// 3: Contain numbers (0-9)
			// 4: Contain following character . - _ + ! ? =
			int ruleCount = 0;		// counting fulfilled rules
			if(password.matches("(.*)[A-Z](.*)")) ruleCount++;
			if(password.matches("(.*)[a-z](.*)")) ruleCount++;
			if(password.matches("(.*)[0-9](.*)")) ruleCount++;
			if(password.matches("(.*)[.\\-_+!?=](.*)")) ruleCount++;

			if (ruleCount >= 3){
				return true;
			}
		}
		return false;
	}

	public static boolean roleValid(int rolle){
		if(rolle >= 1 && rolle <= 4 ){
			return true;
		}
		else 
			return false;
	}

	public static boolean cprValid(String cpr){
		return (cpr.matches("[0-3][0-9][0-1][0-9]\\d{2}-\\d{4}?[^0-9]*"));
	}

	public static boolean nameValid(String name){
		if (name.length()<2 || name.length()>20){
			return false;
		}
		else{
			return true;
		}
	}

	public static boolean numberValid(int i) {
		if(i > 0 && i <= 99999999){
			return true;
		}else{
			return false;
		}
	}

	public static boolean amountValid(double i) {
		if(i > 0.0){
			return true;
		}else{
			return false;
		}
	}

	public static boolean active(boolean active){
		if (active == true || active == false)
			return true;
		else
			return false;
	}
}

