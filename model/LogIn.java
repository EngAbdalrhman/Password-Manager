package passwordmanager.model;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import passwordmanager.filecontroler.FileControl;
import passwordmanager.generate.PassGenerator;

public class LogIn {
	static Map <String, String> userProfileData;
	static Map <String, String> userSiteData;
	static DataModel dataModel;
	static boolean authentication = false;
	static Scanner input;
	
	public static void main(String[] args) {
		input = new Scanner(System.in);
		// log in
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
		String path = "D:\\projects\\passmanager\\"+user;
		File dir = new File(path);
	        if (!dir.exists()) 
	           return false;
	        File file = new File(path+"\\"+user+".pm");
	        
	        userProfileData = FileControl.read(file);
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
		System.out.println("Choose number for operation..");
		System.out.println("1. add Site");
		System.out.println("2. search Site");
		System.out.println("3. modify Site");
		System.out.println("4. remove Site");
		System.out.println("5. View Profile");
		System.out.println("6. Delete Profile");
		System.out.println("7. cancel");
		int operation = input.nextInt();
		input.nextLine();
		switch(operation) 
		{
			case 1 :
				addSite();
			break;
			case 2 :
				searchSite();
			break;
			case 3 :
				modifySite();
			break;
			case 4 :
				removeSite();
			break;
			case 5 :
				readProfile();
			break;
			case 6 :
				removeProfile();
			break;
			case 7 :
			break;
			default : System.out.println("Not vaild");
		}
	}
	
	private static void removeSite() {
		System.out.println("Enter the site");
		String site = input.nextLine();
		dataModel.setSite(site);
		
		String path = "D:\\projects\\passmanager\\"+dataModel.getLogname();
		File file = new File(path+"\\"+dataModel.getSite()+".pm");
		file.delete();
	}
	private static void modifySite() {
		System.out.println("What is the Site?");
		String pastSite = input.nextLine();
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
		switch(operation) 
		{
			case 1 : 
				user = input.nextLine();
				userSiteData.replace("UserName", user);
				dataModel.setUserName(user);
				email = userSiteData.get("Email");
				dataModel.setSite(pastSite);
				dataModel.setOtherEmail(email);
				pass = PassGenerator.generator(pastSite,user,email,year);
				dataModel.setPassword(pass);
				writeOperation();
			break;
			case 2 : 
				email = input.nextLine();
				userSiteData.replace("Email", email);
				dataModel.setOtherEmail(email);
				user = userSiteData.get("UserName");
				dataModel.setUserName(user);
				dataModel.setSite(pastSite);
				pass = PassGenerator.generator(pastSite,user,email,year);
				dataModel.setPassword(pass);
				writeOperation();
			break;
			case 3 : 
				String site = input.nextLine();
				userSiteData.replace("Site", site);
				dataModel.setSite(site);
				user = userSiteData.get("UserName");
				email = userSiteData.get("Email");
				dataModel.setUserName(user);
				dataModel.setOtherEmail(email);
				pass = PassGenerator.generator(site,user,email,year);
				dataModel.setPassword(pass);
				
				String path = "D:\\projects\\passmanager\\"+dataModel.getLogname();
				File file = new File(path+"\\"+pastSite+".pm");
				
				writeOperation();
				file.delete();
			break;
			default : System.out.println("Wrong Answer");
		}
	}
	private static void searchSite() {
		System.out.println("Enter the site");
		String site = input.nextLine();
		dataModel.setSite(site);
		readOperation ();
		readSiteData();
	}
	private static void addSite() 
	{
		System.out.println("Enter The Site");
		String site = input.nextLine();
		System.out.println("Enter The Email");
		String email = input.nextLine(); //TODO check the regex (fast fail)
		System.out.println("Enter The user");
		String user = input.nextLine();
		dataModel.setSite(site);
		dataModel.setUserName(user);
		dataModel.setOtherEmail(email);
		
		String pass = PassGenerator.generator(site,user,email,userProfileData.get("YearOfBirth"));
		dataModel.setPassword(pass);
		writeOperation();
		System.out.println("Done!");
	}
	private static  String info() 
	{
		String txt = ("Site:" + dataModel.getSite() + "\n");
		txt += ("Email:" + dataModel.getOtherEmail()+ "\n");
		txt += ("UserName:" + dataModel.getUserName()+ "\n");
		txt += ("Password:" + dataModel.getPassword()+ "\n");
		//TODO add other parameters
		return txt;
	}
	private static void writeOperation () 
	{
		String path = "D:\\projects\\passmanager\\"+dataModel.getLogname();
		
		File file = new File(path+"\\"+dataModel.getSite()+".pm");
		String data = info();
		FileControl.write(file, data);
	}
	
	private static void readOperation () 
	{
		String path = "D:\\projects\\passmanager\\"+dataModel.getLogname();
		File dir = new File(path);
	        if (!dir.exists()) 
	        	return;
		File file = new File(path+"\\"+dataModel.getSite()+".pm"); //TODO check for sites
		userSiteData = FileControl.read(file);
	}
	
	private static void readProfile() 
	{
		Iterator<String> keys = userProfileData.keySet().iterator();
		Iterator<String> values = userProfileData.values().iterator();
		while(keys.hasNext()) 
        {
        	  System.out.println(keys.next() +":"+ values.next());
        }
	}
	
	private static void readSiteData() 
	{
		
		Iterator<String> keys = userSiteData.keySet().iterator();
		Iterator<String> values = userSiteData.values().iterator();
		while(keys.hasNext()) 
        {
        	  System.out.println(keys.next() +":"+ values.next());
        }
	}
	
	private static void removeProfile() {

		String path = "D:\\projects\\passmanager\\"+dataModel.getLogname();
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
	
}
