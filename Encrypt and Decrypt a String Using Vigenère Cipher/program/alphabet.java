import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
	private Set<Character> english_alphabet = new LinkedHashSet<Character>();
	private Map<Character, Map<Character, Character>> map = new HashMap<Character,  Map<Character, Character>>();
	
	public alphabet() {
		// do not edit this method
		fill_english_alphabet();
		fill_map();
	}
	
	private void fill_english_alphabet() {
		// do not edit this method
		for(char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
		    english_alphabet.add(c);
		}
	}
	
	private void fill_map() {
		// You must use the "english_alphabet" variable in this method, to fill the "map" variable.
		// You can define 1 or 2 iterators to iterate through the set items.
		Iterator<Character> outerIterator = english_alphabet.iterator(); // Iterator for the outer loop
		while (outerIterator.hasNext()) { // Iterates through the english_alphabet set
			Character outerKey = outerIterator.next(); // Gets the outer key. Begins with 'A' and goes up to 'Z'
			Map<Character, Character> innerMap = new HashMap<Character, Character>(); // Inner map
			Iterator<Character> innerIterator = english_alphabet.iterator(); // Iterator for the inner loop
			int j = 0;
			while (innerIterator.hasNext()) { // Iterates through the english_alphabet set
				char innerKey = innerIterator.next(); // Gets the inner key. Begins with 'A' and goes up to 'Z'
				char value = (char) ('A' + (outerKey - 'A' + j) % 26); // Calculates the value of the inner key.
				innerMap.put(innerKey, value); // Puts the inner key and the value of the inner key to the inner map
				j++;
			}
			map.put(outerKey, innerMap); // Puts the current outer key and its inner map to the map. For example, if the outer key is 'C', the inner map will be {'A' -> 'C', 'B' -> 'D', 'C' -> 'E', ...}
		}
	}

	public void print_map() {
		// do not edit this method
		System.out.println("*** Viegenere Cipher ***\n\n");
		System.out.println("    " + english_alphabet);
		System.out.print("    ------------------------------------------------------------------------------");
		for(Character k: map.keySet()) {
			System.out.print("\n" + k + " | ");
			System.out.print(map.get(k).values());
		}
		System.out.println("\n");
		
	}

	public Map get_map() {
		return map; // Returns the map variable
	}
}