package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		String reversedString = "";
		char[] letters = string.toCharArray();
		for (int i = letters.length-1; i > -1; i--) {
			reversedString+=letters[i];
		}
		return reversedString;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String[] words = phrase.split("\\W+");
		String acronym = "";
		for (int i = 0; i < words.length; i++) {
			acronym+=(words[i].substring(0,1)).toUpperCase();
		}
		
		return acronym;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}
		
		// helper function that counts the number of equalities between sides
		private int numEqualSides() {
			int numEqualSides = 0;
			if (((Double) sideOne).equals(sideTwo)) {
				numEqualSides++;
			}
			if (((Double) sideTwo).equals(sideThree)) {
				numEqualSides++;
			}
			if (((Double) sideThree).equals(sideOne)) {
				numEqualSides++;
			}
			return numEqualSides;
		}

		public boolean isEquilateral() {
			return numEqualSides()==3;
		}

		public boolean isIsosceles() {
			return numEqualSides() >= 1;
		}

		public boolean isScalene() {
			return numEqualSides()==0;
		}
	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	
	public int getScrabbleScore(String string) {
		string = string.toUpperCase();
		HashMap<Character, Integer> letterValues = new HashMap<>();
		letterValues.put('A', 1);
		letterValues.put('E', 1);
		letterValues.put('I', 1);
		letterValues.put('O', 1);
		letterValues.put('U', 1);
		letterValues.put('L', 1);
		letterValues.put('N', 1);
		letterValues.put('R', 1);
		letterValues.put('S', 1);
		letterValues.put('T', 1);
		letterValues.put('D', 2);
		letterValues.put('G', 2);
		letterValues.put('B', 3);
		letterValues.put('C', 3);
		letterValues.put('M', 3);
		letterValues.put('P', 3);
		letterValues.put('F', 4);
		letterValues.put('H', 4);
		letterValues.put('V', 4);
		letterValues.put('W', 4);
		letterValues.put('Y', 4);
		letterValues.put('K', 5);
		letterValues.put('J', 8);
		letterValues.put('X', 8);
		letterValues.put('Q', 10);
		letterValues.put('Z', 10);
		
		int result = 0;
		for (char letter : string.toCharArray()) {
			if (!letterValues.containsKey(letter)) {
				// throw error
			} 
			result+=(letterValues.get(letter));
		}
		
		return result;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) throws IllegalArgumentException {
		String legalCharacters = "+ ()-.";
		String phoneNumber = "";
		for (int i = 0; i < string.length(); i++) {
			if (Character.isDigit(string.charAt(i))) {
				phoneNumber = phoneNumber.concat(string.substring(i, i+1));
			} else if (!legalCharacters.contains(string.substring(i, i+1))) {
				throw new IllegalArgumentException("invalid input");
			}
		}
		
		int length = phoneNumber.length();
		if (length == 11 && phoneNumber.charAt(0)=='1') {
			return phoneNumber;
		} else if (length != 10) {
			throw new IllegalArgumentException("must contain 10 digits (or 11 digits starting with 1)");
		}
		return phoneNumber;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		
		String[] words = string.split("\\W+");
		Map<String, Integer> wordCount = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			if (!wordCount.containsKey(words[i])) {
				wordCount.put(words[i], 1);
			} else {
				wordCount.put(words[i], wordCount.get(words[i])+1);
			}
		}
		return wordCount;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			if (sortedList.isEmpty()) {
				return -1;
			}

			int leftPosition = 0;
			int rightPosition = sortedList.size()-1;
			while (sortedList.size() > 0) {
				int value = Integer.parseInt(t.toString());
				int last = Integer.parseInt(sortedList.get(rightPosition).toString());
				if (value == last) {
					return rightPosition;
				}
				
				int mid = (leftPosition + rightPosition)/2;
				int guess = Integer.parseInt(sortedList.get(mid).toString());
				if (value == guess) {
					return mid;
				} else if (value < guess) {
					rightPosition = mid;
				} else if (value > guess) {
					leftPosition = mid;
				}
			}
			return 0;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		string = string.toLowerCase();
		String[] words = string.split("\\W+");
		if (words.length==0) {
			// throw exception
		}
		
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			String suffix = "";
			int j = 0;
			if (word.startsWith("qu")) {
				words[i] = word.substring(2) + "qu" + "ay";
			} else {
				while (j < word.length() && !word.matches("[aeiou].+")) {
					suffix+=word.charAt(0);
					word = word.substring(1, word.length());
					j++;
				}
				words[i] = word + suffix + "ay";
			}
		}
		
		String pigLatin = words[0];
		for (int i = 1; i < words.length; i++) {
			pigLatin = pigLatin + " " + words[i];
		}
		return pigLatin;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		int sum = 0;
		int num = input;
		int length = 0;
		ArrayList<Integer> digits = new ArrayList<>();
		while (num > 0) {
			digits.add(num%10);
			num = num / 10;
			length++;
		}
		
		for (int i = 0; i < digits.size(); i++) {
			int poweredDigit = 1;
			for (int j = 0; j < length; j++) {
				poweredDigit*=digits.get(i);
			}
			sum+=poweredDigit;
		}
		
		return (input == sum);
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> primeFactors = new ArrayList<>();
		if (l < 2L) {
			return primeFactors;
		} 
		long factor = l;
		long inc = 2L;
		while (inc <= factor) {
			if (factor%inc == 0) {
				primeFactors.add(inc);
				factor = factor / inc;
				inc = 2L;
			} else {
				inc++;
			}
		}
		return primeFactors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			if (key > 26) {
				key = key % 26;
			}
			
			String letters = "abcdefghijklmnopqrstuvwxyz";
			String cipher = letters.substring(key) + letters.substring(0,key);
			HashMap<Character, Character> code = new HashMap<>();
			for (int i = 0; i < letters.length(); i++) {
				code.put(letters.charAt(i), cipher.charAt(i));
			}
			
			String result = "";
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				if (Character.isLetter(c)) {
					if (Character.isUpperCase(c)) {
						c = Character.toUpperCase(code.get(Character.toLowerCase(c)));
					} else {
						c = code.get(c);
					}
				}
				result+=c;
			}
			return result;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * s
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) throws IllegalArgumentException {
		if (i < 1) {
			throw new IllegalArgumentException();
		} else if (i==1) {
			return 2;
		}
		
		int[] primeNumbers = new int[i];
		primeNumbers[0] = 2;
		int index = 1;
		
		int n = i-1;
		int num = 2;
		while (n > 0) {
			num++;
			boolean isPrime = true;
			for (int j = 0; j < index; j++) {
				if (num%(primeNumbers[j])==0) {
					isPrime = false;
				}
			}
			if (isPrime) {
				primeNumbers[index] = num;
				index++;
				n--;
			}
		}
		return primeNumbers[i-1];
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			String letters = "abcdefghijklmnopqrstuvwxyz";
			
			EvaluationService es = new EvaluationService();
			String cipher = es.reverse(letters);
			
			HashMap<Character, Character> code = new HashMap<>();
			for (int i = 0; i < letters.length(); i++) {
				code.put(letters.charAt(i), cipher.charAt(i));
			}
			
			String encoding = "";
			int spacing = 0;
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				if (Character.isLetter(c) || Character.isDigit(c)) {
					if (Character.isAlphabetic(c)) {
						c = code.get(Character.toLowerCase(c));
					}
					encoding+=c;
					spacing++;
					if (spacing%5==0)
						encoding+=" ";		
				}
			}
			return encoding.trim();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			String letters = "abcdefghijklmnopqrstuvwxyz";
			EvaluationService es = new EvaluationService();
			String cipher = es.reverse(letters);
			
			HashMap<Character, Character> code = new HashMap<>();
			for (int i = 0; i < letters.length(); i++) {
				code.put(cipher.charAt(i), letters.charAt(i));
			}
			
			String decoding = "";
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				if (Character.isLetter(c)) {
					c = code.get(Character.toLowerCase(c));
					decoding+=c;
				} else if (Character.isDigit(c)) {
					decoding+=c;
				}
			}
			return decoding;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		string = string.trim();
		if (string.length() < 10) {
			return false;
		}
		int numDigits = 0, sum = 0;
		int multiplier = 10;
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (Character.isDigit(c)) {
				numDigits++;
				sum+=(Integer.parseInt(c+"")*multiplier);
				multiplier--;
			} else if (i==string.length()-1) {
				if (c=='X') {
					numDigits++;
					sum+=(10*multiplier);
				} else {
					return false;
				}
			}
		}
		
		if (numDigits != 10) {
			return false;
		}
		return sum%11==0;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		HashSet<Character> alphabet = new HashSet<>();
		String abcs = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < abcs.length(); i++) {
			alphabet.add(abcs.charAt(i));
		}
		
		for (int i = 0; i < string.length(); i++) {
			if (alphabet.contains(string.charAt(i))) {
				alphabet.remove(string.charAt(i));
			}
		}
		
		return alphabet.size()==0;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 10^9 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		String givenType = given.getClass().getSimpleName(); 
		int gigasecond = 1000000000;
		switch (givenType) {
		case "HijrahDate":
			break;
		case "Instant":
			break;
		case "JapaneseDate":
			break;
		case "LocalDate":
			LocalDateTime ldt = ((LocalDate) given).atTime(0,0,0);
			return ldt.plusSeconds(gigasecond);
		case "LocalDateTime":
			return ((LocalDateTime) given).plusSeconds(gigasecond);
		case "LocalTime":
			break;
		case "MinguoDate":
			break;
		case "OffsetDateTime":
			break;
		case "OffsetTime":
			break;
		case "ThaiBuddhistDate":
			break;
		case "Year":
			break;
		case "YearMonth":
			break;
		case "ZonedDateTime":
			break;
		}
		
		return given;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		HashSet<Integer> multiples = new HashSet<>();
		for (int j = 0; j < set.length; j++) {
			for (int k = 0; k < i; k++) {
				if (k%set[j]==0) {
					multiples.add(k);
				}
			}
		}
		int sum = 0;
		Iterator<Integer> iterator = multiples.iterator();
		while (iterator.hasNext()) {
			sum+=((int) iterator.next());
	    }
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		string = string.replaceAll("\\s+", "");
		
		if (string.length() <= 1) {
			return false;
		}
		
		if (string.length() > string.replaceAll("\\D+", "").length()) {
			return false;
		}

		int i = 0;
		int sum = 0;
		int len = string.length();
		while (i < len) {
			int digit = string.charAt(len-i-1)-'0';
			if (i % 2 == 1) {
				digit = (digit*2) % 9;
			} 
			sum+=digit;
			i++;
		}
		
		return sum % 10 == 0;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// assuming single operations of integers only given in format provided
		int result = 0;
		
		String[] words = string.split("\\s+");
		int firstNum = Integer.parseInt(words[2]);
		int secondNum = 0;
		words[words.length - 1] = words[words.length - 1].replace("?", "");
		String operation = words[3];
		switch (operation) {
		case "plus":
			secondNum = Integer.parseInt(words[4]);
			return firstNum + secondNum;
		case "minus":
			secondNum = Integer.parseInt(words[4]);
			return firstNum - secondNum;
		case "multiplied":
			words[4] = "by";
			secondNum = Integer.parseInt(words[5]);
			return firstNum*secondNum;
		case "divided":
			words[4] = "by";
			secondNum = Integer.parseInt(words[5]);
			if (secondNum==0) {
				// throw exception
			}
			return firstNum / secondNum;	
		default:
			// throw exception
		}
		return result;
	}
}
