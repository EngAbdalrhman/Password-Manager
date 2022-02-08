package passwordmanager.model;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import passwordmanager.cryptography.Cryptography;
import passwordmanager.filecontroler.FileControl;
import passwordmanager.generate.PassGenerator;

public class LogIn {
	static Map <String, String> userProfileData;
	static Map <String, String> userSiteData;
	static DataModel dataModel;
	static boolean authentication = false;
	static Scanner input;
	
	public static void logIn() { 
		input = new Scanner(System.in);
		
		System.out.println("Enter The user");
		String user = input.nextLine();
		System.out.println("Enter The password");
		String password = input.nextLine();
		boolean check =Verify(user,password);
		
		if (authentication) // could use check
		{
			System.out.println("Verified");
			menu ();
		}
		else 
		{
			System.out.println("Not Vaild");
		}
		
	}
	static boolean Verify (String user , String password) 
	{
		String path = Consts.path+user;
		File dir = new File(path);
	        if (!dir.exists()) 
	           return false;
	        File file = new File(path+"\\"+user+".pm");
	        
	        userProfileData = FileControl.read(file);
	        // fix key algorithm
	        //TODO readprofile and decrypt all once.
	        userProfileData.replace("LogName", Cryptography.decryption(userProfileData.get("LogName"), Consts.k1, Consts.k2));
	        userProfileData.replace("LogPass", Cryptography.decryption(userProfileData.get("LogPass"), Consts.k1, Consts.k2));
	        
			if (userProfileData.containsValue(user) && userProfileData.containsValue(password))
			{
				authentication = true;
				dataModel = new ModelBuilder().logname(user).logpass(password).build();
				return authentication;
			}
			return false;
	}
	
	static public void menu () 
	{
		//Looping menu
		System.out.println("Choose number for operation..");
		System.out.println("1. add Site");
		System.out.println("2. search Site");
		System.out.println("3. modify Site");
		System.out.println("4. remove Site");
		System.out.println("5. View Profile");
		System.out.println("6. modify Profile");
		System.out.println("7. Delete Profile");
		System.out.println("8. cancel");
		int operation = input.nextInt();
		input.nextLine();
		switch(operation) 
		{
			case 1 :
				addSite();
			break;
			case 2 :
				searchSite();//TODO check if site not exist
			break;
			case 3 :
				modifySite();
			break;
			case 4 :
				removeSite();
			break;
			case 5 :
				readProfile(true);
			break;
			case 7 :
				removeProfile();
			break;
			case 6 :
				modifyProfile();
			break;
			case 8 :
				break;
			default : System.out.println("Not vaild");
		}
	}
	
	private static void removeSite() {
		System.out.println("Enter the site");
		String site = input.nextLine();
		dataModel.setSite(site);
		
		String path = Consts.path+dataModel.getLogname();
		File file = new File(path+"\\"+dataModel.getSite()+".pm");
		file.delete();
	}
	private static void modifySite() {
		System.out.println("What is the Site?");
		String pastSite = input.nextLine();//TODO check if exist
		dataModel.setSite(pastSite);
		readOperation ();
		System.out.println("What do u want to edit");
		System.out.println("1.UserName");
		System.out.println("2.Email");
		System.out.println("3.SiteName");
		int operation = input.nextInt();
		input.nextLine(); // for the bug
		String pass , user , email;
		String year = userProfileData.get("YearOfBirth");
		year = Cryptography.decryption(year, Consts.k1, Consts.k2);
		userProfileData.replace("YearOfBirth", year);
		readSiteData(false);
		
		switch(operation) 
		{
			case 1 : 
				user = input.nextLine();
				userSiteData.replace("UserName", user);
				dataModel.setUserName(user);
				email = userSiteData.get("Email");
				email = Cryptography.decryption(email, Consts.k1, Consts.k2);
				dataModel.setSite(pastSite);
				dataModel.setOtherEmail(email);
				pass = PassGenerator.generator(pastSite,user,email,year);
				dataModel.setPassword(pass);
				writeOperation();
				System.out.println("Done.");
			break;
			case 2 : 
				email = input.nextLine();
				userSiteData.replace("Email", email);
				dataModel.setOtherEmail(email);
				user = userSiteData.get("UserName");
				user = Cryptography.decryption(user, Consts.k1, Consts.k2);
				dataModel.setUserName(user);
				dataModel.setSite(pastSite);
				pass = PassGenerator.generator(pastSite,user,email,year);
				dataModel.setPassword(pass);
				writeOperation();
				System.out.println("Done.");
			break;
			case 3 : 
				String site = input.nextLine();
				userSiteData.replace("Site", site);
				dataModel.setSite(site);
				user = userSiteData.get("UserName");
				user = Cryptography.decryption(user, Consts.k1, Consts.k2);
				email = userSiteData.get("Email");
				email = Cryptography.decryption(email, Consts.k1, Consts.k2);
				dataModel.setUserName(user);
				dataModel.setOtherEmail(email);
				pass = PassGenerator.generator(site,user,email,year);
				dataModel.setPassword(pass);
				
				String path = Consts.path+dataModel.getLogname();
				File file = new File(path+"\\"+pastSite+".pm");
				
				writeOperation();
				file.delete();
				System.out.println("Done.");
			break;
			default : System.out.println("Wrong Answer");
		}
	}
	private static void searchSite() {
		System.out.println("Enter the site");
		String site = input.nextLine();
		dataModel.setSite(site);
		readOperation ();
		readSiteData(true);
	}
	private static void addSite() 
	{
		System.out.println("Enter The Site");
		String site = input.nextLine();
		System.out.println("Enter The Email");
		String email = input.nextLine(); //TODO check the regex (fast fail)
		System.out.println("Enter The user");
		String user = input.nextLine();
		userProfileData.replace("YearOfBirth", Cryptography.decryption(userProfileData.get("YearOfBirth"), Consts.k1, Consts.k2));
		
		String pass = PassGenerator.generator(site,user,email,userProfileData.get("YearOfBirth"));
		
		dataModel.setSite(site);
		dataModel.setUserName(user);
		dataModel.setOtherEmail(email);
		dataModel.setPassword(pass);
		writeOperation();
		System.out.println("Done!");
	}
	private static  String info() 
	{ 
		String site = Cryptography.encryption(dataModel.getSite(), Consts.k1, Consts.k2);
		String user = Cryptography.encryption(dataModel.getUserName(), Consts.k1, Consts.k2);
		String email = Cryptography.encryption(dataModel.getOtherEmail(), Consts.k1, Consts.k2);
		String pass = Cryptography.encryption(dataModel.getPassword(), Consts.k1, Consts.k2);
		
		String txt = ("Site:" + site + "\n");
		txt += ("Email:" + email+ "\n");
		txt += ("UserName:" + user+ "\n");
		txt += ("Password:" + pass+ "\n");
		//TODO add other parameters
		return txt;
	}
	private static void writeOperation () 
	{
		String path = Consts.path+dataModel.getLogname();
		
		File file = new File(path+"\\"+dataModel.getSite()+".pm");
		String data = info();
		FileControl.write(file, data);
	}
	
