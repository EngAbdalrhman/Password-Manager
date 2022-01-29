package passwordmanager.model;

public class DataModel {
	private final String name; 
	private String userName; // list 
	private final String email; // main mail for reset...
	private String otherEmail; //TODO other linked mails - list
	private String phone;
	private String contry; //TODO could use keys code
	private String address;
	private String site; //TODO what do u want to generate - a list - add site
	//private List sites;
	private String password; //TODO save fixed encrypted? - list pass per site
	private final String logname; //TODO Verify user encrypted
	private final String logpass; //TODO Verify user encrypted
	private final String yearOfBirth;
	// private final int dateOfBirth; //date class?
	//security question

 // sql or encrypted files .. to save the data
	
	

	public DataModel(ModelBuilder user) {
		this.name = user.getName();
		this.userName = user.getUserName();
		this.phone = user.getPhone();
		this.contry = user.getContry();
		this.address = user.getAddress();
		this.site = user.getSite();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.yearOfBirth = user.getYearOfBirth();
		this.logname = user.getLogname();
		this.logpass = user.getLogpass();
	}
	
	public DataModel(String name, String userName, String email, String otherEmail, String phone, String contry,
			String address, String site, String password, String logname, String logpass, String yearOfBirth) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.otherEmail = otherEmail;
		this.phone = phone;
		this.contry = contry;
		this.address = address;
		this.site = site;
		this.password = password;
		this.logname = logname;
		this.logpass = logpass;
		this.yearOfBirth = yearOfBirth;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}


	public String getYearOfBirth() {
		return yearOfBirth;
	}


	public String getSite() {
		return site;
	}
	
	public String getName() {
		return name;
	}

	public String getOtherEmail() { // fix
		return otherEmail;
	}

	public String getPhone() {
		return phone;
	}

	public String getContry() {
		return contry;
	}

	public String getAddress() {
		return address;
	}


	public String getPassword() { // fix
		return password;
	}

	// Log in
	public String getLogname() {
		return logname;
	}

	public String getLogpass() {
		return logpass;
	}

	// modifying

		public void setUserName(String userName) {
			this.userName = userName;
		}
		public void setOtherEmail(String otherEmail) {
			this.otherEmail = otherEmail;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public void setContry(String contry) {
			this.contry = contry;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public void setSite(String site) {
			this.site = site;
		}
		public void setPassword(String password) {
			this.password = password;
		}

}
