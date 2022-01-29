package passwordmanager.generate;

public class PassGenerator {
	//twitter + email + date + user = Tweedy@am30#1$7 (site+user+symbol+email+symbol+date)
	// Symbols {#(year)@(user)$(day)}
	// user = speedy , email = ambdo30@g , date = 2001/7
	//TODO Rearrange parts to generate more
	//TODO use Arabic letters 
	private static final char symbols[] = {'@','#','$'};
	// add this year by date
	public static String generator(String site , String user , String email , String date) {
		String pass = "";
		pass += sitePart(site);
		pass += userPart(user);
		pass += emailPart(email);
		pass += datePart(date);
		return pass;
	}
	private static String sitePart(String site) {
		String pass = "";
		pass += site.toUpperCase().charAt(0);
		pass += site.charAt(1);
		return pass;
	}
	
	private static String datePart(String date) {
		String pass = "";
		pass += date.charAt(0);
		pass += symbols[2];
		pass += date.charAt(date.length()-1);
		return pass;
	}
	private static String emailPart(String email) {
		String pass = "";
		pass += email.charAt(0);
		pass += email.charAt(1);
		int k;
		for (k = 0; k < email.length(); k++) {
			if(email.charAt(k) == 64)
			{
				break;
			}
		}
		pass += email.charAt(k-2);
		pass += email.charAt(k-1);
		pass += symbols[1];
		return pass;
	}
	
	private static String userPart(String user) {
		String pass = "";
		for (int i = 0; i < user.length(); i++) {
			if (i < 1 || i > 5)
				continue;
			pass += user.charAt(i);
		}
			pass += symbols[0];
		return pass;
	}
	
}
