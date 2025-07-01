import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;
	
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		map = _map; // Initializes the map variable with the given map
		key = _key; // Initializes the key variable with the given string
		cipher_text = text; // Initializes the cipher_text variable with the given string
	}

	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}
	
	private void generate_keystream() {
		int textLength = cipher_text.length(); // Gets the length of the cipher_text variable
		int keyLength = key.length(); // Gets the length of the key variable

		if (textLength < keyLength) { // If the length of the cipher_text variable is less than the length of the key variable
			// Set the keystream variable to the substring of the key variable from index 0 to the length of the cipher_text variable.
			// For example, if the key variable is "DATA" and the cipher_text variable is "DEN", then the keystream variable will be "DAT".
			keystream = key.substring(0, textLength);
		} else if (textLength > keyLength) { // If the length of the cipher_text variable is greater than the length of the key variable
			String tempKey = key; // Set the tempKey variable to the key variable
			int index = 0; // Set the index variable to 0
			// While the length of the tempKey variable is less than the length of the cipher_text variable, it adds characters from the key variable to the tempKey variable
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
		} else { // If the length of the cipher_text variable is equal to the length of the key variable
			keystream = key; // Set the keystream variable to the key variable
		}
	}
	
	private void generate_plain_text() {
		for (int i = 0; i < cipher_text.length(); ++i) { // Loops through the cipher_text length
			char keyChar = keystream.charAt(i); // Gets the character at the index of the keystream variable
			char cipherChar = cipher_text.charAt(i); // Gets the character at the index of the cipher_text variable
			// Gets the iterator of the keyChar key's set.
			// Set will begin from 'A' and go up to 'Z
			Iterator<Character> iterator = map.get(keyChar).keySet().iterator();
			while (iterator.hasNext()) { // Iterates through the iterator
				char plainChar = iterator.next(); // Gets the next character of the iterator
				if (map.get(keyChar).get(plainChar).equals(cipherChar)) { // If keyChar keySet's key's value is equal to cipherChar
					//  We get key set's current key
					plain_text += plainChar; // Adds the plainChar to the plain_text variable
					break;
				}
			}
		}
	}

	public String get_keystream() {
		return keystream; // Returns the keystream variable
	}
	
	public String get_plain_text() {
		return plain_text; // Returns the plain_text variable
	}
}
