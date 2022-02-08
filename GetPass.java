package passwordmanager;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import passwordmanager.cryptography.Cryptography;
import passwordmanager.filecontroler.FileControl;
import passwordmanager.model.Consts;
import passwordmanager.model.CreateUser;
import passwordmanager.model.LogIn;

public class GetPass {
	static Map<String, String> pinfo;
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		// Dynamic Path - Keys
		//Level 1 : Path choose  and lists
		// Level 2 : GUI files open path
		// Level 3 : GUI & DB
		
		// Switch operation (for first time setup)
		verivy();
		//readPath();
		System.out.println("What Do u want?");
		System.out.println("1. create user");
		System.out.println("2. login");
		System.out.println("3. Modify Path"); // case path is created
		int op = input.nextInt();
		input.nextLine();
		switch (op) {
		case 1:
			CreateUser.createUser();
			break;
		case 2:
			LogIn.logIn();
			break;
		case 3:
			modifyInfo();
			break;
		default:
			System.out.println("Not Vaild");
			break;
		}
		
		
	}
	
	private static boolean verivy() 
	{
		String path = "\\bin\\"; // check where is it? c/bin
		File dir = new File(path);
		File info = new File(path + "inf.pm");
        if (!dir.exists())
        {
            dir.mkdir();
            setup(info);
            return true;
        }
        else if (!info.exists())
    	{
    		setup(info);
    		return true;
    	}
        	return false;
        // make modify method.
		
	}
	private static void setup(File info) {
		
		System.out.println("Enter the path you want to save data in");
		String dpath = input.nextLine();// TODO check path not exist
		System.out.println("Enter Key1");
		int k1 = input.nextInt();
		System.out.println("Enter Key2");
		int k2 = input.nextInt();
		String data = data(dpath,k1,k2);
		
		FileControl.write(info, data);
		System.out.println("Done");
	}
	private static void modifyInfo() {
		String path = "\\bin\\";
		File info = new File(path + "inf.pm");
		
		System.out.println("What do you want to edit?");
		System.out.println("1. path");
		System.out.println("2. key1");
		System.out.println("3. key2");
		int op = input.nextInt();
		input.nextLine();
		String dpath  = Consts.path;
		int k1 = Consts.k1, k2 = Consts.k2;
		switch (op) {
		case 1:
			System.out.println("Enter New Path");
			dpath = input.nextLine();
			break;
		case 2:
			System.out.println("Enter New key1");
			k1 = input.nextInt();
			break;
		case 3:
			System.out.println("Enter New key2");
			k2 = input.nextInt();
			break;	
		default:
			System.out.println("Not Vaild");
			break;
		}
		
		String data = data(dpath,k1,k2);
		
		FileControl.write(info, data);
		System.out.println("Done");
	}
	private static String data(String path , int key1 , int key2) {
		String txt = ("Path:" + path +"\n");
		txt += ("K1:" + key1 +"\n");
		txt += ("K2:" + key2 +"\n");
		return txt;
	}
	private static void readPath() 
	{
		File info = new File("\\bin\\" + "inf.pm");
		pinfo = FileControl.read(info);
		Iterator<String> keys = pinfo.keySet().iterator();
		Iterator<String> values = pinfo.values().iterator();
		while(keys.hasNext()) 
        {
			System.out.println(keys.next() +":"+ values.next());
        }
	}

}
