package passwordmanager.model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

import passwordmanager.encryption.Encryption;
import passwordmanager.filecontroler.FileControl;

public class CreateUser {
		//data
		//Composition
		//build
		//TODO SQL passing || files
	static DataModel model;
	
	public static void main(String[] args) {	
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter The LogName");
		String logname = input.nextLine();
		// fast fail
		 File log = new File("D:\\projects\\passmanager\\"+logname+"\\"+logname+".pm");
		    if(log.exists())
		    {
		    	System.out.println("User exist");
		    	return;
		    }
		System.out.println("Enter The LogPassWord");
		String logpass = input.nextLine();
		
		System.out.println("Enter The Name");
		String name = input.nextLine();
		System.out.println("Enter The Email");
		String email = input.nextLine(); //TODO check the regex (fast fail)
		System.out.println("Enter The year of birth");
		String date = input.nextLine();

		
		model = new ModelBuilder().name(name).logname(logname).logpass(logpass).email(email).yearOfBirth(date).build();
		writeOperation(model);
		
		//readOperation(model);
		//TODO modify model profile by setting new data then build again also separate build to object || RESTORE MODEL

	}
	
	// LOG data
	public static String dataModel(DataModel dataModel)
	{
		String lognameencrypted =  Encryption.encryption(dataModel.getLogname(),2,-2);
		String logpassencrypted =  Encryption.encryption(dataModel.getLogpass(),2,-2);
		String nameencrypted = Encryption.encryption(dataModel.getName(),2,-2);
		String mailencrypted = Encryption.encryption(dataModel.getEmail(),2,-2);
		String yearencrypted = Encryption.encryption(dataModel.getYearOfBirth(),2,-2);
		
		String txt = ("LogName:" + lognameencrypted+ "\n");
		txt += ("LogPass:" + logpassencrypted+ "\n");
		txt += ("Name:" + nameencrypted + "\n");
		txt += ("Email:" + mailencrypted+ "\n");
		txt += ("YearOfBirth:" + yearencrypted+ "\n");
		
		//TODO add other parameters
		return txt;
	}
	
	// modify profile
	
	static void writeOperation (DataModel dataModel) 
	{
		String path = "D:\\projects\\passmanager\\"+dataModel.getLogname();
		File dir = new File(path);
	        if (!dir.exists()) 
	            dir.mkdir();
	       
	    File log = new File(path+"\\"+dataModel.getLogname()+".pm");
	  
		String data = dataModel(model);
		FileControl.write(log, data);
	}
	
	static void readOperation (DataModel dataModel) 
	{
		String path = "D:\\projects\\passmanager\\"+dataModel.getLogname();
		File dir = new File(path);
	        if (!dir.exists()) 
	        	return;
		File file = new File(path+"\\"+dataModel.getLogname()+".pm");
		FileControl.read(file);
	}
	
}
