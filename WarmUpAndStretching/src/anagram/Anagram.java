package anagram;

/**
 * This utility class can test whether two strings are anagrams.
 *
 * @author Claude Anderson.
 * Programmer: William Thesken
 */
public class Anagram {

	/**
	 * We say that two strings are anagrams if one can be transformed into the
	 * other by permuting the characters (and ignoring case).
	 * 
	 * For example, "Data Structure" and "Saturated Curt" are anagrams,
	 * as are "Elvis" and "Lives".
	 * 
	 * @param s1
	 *            first string
	 * @param s2
	 *            second string
	 * @return true iff s1 is an anagram of s2
	 */
	public static boolean isAnagram(String s1, String s2) {
		// TODO: implement this method

		
		if(s1.length()!=s2.length()) //If the two strings have different lengths then they cannot be anagrams and therefore the method will return false
		{
			return false;
		}
		
		String s1Lower = s1.toLowerCase(); //Lines 33 & 34 set the characters of the two strings to lower case to prep for comparing each character
		String s2Lower = s2.toLowerCase();
		
		for(int i= 0; i < s1Lower.length(); i++)
			{
				String currentChar = s1Lower.substring(i, i+1); //currentChar stores the char at index i
				if(!s2Lower.contains(currentChar)) //If the s2 string does not contain the current char from string s1 then it is not an anagram and returns false otherwise remove the found character from s2 and continue to check if all of the 
				{
					return false;
				}
				
				s2Lower = s2Lower.substring(0, s2Lower.indexOf(currentChar)) + s2Lower.substring(s2Lower.indexOf(currentChar)+1,s2Lower.length());
				//alter the lower case s2 string by removing the found character allowing for a shorter string to call contains on allowing for a more efficient code.
			}
		
		return true;
	}
}