	private static void readOperation () 
	{
		String path = Consts.path+dataModel.getLogname();
		File dir = new File(path);
	        if (!dir.exists()) 
	        	return;
		File file = new File(path+"\\"+dataModel.getSite()+".pm"); //TODO check for sites
		userSiteData = FileControl.read(file);
	}
	
	private static void readProfile(boolean print) 
	{
		Iterator<String> keys = userProfileData.keySet().iterator();
		Iterator<String> values = userProfileData.values().iterator();
		while(keys.hasNext()) 
        {
			String key = keys.next();
			String value = values.next();
			if(key.equals("LogPass") || key.equals("LogName") )
				System.out.println(key +":"+ value);
			else
			{
				value = Cryptography.decryption(value, Consts.k1, Consts.k2);
				userProfileData.replace(key, value);
				if(print)
				System.out.println(key +":"+ value);
			}

        }
	}
	
	private static void readSiteData(boolean print) 
	{
		
		Iterator<String> keys = userSiteData.keySet().iterator();
		Iterator<String> values = userSiteData.values().iterator();
		while(keys.hasNext()) 
        {
			String key = keys.next();
			String value = values.next();

			value = Cryptography.decryption(value, Consts.k1, Consts.k2);
			userSiteData.replace(key, value);
			if(print)
			System.out.println(key +":"+ value);

        }
	}
	
	private static void removeProfile() {

		String path = Consts.path+dataModel.getLogname();
		File file = new File(path);
		System.out.println("Confirm Password");
		String pass = input.nextLine();
		System.out.println("Enter Confirm");
		String confirm = input.nextLine();
		if( (pass.equals(userProfileData.get("LogPass"))) && (confirm.toLowerCase().equals("confirm"))) {
			FileControl.deleteDirectory(file);
			file.delete();
			System.out.println("Done");
		}
	}
	private static void modifyProfile() 
	{
		readProfile(false);
		System.out.println("What do you want to Edit?");
		System.out.println("0. Cancel");
		System.out.println("1. Your Password");
		System.out.println("2. Your Email");
		System.out.println("3. Your Birth Year");
		System.out.println("4. Your Name");
		int operation = input.nextInt();
		input.nextLine(); // for the bug
		switch (operation) {
		case 0 :
			return;
		case 1:
			// retype past password
			System.out.println("Enter new Password");
			String password = input.nextLine();
			userProfileData.replace("LogPass", password);
			//logout
			break;
		case 2:
			System.out.println("Enter new Email");
			String email = input.nextLine();
			userProfileData.replace("Email", email);
			break;
		case 3:
			System.out.println("Enter new Year Of Birth");
			String year = input.nextLine();
			userProfileData.replace("YearOfBirth", year);
			break;
		case 4:
			System.out.println("Enter new Name");
			String name = input.nextLine();
			userProfileData.replace("Name", name);
			break;
		default:
			System.out.println("Not Vaild");
			break;
		}
		// could change only 1 (write once) not REWRITE all 
		Iterator<String> keys = userProfileData.keySet().iterator();
		Iterator<String> values = userProfileData.values().iterator();
		String data = "";
		while(keys.hasNext()) 
        {
			String key = keys.next();
			String value = values.next();

			value = Cryptography.encryption(value, Consts.k1, Consts.k2);
			userProfileData.replace(key, value); // NOT IMPORTANT ALSO FOR MOST LIKE IT. otherwise if can write throw map directly
			data += (key +":"+ value+ "\n");
        }
		String path = Consts.path+dataModel.getLogname();
		File log = new File(path+"\\"+dataModel.getLogname()+".pm");
		FileControl.write(log, data);
	}
}
