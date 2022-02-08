package passwordmanager.model;

import java.io.File;
import java.util.Map;

import passwordmanager.filecontroler.FileControl;

public interface Consts {
	File info = new File("\\bin\\" + "inf.pm");
	Map<String, String> pinfo = FileControl.read(info);
	
	String path = pinfo.get("Path").replace( ";" +File.separator, ":"+File.separator)+File.separator; // Path D: PROB.
	int k1 = Integer.valueOf(pinfo.get("K1"));
	int k2 = Integer.valueOf(pinfo.get("K2"));
	
}
