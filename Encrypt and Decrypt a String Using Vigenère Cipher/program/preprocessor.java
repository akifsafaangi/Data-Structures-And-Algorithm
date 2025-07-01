public class preprocessor {
	private String initial_string;
	private String preprocessed_string;
		
	public preprocessor(String str) {
		initial_string = str; // Initializes the initial_string variable with the given string
	}

	public void preprocess() {
		// do not edit this method
		capitalize();
		clean();
	}
	
	private void capitalize() {
		preprocessed_string = initial_string.toUpperCase(); // Capitalize the given string. For example, "abc" -> "ABC"
	}

	private void clean() {
		preprocessed_string = preprocessed_string.replaceAll("[^A-Z]", ""); // Replace all non-alphabetic characters with an empty string. For example, "A1B2C_!32X" -> "ABCX"
	}
	
	public String get_preprocessed_string() {
		return preprocessed_string; // Returns the preprocessed string
	}
}