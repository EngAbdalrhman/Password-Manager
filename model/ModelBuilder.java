package passwordmanager.model;

//import java.util.List;

public class ModelBuilder {
	// use person?
	private String name;
	private String userName;
	private String email; // main mail for reset...
	private String otherEmail; // other linked mails
	private String phone;
	private String contry; // could use keys code
	private String address;
	private String site; // what do u want to generate - a list - add site
	//private List<String> sites;
	private String password; // save fixed encrypted?
	//private List<String> passwords;
	private String logname; // Verify user
	private String logpass;
	private String yearOfBirth;
	
	// return this to continue add other fields for the same object.
	public ModelBuilder name(String name) {
		this.name = name;
		return this;
	}
	public ModelBuilder userName(String userName) {
		this.userName = userName;
		return this;
	}
	public ModelBuilder email(String email) {
		this.email = email;
		return this;
	}
	public ModelBuilder otherEmail(String otherEmail) {
		this.otherEmail = otherEmail;
		return this;
	}
	public ModelBuilder phone(String phone) {
		this.phone = phone;
		return this;
	}
	public ModelBuilder contry(String contry) {
		this.contry = contry;
		return this;
	}
	public ModelBuilder address(String address) {
		this.address = address;
		return this;
	}
	public ModelBuilder site(String site) {
		this.site = site;
		return this;
	}
	public ModelBuilder password(String password) {
		this.password = password;
		return this;
	}
	public ModelBuilder logname(String logname) {
		this.logname = logname;
		return this;
	}
	public ModelBuilder logpass(String logpass) {
		this.logpass = logpass;
		return this;
	}
	public ModelBuilder yearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
		return this;
		
	}
	
	
	public String getName() {
		return name;
	}
	public String getUserName() {
		return userName;
	}
	public String getEmail() {
		return email;
	}
	public String getOtherEmail() {
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
	public String getSite() {
		return site;
	}
	public String getPassword() {
		return password;
	}
	public String getLogname() {
		return logname;
	}
	public String getLogpass() {
		return logpass;
	}
	public String getYearOfBirth() {
		return yearOfBirth;
	}
	
	// building
	public DataModel build() {
		return new DataModel(this);
	}
	
}

// TODO forget pass with email