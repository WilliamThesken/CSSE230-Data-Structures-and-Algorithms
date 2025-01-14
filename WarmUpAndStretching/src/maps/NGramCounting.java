package maps;

import java.util.Map;
import java.util.TreeMap;

/**
 * Practice in using maps.
 * 
 * @author Nate Chenette
 *
 */

public class NGramCounting {

	/**
	 * Given an input text and a length n, the method should produce a Map from n-grams of 
	 * the text (i.e., length-n substrings) to counts, where n-gram S is mapped to count C
	 * if S shows up C times among substrings of the text. 
	 * 
	 * This method would be useful in frequency-based cryptanalysis of the classic substitution 
	 * cipher.
	 * 
	 * @param text
	 * @param n, the length of the n-grams to count
	 * @return
	 */
	static Map<String,Integer> nGramCounter(String text, int n) {
		//TODO: write this method!
		
		Map<String, Integer> nGramMap = new TreeMap<>();
		
		for(int i = 0; i + n <= text.length(); i++)
		{
			
			String initialSub = text.substring(i,i+n);
			if(!nGramMap.containsKey(initialSub))
			{
				nGramMap.put(initialSub, 1);
			}
			else {
				
				nGramMap.put(initialSub, nGramMap.get(initialSub)+1);
				
			}		
		}
		
		return nGramMap;
		
	}
	

}
