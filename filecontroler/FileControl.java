package passwordmanager.filecontroler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileControl {
	private static Map <String, String> userData;
	
	public static void write (File file, String data) 
	{
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(data);
			//fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> read (File file) 
	{
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			//int i = 0;
			String line;
			List<String> data = new ArrayList<String>();
			while((line=bf.readLine())!=null) 
			{
				//System.out.print((char) i); - (i=bf.read()) != -1
				data.add(line);
			}
			//System.out.println();
			bf.close();
			fr.close();
			printAll(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userData;
	}
	
	private static void printAll(List<String> data) 
	{
		int i = 0;
		userData = new HashMap<String,String>();
		for (String str : data) {
			//System.out.println(str);
			String[] arr =str.split(":");
			// 0 for key 1 for value
			userData.put(arr[0], arr[1]);
		}
	}
	// serialization
	public static void deleteDirectory(File file)
    {
        // store all the paths of files and folders present
        // inside directory
        for (File subfile : file.listFiles()) {
  
            // if it is a subfolder,e.g Rohan and Ritik,
            // recursiley call function to empty subfolder
            if (subfile.isDirectory()) {
                deleteDirectory(subfile);
            }
  
            // delete files and empty subfolders
            subfile.delete();
        }
    }
}
