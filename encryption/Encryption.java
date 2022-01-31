package passwordmanager.encryption;

public class Encryption {
	final static private int key1 = 2 , key2 = -2; // save it or fixed ?
	// enc -> sympol key+20 , upper -> 2*key , down ->key , rearange & for decry -> opp. ThEN ENCR ALL
	private static String algorithm(String txt, int key) {
		String ctxt = "";
		boolean upcase = false;
		boolean downcase = false;
		 for (int j=0; j < txt.length();j++) // edit condition
		 {
			upcase = ((int) txt.charAt(j) >=65	&& (int) txt.charAt(j) <=90);
			downcase = ((int) txt.charAt(j) >=97 && (int) txt.charAt(j) <=122);

	         if(upcase)
	         {
	        	 if(key<0) 
					 key += 26; // to avoid char be symbol
		         int p= ((int) txt.charAt(j) +key -65)%26+65;
		          ctxt += (char)p;        
	         }
			 else if(downcase)
			 {
				 if(key<0) 
					 key += 26;
				  int p= ((int) txt.charAt(j) +key -97)%26+97;
				  ctxt += (char)p; 
			 }

		    else
		    	ctxt += (char) txt.charAt(j); 
	}
		return ctxt;
}
	
	public static String encryption(String txt, int key1 , int key2) 
	{
		int length = txt.length();
		String part1 = "" , part2 = "" , ctxt = "";
		
		for (int i = 0; i < length/2; i++) {
			part1 += txt.charAt(i);
		}
		ctxt =algorithm(part1, key1);
		
		for (int i = length-1 ; i >= length/2; i--) {
			part2 += txt.charAt(i);
		}
		ctxt += algorithm(part2, key2);
		
		return ctxt;
	}
	public static String decryption(String ctxt, int key1 , int key2) 
	{
		int length = ctxt.length();
		
		String txt= "";
		String part1 = ""; 
		String part2 = "";
		
		for (int i = 0; i < length/2; i++) {
			part1 += ctxt.charAt(i);
		}
		txt = algorithm(part1, -key1);
		
		for (int i = length-1 ; i >= length/2; i--) {
			part2 += ctxt.charAt(i);
		}
		txt += algorithm(part2, -key2);
		
		return txt;
	}
	// merge by make algorithm encryption , decryption by using op key , divider encryption.
}