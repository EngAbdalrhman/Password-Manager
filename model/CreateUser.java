package passwordmanager.model;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

import passwordmanager.filecontroler.FileControl;
import passwordmanager.generate.PassGenerator;

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
		System.out.println("Enter The LogPassWord");
		String logpass = input.nextLine();
		
		System.out.println("Enter The Name");
		String name = input.nextLine();
		System.out.println("Enter The Email");
		String email = input.nextLine(); //TODO check the regex (fast fail)
		System.out.println("Enter The year of birth");
		String date = input.nextLine();

		//TODO save generated pass in files inside the user folder , encrypted , with some details , could read directly throw the app (dycrypted)
		model = new ModelBuilder().name(name).logname(logname).logpass(logpass).email(email).yearOfBirth(date).build();
		writeOperation(model);
		readOperation(model);
		//TODO modify model by setting new data then build again also separate build to object || RESTORE MODEL

	}
	
	// LOG data
	public static String dataModel(DataModel dataModel)
	{
		String txt = ("LogName:" + dataModel.getLogname()+ "\n");
		txt += ("LogPass:" + dataModel.getLogpass()+ "\n");
		txt += ("Name:" + dataModel.getName()+ "\n");
		txt += ("Email:" + dataModel.getEmail()+ "\n");
		txt += ("YearOfBirth:" + dataModel.getYearOfBirth()+ "\n");
		
		//TODO add other parameters
		return txt;
	}
	
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
