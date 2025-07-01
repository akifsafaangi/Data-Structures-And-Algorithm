import java.util.Map;

public class encryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";
	
	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		map = _map; // Initializes the map variable with the given map
		plain_text = text; // Initializes the plain_text variable with the given string
		key = _key; // Initializes the key variable with the given string
	}
	
	public void encrypt() {
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}
	
	private void generate_keystream() {
		int textLength = plain_text.length(); // Gets the length of the plain_text variable
		int keyLength = key.length(); // Gets the length of the key variable

		if (textLength < keyLength) { // If the length of the plain_text variable is less than the length of the key variable
			// Set the keystream variable to the substring of the key variable from index 0 to the length of the plain_text variable.
			// For example, if the key variable is "DATA" and the plain_text variable is "DEN", then the keystream variable will be "DAT".
			keystream = key.substring(0, textLength);
		} else if (textLength > keyLength) { // If the length of the plain_text variable is greater than the length of the key variable
			String tempKey = key; // Set the tempKey variable to the key variable
			int index = 0; // Set the index variable to 0
			// While the length of the tempKey variable is less than the length of the plain_text variable, it adds characters from the key variable to the tempKey variable
			// For example, if the key variable is "DATA" and the plain_text variable is "DENEME", then the tempKey variable will be "DATADA".
			while (tempKey.length() < textLength) {
				// Add the character at the index of the key variable to the tempKey variable
				tempKey += key.charAt(index);
				index++;
				if(index == keyLength) { // If the index is equal to the length of the key variable
					index = 0; // Set the index to 0
				}
			}
			keystream = tempKey; // Set the keystream variable to the tempKey variable
		} else { // If the length of the plain_text variable is equal to the length of the key variable
			keystream = key; // Set the keystream variable to the key variable
		}
	}
	
	private void generate_cipher_text() {
		for (int i = 0; i < plain_text.length(); ++i) { // Loops through the plain_text length
			char plainChar = plain_text.charAt(i); // Gets the character at the index of the plain_text variable
			char keyChar = keystream.charAt(i); // Gets the character at the index of the keystream variable
			// Gets the character from the map variable with the plainChar key and the keyChar key
			// For example, if the plainChar is 'C' and the keyChar is 'G', the cipherChar will be 'I' according to the table.
			char cipherChar = map.get(plainChar).get(keyChar);
			cipher_text += cipherChar; // Adds the cipherChar to the cipher_text variable
		}
	}

	public String get_keystream() {
		return keystream; // Returns the keystream variable
	}
	
	public String get_cipher_text() {
		return cipher_text; // Returns the cipher_text variable
	}
}
