package com.zhitianweilai.qing.recognizer;

/**
 * 
 * @brief	
 * 
 * @author renjie.rj
 *
 */
public class JavaScritpRecognizer implements IRecognizer {

	/**
	 * @brief	
	 *  { "var", "if", "else", "for", "=", "+", "[", "]" }
	 * 
	 * @param content
	 * @return
	 */
	
	private static final String[] KEY_WORDS = {
		"var", "if", "else", "for", "break", "continue", "while", "=", "+", "[", "]" 
	};
	
	@Override
	public boolean recognize(String content) {
		
		int nfreq = 0;
		for ( String word : KEY_WORDS ) {
			int ndx = 0;
			while ( (ndx = content.indexOf(word, ndx)) != -1 ) {
				nfreq++;
				ndx++;
			}
		}
		
		int nchar = 0;
		for ( int i = 0; i < content.length(); i++ ) {
			char ch = content.charAt(i);
			if ( ch != ' ' || ch != '\n' || ch != '\r' || ch != '\t' ) {
				nchar++;
			}
		}
		
		if ( nfreq * 20 * 3 > nchar ) {
			return true;
		}
		
		return false;
		
	}
	
}




