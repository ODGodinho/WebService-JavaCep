public class Validation {

	public static boolean isValidCep(String cepNumber) {
		if (cepNumber.length() == 8 && isNumeric(cepNumber)) {
			return true;
		}
		return false;
	}

	public static boolean isNumeric(String val) {
		if (val == null) {
			return false;
		}
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean isValidString(String val) {
		if (val == null) {
			return false;
		}

		return val.trim().length() > 0;
	}

	public static boolean isValidState(String val) {
		if (val == null) {
			return false;
		}

		return val.length() == 2;
	}

	public static boolean isValidCity(String val) {
		if(val == null) return false;
		
		return val.length() > 4;
	}

	public static boolean isValidType(String val) {
		return val.equalsIgnoreCase("1") || val.equalsIgnoreCase("2");
	}
}
