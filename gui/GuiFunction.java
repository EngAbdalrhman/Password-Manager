package passwordmanager.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

import passwordmanager.cryptography.Cryptography;
import passwordmanager.filecontroler.FileControl;
import passwordmanager.model.Consts;
import passwordmanager.model.DataModel;
import passwordmanager.model.ModelBuilder;

public class GuiFunction {
	static DataModel dataModel; // TODO Setup
	static Map <String, String> userProfileData;
	
	static Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	
	static void writeProfile () 
	{
		Iterator<String> keys = userProfileData.keySet().iterator();
		Iterator<String> values = userProfileData.values().iterator();
		String data = "";
		while(keys.hasNext()) 
        {
			String key = keys.next();
			String value = values.next();
			// TODO handle all
			if(key.equals("LogName")  || key.equals("LogPass")) 
			{
				value = Cryptography.encryption(value, Consts.k1, Consts.k2);
				userProfileData.replace(key, value); // NOT IMPORTANT ALSO FOR MOST LIKE IT. otherwise if can write throw map directly
			}
			data += (key +":"+ value+ "\n");
        }
		String path = Consts.path+dataModel.getLogname();
		File log = new File(path+"\\"+dataModel.getLogname()+".pm");
		FileControl.write(log, data);
		
	}
	
	static boolean Verify (String user , String password) 
	{
		String path = Consts.path+user;
		File dir = new File(path);
	        if (!dir.exists()) 
	           return false;
	        File file = new File(path+"\\"+user+".pm");
	        
	        userProfileData = FileControl.read(file);
	        
	        userProfileData.replace("LogName", Cryptography.decryption(userProfileData.get("LogName"), Consts.k1, Consts.k2));
	        userProfileData.replace("LogPass", Cryptography.decryption(userProfileData.get("LogPass"), Consts.k1, Consts.k2));
	        
			if (userProfileData.containsValue(user) && userProfileData.containsValue(password))
			{
				dataModel = new ModelBuilder().logname(user).logpass(password).build();
				return true;
			}
			return false;
	}
	
}
